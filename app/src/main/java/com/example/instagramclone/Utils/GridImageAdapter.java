package com.example.instagramclone.Utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.instagramclone.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.ArrayList;


public class GridImageAdapter extends ArrayAdapter<String> {

   private Context mContext;
   private LayoutInflater mInflater;
   private int layoutResource;
   private String mAppend;
   private ArrayList<String> imgURLs;

    public GridImageAdapter(@NonNull Context context, int layoutResource, String mAppend, ArrayList<String> imgURLs) {
        super(context, layoutResource,imgURLs);
        mInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mContext = context;
        this.layoutResource = layoutResource;
        this.mAppend = mAppend;
        this.imgURLs = imgURLs;
    }

    private static class ViewHolder{
        SquareImageView image;
        ProgressBar mPreogressBar;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        final ViewHolder holder;
        if (convertView == null){
            convertView= mInflater.inflate(layoutResource,parent,false);
            holder= new ViewHolder();
            holder.mPreogressBar= convertView.findViewById(R.id.gridImageProgressBar);
            holder.image= convertView.findViewById(R.id.gridImageview);

            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }

        String imgURL= getItem(position);

        ImageLoader imageLoader= ImageLoader.getInstance();
        imageLoader.displayImage(mAppend + imgURL, holder.image, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {
                if(holder.mPreogressBar != null){
                    holder.mPreogressBar.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                if(holder.mPreogressBar != null){
                    holder.mPreogressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                if(holder.mPreogressBar != null){
                    holder.mPreogressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {
                if(holder.mPreogressBar != null){
                    holder.mPreogressBar.setVisibility(View.GONE);
                }
            }
        });

        return convertView;
    }
}
