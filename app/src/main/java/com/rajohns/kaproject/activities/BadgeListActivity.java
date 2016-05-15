package com.rajohns.kaproject.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.rajohns.kaproject.R;
import com.rajohns.kaproject.adapters.BadgeListAdapter;
import com.rajohns.kaproject.constants.BadgeCategory;
import com.rajohns.kaproject.models.Badge;
import com.rajohns.kaproject.retrofit.RestManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BadgeListActivity extends AppCompatActivity {

    private List<Badge> badges = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_badge_list);

        final RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        final BadgeListAdapter adapter = new BadgeListAdapter(this.getApplicationContext(), badges);
        recyclerView.setAdapter(adapter);

        Object data = getLastCustomNonConfigurationInstance();
        if (data != null) {
            badges = (List<Badge>)data;
            adapter.setBadges(badges);
            adapter.notifyDataSetChanged();
        } else {
            final ProgressDialog progressDialog = ProgressDialog.show(this, "", getString(R.string.loading_badges));
            Call<List<Badge>> call = RestManager.getInstance().getAllBadges();
            call.enqueue(new Callback<List<Badge>>() {
                @Override
                public void onResponse(Call<List<Badge>> call, Response<List<Badge>> response) {
                    progressDialog.dismiss();
                    badges.clear();
                    if (response.body() != null) {
                        for (Badge badge : response.body()) {
                            if (badge.badge_category == BadgeCategory.CHALLENGE_PATCHES) {
                                badges.add(badge);
                            }
                        }
                        adapter.setBadges(badges);
                        adapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(BadgeListActivity.this, getString(R.string.error_loading_badges), Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<List<Badge>> call, Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(BadgeListActivity.this, getString(R.string.error_loading_badges), Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return badges;
    }

}
