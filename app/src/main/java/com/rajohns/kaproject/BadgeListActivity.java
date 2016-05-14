package com.rajohns.kaproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class BadgeListActivity extends AppCompatActivity {

    // TODO: MATCH KHAN ACADEMY COLORS
    // TODO: SHARED ELEMENT TRANSITION TO DETAIL WITH MAYBE CIRCULAR REVEAL
    // TODO: DIFFERENT PROPERTY NAMES FOR GSON OBJECT ELEMENTS FROM JSON KEY NAMES
    // TODO: ONLY SHOW BADGES OF CATEGORY 5
    // TODO: MAYBE CHANGE TO STAGGERED GRID LAYOUT MANAGER OR GRIDLAYOUTMANAGER
    // TODO: TAKE OUT REDWOOD.PNG
    // TODO: TAKE OUT STUB DATA
    // TODO: ATTRIBUTE 3RD PARTY LIBS

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_badge_list);

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        BadgeListAdapter adapter = new BadgeListAdapter(this.getApplicationContext(), stubBadges());
        recyclerView.setAdapter(adapter);
    }

    private List<Badge> stubBadges() {
        List<Badge> badges = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            Badge badge = new Badge();
            badge.description = "Fact Checker";
            badge.safe_extended_description = "Complete the Hour of Drawing with Blocks for Hour of Code!";
            BadgeIcon fcIcon = new BadgeIcon();
            fcIcon.large = "https://fastly.kastatic.org/images/badges/moon/fact-checker-512x512.png";
            badge.icons = fcIcon;
            badges.add(badge);
        }

        return badges;
    }

}
