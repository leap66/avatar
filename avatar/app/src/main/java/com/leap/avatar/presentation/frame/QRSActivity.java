package com.leap.avatar.presentation.frame;

import java.util.List;

import com.leap.avatar.R;
import com.leap.avatar.databinding.ActivityTestTestBinding;
import com.leap.avatar.presentation.base.BaseActivity;
import com.leap.mini.util.IsEmpty;
import com.leap.mini.util.ToastUtil;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * 主界面
 * <p>
 * </> Created by weiyaling on 2017/3/7.
 */

public class QRSActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks {
  private static final int SCANNER_REQUEST_CODE = 1;
  private ActivityTestTestBinding binding;

  @Override
  protected void initComponent() {
    binding = DataBindingUtil.setContentView(this, R.layout.activity_test_test);
    binding.setPresenter(new Presenter());
  }

  @Override
  protected void loadData(Bundle savedInstanceState) {

  }

  @Override
  public void onPermissionsGranted(int requestCode, List<String> perms) {
    if (requestCode == QRScannerActivity.RC_CAMERA) {
      Intent intent = new Intent(QRSActivity.this, QRScannerActivity.class);
      startActivityForResult(intent, SCANNER_REQUEST_CODE);
    }
  }

  @Override
  public void onPermissionsDenied(int requestCode, List<String> perms) {
    if (requestCode == QRScannerActivity.RC_CAMERA) {
      ToastUtil.showFailure(QRSActivity.this, R.string.permissions_camera);
    }
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
      @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (resultCode == Activity.RESULT_OK && data != null) {
      if (requestCode == SCANNER_REQUEST_CODE) {
        String result = data.getStringExtra(QRScannerActivity.KEY_RESULT);
        if (!IsEmpty.string(result)) {
          ToastUtil.showSuccess(this, result);
        }
      }
    }
  }

  public class Presenter {

    public void onBack() {
      finish();
    }

    /**
     * 二维码扫描
     */
    @AfterPermissionGranted(QRScannerActivity.RC_CAMERA)
    public void onTest() {
      String[] permission = {
          Manifest.permission.CAMERA };
      if (EasyPermissions.hasPermissions(QRSActivity.this, permission)) {
        // Already have permission, do the thing
        Intent intent = new Intent(QRSActivity.this, QRScannerActivity.class);
        startActivityForResult(intent, SCANNER_REQUEST_CODE);
      } else {
        // Do not have permissions, request them now
        EasyPermissions.requestPermissions(QRSActivity.this,
            getString(R.string.permissions_camera), QRScannerActivity.RC_CAMERA, permission);
      }
    }
  }
}
