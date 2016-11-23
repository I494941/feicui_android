package com.feicuiedu.android.slidingmenudemo;

import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public class MainActivity extends AppCompatActivity {

    private SlidingMenu menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // configure the SlidingMenu
        menu = new SlidingMenu(this);

        // 设置动画
        menu.setBehindCanvasTransformer(new SlidingMenu.CanvasTransformer() {
            @Override
            public void transformCanvas(Canvas canvas, float percentOpen) {

                // 设置侧滑栏显示动画为折叠动画
                // canvas.scale(percentOpen, 1, 0, 0);

                // 放缩动画
                float scale = (float) (percentOpen*0.25 + 0.75);
                canvas.scale(scale, scale, canvas.getWidth()/2, canvas.getHeight()/2);

            }
        });

        // 设置侧滑菜单的位置，可选值LEFT , RIGHT , LEFT_RIGHT （两边都有菜单时设置）
        menu.setMode(SlidingMenu.LEFT_RIGHT);

        // 设置触摸屏幕的模式 可以选择全屏划出，或者是边缘划出，或者是不可划出,这里为全屏划出
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);

        // 根据dimension资源文件的ID来设置阴影的宽度 侧滑栏完全展开之后，距离另外一边的距离，单位px，设置的越大，侧滑栏的宽度越小
        menu.setShadowWidthRes(R.dimen.shadow_width);

        // 根据资源文件ID来设置滑动菜单的阴影效果
        menu.setShadowDrawable(R.mipmap.ic_launcher);


        // 设置滑动菜单视图的宽度
        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);

        // 设置渐入渐出效果的值 范围是0-1.0f,设置的越大，则在侧滑栏刚划出的时候， 颜色就越暗。1.0f的时候，颜色为全黑刚划出的时候
        menu.setFadeDegree(0.35f);

        /**
         * SLIDING_WINDOW will include the Title/ActionBar in the content
         * section of the SlidingMenu, while SLIDING_CONTENT does not.
         */
        // 把滑动菜单添加进所有的Activity中，可选值SLIDING_CONTENT ， SLIDING_WINDOW
        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);

        //为左侧滑菜单设置布局
        menu.setMenu(R.layout.activity_left);

        menu.setSecondaryShadowDrawable(R.drawable.i2);
        menu.setSecondaryMenu(R.layout.activity_right);

        menu.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (menu.isMenuShowing())
                    menu.toggle();
            }
        });

        //menu.setLeft(R.layout.activity_left);
        //menu.setRight(R.layout.activity_right);
    }
}
