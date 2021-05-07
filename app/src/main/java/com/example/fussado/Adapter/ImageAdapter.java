package com.example.fussado.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;

public class ImageAdapter extends PagerAdapter {
    private static final String TAG = "ImageViewPage";
    Context mContext;
    String[] mResources = {
            "https://i.imgur.com/gi4Vaeq.png",
            "https://i.imgur.com/dqX7aqe.png",
            "https://i.imgur.com/t30nZjb.png",
            "https://i.imgur.com/i9hM662.png"
    };

    public ImageAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mResources.length;
    }

    @Override
    public boolean isViewFromObject(View view,  Object object) {
        return view == object;
    }



    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView((mContext));
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        Glide.with(mContext)
                .load(mResources[position])
                .into(imageView);

        container.addView(imageView);
        return imageView;

    }

    @Override
    public void destroyItem(ViewGroup container, int position,  Object object) {
        container.removeView((ImageView) object);
    }
}
