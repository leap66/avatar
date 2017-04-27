package com.leap.avatar.presentation.frame;

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
import com.leap.mini.util.ToastUtil;
import com.leap.mini.widget.SearchView;
import com.leap.mini.widget.pullrefresh.base.layout.BaseFooterView;
import com.leap.mini.widget.pullrefresh.base.layout.BaseHeaderView;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

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
    binding = DataBindingUtil.setContentView(this, R.layout.activity_test_pull);
    binding.setPresenter(new Presenter());
    stringList = new ArrayList<>();
    loadRecyclerView();
  }

  @Override
  protected void createEventHandlers() {
    binding.refreshLayout.setOnRefreshListener(new BaseHeaderView.OnRefreshListener() {
      @Override
      public void onRefresh(BaseHeaderView baseHeaderView) {
        queryData(true);
      }
    });
    binding.refreshLayout.setOnLoadListener(new BaseFooterView.OnLoadListener() {
      @Override
      public void onLoad(BaseFooterView baseFooterView) {
        queryData(false);
      }
    });
    binding.searchView.setSearchListener(new SearchView.OnSearchListener<View, String>() {
      @Override
      public void onSearch(View view, String data) {
        binding.searchView.setHint(binding.searchView.getText());
        binding.refreshLayout.startRefresh();
      }
    });
  }

  @Override
  protected void loadData(Bundle savedInstanceState) {

  }

  public class Presenter implements BaseViewAdapter.Presenter {

    public void onBack() {
      finish();
    }

    public void onItem(String item) {
      ToastUtil.showSuccess(PullActivity.this, item);
    }
  }

  private void queryData(final boolean isRefresh) {
    new SendSmsCase("1390000000").execute(new PureSubscriber() {
      @Override
      public void onFailure(String errorMsg, Response response) {
        if (isRefresh) {
          stringList.clear();
        }
        adapter.clear();
        for (int i = 0; i < 5; i++) {
          stringList.add(UUID.randomUUID().toString());
        }
        adapter.addAll(stringList, VIEW_TYPE_LIST);
        binding.refreshLayout.stopLoad(true);
      }

      @Override
      public void onSuccess(Response response) {
        if (isRefresh) {
          stringList.clear();
        }
        adapter.clear();
        for (int i = 0; i < 5; i++) {
          stringList.add(UUID.randomUUID().toString());
        }
        adapter.addAll(stringList, VIEW_TYPE_LIST);
        binding.refreshLayout.stopLoad(true);
      }
    });
  }

  private void loadRecyclerView() {
    adapter = new MultiTypeAdapter(this);
    adapter.addViewTypeToLayoutMap(VIEW_TYPE_LIST, R.layout.item_pull);
    adapter.setPresenter(new Presenter());
    binding.setAdapter(adapter);
  }
}
