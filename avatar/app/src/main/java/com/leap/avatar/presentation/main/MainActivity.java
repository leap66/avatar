package com.leap.avatar.presentation.main;

import com.leap.avatar.R;
import com.leap.avatar.databinding.ActivityMainBinding;
import com.leap.avatar.presentation.base.BaseActivity;
import com.leap.avatar.presentation.test.TestActivity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

/**
 * 主界面
 * <p>
 * </> Created by weiyaling on 2017/3/7.
 */

public class MainActivity extends BaseActivity {
    private ActivityMainBinding binding;

    @Override
    protected void initComponent() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setPresenter(new Presenter());
    }

    @Override
    protected void loadData(Bundle savedInstanceState) {

    }

    public class Presenter {

        public void onBack() {
            finish();
        }

        public void onTest() {
            startActivity(TestActivity.class);
        }
    }

    private void startActivity(Class c) {
        startActivity(new Intent(this, c));
    }
}
