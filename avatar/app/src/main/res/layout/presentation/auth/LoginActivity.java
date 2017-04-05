package com.leap.mini.presentation.auth;

import com.leap.mini.cmp.StorageMgr;
import com.leap.mini.databinding.ActivityAuthLoginBinding;
import com.leap.mini.interactor.api_interface.auth.usecase.LoginCase;
import com.leap.mini.interactor.model.auth.BLoginResult;
import com.leap.mini.interactor.network.subscriber.PureSubscriber;
import com.leap.mini.interactor.network.subscriber.Response;
import com.leap.mini.presentation.main.MainActivity;
import com.leap.mini.util.DialogUtil;
import com.leap.mini.util.IsEmpty;
import com.leap.mini.util.ToastUtil;
import com.leap.mini.widget.cleartextfield.validator.FieldValidateError;
import com.mini.leap.TestStore;

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

public class LoginActivity extends com.leap.mini.presentation.base.BaseActivity {
  private ActivityAuthLoginBinding binding;
  private String phone;

  /**
   * 初始化控件
   */
  protected void initComponent() {
    binding = DataBindingUtil.setContentView(this, com.leap.mini.R.layout.activity_auth_login);
    binding.setPresenter(new Presenter());
  }

  /**
   * 加载数据
   */
  protected void loadData(Bundle savedInstanceState) {
    phone = getIntent().getStringExtra("mobile");
    if (!IsEmpty.string(phone)) {
      binding.phoneEt.setText(phone);
      binding.phoneEt.getClearEditText().setSelection(phone.length());
      return;
    }
    phone = StorageMgr.get("phone", StorageMgr.LEVEL_GLOBAL);
    if (!IsEmpty.string(phone)) {
      binding.phoneEt.setText(phone);
      binding.phoneEt.getClearEditText().setSelection(phone.length());
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
      new LoginCase(com.leap.mini.presentation.auth.LoginActivity.this, phone, password)
          .execute(new PureSubscriber<BLoginResult>() {
            /**
             * 失败
             */
            @Override
            public void onFailure(String errorMsg, Response<BLoginResult> data) {
              DialogUtil.showError(com.leap.mini.presentation.auth.LoginActivity.this, errorMsg).show();
            }

            /**
             * 成功
             */
            @Override
            public void onSuccess(Response<BLoginResult> response) {
              StorageMgr.set("phone", phone, StorageMgr.LEVEL_GLOBAL);
              Intent intent = new Intent(com.leap.mini.presentation.auth.LoginActivity.this, MainActivity.class);
              startActivity(intent);
            }
          });
    }

    /**
     * 忘记密码
     */
    public void onForgetPassword() {
      TestStore.print();
      new ResetPwdDialog(com.leap.mini.presentation.auth.LoginActivity.this, binding.phoneEt.getText().toString().trim()).show();
    }

    /**
     * 注册
     */
    public void onRegister() {
      Intent intent = new Intent(com.leap.mini.presentation.auth.LoginActivity.this, com.leap.mini.presentation.auth.RegisterActivity.class);// RegisterActivity
      startActivity(intent);
    }
  }
}
