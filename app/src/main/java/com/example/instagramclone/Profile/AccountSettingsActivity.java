package com.example.instagramclone.Profile;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.instagramclone.R;
import com.example.instagramclone.Utils.BottomNavigationViewHelper;
import com.example.instagramclone.Utils.SectionsStatePagerAdapter;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;

public class AccountSettingsActivity extends AppCompatActivity {

    private static final String TAG = "AccountSettingsActivity";
    private static final int ACTIVITY_NUM= 4;

    Context mContext;

    private SectionsStatePagerAdapter pagerAdapter;
    private ViewPager mViewPager;
    private RelativeLayout mRelativeLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext= AccountSettingsActivity.this;
        setContentView(R.layout.activity_accountsettings);
        Log.d(TAG, "onCreate: started");

        mViewPager= findViewById(R.id.container);
        mRelativeLayout= findViewById(R.id.relLayout1);



        setupSettingsList();
        setupBottomNavigationView();
        setupFragments();
        // backArraw to profile activity

        ImageView backarraow= findViewById(R.id.backArrow);
        backarraow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: retun to profileActivity");
                finish();
            }
        });
    }

    private void setupFragments(){
        pagerAdapter= new SectionsStatePagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragments(new EditprofileFragment(), getString(R.string.edit_profile));
        pagerAdapter.addFragments(new SignoutFragment(), getString(R.string.sign_out));
    }

    private void setViewPager(int fragmentNumber){
        Log.d(TAG, "setViewPager: setting up pager");
        mRelativeLayout.setVisibility(View.GONE);
        Log.d(TAG, "setViewPager: navigating to frgment #: " +fragmentNumber);
        mViewPager.setAdapter(pagerAdapter);
        mViewPager.setCurrentItem(fragmentNumber);
    }



    private void setupSettingsList(){
        Log.d(TAG, "setupSettingsList: setting up profile settings list.");

        ListView listView= findViewById(R.id.lvAccountSettings);

        ArrayList<String> options= new ArrayList<>();
        options.add(getString(R.string.edit_profile));
        options.add(getString(R.string.sign_out));

        ArrayAdapter adapter= new ArrayAdapter(mContext, android.R.layout.simple_list_item_1, options);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "onItemClick: navigating to frgments#: " +position);
                setViewPager(position);
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
