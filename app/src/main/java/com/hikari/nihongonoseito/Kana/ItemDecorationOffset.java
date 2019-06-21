package com.hikari.nihongonoseito.Kana;

import android.content.Context;
import android.graphics.Rect;
import androidx.annotation.DimenRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;


public class ItemDecorationOffset extends RecyclerView.ItemDecoration {

    private int mItemOffset;

    public ItemDecorationOffset(int itemOffset) {
        mItemOffset = itemOffset;
    }

    public ItemDecorationOffset(@NonNull Context context, @DimenRes int itemOffsetId) {
        this(context.getResources().getDimensionPixelSize(itemOffsetId));
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if(parent.getAdapter().getItemViewType(parent.getChildAdapterPosition(view))==2)
        outRect.set(0, 5, 0, 5);


    }
}
