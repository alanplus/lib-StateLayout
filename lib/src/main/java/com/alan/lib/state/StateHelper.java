package com.alan.lib.state;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;


/**
 * @author Alan
 * 时 间：2019-11-21
 * 简 述：<功能简述>
 */
public class StateHelper implements IBaseStateView {

    protected IStateView iStateView;

    private static final String EXCEPTION_VIEW_NULL = "root view is null";
    private static final String EXCEPTION_VIEW_NOT_FIND = "state view is not find";

    public static final String DEFAULT_LOADING_TEXT = "正在加载";

    private Context context;

    public StateHelper(Context context, ViewGroup viewGroup) {
        try {
            this.context = context;
            iStateView = initStateView(viewGroup);
        } catch (Exception e) {
//            Logger.error(e);
        }
    }

    public StateHelper(Context context, IStateView iStateView) {
        this.context = context;
        this.iStateView = iStateView;
    }


    private IStateView initStateView(ViewGroup viewGroup) throws Exception {
        if (null == viewGroup) {
            throw new Exception(EXCEPTION_VIEW_NULL);
        }
        if (viewGroup instanceof IStateView) {
            return (IStateView) viewGroup;
        }

        int childCount = viewGroup.getChildCount();
        if (childCount == 0) {
            throw new Exception(EXCEPTION_VIEW_NOT_FIND);
        } else {
            for (int i = 0; i < childCount; ++i) {
                View v = viewGroup.getChildAt(i);
                if (v instanceof IStateView) {
                    return (IStateView) v;
                }
                if (v instanceof ViewGroup) {
                    IStateView stateView = this.initStateView((ViewGroup) v);
                    if (null != stateView) {
                        return stateView;
                    }
                }
            }
            return null;
        }
    }

    public IStateView getIStateView() {
        return iStateView;
    }

    @Override
    public void showLoadingState() {
        showLoadingState(DEFAULT_LOADING_TEXT);
    }

    @Override
    public void showLoadingState(String text) {
        if (null != iStateView) {
            iStateView.showLoadingState(text);
        }
    }

    @Override
    public void showFailureState(String text, boolean isRetry) {
        if (null != iStateView) {
            iStateView.showFailureState(text, isRetry);
        }
    }

    @Override
    public void showSuccessState() {
        if (null != iStateView) {
            iStateView.showSuccessState();
        }
    }


    @Override
    public void setRetryListener(View.OnClickListener onClickListener) {
        if (null != iStateView && iStateView.getRetryView() != null) {
            iStateView.getRetryView().setOnClickListener(onClickListener);
        }
    }
}
