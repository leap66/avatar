package com.leap.mini.mgr.updata;

import com.leap.mini.R;
import com.leap.mini.SystemUtils;
import com.leap.mini.model.UpdateModel;
import com.leap.mini.util.ToastUtil;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import pub.devrel.easypermissions.AfterPermissionGranted;

class ProgressDialog extends Dialog {
  private Context mContext;
  private ProgressBar progressBar;
  private TextView tvProgress;
  private IUpdateListener listener;

  ProgressDialog(Context context) {
    super(context, R.style.FullScreenDialog);
    this.mContext = context;
    initViews();
  }

  private void initViews() {
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    setCanceledOnTouchOutside(false);
    View rootView = LayoutInflater.from(mContext).inflate(R.layout.dialog_update_progress, null,
        false);

    RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
        SystemUtils.getScreenWidth() * 11 / 15, RelativeLayout.LayoutParams.WRAP_CONTENT);
    setContentView(rootView, params);

    progressBar = (ProgressBar) rootView.findViewById(R.id.progress_bar);
    tvProgress = (TextView) rootView.findViewById(R.id.tv_progress);
  }

  @AfterPermissionGranted(11)
  void downloadApk(String filePath, UpdateModel model) {
    final UpdateTask updateTask = new UpdateTask(mContext, model.position, progressBar, tvProgress,
        filePath);
    updateTask.setSuccessListener(new UpdateTask.OnSuccessListener<String>() {
      @Override
      public void onSuccess(String data) {
        dismiss();
        UpdataUtil.installApk(getContext(), data);
      }
    });
    updateTask.setErrorListener(new UpdateTask.OnErrorListener() {
      @Override
      public void onError(Object data) {
        dismiss();
        ToastUtil.showFailure(mContext, mContext.getString(R.string.update_failure));
        if (listener != null) {
          listener.onCancel(IUpdateListener.UPDATE_CODE_FAIL);
        }
      }
    });
    updateTask.execute(model.downloadUrl);
  }

  void setUpdateListener(IUpdateListener listener) {
    this.listener = listener;
  }

}
