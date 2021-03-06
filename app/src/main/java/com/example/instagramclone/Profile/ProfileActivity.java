package com.example.instagramclone.Profile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.instagramclone.R;
import com.example.instagramclone.Utils.BottomNavigationViewHelper;
import com.example.instagramclone.Utils.GridImageAdapter;
import com.example.instagramclone.Utils.UniversalImageLoader;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {

    private static final String TAG = "ProfileActivity";
    private static final int NUM_GRID_COLUMNS = 3;

    private Context mContext= ProfileActivity.this;
    private static final int ACTIVITY_NUM= 4;
    private ImageView profilePhoto;

    private ProgressBar mProgressBar ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);




        Log.d(TAG, "onCreate: started");

        setupBottomNavigationView();
        setupToolbar();
        setupActivityWidgets();
        setProfileImage();

        //tempGridSetup();

    }

    private void tempGridSetup(){

        ArrayList<String> imgURLS = new ArrayList<>();
        imgURLS.add("https://i.redd.it/tmo2ph0oaj761.jpg");
        imgURLS.add("https://i.redd.it/h545137byb861.jpg");
        imgURLS.add("https://i.redd.it/nsv5a46sfd761.jpg");
        imgURLS.add("https://i.redd.it/50qbq8euca761.jpg");
        imgURLS.add("https://i.redd.it/v8yznm5ftf761.jpg");
        imgURLS.add("https://i.redd.it/ph3lxu30rw761.jpg");
        imgURLS.add("https://i.redd.it/wy5rv90f80861.jpg");
        imgURLS.add("https://i.redd.it/3bktkqtohs761.jpg");
        imgURLS.add("https://i.redd.it/qo6j3pzxtq761.jpg");
        imgURLS.add("https://i.redd.it/ggjoziw34l761.jpg");
        imgURLS.add("https://external-preview.redd.it/zG0tQ_EWil68Yk064heAjCJlJT-EUo7dvleXhhuXfvU.png?auto=webp&s=3315645dfe0ebc5ce4b1520cab532c856b9dadb3");

        setupImageGrid(imgURLS);

    }

    private void setupImageGrid(ArrayList<String> imgURLs){
        GridView gridView= findViewById(R.id.gridView);

        int gridWidth= getResources().getDisplayMetrics().widthPixels;
        int imageWidth= gridWidth/NUM_GRID_COLUMNS;
        gridView.setColumnWidth(imageWidth);

        GridImageAdapter adapter= new GridImageAdapter(mContext, R.layout.layout_grid_imageview, "",imgURLs);
        gridView.setAdapter(adapter);
    }

    private void setProfileImage(){
        Log.d(TAG, "setProfileImage: setting up profile photo");
        String imageURL= "https://external-preview.redd.it/zG0tQ_EWil68Yk064heAjCJlJT-EUo7dvleXhhuXfvU.png?auto=webp&s=3315645dfe0ebc5ce4b1520cab532c856b9dadb3";
        UniversalImageLoader.setImage(imageURL, profilePhoto, mProgressBar, "");
    }

    private void setupActivityWidgets(){
        mProgressBar= findViewById(R.id.profileProgressbar);
        mProgressBar.setVisibility(View.GONE);
        profilePhoto= findViewById(R.id.profile_photo);
    }
    
    private void setupToolbar(){

        Toolbar toolbar= findViewById(R.id.profileToolbar);
        setSupportActionBar(toolbar);

        ImageView profileMenu = findViewById(R.id.profileMenu);
        profileMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: navigating to account settings");

                Intent intent= new Intent(mContext, AccountSettingsActivity.class);
                startActivity(intent);
            }
        });
    }

    //bottomNavigationViewsetup

    private void setupBottomNavigationView(){

        Log.d(TAG, "setupBottomNavigationView: setting up bottom navigation");

        BottomNavigationViewEx bottomNavigationViewEx= findViewById(R.id.bottomNavViewBar);

        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);

        BottomNavigationViewHelper.enableNavigation(mContext,bottomNavigationViewEx);

        Menu menu= bottomNavigationViewEx.getMenu();
        MenuItem menuItem= menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);


    }


}
