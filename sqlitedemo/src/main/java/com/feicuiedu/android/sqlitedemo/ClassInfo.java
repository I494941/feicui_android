package com.feicuiedu.android.sqlitedemo;

/**
 * Created by chenyan on 2016/10/24.
 */

public class ClassInfo {

    private Long idx;

    private String name;

    public ClassInfo(Long idx, String name) {
        this.idx = idx;
        this.name = name;
    }

    public Long getIdx() {
        return idx;
    }

    public void setIdx(Long idx) {
        this.idx = idx;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
