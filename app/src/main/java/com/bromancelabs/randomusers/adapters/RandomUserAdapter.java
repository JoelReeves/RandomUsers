package com.bromancelabs.randomusers.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bromancelabs.randomusers.R;
import com.bromancelabs.randomusers.activities.UserDetailActivity;
import com.bromancelabs.randomusers.models.Result;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RandomUserAdapter extends RecyclerView.Adapter<RandomUserAdapter.RandomUserViewHolder> {

    private List<Result> mResultList;

    public RandomUserAdapter(List<Result> resultList) {
        mResultList = resultList;
    }

    @Override
    public RandomUserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_row, parent, false);
        return new RandomUserViewHolder((view));
    }

    @Override
    public void onBindViewHolder(RandomUserViewHolder holder, int position) {
        holder.bindRandomUser(mResultList.get(position));
    }

    @Override
    public int getItemCount() {
        return mResultList.size();
    }

    public class RandomUserViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_user_image) ImageView userImageView;
        @BindView(R.id.tv_user_name) TextView userTextView;

        private Result mResult;
        private Gson mGson;

        public RandomUserViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mGson = new Gson();
        }

        public void bindRandomUser(Result result) {
            mResult = result;

            Glide.with(itemView.getContext())
                    .load(mResult.getPicture().getLarge())
                    .centerCrop()
                    .override(getUserImageDimension(), getUserImageDimension())
                    .into(userImageView);

            userTextView.setText(mResult.getName().toString());
        }

        private int getUserImageDimension() {
            return itemView.getContext().getResources().getDimensionPixelSize(R.dimen.user_image_dimension);
        }

        @OnClick({R.id.iv_user_image, R.id.tv_user_name})
        public void userClicked() {
            Context context = itemView.getContext();
            context.startActivity(UserDetailActivity.newIntent(context, mGson.toJson(mResult)));
        }
    }
}
