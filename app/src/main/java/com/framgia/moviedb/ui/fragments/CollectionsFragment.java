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
import com.framgia.moviedb.service.ApiClient;
import com.framgia.moviedb.service.RetrofitCallback;
import com.framgia.moviedb.service.SearchApi;
import com.framgia.moviedb.ui.adapter.CollectionAdapter;
import com.framgia.moviedb.ui.interactor.OnListenerCallback;
import com.framgia.moviedb.ui.interactor.OnSearchDataListenner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by trungnguyens93gmail.com on 1/5/17.
 */
public class CollectionsFragment extends Fragment implements OnListenerCallback,
    OnSearchDataListenner {
    private RecyclerView mCollectionRecycleView;
    private SearchApi mSearchApi;
    private CollectionAdapter mCollectionAdapter;
    private String mPreviosSearch;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_genre, container, false);
        // Init RecycleView
        mCollectionRecycleView = (RecyclerView) view.findViewById(R.id.recycle_genres);
        mCollectionRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        // Init SearchApi
        mSearchApi = ApiClient.getClient().create(SearchApi.class);
        mCollectionAdapter = new CollectionAdapter(getContext(), null);
        mCollectionRecycleView.setAdapter(mCollectionAdapter);
        mPreviosSearch = "";
        return view;
    }

    @Override
    public void onSearch(String strSearch) {
        if (strSearch.equals("") || mPreviosSearch.equals(strSearch.toLowerCase())) return;
        mPreviosSearch = strSearch.toLowerCase();
        Map<String, String> data = new HashMap<>();
        data.put(SearchApi.Api.API_KEY.getValues(), BuildConfig.API_KEY);
        data.put(SearchApi.Api.LANGUAGE.getValues(), ApiClient.LANGUAGE);
        data.put(SearchApi.Api.QUERY.getValues(), strSearch);
        data.put(SearchApi.Api.PAGE.getValues(), String.valueOf(1));
        mSearchApi.getCollections(SearchApi.Api.COLLECTIONS.getValues(), data)
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
