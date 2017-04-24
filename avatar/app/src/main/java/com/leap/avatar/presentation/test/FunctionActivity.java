package com.leap.avatar.presentation.test;

import com.leap.avatar.R;
import com.leap.avatar.databinding.ActivityTestFunctionBinding;
import com.leap.avatar.presentation.base.BaseActivity;
import com.leap.avatar.presentation.frame.PupActivity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

/**
 * 主界面
 * <p>
 * </> Created by weiyaling on 2017/3/7.
 */

public class FunctionActivity extends BaseActivity {
  private ActivityTestFunctionBinding binding;

  @Override
  protected void initComponent() {
    binding = DataBindingUtil.setContentView(this, R.layout.activity_test_function);
    binding.setPresenter(new Presenter());
  }

  @Override
  protected void loadData(Bundle savedInstanceState) {

  }

  public class Presenter {

    public void onBack() {
      finish();
    }

    public void onPup() {
      startActivity(PupActivity.class);
    }
  }

  private void startActivity(Class c) {
    startActivity(new Intent(this, c));
  }
}
