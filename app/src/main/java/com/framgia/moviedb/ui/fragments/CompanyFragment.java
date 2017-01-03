package com.framgia.moviedb.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.framgia.moviedb.BuildConfig;
import com.framgia.moviedb.R;
import com.framgia.moviedb.data.model.Company;
import com.framgia.moviedb.data.model.MovieResponse;
import com.framgia.moviedb.data.model.PrimaryMovieInfo;
import com.framgia.moviedb.service.ApiClient;
import com.framgia.moviedb.service.CompanyApi;
import com.framgia.moviedb.service.RetrofitCallback;
import com.framgia.moviedb.ui.adapter.HorizontalMoviesAdapter;
import com.framgia.moviedb.ui.adapter.VerticalMoviesAdapter;
import com.framgia.moviedb.ui.interactor.OnListenerCallback;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by trungnguyens93gmail.com on 1/2/17.
 */
public class CompanyFragment extends Fragment implements OnListenerCallback {
    private TextView mDescription, mHeadquarters, mHomepage, mName, mLogoPath, mParentCompany;
    private RecyclerView mMoviesRecycleView;
    private CompanyApi mCompanyApi;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_company, container, false);
        // Get companyId
        int companyId = getArguments().getInt("id");
        // Init TextView
        mDescription = (TextView) view.findViewById(R.id.text_description_company);
        mHeadquarters = (TextView) view.findViewById(R.id.text_headquarters_company);
        mHomepage = (TextView) view.findViewById(R.id.text_homepage_company);
        mName = (TextView) view.findViewById(R.id.text_name_company);
        mLogoPath = (TextView) view.findViewById(R.id.text_logo_path_company);
        mParentCompany = (TextView) view.findViewById(R.id.text_parent_company);
        // Init and Setting RecycleView
        mMoviesRecycleView = (RecyclerView) view.findViewById(R.id.recycle_movies_of_company);
        mMoviesRecycleView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager
            .VERTICAL, false));
        // Init CompanyApi
        mCompanyApi = ApiClient.getClient().create(CompanyApi.class);
        // Load data
        loadDetail(companyId);
        loadMovies(companyId);
        return view;
    }

    public void loadDetail(final int companyId) {
        Map<String, String> data = new HashMap<>();
        data.put(CompanyApi.Api.API_KEY.getValues(), BuildConfig.API_KEY);
        mCompanyApi.getDetail(companyId, data)
            .enqueue(new RetrofitCallback<Company>(this) {
                @Override
                public void onResponse(Call<Company> call, Response<Company> response) {
                    super.onResponse(call, response);
                    Company company = response.body();
                    if (company == null) {
                        getFragmentManager().popBackStack();
                        Toast.makeText(getContext(), R.string.company_error, Toast.LENGTH_SHORT).show();
                        return;
                    }
                    mDescription.setText(company.getDescription());
                    mHeadquarters.setText(company.getHeadquarters());
                    mHomepage.setText(company.getHomePage());
                    mName.setText(company.getName());
                    mLogoPath.setText(company.getLogoPath());
                    mParentCompany.setText(company.getParentCompany());
                }
            });
    }

    public void loadMovies(final int companyId) {
        Map<String, String> data = new HashMap<>();
        data.put(CompanyApi.Api.API_KEY.getValues(), BuildConfig.API_KEY);
        data.put(CompanyApi.Api.LANGUAGE.getValues(), ApiClient.LANGUAGE);
        mCompanyApi.getMovies(companyId, CompanyApi.Api.MOVIES.getValues(), data)
            .enqueue(new RetrofitCallback<MovieResponse>(this) {
                @Override
                public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                    super.onResponse(call, response);
                    List<PrimaryMovieInfo> primaryMovieInfos = response.body().getResult();
                    mMoviesRecycleView.setAdapter(new VerticalMoviesAdapter(getContext(),
                        primaryMovieInfos));
                }
            });
    }

    @Override
    public void onFailure() {
        Toast.makeText(getContext(), R.string.error_load, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEmptyData() {
        Toast.makeText(getContext(), R.string.error_data, Toast.LENGTH_SHORT).show();
    }
}
