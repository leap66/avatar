package com.leap.mini.util;

import com.leap.mini.R;

import android.content.Context;
import android.widget.Toast;

/**
 * Toast 格式化工具
 * <p>
 * </> Created by weiyaling on 2017/3/7.
 */

public class ToastUtil {

  public static void toastHint(Context context, int stringResId) {
    String s = context.getResources().getString(stringResId);
    toast(context, s, 0);
  }

  public static void toastHint(Context context, String text) {
    toast(context, text, 0);
  }

  public static void toastFailure(Context context, int stringResId) {
    String text = context.getResources().getString(stringResId);
    toast(context, text, R.mipmap.ic_toast_warning);
  }

  public static void toastFailure(Context context, String text) {
    toast(context, text, R.mipmap.ic_toast_warning);
  }

  public static void toastSuccess(Context context, String text) {
    toast(context, text, R.mipmap.ic_toast_success);
  }

  public static void toastSuccess(Context context, int stringResId) {
    String text = context.getResources().getString(stringResId);
    toast(context, text, R.mipmap.ic_toast_success);
  }

  private static void toast(Context context, String text, int imageResource) {
    Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    // ItemToastBinding binding =
    // DataBindingUtil.inflate(LayoutInflater.from(context),
    // R.layout.item_toast, null, false);
    // Toast toast = new Toast(context);
    // toast.setDuration(Toast.LENGTH_SHORT);
    // toast.setGravity(Gravity.CENTER, 0, 0);
    // if (0 == imageResource) {
    // binding.imageIv.setVisibility(View.GONE);
    // }
    // binding.imageIv.setImageResource(imageResource);
    // binding.titleTv.setText(text);
    // binding.titleTv.setMaxWidth(Resources.getSystem().getDisplayMetrics().widthPixels
    // * 3 / 5);
    // toast.setView(binding.getRoot());
    // toast.show();
  }
}
