package com.example.mamorky.socialplayer.ui;

import android.graphics.Color;
import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.ImageView;

import com.example.mamorky.socialplayer.R;
import com.example.mamorky.socialplayer.ui.base.BaseActivity;
import com.vansuita.materialabout.builder.AboutBuilder;
import com.vansuita.materialabout.views.AboutView;

import mehdi.sakout.aboutpage.AboutPage;

public class AboutActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.argb(255,0,0,0));
        }

        AboutView view = AboutBuilder.with(this)
                .setPhoto(R.drawable.about_me)
                .setCover(R.mipmap.profile_cover)
                .setName(R.string.developerName)
                .setSubTitle("Mobile Developer")
                .setBrief("I'm warmed of mobile technologies. Ideas maker, curious and nature lover.")
                .setAppIcon(R.drawable.logo_app)
                .setAppName(R.string.app_name)
                .addGooglePlayStoreLink("8002078663318221363")
                .addGitHubLink("mamorky")
                .addFacebookLink("mamorky")
                .addFiveStarsAction()
                .setVersionNameAsAppSubTitle()
                .addShareAction(R.string.app_name)
                .setWrapScrollView(true)
                .setLinksAnimated(true)
                .setShowAsCard(true)
                .build();


        addContentView(view,((ConstraintLayout)findViewById(R.id.aboutConstraint)).getLayoutParams());
    }
}
