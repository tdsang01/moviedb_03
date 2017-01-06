package com.framgia.moviedb.ui.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.framgia.moviedb.R;
import com.framgia.moviedb.ui.fragments.CollectionsFragment;
import com.framgia.moviedb.ui.fragments.GenreFragment;
import com.framgia.moviedb.ui.fragments.MainFragment;
import com.framgia.moviedb.ui.interactor.OnSearchDataListenner;

public class MainActivity extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener{
    private NavigationView mNavigationView;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainFragment mainFragment = new MainFragment();
        getSupportFragmentManager().beginTransaction()
            .replace(R.id.fragment_container, mainFragment)
            .commit();
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, mToolbar, R.string.navigation_drawer_open,
            R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    // Get the items in menu file to add item to the action bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        ((SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search)))
            .setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    Fragment currentFragment =
                        getSupportFragmentManager().findFragmentById(R.id.fragment_container);
                    if (currentFragment instanceof OnSearchDataListenner) {
                        ((OnSearchDataListenner) currentFragment).onSearch(query);
                    }
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    return false;
                }
            });
        return super.onCreateOptionsMenu(menu);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //Fragment fragment = null;
        Fragment fragment = new MainFragment();// tạm thời để thế này để test đã.
        switch (id) {
            case R.id.nav_home:
                fragment = new MainFragment();
                break;
            case R.id.nav_account:
                // TODO Account feature
                break;
            case R.id.nav_genres:
                fragment = new GenreFragment();
                break;
            case R.id.nav_company:
                // TODO Search companies
                break;
            case R.id.nav_collection:
                fragment = new CollectionsFragment();
                break;
            case R.id.nav_about:
                // TODO Information of the team who create this app
                break;
            default:
                break;
        }
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
