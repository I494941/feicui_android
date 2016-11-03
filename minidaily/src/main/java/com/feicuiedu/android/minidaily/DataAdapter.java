package com.feicuiedu.android.minidaily;

import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenyan on 2016/11/3.
 */

public abstract class DataAdapter<T> extends BaseAdapter {

    private List<T> list = new ArrayList<T>();

    public DataAdapter() {

    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public T getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
