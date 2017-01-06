package com.framgia.moviedb.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.framgia.moviedb.R;
import com.framgia.moviedb.data.model.Company;

import java.util.List;

/**
 * Created by trungnguyens93gmail.com on 1/6/17.
 */
public class CompanyAdapter
    extends RecyclerView.Adapter<CompanyAdapter.CompanyViewHolder> {
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<Company> mCompanies;

    public CompanyAdapter(Context context, List<Company> companies) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        mCompanies = companies;
    }

    public void setCompanies(List<Company> companies) {
        mCompanies = companies;
    }

    @Override
    public CompanyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.adapter_genre, parent, false);
        return new CompanyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CompanyViewHolder holder, int position) {
        Company company = mCompanies.get(position);
        holder.bindData(company);
    }

    @Override
    public int getItemCount() {
        return mCompanies == null ? 0 : mCompanies.size();
    }

    class CompanyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Button mName;

        public CompanyViewHolder(View itemView) {
            super(itemView);
            mName = (Button) itemView.findViewById(R.id.button_genre);
            mName.setOnClickListener(this);
        }

        public void bindData(Company company) {
            if (company == null) return;
            mName.setText(company.getName());
        }

        @Override
        public void onClick(View v) {
            // TODO Go to the Detail Collection Fragment
        }
    }
}

