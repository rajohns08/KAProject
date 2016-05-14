package com.rajohns.kaproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BadgeListActivity extends AppCompatActivity {

    // TODO: SHARED ELEMENT TRANSITION TO DETAIL WITH MAYBE CIRCULAR REVEAL
    // TODO: DIFFERENT PROPERTY NAMES FOR GSON OBJECT ELEMENTS FROM JSON KEY NAMES
    // TODO: ATTRIBUTE 3RD PARTY LIBS
    // TODO: FAILURE AND LOADING STATES
    // TODO: GRAY CIRCLE BEFORE IMAGES LOAD
    // TODO: GROUP FILES IN FOLDERS

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

        Call<List<Badge>> call = RestManager.getInstance().getAllBadges();
        call.enqueue(new Callback<List<Badge>>() {
            @Override
            public void onResponse(Call<List<Badge>> call, Response<List<Badge>> response) {
                badges.clear();
                for (Badge badge : response.body()) {
                    if (badge.badge_category == 5) {
                        badges.add(badge);
                    }
                }
                adapter.setBadges(badges);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Badge>> call, Throwable t) {
                Log.d("tagzzz", "failure loading stuff");
            }
        });
    }

}
