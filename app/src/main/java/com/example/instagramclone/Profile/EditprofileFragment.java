package com.example.instagramclone.Profile;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.instagramclone.R;
import com.example.instagramclone.Utils.UniversalImageLoader;
import com.nostra13.universalimageloader.core.ImageLoader;

public class EditprofileFragment extends Fragment {

    private static final String TAG = "EditprofileFragment";

    private ImageView mProfilePhoto;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Log.d(TAG, "onCreateView: editprofilefrgment");

        View view= inflater.inflate(R.layout.fragment_edit_profile, container, false);
        mProfilePhoto= view.findViewById(R.id.profile_photo);


        setProfileImage();

        // back arrow for navigating back to profileActivity
        ImageView backArrow = view.findViewById(R.id.backArrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: navigating back to ProfileActivity");
                getActivity().finish();
            }
        });


        return view;
    }



    private void setProfileImage(){
        Log.d(TAG, "setProfileImage: setting profile image");
        String imageURL= "https://external-preview.redd.it/zG0tQ_EWil68Yk064heAjCJlJT-EUo7dvleXhhuXfvU.png?auto=webp&s=3315645dfe0ebc5ce4b1520cab532c856b9dadb3";
        UniversalImageLoader.setImage(imageURL, mProfilePhoto, null, "");

    }
}
