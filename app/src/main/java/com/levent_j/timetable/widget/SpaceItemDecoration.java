package com.levent_j.timetable.widget;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by levent_j on 16-11-17.
 */
public class SpaceItemDecoration extends RecyclerView.ItemDecoration{

    private int space;

    public SpaceItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left = space;
        outRect.bottom = space;
        int type = parent.getChildPosition(view) %8;

        switch (type){
            case 0:
                outRect.left = 0;
                outRect.bottom = space;
                break;
            case 1:
                outRect.left = space;
                outRect.bottom = 0;
                break;
            default:
                outRect.left = 0;
                outRect.bottom = 0;
        }

    }
}
