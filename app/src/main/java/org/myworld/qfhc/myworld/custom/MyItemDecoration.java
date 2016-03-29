package org.myworld.qfhc.myworld.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Ken on 2016/3/14.
 * 自定义分割线
 */
public class MyItemDecoration extends RecyclerView.ItemDecoration{

    private static final int[] ATTR = {android.R.attr.listDivider};
    private Drawable drawable;

    public MyItemDecoration(Context context){
        TypedArray typedArray = context.obtainStyledAttributes(ATTR);
        drawable = typedArray.getDrawable(0);
        typedArray.recycle();
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int count = parent.getChildCount();
        for(int i = 0; i < count; i++){
            View childView = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) childView.getLayoutParams();

            //绘制底部分割线
            int left = childView.getLeft() + layoutParams.leftMargin;
            int right = childView.getRight() - layoutParams.rightMargin;
            int top = childView.getBottom() - layoutParams.bottomMargin;
            int bottom = top + drawable.getIntrinsicHeight();

            drawable.setBounds(left, top, right, bottom);
            drawable.draw(c);

            /*//绘制右侧分割线
            int left1 = childView.getRight() - layoutParams.rightMargin;
            int right1 = left1 + drawable.getIntrinsicWidth();
            int top1 = childView.getTop() + layoutParams.topMargin;
            int bottom1 = childView.getBottom() - layoutParams.bottomMargin;
            drawable.setBounds(left1, top1, right1, bottom1);
            drawable.draw(c);*/
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.set(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
    }
}
