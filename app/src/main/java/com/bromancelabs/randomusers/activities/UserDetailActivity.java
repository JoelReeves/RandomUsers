package com.bromancelabs.randomusers.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bromancelabs.randomusers.R;
import com.bromancelabs.randomusers.models.Result;
import com.bromancelabs.randomusers.utils.SnackbarUtils;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;

public class UserDetailActivity extends AppCompatActivity {

    private static final String EXTRA_USER = "extra_user";

    @Bind(R.id.tb_toolbar) Toolbar mToolbar;
    @Bind(R.id.iv_user_detail_image) ImageView userImageView;
    @Bind(R.id.tv_user_name_field) TextView nameTextView;
    @Bind(R.id.tv_user_email_field) TextView emailTextView;
    @Bind(R.id.tv_user_birthday_field) TextView birthdayTextView;
    @Bind(R.id.tv_user_address_field) TextView addressTextView;
    @Bind(R.id.tv_user_phone_number_field) TextView phoneNumberTextView;

    private Result mUser;

    public static Intent newIntent(Context context, String result) {
        Intent intent = new Intent(context, UserDetailActivity.class);
        intent.putExtra(EXTRA_USER, result);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);

        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);

        if (getIntent().getExtras() != null && !getIntent().getExtras().isEmpty()) {
            Gson gson = new Gson();
            mUser = gson.fromJson(getIntent().getStringExtra(EXTRA_USER), Result.class);

        } else {
            finish();
            SnackbarUtils.showErrorSnackbar(this, R.string.activity_start_error);
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(mUser.getName().toString());
        }

        setupUserFields();
    }

    private void setupUserFields() {
        Glide.with(this)
                .load(mUser.getPicture().getLarge())
                .centerCrop()
                .override(getUserImageDimension(), getUserImageDimension())
                .into(userImageView);

        nameTextView.setText(mUser.getName().toString());
        emailTextView.setText(mUser.getEmail());
        birthdayTextView.setText(getUserDOB(mUser));
        addressTextView.setText(formatUserAddress(mUser));
        phoneNumberTextView.setText(mUser.getPhone());
    }

    private int getUserImageDimension() {
        return getResources().getDimensionPixelSize(R.dimen.user_detail_image_dimension);
    }

    private String formatUserAddress(Result user) {
        return String.format(getString(R.string.full_address),user.getLocation().getStreet(),
                user.getLocation().getCity(), user.getLocation().getState(),
                user.getLocation().getPostcode());
    }

    private String getUserDOB(Result user) {
        long epochTime = user.getDob();
        Date birthday = new Date(epochTime * 1000L);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("M/dd/yyyy", Locale.US);
        return simpleDateFormat.format(birthday);
    }
}
