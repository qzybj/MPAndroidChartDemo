package com.example.mpandroidchart.adapter;

import android.content.Context;
import com.example.mpandroidchart.R;
import com.example.mpandroidchart.aosp.baseadapter.CommonAdapter;
import com.example.mpandroidchart.aosp.baseadapter.base.ViewHolder;
import com.example.mpandroidchart.aosp.mpchart.interfaces.IPieLabelModel;
import java.util.List;


/**
 * Created by Clair on 2017/3/13.
 */
public class PieViewLabelAdapter extends CommonAdapter<IPieLabelModel> {
    public PieViewLabelAdapter(Context con, int layoutResId, List<IPieLabelModel> list) {
        super(con,layoutResId, list);
    }

    @Override
    protected void convert(ViewHolder viewHolder, IPieLabelModel item, int position) {

        viewHolder.setText(R.id.tv_item, item.getTitle());
        viewHolder.setImageResource(R.id.iv_item, item.getColorResId());

    }
}