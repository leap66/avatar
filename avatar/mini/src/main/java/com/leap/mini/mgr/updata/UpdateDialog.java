package com.leap.mini.mgr.updata;

import com.leap.mini.R;
import com.leap.mini.SystemUtils;
import com.leap.mini.model.UpdateModel;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class UpdateDialog extends Dialog {

  private View lineView;
  private TextView tvCancel;
  private TextView tvDownload;
  private TextView tvVersion;
  private TextView tvContent;

  private UpdateModel model;
  private Context mContext;

  private IUpdateListener listener;
  private String filePath;

  public UpdateDialog(Context context) {
    super(context, R.style.FullScreenDialog);
    initView(context);
  }

  private void initView(Context context) {
    this.mContext = context;
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    setCanceledOnTouchOutside(false);
    View rootView = LayoutInflater.from(context).inflate(R.layout.dialog_update, null, false);

    RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
        SystemUtils.getScreenWidth() * 11 / 15, SystemUtils.getScreenHeight() / 2);
    setContentView(rootView, params);

    lineView = rootView.findViewById(R.id.divider_line_view);
    tvCancel = (TextView) rootView.findViewById(R.id.tv_cancel);
    tvDownload = (TextView) rootView.findViewById(R.id.tv_update);
    tvVersion = (TextView) rootView.findViewById(R.id.tv_title);
    tvContent = (TextView) rootView.findViewById(R.id.tv_content);

    registerListener();
  }

  private void registerListener() {
    tvDownload.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        ProgressDialog progressDialog = new ProgressDialog(mContext);
        progressDialog.setUpdateListener(listener);
        progressDialog.downloadApk(filePath, model);
        progressDialog.show();
        dismiss();
      }
    });

    tvCancel.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        if (listener != null) {
          listener.onCancel(IUpdateListener.UPDATE_CODE_IGNORE);
        }

        dismiss();
      }
    });
  }

  public void setUpdateListener(IUpdateListener listener) {
    this.listener = listener;
  }

  public void setFilePath(String filePath) {
    this.filePath = filePath;
  }

  public void init(UpdateModel model) {
    this.model = model;
    if (model.updateMode.equals(UpdataUtil.UPDATE_MODE_BLOCK)) {
      tvCancel.setVisibility(View.GONE);
      lineView.setVisibility(View.GONE);
    }

    tvVersion.setText(String.format(mContext.getResources().getString(R.string.update_version_lab),
        model.versionName));
    tvContent.setText(model.description);
  }
}
