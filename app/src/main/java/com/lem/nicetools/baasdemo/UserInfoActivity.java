package com.lem.nicetools.baasdemo;

import android.view.LayoutInflater;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.lem.nicetools.baasdemo.base.BaseActivity;
import com.lem.nicetools.baasdemo.databinding.ActivityUserInfoBinding;

public class UserInfoActivity extends BaseActivity {

  private ActivityUserInfoBinding binding;

  @Override protected View getBindView() {
    binding = ActivityUserInfoBinding.inflate(LayoutInflater.from(this));
    return binding.getRoot();
  }

  @Override protected void init() {
    setBackToolBar(binding.toolbarLayout.toolbar, "用户信息");

  }

  public void getUserExtra() {

  }
}