package com.leap.avatar.presentation.test;

import com.leap.avatar.R;
import com.leap.avatar.databinding.ActivityTestBinding;
import com.leap.avatar.presentation.base.BaseActivity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

/**
 * 主界面
 * <p>
 * </> Created by weiyaling on 2017/3/7.
 */

public class TestActivity extends BaseActivity {
  private ActivityTestBinding binding;

  @Override
  protected void initComponent() {
    binding = DataBindingUtil.setContentView(this, R.layout.activity_test);
    binding.setPresenter(new Presenter());
  }

  @Override
  protected void loadData(Bundle savedInstanceState) {

  }

  public class Presenter {

    public void onBack() {
      finish();
    }

    public void onFrame() {
      startActivity(FrameActivity.class);
    }

    public void onFunction() {
      startActivity(FunctionActivity.class);
    }

    public void onRecreation() {
      startActivity(RecreationActivity.class);
    }
  }

  private void startActivity(Class c) {
    startActivity(new Intent(this, c));
  }
}
