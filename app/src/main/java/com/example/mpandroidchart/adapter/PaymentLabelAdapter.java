package com.example.mpandroidchart.adapter;

import android.content.Context;
import com.example.mpandroidchart.R;
import com.example.mpandroidchart.aosp.baseadapter.CommonAdapter;
import com.example.mpandroidchart.aosp.baseadapter.base.ViewHolder;
import com.example.mpandroidchart.aosp.mpchart.interfaces.IPaymentModel;
import java.util.List;


/**
 * Created by Clair on 2017/3/13.
 */
public class PaymentLabelAdapter extends CommonAdapter<IPaymentModel> {
    public PaymentLabelAdapter(Context con, int layoutResId, List<IPaymentModel> list) {
        super(con,layoutResId, list);
    }

    @Override
    protected void convert(ViewHolder viewHolder, IPaymentModel item, int position) {
        viewHolder.setText(R.id.tv_left, item.getLabel());
        viewHolder.setText(R.id.tv_right, "￥"+item.getValue());
    }
}