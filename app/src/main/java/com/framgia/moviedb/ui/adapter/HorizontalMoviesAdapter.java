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

public class HorizontalMoviesAdapter
    extends RecyclerView.Adapter<HorizontalMoviesAdapter.HorizontalViewHolder> {
    private Context mContext;
    private List<PrimaryMovieInfo> mMovies;
    private LayoutInflater mLayoutInflater;
    private OnFragmentEvent mListenner;

    public HorizontalMoviesAdapter(Context context, List<PrimaryMovieInfo> movies,
                                   OnFragmentEvent listenner) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        mMovies = movies;
        mListenner = listenner;
    }

    @Override
    public HorizontalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.adapter_horizontal_movie, parent, false);
        return new HorizontalViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(HorizontalViewHolder holder, int position) {
        PrimaryMovieInfo movie = mMovies.get(position);
        holder.bindData(movie);
    }

    @Override
    public int getItemCount() {
        return mMovies == null ? 0 : mMovies.size();
    }

    public interface OnFragmentEvent {
        void onDataSelected(PrimaryMovieInfo movieInfo);
    }

    // Setting item of RecycleView
    public class HorizontalViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView mNameMovie;
        private ImageView mImageView;

        public HorizontalViewHolder(View itemView) {
            super(itemView);
            mNameMovie = (TextView) itemView.findViewById(R.id.text_name_of_movie);
            mImageView = (ImageView) itemView.findViewById(R.id.image_title);
            itemView.setOnClickListener(this);
        }

        public void bindData(PrimaryMovieInfo primaryMovieInfo) {
            if (primaryMovieInfo == null) return;
            mNameMovie.setText(primaryMovieInfo.getTitle());
            Picasso.with(mContext)
                .load(ApiClient.BASE_IMAGE_URL + primaryMovieInfo.getBackdropPath())
                .placeholder(R.mipmap.ic_launcher)
                .into(mImageView);
        }

        @Override
        public void onClick(View v) {
            if (mListenner != null) mListenner.onDataSelected(mMovies.get(getAdapterPosition()));
        }
    }
}
