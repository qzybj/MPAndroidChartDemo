package com.example.mpandroidchart.fragment.base;


import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

import com.brady.coreframe.utils.LogUtils;
import com.example.mpandroidchart.aosp.mpchart.utils.CListUtil;

import java.util.ArrayList;


/**
 * 框架 - Fragment的base基类，只包含最基本设置
 */
public abstract class CheckInputFragment extends FrameBaseFragment{

    private ArrayList<TextView> tvList = new ArrayList<TextView>();
    private CTextChangeListener mChangeListener;

    @Override
    public void onDestroy() {
        if(mChangeListener!=null){
            removeTextWatch();
        }
        super.onDestroy();
    }

    /**
     * 添加注册TextView的监听实现
     * @param tv
     */
    protected void addTextWatch(TextView tv){
        if(tv!=null){
            if(tvList==null){
                tvList = new ArrayList<TextView>();
            }
            if(mChangeListener==null){
                mChangeListener = new CTextChangeListener();
            }
            tv.addTextChangedListener(mChangeListener);
            if(!tvList.contains(tv)){
                tvList.add(tv);
            }
        }
    }

    /**
     * 移除文字录入监听
     */
    private void removeTextWatch() {
        if(mChangeListener!=null&&!CListUtil.isEmpty(tvList)){
            for (TextView tv : tvList) {
                if(tv!=null){
                    try {
                        tv.removeTextChangedListener(mChangeListener);
                    }catch (Exception e){
                        LogUtils.e("error",e.getMessage());
                    }
                }
            }
        }
    }

    /**
     * 检查输入校验逻辑
     */
    protected abstract void checkInput();


    protected class CTextChangeListener implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }
        @Override
        public void afterTextChanged(Editable s) {
            checkInput();
        }
    }
}