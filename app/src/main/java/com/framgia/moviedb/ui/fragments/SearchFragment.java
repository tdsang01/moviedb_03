package com.framgia.moviedb.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.framgia.moviedb.BuildConfig;
import com.framgia.moviedb.R;
import com.framgia.moviedb.data.model.Collection;
import com.framgia.moviedb.data.model.CollectionResponse;
import com.framgia.moviedb.data.model.Company;
import com.framgia.moviedb.data.model.CompanyResponse;
import com.framgia.moviedb.data.model.ManagerConstant;
import com.framgia.moviedb.service.ApiClient;
import com.framgia.moviedb.service.RetrofitCallback;
import com.framgia.moviedb.service.SearchApi;
import com.framgia.moviedb.ui.adapter.CollectionAdapter;
import com.framgia.moviedb.ui.adapter.CompanyAdapter;
import com.framgia.moviedb.ui.interactor.OnListenerCallback;
import com.framgia.moviedb.ui.interactor.OnSearchDataListenner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by trungnguyens93gmail.com on 1/5/17.
 */
public class SearchFragment extends Fragment implements OnListenerCallback,
    OnSearchDataListenner {
    private RecyclerView mSearchRecycleView;
    private SearchApi mSearchApi;
    private CollectionAdapter mCollectionAdapter;
    private CompanyAdapter mCompanyAdapter;
    private String mPreviosSearch;
    private String mTypeOfSearch;

    public static SearchFragment newInstance(String argumentTypeOfSearch) {
        Fragment fragment = new SearchFragment();
        Bundle bundle = new Bundle();
        bundle.putString(
            ManagerConstant.ARGUMENT_TYPE_OF_SEARCH, argumentTypeOfSearch);
        fragment.setArguments(bundle);
        return (SearchFragment) fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_genre, container, false);
        // Get Type Of Search
        Bundle bundle = getArguments();
        if (bundle != null) {
            mTypeOfSearch = bundle.getString(ManagerConstant.ARGUMENT_TYPE_OF_SEARCH);
        }
        // Init Collection RecycleView
        mSearchRecycleView = (RecyclerView) view.findViewById(R.id.recycle_genres);
        mSearchRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        // Init SearchApi
        mSearchApi = ApiClient.getClient().create(SearchApi.class);
        mCollectionAdapter = new CollectionAdapter(getContext(), null);
        mCompanyAdapter = new CompanyAdapter(getContext(), null);
        // Setting Adapter for RecycleView
        setAdapterRecycleView();
        mPreviosSearch = "";
        return view;
    }

    public void setAdapterRecycleView() {
        switch (mTypeOfSearch) {
            case ManagerConstant.Param.PARAM_TYPE_COMPANY:
                mSearchRecycleView.setAdapter(mCompanyAdapter);
                break;
            case ManagerConstant.Param.PARAM_TYPE_COLLECTION:
                mSearchRecycleView.setAdapter(mCollectionAdapter);
                break;
            default:
                getFragmentManager().popBackStack();
                Toast.makeText(getContext(), R.string.company_error, Toast.LENGTH_SHORT)
                    .show();
                break;
        }
    }

    @Override
    public void onSearch(String strSearch) {
        if (strSearch.equals("") || mPreviosSearch.equals(strSearch.toLowerCase())) return;
        mPreviosSearch = strSearch.toLowerCase();
        // Select type of search
        switch (mTypeOfSearch) {
            case ManagerConstant.Param.PARAM_TYPE_COLLECTION:
                Map<String, String> dataCollection = new HashMap<>();
                dataCollection.put(SearchApi.Api.API_KEY.getValues(), BuildConfig.API_KEY);
                dataCollection.put(SearchApi.Api.LANGUAGE.getValues(), ApiClient.LANGUAGE);
                dataCollection.put(SearchApi.Api.QUERY.getValues(), strSearch);
                dataCollection.put(SearchApi.Api.PAGE.getValues(), String.valueOf(1));
                mSearchApi.getCollections(mTypeOfSearch, dataCollection)
                    .enqueue(new RetrofitCallback<CollectionResponse>(this) {
                        @Override
                        public void onResponse(Call<CollectionResponse> call,
                                               Response<CollectionResponse> response) {
                            super.onResponse(call, response);
                            List<Collection> collections = response.body().getCollections();
                            mCollectionAdapter.setCollections(collections);
                            mCollectionAdapter.notifyDataSetChanged();
                        }
                    });
                break;
            case ManagerConstant.Param.PARAM_TYPE_COMPANY:
                Map<String, String> dataCompany = new HashMap<>();
                dataCompany.put(SearchApi.Api.API_KEY.getValues(), BuildConfig.API_KEY);
                dataCompany.put(SearchApi.Api.QUERY.getValues(), strSearch);
                dataCompany.put(SearchApi.Api.PAGE.getValues(), String.valueOf(1));
                mSearchApi.getCompanies(mTypeOfSearch, dataCompany)
                    .enqueue(new RetrofitCallback<CompanyResponse>(this) {
                        @Override
                        public void onResponse(Call<CompanyResponse> call,
                                               Response<CompanyResponse> response) {
                            super.onResponse(call, response);
                            List<Company> companies = response.body().getCompanies();
                            mCompanyAdapter.setCompanies(companies);
                            mCompanyAdapter.notifyDataSetChanged();
                        }
                    });
                break;
            default:
                break;
        }
    }

    @Override
    public void onFailure() {
        Toast.makeText(getContext(), getResources().getString(R.string.error_load),
            Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEmptyData() {
        Toast.makeText(getContext(), getResources().getString(R.string.error_data),
            Toast.LENGTH_SHORT).show();
    }
}
