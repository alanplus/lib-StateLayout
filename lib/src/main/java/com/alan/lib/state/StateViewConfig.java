package com.alan.lib.state;

import android.content.Context;
import android.view.View;

import com.alan.lib.state.view.CommonFailureView;
import com.alan.lib.state.view.CommonLoadingView;

/**
 * @author Alan
 * 时 间：2020-05-12
 * 简 述：<功能简述>
 */
public class StateViewConfig {

    public static StateViewConfig mStateViewConfig;

    public static void register(StateViewConfig stateViewConfig) {
        mStateViewConfig = stateViewConfig;
    }

    public static StateViewConfig getInstance() {
        if (null == mStateViewConfig) {
            mStateViewConfig = new StateViewConfig();
        }
        return mStateViewConfig;
    }

    public ILoadingView getLoadingView(Context context) {
        return new CommonLoadingView(context);
    }

    public IFailureView getFailureView(Context context) {
        return new CommonFailureView(context);
    }

}
