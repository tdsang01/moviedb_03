package com.framgia.moviedb.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.framgia.moviedb.R;
import com.framgia.moviedb.data.model.Genre;

import java.util.List;

public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.GenreViewHolder> {
    private Context mContext;
    private List<Genre> mGenres;
    private LayoutInflater mLayoutInflater;

    public GenreAdapter(Context context, List<Genre> genres) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        mGenres = genres;
    }

    @Override
    public GenreViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.adapter_genre, parent, false);
        return new GenreViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(GenreViewHolder holder, int position) {
        Genre genre = mGenres.get(position);
        holder.bindData(genre);
    }

    @Override
    public int getItemCount() {
        return mGenres.size();
    }

    class GenreViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Button mButton;

        public GenreViewHolder(final View itemView) {
            super(itemView);
            mButton = (Button) itemView.findViewById(R.id.button_genre);
            mButton.setOnClickListener(this);
        }

        public void bindData(final Genre genre) {
            if (genre == null) return;
            mButton.setText(genre.getName());
        }

        @Override
        public void onClick(View v) {
            // TODO Get the List of Movies that have the same Genre
        }
    }
}
