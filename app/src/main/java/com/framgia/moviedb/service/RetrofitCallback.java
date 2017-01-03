package com.framgia.moviedb.service;

import com.framgia.moviedb.ui.interactor.OnListenerCallback;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by trungnguyens93gmail.com on 1/3/17.
 */
public class RetrofitCallback<T> implements Callback<T> {
    private OnListenerCallback mListener;

    public RetrofitCallback(OnListenerCallback listener) {
        mListener = listener;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response == null) {
            mListener.onEmptyData();
            return;
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        mListener.onFailure();
    }
}
