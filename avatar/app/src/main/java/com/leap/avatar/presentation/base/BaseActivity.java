package com.leap.avatar.presentation.base;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import com.leap.mini.net.network.event.AuthEvent;
import com.leap.mini.util.ToastUtil;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * 基础Activity
 * <p>
 * </> Created by weiyaling on 2017/3/7.
 */

public abstract class BaseActivity extends AppCompatActivity {
  private boolean isAutomaticHide = false;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    initComponent();
    createEventHandlers();
    loadData(savedInstanceState);
    EventBus.getDefault().register(this);
  }

  /**
   * 初始化界面控件
   */
  protected abstract void initComponent();

  /**
   * 初次加载数据
   */
  protected abstract void loadData(Bundle savedInstanceState);

  /**
   * 界面事件响应
   */
  protected void createEventHandlers() {
  }

  /**
   * 设置软键盘是否自动隐藏
   */
  protected void setAutomaticHide(boolean b) {
    this.isAutomaticHide = b;
  }

  /**
   * 点击空白处隐藏软键盘
   */
  @Override
  public boolean dispatchTouchEvent(MotionEvent ev) {
    if (ev.getAction() == MotionEvent.ACTION_DOWN) {
      View v = getCurrentFocus();
      if (isEdt(v, ev) && isAutomaticHide) {
        InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        im.hideSoftInputFromWindow(v.getWindowToken(), 0);
      }
      return super.dispatchTouchEvent(ev);
    } else if (getWindow().superDispatchTouchEvent(ev)) {
      return true;
    }
    return onTouchEvent(ev);
  }

  /**
   * 判断当前焦点是否是输入框
   */
  public boolean isEdt(View v, MotionEvent event) {
    if (v != null && (v instanceof EditText)) {
      int[] leftTop = {
          0, 0 };
      v.getLocationInWindow(leftTop);
      int left = leftTop[0];
      int top = leftTop[1];
      int bottom = top + v.getHeight();
      int right = left + v.getWidth();
      boolean ensure = event.getX() > left && event.getX() < right && event.getY() > top
          && event.getY() < bottom;
      return !ensure;
    }
    return false;
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    EventBus.getDefault().unregister(this);
  }

  @Subscribe(threadMode = ThreadMode.MAIN)
  public void handleTokenExpired(AuthEvent event) {
    if (event.type == AuthEvent.TOKEN_EXPIRED) {
      ToastUtil.toastHint(this, com.leap.mini.R.string.network_request_err_401);
      logout();
    }
  }

  private void logout() {
    // Intent intent = new Intent(this, LoginActivity.class);
    // intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK |
    // Intent.FLAG_ACTIVITY_NEW_TASK);
    // startActivity(intent);
  }

}
