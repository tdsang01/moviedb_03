package com.framgia.moviedb.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.framgia.moviedb.R;
import com.framgia.moviedb.data.model.ManagerConstant;
import com.framgia.moviedb.data.model.PrimaryMovieInfo;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MoviePopularAdapter extends RecyclerView.Adapter<MoviePopularAdapter.MovieHolder> {
    private Context mContext;
    private List<PrimaryMovieInfo> mListMovieInfoList;
    private LayoutInflater mInflater;
    private OnFragmentEvent mListenner;

    public MoviePopularAdapter(Context context, List<PrimaryMovieInfo> listMovieInfoList,
                               OnFragmentEvent listenner) {
        mContext = context;
        mListMovieInfoList = listMovieInfoList;
        mInflater = LayoutInflater.from(context);
        mListenner = listenner;
    }

    @Override
    public MovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.layout_row_of_movie_popular_list, parent, false);
        return new MovieHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MovieHolder holder, int position) {
        PrimaryMovieInfo movie = mListMovieInfoList.get(position);
        holder.bindData(movie);
    }

    @Override
    public int getItemCount() {
        return mListMovieInfoList == null ? 0 : mListMovieInfoList.size();
    }

    class MovieHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView mImageView;
        private TextView mTextName, mTextReleaseDate, mTextVoteCount, mTextVoteAverage;
        private PrimaryMovieInfo mMovieInfo;

        public MovieHolder(View itemView) {
            super(itemView);
            mTextName = (TextView) itemView.findViewById(R.id.text_name_of_movie_detail);
            mTextReleaseDate =
                (TextView) itemView.findViewById(R.id.text_release_date_movie_detail);
            mTextVoteCount = (TextView) itemView.findViewById(R.id.text_vote_count_movie_detail);
            mTextVoteAverage =
                (TextView) itemView.findViewById(R.id.text_vote_average_movie_detail);
            mImageView = (ImageView) itemView.findViewById(R.id.image_movie_detail);
            itemView.setOnClickListener(this);
        }

        public void bindData(PrimaryMovieInfo movie) {
            mTextName.setText(movie.getOriginalTitle());
            mTextReleaseDate.setText(movie.getReleaseDate());
            mTextVoteCount.setText(String.valueOf(movie.getVoteCount()));
            mTextVoteAverage.setText(String.valueOf(movie.getVoteAverage()));
            Picasso.with(mContext)
                .load(ManagerConstant.UrlManager.IMAGE_URL + movie.getBackdropPath())
                .placeholder(R.mipmap.ic_launcher)
                .into(mImageView);
            mMovieInfo = movie;
        }

        @Override
        public void onClick(View v) {
            if(mListenner != null) mListenner.onDataSelected(mMovieInfo);
        }
    }
    public interface OnFragmentEvent {
        void onDataSelected(PrimaryMovieInfo movieInfo);
    }
}
