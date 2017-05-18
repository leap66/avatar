package com.leap.avatar.presentation.recreation;

import com.leap.avatar.R;
import com.leap.avatar.databinding.ActivityTestRecreationBinding;
import com.leap.avatar.presentation.base.BaseActivity;
import com.leap.avatar.presentation.test.TestActivity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

/**
 * 娱乐主界面
 * <p>
 * </> Created by weiyaling on 2017/3/7.
 */

public class RecreationActivity extends BaseActivity {
  private ActivityTestRecreationBinding binding;

  @Override
  protected void initComponent() {
    binding = DataBindingUtil.setContentView(this, R.layout.activity_test_recreation);
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
      finish();
    }

    public void onFunction() {
      startActivity(TestActivity.class);
    }

    public void onRecreation() {
      finish();
    }
  }

  private void startActivity(Class c) {
    startActivity(new Intent(this, c));
  }
}
