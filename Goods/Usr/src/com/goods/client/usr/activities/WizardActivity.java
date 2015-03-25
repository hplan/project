package com.goods.client.usr.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.goods.client.lib.BaseActivity;
import com.goods.client.usr.R;
import com.goods.client.usr.adapter.WizardAdapter;
import com.goods.client.usr.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 2015/3/23.
 */
public class WizardActivity extends BaseActivity implements ViewPager.OnPageChangeListener,
        View.OnClickListener {

    private ViewPager mViewPager;
    private WizardAdapter mAdapter;
    private Button mExperBtn;
    private Button mSkipBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_wizard);

        initViews();
    }

    private void initViews() {
        mSkipBtn = findView(R.id.btn_skip);
        mSkipBtn.setOnClickListener(this);

        mExperBtn = findView(R.id.btn_exper);
        mExperBtn.setOnClickListener(this);

        mViewPager = findView(R.id.wizard);
        mAdapter = new WizardAdapter(mContext);
        mViewPager.setAdapter(mAdapter);
        setWizardValues();
        mViewPager.setCurrentItem(0);
        mViewPager.setOnPageChangeListener(this);
    }

    private void setWizardValues() {
        List<View> list = new ArrayList<View>();
        int[] colors = new int[] {Color.YELLOW, Color.BLUE, Color.GREEN};

        View rootView;
        ImageView imgView;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        for (int i = 0; i < Constants.WIZARD_PAGE_SIZE; i++) {
            rootView = inflater.inflate(R.layout.item_wizard, null);
            if (rootView != null) {
                imgView = findView(rootView, R.id.img_wizard);
                imgView.setBackgroundColor(colors[i]);
                list.add(rootView);
            }
        }

        if (mAdapter != null) {
            mAdapter.setItemValues(list);
        }
    }

    private void setNotFirstIn() {
        SharedPreferences sp = getSharedPreferences(Constants.SHARED_PREFERENCES_FILE_NAME, Context.MODE_PRIVATE);
        if (sp == null) {
            return;
        }

        SharedPreferences.Editor editor = sp.edit();
        if (editor == null) {
            return;
        }
        editor.putBoolean(Constants.SP_FIELD_FIRST_IN, false);
        editor.commit();
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {}

    @Override
    public void onPageScrollStateChanged(int i) {}

    @Override
    public void onPageSelected(int i) {
        if (i == Constants.WIZARD_PAGE_SIZE - 1) {
            mExperBtn.setVisibility(View.VISIBLE);
        } else {
            mExperBtn.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View view) {
        setNotFirstIn();

        Intent intent = new Intent(mContext, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
