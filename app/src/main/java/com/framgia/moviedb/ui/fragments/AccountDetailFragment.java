package com.framgia.moviedb.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.framgia.moviedb.BuildConfig;
import com.framgia.moviedb.R;
import com.framgia.moviedb.data.model.AccountDetail;
import com.framgia.moviedb.data.model.ManagerConstant;
import com.framgia.moviedb.service.RetrofitClient;
import com.framgia.moviedb.ultils.Utils;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountDetailFragment extends Fragment {
    private ImageView mImageAvatar;
    private TextView mTextAccountId, mTextAccountName, mTextAccountCountry, mTextAccountUsername;
    private ImageButton mImageButtonFavorite, mImageButtonWatchlist, mImageButtonRated;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account_detail, container, false);
        init(view);
        loadAccountDetail();
        return view;
    }

    private void init(View v) {
        mImageAvatar = (ImageView) v.findViewById(R.id.image_account_avatar);
        mTextAccountId = (TextView) v.findViewById(R.id.text_account_id);
        mTextAccountName = (TextView) v.findViewById(R.id.text_account_name);
        mTextAccountCountry = (TextView) v.findViewById(R.id.text_account_country);
        mTextAccountUsername = (TextView) v.findViewById(R.id.text_account_username);
        mImageButtonFavorite = (ImageButton) v.findViewById(R.id.image_button_account_favorite);
        mImageButtonWatchlist = (ImageButton) v.findViewById(R.id.image_button_account_watchlist);
        mImageButtonRated = (ImageButton) v.findViewById(R.id.image_button_account_rated);
    }

    private void loadAccountDetail() {
        Map<String, String> params = new HashMap<>();
        params.put(ManagerConstant.Param.PARAM_API_KEY, BuildConfig.API_KEY);
        params.put(ManagerConstant.Param.PARAM_SESSION_ID, BuildConfig.SESSION_ID);
        RetrofitClient.getAccountDetail(params, new Callback<AccountDetail>() {
            @Override
            public void onResponse(Call<AccountDetail> call, Response<AccountDetail> response) {
                if (response == null) {
                    Utils.makeToast(getActivity(), ManagerConstant.MessengerManager.MSG_LOAD);
                    return;
                }
                if (response.body() == null) {
                    Utils.makeToast(getActivity(), ManagerConstant.MessengerManager.MSG_CONVERT);
                    return;
                }
                loadDataView(response.body());
            }

            @Override
            public void onFailure(Call<AccountDetail> call, Throwable t) {
                Utils.makeToast(getActivity(), ManagerConstant.MessengerManager.MSG_LOAD);
            }
        });
    }

    private void loadDataView(AccountDetail ad) {
        mTextAccountId.setText(buildDataText(
            R.string.account_detail_id,
            String.valueOf(ad.getId())));
        mTextAccountName.setText(buildDataText(
            R.string.account_detail_name,
            ad.getName()));
        mTextAccountCountry.setText(buildDataText(
            R.string.account_detail_country,
            ad.getIso31661()));
        mTextAccountUsername.setText(buildDataText(
            R.string.account_detail_username,
            ad.getUserName()));
        Picasso.with(getActivity())
            .load(
                ManagerConstant.UrlManager.IMAGE_AVATAR_URL +
                    ad.getAvatarPath().getGravatar().getHash())
            .placeholder(R.mipmap.ic_launcher)
            .into(mImageAvatar);
    }

    public String buildDataText(int stringId, String textDataShow) {
        return new StringBuffer().append(getString(stringId)).append(textDataShow).toString();
    }
}
