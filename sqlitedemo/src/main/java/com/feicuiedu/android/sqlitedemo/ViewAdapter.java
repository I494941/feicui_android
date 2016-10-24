package com.feicuiedu.android.sqlitedemo;

import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenyan on 2016/10/24.
 */

public abstract class ViewAdapter<T> extends BaseAdapter {

    private List<T> listData = new ArrayList<T>();

    public ViewAdapter() {
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public T getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public List<T> getListData() {
        return listData;
    }

    public void setListData(List<T> listData) {
        this.listData = listData;
    }
}
