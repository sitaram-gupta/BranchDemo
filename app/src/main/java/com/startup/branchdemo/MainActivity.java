package com.startup.branchdemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

import io.branch.indexing.BranchUniversalObject;
import io.branch.referral.Branch;
import io.branch.referral.BranchError;
import io.branch.referral.SharingHelper;
import io.branch.referral.util.BranchEvent;
import io.branch.referral.util.ContentMetadata;
import io.branch.referral.util.LinkProperties;
import io.branch.referral.util.ShareSheetStyle;

public class MainActivity extends AppCompatActivity {
    Button createLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createLink = findViewById(R.id.btn_create_link);
        createLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(MainActivity.this, "created", Toast.LENGTH_SHORT).show();

//                LinkProperties lp = new LinkProperties()
//                        .setChannel("facebook")
//                        .setFeature("sharing")
//                        .setCampaign("content 123 launch")
//                        .setStage("new user")
//                        .addControlParameter("$desktop_url", "http://example.com/home")
//                        .addControlParameter("custom", "data")
//                        .addControlParameter("custom_random", Long.toString(Calendar.getInstance().getTimeInMillis()));
//
//                ShareSheetStyle ss = new ShareSheetStyle(MainActivity.this, "Check this out!", "This stuff is awesome: ")
//                        .setCopyUrlStyle(ContextCompat.getDrawable(MainActivity.this, android.R.drawable.ic_menu_send), "Copy", "Added to clipboard")
//                        .setMoreOptionStyle(ContextCompat.getDrawable(MainActivity.this, android.R.drawable.ic_menu_search), "Show more")
//                        .addPreferredSharingOption(SharingHelper.SHARE_WITH.FACEBOOK)
//                        .addPreferredSharingOption(SharingHelper.SHARE_WITH.EMAIL)
//                        .addPreferredSharingOption(SharingHelper.SHARE_WITH.MESSAGE)
//                        .addPreferredSharingOption(SharingHelper.SHARE_WITH.HANGOUT)
//                        .setAsFullWidthStyle(true)
//                        .setSharingTitle("Share With");
//
//                BranchUniversalObject buo = new BranchUniversalObject()
//                        .setCanonicalIdentifier("content/12345")
//                        .setTitle("My Content Title")
//                        .setContentDescription("My Content Description")
//                        .setContentImageUrl("https://lorempixel.com/400/400")
//                        .setContentIndexingMode(BranchUniversalObject.CONTENT_INDEX_MODE.PUBLIC)
//                        .setContentMetadata(new ContentMetadata().addCustomMetadata("key1", "value1"));
//
//
//                buo.showShareSheet(MainActivity.this, lp, ss, new Branch.BranchLinkShareListener() {
//                    @Override
//                    public void onShareLinkDialogLaunched() {
//                    }
//
//                    @Override
//                    public void onShareLinkDialogDismissed() {
//                    }
//
//                    @Override
//                    public void onLinkShareResponse(String sharedLink, String sharedChannel, BranchError error) {
//                    }
//
//                    @Override
//                    public void onChannelSelected(String channelName) {
//                    }
//                });

                BranchUniversalObject branchUniversalObject = new BranchUniversalObject()
                        .setContentExpiration(new Date(System.currentTimeMillis() + expireDurationInMiliSeconds))
                        .setTitle(selectedOfferObject.getTitle() + " (Coupon Gifted From " + Profile.getCurrentProfile().getFirstName() + " " + Profile.getCurrentProfile().getLastName() + ")")
                        .setContentDescription(selectedOfferObject.getDescription())
                        .setContentImageUrl(selectedOfferObject.getBanner())
                        // To specify whether this content can be discovered publicly - default is public
                        .setContentIndexingMode(BranchUniversalObject.CONTENT_INDEX_MODE.PUBLIC);

                LinkProperties linkProperties = new LinkProperties()
                        .addControlParameter(mContext.getResources().getString(R.string.key_is_promo), String.valueOf(selectedOfferObject.isPromo()))
                        .addControlParameter(mContext.getResources().getString(R.string.key_referral_user_id), FirebaseAuth.getInstance().getUid())
                        .addControlParameter(mContext.getResources().getString(R.string.key_gifted_offer_id), selectedOfferObject.getOfferId())
                        .addControlParameter(mContext.getResources().getString(R.string.from_key), mContext.getResources().getString(R.string.coupon_share))
                        .addControlParameter(mContext.getResources().getString(R.string.key_coupon_gifted_time), Long.toString(Calendar.getInstance().getTimeInMillis()));

                ShareSheetStyle shareSheetStyle = new ShareSheetStyle(mContext, "", "")
                        .setCopyUrlStyle(ContextCompat.getDrawable(mContext, android.R.drawable.ic_menu_send), TXT_COPY, MSG_CLIPBOARD)
                        .setMoreOptionStyle(ContextCompat.getDrawable(mContext, android.R.drawable.ic_menu_search), MORE_OPTION_STYLE_LABEL)
                        .addPreferredSharingOption(SharingHelper.SHARE_WITH.FACEBOOK_MESSENGER)
                        .addPreferredSharingOption(SharingHelper.SHARE_WITH.WHATS_APP)
                        .addPreferredSharingOption(SharingHelper.SHARE_WITH.INSTAGRAM)
                        .addPreferredSharingOption(SharingHelper.SHARE_WITH.WECHAT)
                        .setAsFullWidthStyle(true)
                        .setSharingTitle(SHARE_SHEET_TITLE);
                branchUniversalObject.userCompletedAction(BranchEvent.VIEW);
                branchUniversalObject.showShareSheet((Activity) mContext, linkProperties, shareSheetStyle, new Branch.BranchLinkShareListener() {
                    @Override
                    public void onShareLinkDialogLaunched() {
                    }

                    @Override
                    public void onShareLinkDialogDismissed() {
                    }

                    @Override
                    public void onLinkShareResponse(String sharedLink, String sharedChannel, BranchError error) {
                    }

                    @Override
                    public void onChannelSelected(String channelName) {
                    }
                });
            }
        });
    }
}
