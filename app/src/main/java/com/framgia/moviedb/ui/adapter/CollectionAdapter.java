package com.framgia.moviedb.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.framgia.moviedb.R;
import com.framgia.moviedb.data.model.Collection;

import java.util.List;

/**
 * Created by trungnguyens93gmail.com on 1/5/17.
 */
public class CollectionAdapter
    extends RecyclerView.Adapter<CollectionAdapter.CollectionViewHolder> {
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<Collection> mCollections;

    public CollectionAdapter(Context context, List<Collection> collections) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        mCollections = collections;
    }

    public void setCollections(List<Collection> collections) {
        mCollections = collections;
    }

    @Override
    public CollectionViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.adapter_genre, parent, false);
        return new CollectionViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CollectionViewHolder holder, int position) {
        Collection collection = mCollections.get(position);
        holder.bindData(collection);
    }

    @Override
    public int getItemCount() {
        return mCollections == null ? 0 : mCollections.size();
    }

    class CollectionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Button mName;

        public CollectionViewHolder(View itemView) {
            super(itemView);
            mName = (Button) itemView.findViewById(R.id.button_genre);
            mName.setOnClickListener(this);
        }

        public void bindData(Collection collection) {
            if (collection == null) return;
            mName.setText(collection.getName());
        }

        @Override
        public void onClick(View v) {
            // TODO Go to the Detail Collection Fragment
        }
    }
}
