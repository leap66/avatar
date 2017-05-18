package com.leap.avatar.presentation.function;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.github.markzhai.recyclerview.BaseViewAdapter;
import com.github.markzhai.recyclerview.SingleTypeAdapter;
import com.leap.avatar.R;
import com.leap.avatar.databinding.ActivityThrottleBinding;
import com.leap.avatar.model.entity.BRegion;
import com.leap.avatar.model.shop.BShop;
import com.leap.avatar.model.shop.BShopAddress;
import com.leap.avatar.presentation.base.BaseActivity;
import com.leap.mini.util.ThrottleUtil;
import com.leap.mini.util.ToastUtil;
import com.leap.mini.widget.SearchBar;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import rx.functions.Action1;

/**
 * 防重点击测试界面
 * <p>
 * </> Created by weiyaling on 2017/5/2.
 */

public class ThrottleActivity extends BaseActivity {
  private ActivityThrottleBinding binding;
  private List<BShop> stringList;
  private int i;
  private SingleTypeAdapter adapter;
  private Context context;

  @Override
  protected void initComponent() {
    binding = DataBindingUtil.setContentView(this, R.layout.activity_throttle);
    binding.setPresenter(new Presenter());
    context = this;
    BShop shop = new BShop();
    shop.setName("");
    BShopAddress shopAddress = new BShopAddress();
    shopAddress.setCity(new BRegion("gfahgah", "109346jsfh"));
    shop.setShopAddress(shopAddress);
    binding.setShop(shop);
    stringList = new ArrayList<>();
    loadRecycleView();
  }

  @Override
  protected void loadData(Bundle savedInstanceState) {
  }

  @Override
  protected void createEventHandlers() {
    ThrottleUtil.clicks(binding.toastTv).subscribe(new Action1<Void>() {
      @Override
      public void call(Void aVoid) {
        ToastUtil.showSuccess(context, getString(R.string.main_test_test));
      }
    });
    binding.searchBar.setOnSearchListener(new SearchBar.OnSearchListener<View, String>() {
      @Override
      public void onSearch(View view, String data) {
        queryData();
      }
    });
  }

  private void queryData() {
    i++;
    stringList.clear();
    for (int j = 0; j < i; j++) {
      BShop shop = new BShop();
      shop.setName("");
      BShopAddress shopAddress = new BShopAddress();
      shopAddress.setCity(new BRegion("gfahgah", UUID.randomUUID().toString() + i));
      shop.setShopAddress(shopAddress);
      stringList.add(shop);
    }
    adapter.set(stringList);
  }

  private void loadRecycleView() {
    adapter = new SingleTypeAdapter(context, R.layout.item_throttle);
    adapter.setPresenter(new Presenter());
    binding.setAdapter(adapter);
  }

  public class Presenter implements BaseViewAdapter.Presenter {

    public void onBack() {
      finish();
    }

    public void onToast(BShop shop) {
      ToastUtil.showSuccess(context, shop.getShopAddress().getCity().getText());
    }

    public void onItem(BShop shop) {
      ToastUtil.showSuccess(context, shop.getShopAddress().getCity().getText());
    }
  }
}
