package com.skylark.imageeditorsl71.Utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.DecelerateInterpolator;
import android.widget.Scroller;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import java.lang.reflect.Field;

public class NinSwipeableViewPager extends ViewPager {

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;
    }

    public NinSwipeableViewPager(@NonNull Context context) {
        super(context);
        SetMyScroller();
    }

    private void SetMyScroller() {
        try {
            Class<?> viewpager = ViewPager.class;
            Field scrooler = viewpager.getDeclaredField("mScroller");
            scrooler.setAccessible(true);
            scrooler.set(this,new MyScroller(getContext()));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    public NinSwipeableViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        SetMyScroller();
    }

    private class MyScroller extends Scroller {
        public MyScroller(Context context) {
            super(context, new DecelerateInterpolator());
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy, int duration) {
            super.startScroll(startX, startY, dx, dy, 400);
        }
    }
}
