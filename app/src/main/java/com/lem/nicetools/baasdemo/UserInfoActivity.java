package com.lem.nicetools.baasdemo;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.lem.nicetools.baasdemo.base.BaseActivity;
import com.lem.nicetools.baasdemo.bean.UserExtra;
import com.lem.nicetools.baasdemo.databinding.ActivityUserInfoBinding;
import com.lem.nicetools.baasdemo.sdk.SDKClient;
import com.lem.nicetools.baasdemo.sdk.bean.Result;
import com.lem.nicetools.baasdemo.util.SnackbarUtil;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class UserInfoActivity extends BaseActivity {

  private ActivityUserInfoBinding binding;
  private Gson gson = new Gson();

  @Override protected View getBindView() {
    binding = ActivityUserInfoBinding.inflate(LayoutInflater.from(this));
    return binding.getRoot();
  }

  @Override protected void init() {
    setBackToolBar(binding.toolbarLayout.toolbar, "用户信息");
    getUserExtra();

    binding.etUserInfoInput.setText(gson.toJson(new UserExtra(12, 100, "测试Tag")));

    binding.btnLogout.setOnClickListener(view -> {
      SDKClient.getInstance().getDottoolsService()
          .logout()
          .subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe(objectResult -> {
            Toast.makeText(this, "登出成功", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
          }, throwable -> {
            Toast.makeText(this, "登出异常", Toast.LENGTH_SHORT).show();
          });
    });
    binding.btnNewpasswd.setOnClickListener(view -> {
      startActivity(new Intent(this, ModifyPasswdActivity.class));
    });
    binding.btnSend.setOnClickListener(view -> {
      UserExtra userExtra = null;

      try {
        userExtra = gson.fromJson(binding.etUserInfoInput.getText().toString(), UserExtra.class);
      } catch (Exception e) {
        e.printStackTrace();
        Toast.makeText(this, "Json解析异常请检查json格式", Toast.LENGTH_LONG).show();
        return;
      }

      SDKClient.getInstance().getDottoolsService()
          .postUserExtra(userExtra)
          .subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe(stringResult -> {
            if (stringResult.getCode() == 0) {
              SnackbarUtil.showShort(binding.getRoot(), "发布成功");
              getUserExtra();
            } else {
              SnackbarUtil.showShort(binding.getRoot(), stringResult.getMsg());
            }
          }, throwable -> {
            SnackbarUtil.showShort(binding.getRoot(), "发布失败");
          });
    });
  }

  public void getUserExtra() {
    SDKClient.getInstance().getDottoolsService()
        .getUserExtra()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(userExtraResult -> {
          if (userExtraResult.getCode() == 0) {
            userExtraResult.getData();
            binding.tvUserInfo.setText(gson.toJson(userExtraResult));
          } else {
            binding.tvUserInfo.setText(userExtraResult.getMsg());
          }
        }, throwable -> {
          binding.tvUserInfo.setText("获取异常");
        });
  }
}