package com.goods.client.usr.activities;

import android.app.ActionBar;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import com.goods.client.lib.BaseMenuActivity;
import com.goods.client.usr.R;
import com.goods.client.usr.fragment.HomeFragment;
import com.goods.client.usr.fragment.MenuFragment;
import com.goods.client.usr.utils.DisplayUtil;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public class MainActivity extends BaseMenuActivity {

    private Fragment mContent;
    private SlidingMenu.CanvasTransformer mTransformer;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initAnimation();
        // 初始化滑动视图
        initSlidingMenu(savedInstanceState);
        // 设置是否能够使用ActionBar来滑动
        setSlidingActionBarEnabled(true);
        // 设置是否显示Home图标按钮
        getActionBar().setDisplayHomeAsUpEnabled(true);

        final ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setCustomView(R.layout.layout_action_bar);
        TextView titleTv = (TextView) actionBar.getCustomView().findViewById(R.id.tv_action_title);
        titleTv.setText(R.string.app_name);

        getActionBar().setIcon(android.R.drawable.ic_menu_sort_by_size);
    }

    private void initSlidingMenu(Bundle savedInstanceState) {
        // 如果保存的状态不为空则得到之前保存的Fragment, 否则实例化MyFragment
        if (savedInstanceState != null) {
            mContent = getSupportFragmentManager().getFragment(
                    savedInstanceState, "mContent");
        }
        if (mContent == null) {
            mContent = new Fragment();
        }

        setContentView(R.layout.content_frame);
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new HomeFragment()).commit();
        // 设置滑动菜单的视图
        setBehindContentView(R.layout.menu_frame);
        getSupportFragmentManager().beginTransaction().replace(R.id.menu_frame, new MenuFragment()).commit();

        // 实例话滑动菜单对象
        SlidingMenu sm = getSlidingMenu();
        // 设置滑动阴影的宽度
        sm.setShadowWidthRes(R.dimen.shadow_width);
        // 设置滑动阴影的图像资源
        sm.setShadowDrawable(R.drawable.shadow);
        //设置滑动菜单视图的宽度
        sm.setBehindOffset((int) (DisplayUtil.getScreenWidth(this) * 0.35));
        // 设置渐入渐出效果的值
        sm.setFadeDegree(0.35f);
        // 设置触摸屏幕的模式
        sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        sm.setBehindScrollScale(0.0f);
//        sm.setBehindCanvasTransformer(mTransformer);
    }

    /**
     * 初始化动画效果
     */
    private void initAnimation(){
        mTransformer = new SlidingMenu.CanvasTransformer(){
            @Override
            public void transformCanvas(Canvas canvas, float percentOpen) {
//                canvas.scale(percentOpen, 1, 0, 0);
                float scale = (float) (percentOpen * 0.25 + 0.75);
                canvas.scale(scale, scale, canvas.getWidth()/2, canvas.getHeight()/2);
            }
        };
    }

    /**
     * 保存Fragment的状态
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getSupportFragmentManager().putFragment(outState, "mContent", mContent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                toggle();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
