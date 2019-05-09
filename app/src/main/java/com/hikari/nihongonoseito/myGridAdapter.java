package com.hikari.nihongonoseito;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class myGridAdapter extends BaseAdapter {
    Context context;
    Integer[] imagesIds;
    LayoutInflater thisInflater;
    public myGridAdapter(Context context, Integer[] ids){
        imagesIds=ids;

        this.thisInflater = LayoutInflater.from(context);
        this.context=context;
    }
    @Override
    public int getCount() {
        return imagesIds.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = thisInflater.inflate( R.layout.achievement_item, parent, false );
            ImageView thumbnailImage = (ImageView) convertView.findViewById(R.id.achievement);
            thumbnailImage.setBackground(context.getDrawable(imagesIds[position]));
        }
        return convertView;
    }
}
