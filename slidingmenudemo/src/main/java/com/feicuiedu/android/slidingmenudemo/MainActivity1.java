package com.feicuiedu.android.slidingmenudemo;

import android.os.Bundle;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingActivity;

public class MainActivity1 extends SlidingActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setBehindContentView(R.layout.activity_left);

        // configure the SlidingMenu
        SlidingMenu menu = getSlidingMenu();

        // 设置侧滑菜单的位置，可选值LEFT , RIGHT , LEFT_RIGHT （两边都有菜单时设置）
        menu.setMode(SlidingMenu.LEFT);

        // 设置触摸屏幕的模式
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);

        // 根据dimension资源文件的ID来设置阴影的宽度
        menu.setShadowWidthRes(R.dimen.shadow_width);

        // 根据资源文件ID来设置滑动菜单的阴影效果
        menu.setShadowDrawable(R.mipmap.ic_launcher);

        // 设置滑动菜单视图的宽度
        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);

        // 设置渐入渐出效果的值
        menu.setFadeDegree(0.35f);

        /**
         * SLIDING_WINDOW will include the Title/ActionBar in the content
         * section of the SlidingMenu, while SLIDING_CONTENT does not.
         */
        // 把滑动菜单添加进所有的Activity中，可选值SLIDING_CONTENT ， SLIDING_WINDOW
        // menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);


        // menu.setSecondaryShadowDrawable(R.mipmap.ic_launcher);
        // menu.setSecondaryMenu(R.layout.activity_right);
        //menu.setLeft(R.layout.activity_left);
        //menu.setRight(R.layout.activity_right);
    }
}
