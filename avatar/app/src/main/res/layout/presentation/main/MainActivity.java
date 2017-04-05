package com.leap.mini.presentation.main;

import com.leap.mini.databinding.ActivityMainBinding;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

/**
 * 主界面
 * <p>
 * </> Created by weiyaling on 2017/3/7.
 */

public class MainActivity extends com.leap.mini.presentation.base.BaseActivity {
  private ActivityMainBinding binding;

  @Override
  protected void initComponent() {
    binding = DataBindingUtil.setContentView(this, com.leap.mini.R.layout.activity_main);
    binding.setPresenter(new Presenter());
  }

  @Override
  protected void loadData(Bundle savedInstanceState) {

  }

  public class Presenter {
    public void onBack() {
      finish();
    }
  }
}
