package com.exmple.corelib.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

/**
 * Created by Samda on 2017/2/27.
 */

public class GlideUtils {
    public static void loadImage(Context context, ImageView imageView,
                                 String imgUrl){
        if(TextUtils.isEmpty(imgUrl)){
            return;
        }
        RequestOptions options = new RequestOptions();
//        options.placeholder(R.mipmap.ic_launcher);
//        options.error(R.mipmap.ic_launcher);
        Glide.with(context).load(imgUrl).apply(options).into(imageView);

    }
}
