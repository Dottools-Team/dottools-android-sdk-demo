package com.lem.nicetools.baasdemo;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.widget.Toolbar;
import com.lem.nicetools.baasdemo.base.BaseActivity;
import com.lem.nicetools.baasdemo.bean.InfoContent;
import com.lem.nicetools.baasdemo.databinding.ActivityMainBinding;
import com.lem.nicetools.baasdemo.sdk.SDKClient;
import com.lem.nicetools.baasdemo.sdk.bean.Result;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.util.List;

public class MainActivity extends BaseActivity implements  Toolbar.OnMenuItemClickListener {

  private ActivityMainBinding binding;

  @Override protected View getBindView() {
    binding = ActivityMainBinding.inflate(LayoutInflater.from(this));
    View view = binding.getRoot();
    return view;
  }

  @Override protected void init() {
    setToolBar(binding.toolbarLayout.toolbar, "起点云演示Demo");
    getSupportActionBar().setDisplayShowHomeEnabled(true);
    binding.toolbarLayout.toolbar.setOnMenuItemClickListener(this);
    getRemoteConfig();

    binding.fabAdd.setOnClickListener(view1 -> {
      startActivity(new Intent(this, AddInfoActivity.class));
    });
  }

  @Override protected void onResume() {
    super.onResume();
    getInfoList();
  }

  private void getRemoteConfig() {
    SDKClient.getInstance().getDottoolsService()
        .getRemoteConfig("notification")
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(simpleConfigResult -> {
          if (simpleConfigResult.getCode() == 0) {
            binding.tvTitle.setText(simpleConfigResult.getData().getTitle());
            binding.tvMsg.setText(simpleConfigResult.getData().getMsg());
          } else {
            binding.tvTitle.setText("获取配置失败");
            binding.tvMsg.setText(simpleConfigResult.getMsg());
          }
        }, throwable -> {
          binding.tvTitle.setText("获取配置失败");
        });
  }

  private void getInfoList() {
    SDKClient.getInstance().getDottoolsService()
        .getDatatableContent("info")
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<Result<List<InfoContent>>>() {
          @Override public void accept(Result<List<InfoContent>> listResult) throws Throwable {
            if (listResult.getCode() == 0) {
              if (listResult.getData() == null) {
                binding.tvContent.setText("空");
                return;
              }
              binding.tvContent.setText("");
              for (InfoContent datum : listResult.getData()) {
                binding.tvContent.append(datum.toString() + "\n-------------------------\n");
              }
              binding.tvContent.scrollTo(0, binding.tvContent.getHeight());
            } else {
              binding.tvContent.setText("获取失败");
            }
          }
        }, throwable -> {
          binding.tvTitle.setText("获取失败");
        });
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override public boolean onMenuItemClick(MenuItem menuItem) {
    switch (menuItem.getItemId()) {
      case R.id.action_user:
        startActivity(new Intent(this, UserInfoActivity.class));
        break;
    }
    return true;
  }

}