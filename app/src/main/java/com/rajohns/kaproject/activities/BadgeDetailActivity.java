package com.rajohns.kaproject.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.rajohns.kaproject.constants.IntentKey;
import com.rajohns.kaproject.R;
import com.squareup.picasso.Picasso;

public class BadgeDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_badge_detail);

        String badgeName = "";
        String badgeDescription = "";
        String badgeIconUrl = "";

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            badgeName = extras.getString(IntentKey.BADGE_NAME);
            badgeDescription = extras.getString(IntentKey.BADGE_DESCRIPTION);
            badgeIconUrl = extras.getString(IntentKey.BADGE_ICON_URL);
        }

        ImageView badgeIconImageView = (ImageView)findViewById(R.id.badge_icon_image_view);
        TextView badgeNameTextView = (TextView)findViewById(R.id.badge_name_text_view);
        TextView badgeDescriptionTextView = (TextView)findViewById(R.id.badge_description_text_view);

        badgeNameTextView.setText(badgeName);
        badgeDescriptionTextView.setText(badgeDescription);

        Picasso.with(this).load(badgeIconUrl).into(badgeIconImageView);
    }

}
