package com.trojanstudio.movie2;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by smjs2000 on 8/29/17.
 */
public class CustomBindingAdapter {

    @BindingAdapter("app:loadImage")
    public static void loadImageFromURL(ImageView imageView, String imageUrl) {
        Picasso.with(imageView.getContext()).load(imageUrl).into(imageView);
    }
}
