package com.bromancelabs.randomusers.activities;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.bromancelabs.randomusers.R;
import com.bromancelabs.randomusers.adapters.RandomUserAdapter;
import com.bromancelabs.randomusers.models.Result;
import com.bromancelabs.randomusers.models.Results;
import com.bromancelabs.randomusers.services.RandomUserService;
import com.bromancelabs.randomusers.utils.DialogUtils;
import com.bromancelabs.randomusers.utils.NetworkUtils;
import com.bromancelabs.randomusers.utils.SharedPreferencesUtils;
import com.bromancelabs.randomusers.utils.SnackbarUtils;
import com.bromancelabs.randomusers.views.SimpleDividerItemDecoration;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import timber.log.Timber;

public class UsersActivity extends AppCompatActivity {

    private static final String BASE_URL = "http://api.randomuser.me/";
    private List<Result> mRandomUsersList;

    @Bind(R.id.tb_toolbar) Toolbar mToolbar;
    @Bind(R.id.rv_recyclerview) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                if (!NetworkUtils.isNetworkAvailable(this)) {
                    SnackbarUtils.showErrorSnackbar(this, R.string.network_unavailable);
                } else {
                    getRandomUsers();
                }
                return true;

            case R.id.action_settings:
                startActivity(new Intent(this, SettingsActivity.class));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void getRandomUsers() {
        final Dialog progressDialog = DialogUtils.showProgressDialog(this);
        final String usersCount = SharedPreferencesUtils.getSavedUsersCount(this);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        RandomUserService service = retrofit.create(RandomUserService.class);
        Call<Results> resultsCall = service.getRandomUsers(usersCount);

        resultsCall.enqueue(new Callback<Results>() {
            @Override
            public void onResponse(Call<Results> call, Response<Results> response) {
                cancelProgressDialog(progressDialog);

                if (response.isSuccessful()) {
                    mRandomUsersList = response.body().getResults();
                    setupRecyclerView(mRandomUsersList);
                } else {
                    Timber.e(response.message());
                    SnackbarUtils.showErrorSnackbar(UsersActivity.this, R.string.users_download_error);
                }
            }

            @Override
            public void onFailure(Call<Results> call, Throwable t) {
                Timber.e(t, t.getMessage());
                SnackbarUtils.showErrorSnackbar(UsersActivity.this, R.string.users_download_error);
            }
        });
    }

    private void setupRecyclerView(List<Result> resultList) {
        Timber.d("List size: %s", resultList.size());

        if (!resultList.isEmpty()) {
            mRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(this, R.drawable.recyclerview_horizontal_divider));

            RandomUserAdapter adapter = new RandomUserAdapter(resultList);
            mRecyclerView.setAdapter(adapter);
        } else {
            SnackbarUtils.showErrorSnackbar(this, R.string.users_download_error);
        }
    }

    private void cancelProgressDialog(Dialog dialog) {
        if (dialog != null) {
            dialog.cancel();
        }
    }
}
