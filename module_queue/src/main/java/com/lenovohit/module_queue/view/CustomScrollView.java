package com.lenovohit.module_queue.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.lenovohit.module_queue.R;

/**
 * Created by SharkChao on 2017-07-17.
 * 解决滑动冲突
 */

public class CustomScrollView extends ScrollView{

    private RecyclerView mTopView;
    private RecyclerView mParentView;
    private RecyclerView mSonView;
    private int mX;
    private int mY;

    public CustomScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        LinearLayout childAt = (LinearLayout) getChildAt(0);
        mTopView= (RecyclerView) childAt.findViewById(R.id.rvQueueUp);
        mParentView= (RecyclerView) childAt.findViewById(R.id.parentRecycle);
        mSonView= (RecyclerView) childAt.findViewById(R.id.sonRecycle);
        switch (action){
            case MotionEvent.ACTION_DOWN:
                mX = (int) ev.getX();
                mY = (int) ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                if (mParentView.getTop() <= 0){
                    this.requestDisallowInterceptTouchEvent(true);
                    this.requestDisallowInterceptTouchEvent(true);
                    this.requestDisallowInterceptTouchEvent(false);
                }else {
                    this.requestDisallowInterceptTouchEvent(false);
                    this.requestDisallowInterceptTouchEvent(false);
                    this.requestDisallowInterceptTouchEvent(false);
                }
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }
}
