package com.example.instagramclone.Home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.instagramclone.Login.LoginActivity;
import com.example.instagramclone.R;
import com.example.instagramclone.Utils.BottomNavigationViewHelper;
import com.example.instagramclone.Utils.SectionPagerAdapter;
import com.example.instagramclone.Utils.UniversalImageLoader;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.nostra13.universalimageloader.core.ImageLoader;

public class HomeActivity extends AppCompatActivity {

    private static final String TAG = "HomeActivity";

    private Context mContext= HomeActivity.this;
    private static final int ACTIVITY_NUM= 0;

    // firebase stuff

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);

        initImageLoader();
        setupBottomNavigationView();
        setupViewPager();
        setupFirebaseAuth();

    }

    //----------------firebase----------------------

    private void checkCurrentUser(FirebaseUser user){
        Log.d(TAG, "checkCurrentUser: checking if the user id logged in");

        if(user== null){
            Intent intent= new Intent(mContext, LoginActivity.class);
            startActivity(intent);
        }

    }

    private void setupFirebaseAuth(){
        Log.d(TAG, "setupFirebaseAuth: settingup firebase auth");
        mAuth = FirebaseAuth.getInstance();
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user= mAuth.getCurrentUser();
                checkCurrentUser(user);

                if (user != null){
                    Log.d(TAG, "onAuthStateChanged: signed-in"+user.getUid());
                }else {
                    Log.d(TAG, "onAuthStateChanged: signed-out");
                }
            }
        };


    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        mAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    public void onStop(){
     super.onStop();
     if (mAuthStateListener != null){
         mAuth.removeAuthStateListener(mAuthStateListener);
     }
    }



    //-------------------firebase codesection ends------------

    private  void initImageLoader(){
        UniversalImageLoader universalImageLoader= new UniversalImageLoader(mContext);
        ImageLoader.getInstance().init(universalImageLoader.getConfig());
    }

    // adding 3 tabs camera, home , messages

    private void setupViewPager(){

        SectionPagerAdapter adapter= new SectionPagerAdapter(getSupportFragmentManager());
        adapter.addFragments(new CameraFragment());
        adapter.addFragments(new HomeFragment());
        adapter.addFragments(new MessagesFragment());

        ViewPager viewPager= findViewById(R.id.container);
        viewPager.setAdapter(adapter);

        TabLayout tabLayout= findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_camera);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_instaicon);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_arrow);
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
