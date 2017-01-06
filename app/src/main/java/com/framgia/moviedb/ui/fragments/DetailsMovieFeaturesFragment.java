package com.framgia.moviedb.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.framgia.moviedb.BuildConfig;
import com.framgia.moviedb.R;
import com.framgia.moviedb.data.model.ManagerConstant;
import com.framgia.moviedb.data.model.PrimaryMovieInfo;
import com.framgia.moviedb.service.RetrofitClient;
import com.framgia.moviedb.ultils.Utils;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsMovieFeaturesFragment extends Fragment {
    private String mArgumentMovieId = ManagerConstant.ARGUMENT_MOVIE_ID_DEFAULT;
    private ImageView mImagePoster;
    private TextView mTextNameOfMovie, mTextReleaseDate, mTextRuntime, mTextPopulary,
        mTextVoteCount, mTextVoteAverage, mTextOverview;

    public DetailsMovieFeaturesFragment() {
    }

    public static DetailsMovieFeaturesFragment newInstance(PrimaryMovieInfo movieInfo){
        Fragment fragment = new DetailsMovieFeaturesFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ManagerConstant.ARGUMENT_MOVIE_ID, String.valueOf(movieInfo.getId()));
        fragment.setArguments(bundle);
        return (DetailsMovieFeaturesFragment) fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details_movie_features, container, false);
        Bundle bundle = getArguments();
        if (bundle != null) {
            mArgumentMovieId = bundle.getString(ManagerConstant.ARGUMENT_MOVIE_ID);
        }
        init(view);
        loadMovieDetails(mArgumentMovieId);
        return view;
    }

    public void init(View v) {
        mImagePoster = (ImageView) v.findViewById(R.id.image_poster_movie_detail);
        mTextNameOfMovie = (TextView) v.findViewById(R.id.text_name_of_movie_detail);
        mTextReleaseDate = (TextView) v.findViewById(R.id.text_release_date_detail);
        mTextRuntime = (TextView) v.findViewById(R.id.text_runtime_detail);
        mTextPopulary = (TextView) v.findViewById(R.id.text_populary_detail);
        mTextVoteCount = (TextView) v.findViewById(R.id.text_vote_count_detail);
        mTextVoteAverage = (TextView) v.findViewById(R.id.text_vote_average_detail);
        mTextOverview = (TextView) v.findViewById(R.id.text_overview_detail);
    }

    public void loadMovieDetails(String movieId) {
        Map<String, String> params = new HashMap<>();
        params.put(ManagerConstant.Param.PARAM_API_KEY, BuildConfig.API_KEY);
        params.put(ManagerConstant.Param.PARAM_LANGUAGE, ManagerConstant.LANGUAGE);
        RetrofitClient.getMovieDetails(mArgumentMovieId, params,
            new Callback<PrimaryMovieInfo>() {
                @Override
                public void onResponse(Call<PrimaryMovieInfo> call,
                                       Response<PrimaryMovieInfo> response) {
                    if (response == null) {
                        Utils.makeToast(getActivity(), ManagerConstant.MessengerManager.MSG_LOAD);
                        return;
                    } else if (response.body() == null) {
                        Utils.makeToast(getActivity(), ManagerConstant.MessengerManager.MSG_CONVERT);
                        return;
                    }else {
                        loadDataView(response.body());
                    }
                }

                @Override
                public void onFailure(Call<PrimaryMovieInfo> call, Throwable t) {
                    Utils.makeToast(getActivity(), ManagerConstant.MessengerManager.MSG_LOAD);
                }
            });
    }

    private void loadDataView(PrimaryMovieInfo mv) {
        mTextNameOfMovie.setText(mv.getOriginalTitle());
        mTextReleaseDate.setText(buildDataText(
            R.string.text_release_date_detail,
            mv.getReleaseDate()));
        mTextRuntime.setText(buildDataText(
            R.string.text_runtime_detail,
            String.valueOf(mv.getRuntime())));
        mTextPopulary.setText(buildDataText(
            R.string.text_populary_detail,
            String.valueOf(mv.getPopularity())));
        mTextVoteCount.setText(buildDataText(
            R.string.text_vote_count_detail,
            String.valueOf(mv.getVoteCount())));
        mTextVoteAverage.setText(buildDataText(
            R.string.text_vote_average_detail,
            String.valueOf(mv.getVoteAverage())));
        mTextOverview.setText(String.valueOf(mv.getOverview()));
        Picasso.with(getActivity())
            .load(ManagerConstant.UrlManager.IMAGE_URL + mv.getPosterPath())
            .placeholder(R.mipmap.ic_launcher)
            .into(mImagePoster);
    }

    public String buildDataText(int stringId, String textDataShow) {
        return new StringBuffer().append(getString(stringId)).append(textDataShow).toString();
    }
}
