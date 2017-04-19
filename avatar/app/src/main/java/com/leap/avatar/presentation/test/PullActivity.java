package com.leap.avatar.presentation.test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.github.markzhai.recyclerview.BaseViewAdapter;
import com.github.markzhai.recyclerview.MultiTypeAdapter;
import com.leap.avatar.R;
import com.leap.avatar.databinding.ActivityTestPullBinding;
import com.leap.avatar.net.auth.usecase.SendSmsCase;
import com.leap.avatar.presentation.base.BaseActivity;
import com.leap.mini.net.PureSubscriber;
import com.leap.mini.net.network.subscriber.Response;
import com.leap.mini.widget.pullrefresh.base.layout.BaseFooterView;
import com.leap.mini.widget.pullrefresh.base.layout.BaseHeaderView;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

/**
 * 主界面
 * <p>
 * </> Created by weiyaling on 2017/3/7.
 */

public class PullActivity extends BaseActivity {
  private ActivityTestPullBinding binding;
  private List<String> stringList;
  private MultiTypeAdapter adapter;
  private final int VIEW_TYPE_LIST = 1;

  @Override
  protected void initComponent() {
    binding = DataBindingUtil.setContentView(this, R.layout.activity_test_frame);
    binding.setPresenter(new Presenter());
    stringList = new ArrayList<>();
    loadRecyclerView();
  }

  @Override
  protected void createEventHandlers() {
    binding.refreshLayout.setOnRefreshListener(new BaseHeaderView.OnRefreshListener() {
      @Override
      public void onRefresh(BaseHeaderView baseHeaderView) {
        queryData();
      }
    });
    binding.refreshLayout.setOnLoadListener(new BaseFooterView.OnLoadListener() {
      @Override
      public void onLoad(BaseFooterView baseFooterView) {
        queryData();
      }
    });
    binding.refreshLayout.startRefresh();
  }

  @Override
  protected void loadData(Bundle savedInstanceState) {

  }

  public class Presenter implements BaseViewAdapter.Presenter {

    public void onBack() {
      finish();
    }
  }

  private void queryData() {
    new SendSmsCase("13900000000").execute(new PureSubscriber() {
      @Override
      public void onFailure(String errorMsg, Response response) {
        for (int i = 0; i < 50; i++) {
          stringList.add(UUID.randomUUID().toString());
        }
        adapter.set(stringList, VIEW_TYPE_LIST);
        stopLoad(true);
      }

      @Override
      public void onSuccess(Response response) {
        for (int i = 0; i < 50; i++) {
          stringList.add(UUID.randomUUID().toString());
        }
        adapter.set(stringList, VIEW_TYPE_LIST);
        stopLoad(true);
      }
    });
  }

  private void loadRecyclerView() {
    adapter = new MultiTypeAdapter(this);
    adapter.add(VIEW_TYPE_LIST, R.layout.item_pull);
    adapter.setPresenter(new Presenter());
    binding.setAdapter(adapter);
  }

  /**
   * RefreshLayout 停止加载刷新
   */
  private void stopLoad(boolean isMore) {
    if (binding.refreshLayout.isRefreshing()) {
      binding.refreshLayout.stopRefresh();
    }
    if (binding.refreshLayout.isLoading()) {
      binding.refreshLayout.stopLoad();
    }
    binding.refreshLayout.hideView();
    binding.refreshLayout.setHasFooter(isMore);
  }
}
