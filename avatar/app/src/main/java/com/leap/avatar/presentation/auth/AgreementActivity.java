package com.leap.avatar.presentation.auth;

import com.leap.avatar.BuildConfig;
import com.leap.avatar.R;
import com.leap.avatar.databinding.ActivityAuthAgreementBinding;
import com.leap.avatar.presentation.base.BaseActivity;

import android.annotation.SuppressLint;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * 用户协议查看界面
 * <p>
 * Created by weiyaling on 2016/12/1.
 */
@SuppressLint("SetJavaScriptEnabled")
public class AgreementActivity extends BaseActivity {
  private ActivityAuthAgreementBinding binding;

  /**
   * 初始化控件
   */
  protected void initComponent() {
    binding = DataBindingUtil.setContentView(this, R.layout.activity_auth_agreement);
    binding.setPresenter(new Presenter());
  }

  /**
   * 加载数据
   */
  protected void loadData(Bundle savedInstanceState) {
    WebSettings settings = binding.web.getSettings();
    settings.setJavaScriptEnabled(true);
    binding.web.loadUrl(BuildConfig.AGREEMENT_URL);
    binding.web.setWebChromeClient(new WebChromeClient() {
      @Override
      public void onProgressChanged(WebView view, int newProgress) {
        super.onProgressChanged(view, newProgress);
        if (newProgress == binding.pb.getMax()) {
          binding.pb.setVisibility(View.GONE);
        } else {
          binding.pb.setProgress(newProgress);
        }
      }
    });
  }

  /**
   * 主界面Presenter
   */
  public class Presenter {

    /**
     * 返回注册
     */
    public void onBack() {
      finish();
    }
  }

}
