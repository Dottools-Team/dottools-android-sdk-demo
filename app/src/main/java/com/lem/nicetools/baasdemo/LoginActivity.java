package com.lem.nicetools.baasdemo;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.snackbar.Snackbar;
import com.lem.nicetools.baasdemo.base.BaseActivity;
import com.lem.nicetools.baasdemo.databinding.ActivityLoginBinding;
import com.lem.nicetools.baasdemo.sdk.SDKClient;
import com.lem.nicetools.baasdemo.sdk.bean.Result;
import com.lem.nicetools.baasdemo.sdk.bean.Token;
import com.lem.nicetools.baasdemo.util.SnackbarUtil;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class LoginActivity extends BaseActivity {

  private ActivityLoginBinding binding;

  @Override protected View getBindView() {
    binding = ActivityLoginBinding.inflate(LayoutInflater.from(this));
    return binding.getRoot();
  }

  @Override protected void init() {
    addEvent();
  }

  private void addEvent() {

    binding.btnLogin.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        if (TextUtils.isEmpty(binding.etUsername.getText().toString())) {
          binding.tilUsername.setError("请输入用户名");
          return;
        }
        if (TextUtils.isEmpty(binding.etPassword.getText().toString())) {
          binding.etPassword.setError("请输入密码");
          return;
        }
        SDKClient.getInstance().getDottoolsService()
            .login(binding.etUsername.getText().toString(),
                binding.etPassword.getText().toString(),
                "1")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(tokenResult -> {
              if (tokenResult.getCode() == 0) {
                SnackbarUtil.showShort(binding.btnLogin, "登录成功");
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
              } else {
                SnackbarUtil.showShort(binding.btnLogin, tokenResult.getMsg());
              }
            }, throwable -> SnackbarUtil.showShort(binding.btnLogin, "登录失败"));
      }
    });

    binding.tvRegister.setOnClickListener(
        view -> startActivity(new Intent(LoginActivity.this, RegisterActivity.class)));
  }
}