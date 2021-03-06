package com.rajohns.kaproject.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rajohns.kaproject.R;
import com.rajohns.kaproject.activities.BadgeDetailActivity;
import com.rajohns.kaproject.constants.IntentKey;
import com.rajohns.kaproject.models.Badge;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BadgeListAdapter extends RecyclerView.Adapter<BadgeListAdapter.ViewHolder> {

    private List<Badge> badges;
    private Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView badgeNameTextView;
        public ImageView badgeIconImageView;
        public Badge badge;

        public ViewHolder(final View cardView) {
            super(cardView);

            badgeNameTextView = (TextView)cardView.findViewById(R.id.badge_name_text_view);
            badgeIconImageView = (ImageView)cardView.findViewById(R.id.badge_icon_image_view);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(cardView.getContext(), BadgeDetailActivity.class);
                    intent.putExtra(IntentKey.BADGE_NAME, badge.description);
                    intent.putExtra(IntentKey.BADGE_DESCRIPTION, badge.safe_extended_description);
                    intent.putExtra(IntentKey.BADGE_ICON_URL, badge.icons.large);
                    Pair<View, String> badgeIconPair = Pair.create((View)badgeIconImageView, "badge_icon");
                    ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity)cardView.getContext(), badgeIconPair);
                    cardView.getContext().startActivity(intent, options.toBundle());
                }
            });
        }

        public void bindBadge(Badge badge) {
            this.badge = badge;
        }
    }

    public BadgeListAdapter(Context context, List<Badge> badges) {
        this.context = context;
        this.badges = badges;
    }

    public void setBadges(List<Badge> badges) {
        this.badges = badges;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View cardView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_badge, parent, false);
        return new ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Badge badge = badges.get(position);
        holder.bindBadge(badge);
        holder.badgeNameTextView.setText(badge.description);
        Picasso.with(context)
            .load(badge.icons.large)
            .placeholder(R.drawable.badge_icon_placeholder)
            .into(holder.badgeIconImageView);
    }

    @Override
    public int getItemCount() {
        return badges.size();
    }

}
