package com.leap.avatar.presentation.frame;

import java.util.List;
import java.util.concurrent.TimeUnit;

import com.jakewharton.rxbinding.view.RxView;
import com.leap.avatar.R;
import com.leap.avatar.databinding.ActivityTestFrameBinding;
import com.leap.avatar.mgr.UpdateMgr;
import com.leap.avatar.presentation.base.BaseActivity;
import com.leap.mini.mgr.logger.Logger;
import com.leap.mini.util.DialogUtil;
import com.leap.mini.util.ThrottleUtil;
import com.leap.mini.util.ToastUtil;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import rx.functions.Action1;

/**
 * 主界面
 * <p>
 * </> Created by weiyaling on 2017/3/7.
 */

public class FrameActivity extends BaseActivity {
  private ActivityTestFrameBinding binding;
  private Context context;
  private List<String> stringList;

  @Override
  protected void initComponent() {
    binding = DataBindingUtil.setContentView(this, R.layout.activity_test_frame);
    binding.setPresenter(new Presenter());
    context = this;
  }

  @Override
  protected void loadData(Bundle savedInstanceState) {

  }

  public class Presenter {

    public void onBack() {
      finish();
    }

    public void onUpData() {
      UpdateMgr.getInstance().checkTask(context);
    }

    public void onCrash() {
      stringList.add(null);
    }

    public void onLogger() {
      Logger.info("我是一个测试信息100861");
    }

    public void onDialog() {
      DialogUtil.getProgressDialog(context).show();
    }

    public void onToast(View view) {
      RxView.clicks(view).throttleFirst(5, TimeUnit.SECONDS).subscribe(new Action1<Void>() {
        @Override
        public void call(Void aVoid) {
          ToastUtil.showSuccess(context, "我是一个测试信息100861");
        }
      });
    }

    public void onPull() {
      startActivity(PullActivity.class);
    }

    public void onScanner() {
      startActivity(QRScannerActivity.class);
    }
  }

  private void startActivity(Class c) {
    startActivity(new Intent(this, c));
  }
}
