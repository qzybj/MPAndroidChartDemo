package com.example.mpandroidchart.adapter;

import android.content.Context;
import com.brady.coreframe.utils.dataprocess.StringUtils;
import com.example.mpandroidchart.R;
import com.example.mpandroidchart.aosp.baseadapter.CommonAdapter;
import com.example.mpandroidchart.aosp.baseadapter.base.ViewHolder;
import com.example.mpandroidchart.aosp.mpchart.interfaces.IPieSortModel;
import java.util.List;

/**
 * Created by Clair on 2017/3/13.
 */
public class PieViewSortAdapter extends CommonAdapter<IPieSortModel> {
    private String curPrefix;
    public PieViewSortAdapter(Context con, int layoutResId, List<IPieSortModel> list, String prefix) {
        super(con,layoutResId, list);
        this.curPrefix=prefix;
    }

    @Override
    protected void convert(ViewHolder viewHolder, IPieSortModel item, int position) {
        viewHolder.setText(R.id.tv_left, item.getTitle());
        viewHolder.setText(R.id.tv_right, StringUtils.isNotEmpty(curPrefix)?curPrefix+item.getValue():item.getValue());
    }
}