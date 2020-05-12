package com.alan.lib.state;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * Created by Mouse on 2018/9/20.
 */

public class StateLinearLayout extends LinearLayout implements IStateView {


    protected ILoadingView iLoadingView;
    protected IFailureView iFailureView;


    public StateLinearLayout(Context context) {
        this(context, null);
    }

    public StateLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public void showLoadingState(String text) {
        reset();
        addView(generateLoadingView(text), generateLoadingLayoutParams());
        iLoadingView.start();
    }

    @Override
    public void showFailureState(String text, boolean isRetry) {
        reset();
        addView(generateFailureView(), 0, generateLoadingLayoutParams());
        iFailureView.setText(text);
        iFailureView.setVisiable(isRetry);
    }

    @Override
    public void showSuccessState() {
        reset();
    }

    @Override
    public TextView getRetryView() {
        return iFailureView.getRetryTextView();
    }

    protected LayoutParams generateLoadingLayoutParams() {
        LayoutParams lp = new LayoutParams(-1, -1);
        lp.gravity = Gravity.CENTER;
        return lp;
    }

    protected View generateLoadingView(String text) {
        if (null == iLoadingView) {
            iLoadingView = StateViewConfig.getInstance().getLoadingView(getContext());
        }
        iLoadingView.setText(text);
        return (View) iLoadingView;
    }

    protected View generateFailureView() {
        if (null == iFailureView) {
            iFailureView = StateViewConfig.getInstance().getFailureView(getContext());
        }
        return iFailureView.getView();
    }


    public void reset() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View v = getChildAt(i);
            if ((null != iLoadingView && iLoadingView.getView() == v) || (null != iFailureView && iFailureView.getView() == v)) {
                removeView(v);
            }
        }
    }



}
