package com.leap.avatar.presentation.auth;

import com.leap.avatar.R;
import com.leap.avatar.net.auth.usecase.LoginCase;
import com.leap.avatar.databinding.ActivityAuthLoginBinding;
import com.leap.avatar.model.auth.BLoginResult;
import com.leap.avatar.presentation.base.BaseActivity;
import com.leap.avatar.presentation.main.MainActivity;
import com.leap.mini.mgr.StorageMgr;
import com.leap.mini.net.PureSubscriber;
import com.leap.mini.net.network.subscriber.Response;
import com.leap.mini.util.DialogUtil;
import com.leap.mini.util.IsEmpty;
import com.leap.mini.util.ToastUtil;
import com.leap.mini.widget.cleartextfield.validator.FieldValidateError;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

/**
 * 登录界面
 * <p>
 * Created by weiyaling on 2016/12/1.
 */

public class LoginActivity extends BaseActivity {
  private ActivityAuthLoginBinding binding;
  private String phone;

  /**
   * 初始化控件
   */
  protected void initComponent() {
    binding = DataBindingUtil.setContentView(this, R.layout.activity_auth_login);
    binding.setPresenter(new Presenter());
  }

  /**
   * 加载数据
   */
  protected void loadData(Bundle savedInstanceState) {
    phone = getIntent().getStringExtra("mobile");
    if (!IsEmpty.string(phone)) {
      binding.phoneEt.setText(phone);
      binding.phoneEt.setSelection(phone.length());
      return;
    }
    phone = StorageMgr.get("phone", StorageMgr.LEVEL_GLOBAL);
    if (!IsEmpty.string(phone)) {
      binding.phoneEt.setText(phone);
      binding.phoneEt.setSelection(phone.length());
    }
  }

  @Override
  protected void createEventHandlers() {
    binding.passwordEt.setOnKeyListener(new View.OnKeyListener() {
      @Override
      public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
        if (keyCode == KeyEvent.KEYCODE_ENTER && keyEvent.getAction() == KeyEvent.ACTION_UP) {
          new Presenter().onLogin();
          return true;
        }
        return false;
      }
    });
  }

  /**
   * 判断数据是否有效
   */
  protected boolean isValid() {
    FieldValidateError phoneError = binding.phoneEt.validateEditText();
    FieldValidateError passwordError = binding.passwordEt.validateEditText();
    if (!IsEmpty.object(phoneError)) {
      ToastUtil.toastFailure(this, phoneError.getErrorMessage());
      return false;
    } else if (!IsEmpty.object(passwordError)) {
      ToastUtil.toastFailure(this, passwordError.getErrorMessage());
      return false;
    } else {
      return true;
    }
  }

  /**
   * 主界面Presenter
   */
  public class Presenter {

    /**
     * 登录
     */
    public void onLogin() {
      if (!isValid()) {
        return;
      }
      phone = binding.phoneEt.getText().toString().trim();
      String password = binding.passwordEt.getText().toString();
      new LoginCase(LoginActivity.this, phone, password)
          .execute(new PureSubscriber<BLoginResult>() {
            /**
             * 失败
             */
            @Override
            public void onFailure(String errorMsg, Response<BLoginResult> data) {
              DialogUtil.showError(LoginActivity.this, errorMsg).show();
            }

            /**
             * 成功
             */
            @Override
            public void onSuccess(Response<BLoginResult> response) {
              StorageMgr.set("phone", phone, StorageMgr.LEVEL_GLOBAL);
              Intent intent = new Intent(LoginActivity.this, MainActivity.class);
              startActivity(intent);
            }
          });
    }

    /**
     * 忘记密码
     */
    public void onForgetPassword() {
      new ResetPwdDialog(LoginActivity.this, binding.phoneEt.getText().toString().trim()).show();
    }

    /**
     * 注册
     */
    public void onRegister() {
      Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);// RegisterActivity
      startActivity(intent);
    }
  }
}
