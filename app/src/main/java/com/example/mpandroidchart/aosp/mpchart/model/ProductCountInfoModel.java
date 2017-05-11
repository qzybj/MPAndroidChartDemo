package com.example.mpandroidchart.aosp.mpchart.model;


import com.example.mpandroidchart.aosp.mpchart.interfaces.IProductCountInfoModel;

/**
 * Created by Clair on 2017/3/17.
 */
public class ProductCountInfoModel implements IProductCountInfoModel {
    private String name;
    private String number;
    private String received;
    private String count;

    public ProductCountInfoModel(String name, String number, String received, String count) {
        this.name = name;
        this.number = number;
        this.received = received;
        this.count = count;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getNumber() {
        return number;
    }

    @Override
    public String getReceived() {
        return received;
    }

    @Override
    public String getCount() {
        return count;
    }
}
