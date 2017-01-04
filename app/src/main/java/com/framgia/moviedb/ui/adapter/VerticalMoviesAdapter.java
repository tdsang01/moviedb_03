package com.framgia.moviedb.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.framgia.moviedb.R;
import com.framgia.moviedb.data.model.PrimaryMovieInfo;
import com.framgia.moviedb.service.ApiClient;
import com.squareup.picasso.Picasso;

import java.util.List;

public class VerticalMoviesAdapter
    extends RecyclerView.Adapter<VerticalMoviesAdapter.VerticalViewHolder> {
    private Context mContext;
    private List<PrimaryMovieInfo> mPrimaryMovieInfos;
    private LayoutInflater mLayoutInflater;

    public VerticalMoviesAdapter(Context context, List<PrimaryMovieInfo> primaryMovieInfos) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        mPrimaryMovieInfos = primaryMovieInfos;
    }

    @Override
    public VerticalViewHolder onCreateViewHolder(ViewGroup parent,
                                                 int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.adapter_vertical_movie, parent, false);
        return new VerticalViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(VerticalViewHolder holder, int position) {
        PrimaryMovieInfo primaryMovieInfo = mPrimaryMovieInfos.get(position);
        holder.bindData(primaryMovieInfo);
    }

    @Override
    public int getItemCount() {
        return mPrimaryMovieInfos == null ? 0 : mPrimaryMovieInfos.size();
    }

    class VerticalViewHolder extends RecyclerView.ViewHolder {
        private ImageView mLogo;
        private TextView mTitle, mReleaseDate, mVoteCount, mVoteAverage;

        public VerticalViewHolder(View view) {
            super(view);
            mLogo = (ImageView) view.findViewById(R.id.image_movie_detail);
            mTitle = (TextView) view.findViewById(R.id.text_name_of_movie);
            mReleaseDate = (TextView) view.findViewById(R.id.text_release_date_movie_detail);
            mVoteCount = (TextView) view.findViewById(R.id.text_vote_count_movie);
            mVoteAverage = (TextView) view.findViewById(R.id.text_vote_average_movie);
        }

        public void bindData(PrimaryMovieInfo primaryMovieInfo) {
            if (primaryMovieInfo == null) return;
            Picasso.with(mContext)
                .load(ApiClient.BASE_IMAGE_URL + primaryMovieInfo.getBackdropPath())
                .placeholder(R.mipmap.ic_launcher)
                .into(mLogo);
            mTitle.setText(primaryMovieInfo.getTitle());
            mReleaseDate.setText(primaryMovieInfo.getReleaseDate());
            mVoteCount.setText(String.valueOf(primaryMovieInfo.getVoteCount()));
            mVoteAverage.setText(String.valueOf(primaryMovieInfo.getVoteAverage()));
        }
    }
}
