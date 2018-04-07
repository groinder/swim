package com.example.krzys.cw2;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by krzys on 25.03.2018.
 */

public class MyAdapter extends BaseAdapter {
    private Context ctx;

    public Integer[] id_obrazkow = {
            R.drawable.pizza1, R.drawable.pizza2, R.drawable.pizza3,
            R.drawable.pizza1, R.drawable.pizza2, R.drawable.pizza3,
            R.drawable.pizza1, R.drawable.pizza2, R.drawable.pizza3
    };

    public MyAdapter(Context c) {
        ctx = c;
    }

    @Override
    public int getCount() {
        return id_obrazkow.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView mV;

        if (view == null) {
            mV = new ImageView(ctx);
            mV.setLayoutParams(new GridView.LayoutParams(200,200));
            mV.setScaleType(ImageView.ScaleType.CENTER_CROP);
            mV.setPadding(8,8,8, 8);
        }
        else {
            mV = (ImageView) view;
        }

        mV.setImageResource(id_obrazkow[i]);

        return mV;
    }
}
