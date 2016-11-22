package com.feicuiedu.fragmentdemo;

import java.util.UUID;

/**
 * Created by chenyan on 2016/11/21.
 */

public class Crime {

    private UUID mid;

    private String mTtitle;

    public Crime() {

        mid = UUID.randomUUID();
    }

    public UUID getMid() {
        return mid;
    }

    public void setMid(UUID mid) {
        this.mid = mid;
    }

    public String getmTtitle() {
        return mTtitle;
    }

    public void setmTtitle(String mTtitle) {
        this.mTtitle = mTtitle;
    }
}
