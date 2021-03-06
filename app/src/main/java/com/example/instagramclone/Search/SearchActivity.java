package com.example.instagramclone.Search;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.instagramclone.R;
import com.example.instagramclone.Utils.BottomNavigationViewHelper;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class SearchActivity extends AppCompatActivity {

    private Context mContext= SearchActivity.this;
    private static final int ACTIVITY_NUM= 1;

    private static final String TAG = "SearchActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Log.d(TAG, "onCreate: started");

        setupBottomNavigationView();
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
