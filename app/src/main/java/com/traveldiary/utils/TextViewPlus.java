package com.traveldiary.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;


import com.traveldiary.R;

import java.util.Hashtable;

public class TextViewPlus extends android.support.v7.widget.AppCompatTextView {
    private static final String TAG = "TextView";
    private static final Hashtable<String, Typeface> cache = new Hashtable<String, Typeface>();

    public TextViewPlus(Context context) {
        super(context);
    }

    public TextViewPlus(Context context, AttributeSet attrs) {
        super(context, attrs);
        setCustomFont(context, attrs);
    }

    public TextViewPlus(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setCustomFont(context, attrs);
    }

    private void setCustomFont(Context ctx, AttributeSet attrs) {
        TypedArray a = ctx.obtainStyledAttributes(attrs, R.styleable.TextViewPlus);
        String customFont = a.getString(R.styleable.TextViewPlus_customFont);
        setCustomFont(ctx, customFont);
        a.recycle();
    }

    public boolean setCustomFont(Context ctx, String asset) {
        Typeface tf = null;
        try {
        tf = Typeface.createFromAsset(ctx.getAssets(), asset);  
        } catch (Exception e) {
            Log.e(TAG, "Could not get typeface: "+e.getMessage());
            return false;
        }
        synchronized (cache) {
            if (!cache.containsKey(asset)) {
                try {
                    Typeface t = Typeface.createFromAsset(ctx.getAssets(),
                            asset);
                    cache.put(asset, t);
                } catch (Exception e) {
                    Log.e(TAG, "Could not get typeface '" + asset
                            + "' because " + e.getMessage());
                    return false;
                }
            }
        }

        setTypeface(cache.get(asset));
        return true;
    }

}

