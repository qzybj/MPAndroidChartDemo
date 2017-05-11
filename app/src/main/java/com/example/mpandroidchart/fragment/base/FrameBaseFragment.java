package com.example.mpandroidchart.fragment.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.brady.coreframe.utils.LogUtils;
import com.example.mpandroidchart.fragment.interfaces.IBaseUI;


/**
 * 框架 - Fragment的base基类，只包含最基本设置
 */
public abstract class FrameBaseFragment extends Fragment implements IBaseUI {
    protected Activity mParentActivity;
    private View mRootView ;

    @Override
    public final void onCreate(Bundle savedInstanceState) {
        mParentActivity  = this.getActivity();
        super.onCreate(savedInstanceState);
    }

    @Override
    public final View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int layoutResId = getLayoutResId();
        if (layoutResId>0) {
            try {
                mRootView  = inflater.inflate(layoutResId, container, false);
            } catch (Exception e) {
                LogUtils.e("error",e.getMessage());
            }
            if (mRootView != null) {
                initContentView(mRootView);
                return mRootView;
            }
        }
        return super.onCreateView(inflater,container,savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData(savedInstanceState);
    }

    public Activity getBaseActivity(){
        return mParentActivity;
    }

}