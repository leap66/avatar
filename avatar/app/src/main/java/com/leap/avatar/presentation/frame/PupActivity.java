package com.leap.avatar.presentation.frame;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.github.markzhai.recyclerview.BaseViewAdapter;
import com.github.markzhai.recyclerview.MultiTypeAdapter;
import com.github.markzhai.recyclerview.SingleTypeAdapter;
import com.leap.avatar.R;
import com.leap.avatar.databinding.ActivityTestPupBinding;
import com.leap.avatar.databinding.ItemRcvBinding;
import com.leap.avatar.presentation.base.BaseActivity;
import com.leap.mini.util.ToastUtil;

import android.databinding.DataBindingUtil;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

/**
 * PupWindow界面
 * <p>
 * </> Created by weiyaling on 2017/3/7.
 */

public class PupActivity extends BaseActivity {
  private ActivityTestPupBinding binding;
  private List<String> stringList;
  private MultiTypeAdapter adapter;
  private PopupWindow popupWindow;

  @Override
  protected void initComponent() {
    binding = DataBindingUtil.setContentView(this, R.layout.activity_test_pup);
    binding.setPresenter(new Presenter());
    stringList = new ArrayList<>();
    stringList.add("我是一条测试数据");
    for (int i = 0; i < 20; i++) {
      stringList.add(UUID.randomUUID().toString());
    }
  }

  @Override
  protected void createEventHandlers() {
  }

  @Override
  protected void loadData(Bundle savedInstanceState) {

  }

  public class Presenter implements BaseViewAdapter.Presenter {

    public void onBack() {
      finish();
    }

    public void onPup() {
      showPup();
    }

    public void onItem(String string) {
      ToastUtil.showSuccess(PupActivity.this, string);
    }
  }

  private void showPup() {
    ItemRcvBinding rcvBinding = DataBindingUtil.inflate(LayoutInflater.from(this),
        R.layout.item_rcv, null, false);
    SingleTypeAdapter adapter = new SingleTypeAdapter(this, R.layout.item_pup);
    adapter.set(stringList);
    adapter.setPresenter(new Presenter());
    rcvBinding.setAdapter(adapter);
    adapter.notifyDataSetChanged();
    int anchorLoc[] = new int[2];
    binding.titleLl.getLocationOnScreen(anchorLoc);
    popupWindow = new PopupWindow(rcvBinding.getRoot(), LinearLayout.LayoutParams.MATCH_PARENT,
        binding.getRoot().getHeight() - binding.titleLl.getHeight(), true);
    popupWindow.setBackgroundDrawable(new AnimationDrawable());
    popupWindow.showAtLocation(binding.getRoot(), Gravity.NO_GRAVITY, 0,
        anchorLoc[1] + binding.titleLl.getHeight());
  }
}
