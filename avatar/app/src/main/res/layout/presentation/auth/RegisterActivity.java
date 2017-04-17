package com.leap.mini.presentation.auth;

import java.util.UUID;

import com.leap.mini.databinding.ActivityAuthRegisterBinding;
import com.leap.mini.net.api_interface.auth.usecase.CheckMobileCase;
import com.leap.mini.net.api_interface.auth.usecase.RegisterCase;
import com.leap.mini.net.api_interface.auth.usecase.RegisterRequest;
import com.leap.mini.net.api_interface.auth.usecase.SendSmsCase;
import com.leap.mini.net.PureSubscriber;
import com.leap.mini.net.network.subscriber.Response;
import com.leap.mini.presentation.base.BaseActivity;
import com.leap.mini.util.DialogUtil;
import com.leap.mini.util.IsEmpty;
import com.leap.mini.util.StringUtil;
import com.leap.mini.util.ToastUtil;
import com.leap.mini.widget.cleartextfield.validator.FieldValidateError;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

/**
 * 注册界面
 * <p>
 * Created by weiyaling on 2016/12/1.
 */

public class RegisterActivity extends BaseActivity {
  private ActivityAuthRegisterBinding binding;

  /**
   * 初始化控件
   */
  protected void initComponent() {
    binding = DataBindingUtil.setContentView(this, com.leap.mini.R.layout.activity_auth_register);
    binding.setPresenter(new Presenter());
  }

  @Override
  protected void loadData(Bundle savedInstanceState) {

  }

  /**
   * 判断输入数据是否有效
   */
  protected boolean isValid() {
    FieldValidateError phoneError = binding.phoneEt.validateEditText();
    FieldValidateError nameError = binding.realNameEt.validateEditText();
    FieldValidateError passwordError = binding.passwordEt.validateEditText();
    FieldValidateError repeatPasswordError = binding.repeatPasswordEt.validateEditText();
    FieldValidateError codeError = binding.codeEt.validateEditText();
    if (!IsEmpty.object(phoneError)) {
      ToastUtil.toastFailure(this, phoneError.getErrorMessage());
      return false;
    } else if (!IsEmpty.object(nameError)) {
      ToastUtil.toastFailure(this, nameError.getErrorMessage());
      return false;
    } else if (!IsEmpty.object(passwordError)) {
      ToastUtil.toastFailure(this, passwordError.getErrorMessage());
      return false;
    } else if (!IsEmpty.object(repeatPasswordError)) {
      ToastUtil.toastFailure(this, repeatPasswordError.getErrorMessage());
      return false;
    } else if (!IsEmpty.object(codeError)) {
      ToastUtil.toastFailure(this, codeError.getErrorMessage());
      return false;
    } else if (!binding.passwordEt.getText().toString()
        .equals(binding.repeatPasswordEt.getText().toString())) {
      ToastUtil.toastFailure(this, getString(com.leap.mini.R.string.register_pwd_confirm_error));
      return false;
    } else if (!binding.agreeCb.isChecked()) {
      ToastUtil.toastFailure(this, getString(com.leap.mini.R.string.register_read_agreement));
      return false;
    } else if (!StringUtil.isMobileNO(binding.phoneEt.getText().toString().trim())) {
      ToastUtil.toastFailure(com.leap.mini.presentation.auth.RegisterActivity.this, com.leap.mini.R.string.register_phone_err_msg);
      return false;
    } else {
      return true;
    }
  }

  public RegisterRequest getParam() {
    RegisterRequest param = new RegisterRequest();
    param.setId(UUID.randomUUID().toString());
    param.setMobile(binding.phoneEt.getText().toString());
    param.setName(binding.realNameEt.getText().toString());
    param.setPassword(binding.passwordEt.getText().toString());
    return param;
  }

  /**
   * 主界面Presenter
   */
  public class Presenter {

    /**
     * 返回
     */
    public void onBack() {
      finish();
    }

    /**
     * 获取验证码
     */
    public void onGetCode() {
      final String mobile = binding.phoneEt.getText().toString().trim();
      if (StringUtil.isMobileNO(mobile)) {
        new CheckMobileCase(com.leap.mini.presentation.auth.RegisterActivity.this, mobile).execute(new PureSubscriber<Object>() {
          @Override
          public void onFailure(String errorMsg, Response<Object> response) {
            DialogUtil.showError(com.leap.mini.presentation.auth.RegisterActivity.this, errorMsg).show();
          }

          @Override
          public void onSuccess(Response<Object> response) {
            new SendSmsCase(mobile).execute(new PureSubscriber<Object>() {
              @Override
              public void onFailure(String errorMsg, Response<Object> data) {
                DialogUtil.showError(com.leap.mini.presentation.auth.RegisterActivity.this, errorMsg).show();
              }

              @Override
              public void onSuccess(Response data) {
                binding.sendCodeCt.startCount();
              }
            });

          }
        });
      } else {
        ToastUtil.toastFailure(com.leap.mini.presentation.auth.RegisterActivity.this, com.leap.mini.R.string.register_phone_err_msg);
      }
    }

    /**
     * 注册
     */
    public void onRegister() {
      if (isValid()) {
        String code = binding.codeEt.getText().toString();
        new RegisterCase(com.leap.mini.presentation.auth.RegisterActivity.this, code, getParam())
            .execute(new PureSubscriber<Object>() {
              @Override
              public void onFailure(String errorMsg, Response<Object> data) {
                DialogUtil.showError(com.leap.mini.presentation.auth.RegisterActivity.this, errorMsg).show();
              }

              @Override
              public void onSuccess(Response<Object> response) {
                Intent intent = new Intent(com.leap.mini.presentation.auth.RegisterActivity.this, LoginActivity.class);
                intent.putExtra("mobile", binding.phoneEt.getText().toString().trim());
                startActivity(intent);
                ToastUtil.toastSuccess(com.leap.mini.presentation.auth.RegisterActivity.this, com.leap.mini.R.string.register_success);
                finish();
              }
            });
      }

    }

    /**
     * 用户协议
     */
    public void onReadAgreement() {
      Intent intent = new Intent(com.leap.mini.presentation.auth.RegisterActivity.this, AgreementActivity.class);
      startActivity(intent);
    }
  }
}
