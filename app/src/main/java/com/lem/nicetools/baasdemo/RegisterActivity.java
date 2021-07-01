package com.lem.nicetools.baasdemo;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.lem.nicetools.baasdemo.base.BaseActivity;
import com.lem.nicetools.baasdemo.databinding.ActivityRegisterBinding;
import com.lem.nicetools.baasdemo.sdk.SDKClient;
import com.lem.nicetools.baasdemo.sdk.bean.RegUserInfo;
import com.lem.nicetools.baasdemo.sdk.bean.Result;
import com.lem.nicetools.baasdemo.sdk.bean.Token;
import com.lem.nicetools.baasdemo.util.SnackbarUtil;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RegisterActivity extends BaseActivity {

  private ActivityRegisterBinding binding;

  @Override protected View getBindView() {
    binding = ActivityRegisterBinding.inflate(LayoutInflater.from(this));
    return binding.getRoot();
  }

  @Override protected void init() {

    addEvent();
  }

  private void addEvent() {
    binding.btnRegister.setOnClickListener(view -> {
      String username = binding.etUsername.getText().toString();
      String passwd = binding.etPassword.getText().toString();
      String passwdConfirm = binding.etPasswordConfirm.getText().toString();

      if (TextUtils.isEmpty(username)) {
        binding.tilUsername.setError("请输入用户名");
        return;
      }
      if (TextUtils.isEmpty(passwd)) {
        binding.etPassword.setError("请输入密码");
        return;
      }
      if (TextUtils.isEmpty(passwdConfirm)) {
        binding.etPasswordConfirm.setError("请输入确认密码");
        return;
      }
      if (!passwd.equals(passwdConfirm)) {
        binding.etPassword.setError("两次输入密码不一致");
        return;
      }
      SDKClient.getInstance().getDottoolsService()
          .register(new RegUserInfo(1, username, null, null, 1, passwd))
          .subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe(obj -> {
            if (obj.getCode() == 0) {
              Toast.makeText(this, "注册成功，请登录", Toast.LENGTH_SHORT).show();
              finish();
            } else {
              SnackbarUtil.showShort(binding.btnRegister, obj.getMsg());
            }
          }, throwable -> SnackbarUtil.showShort(binding.btnRegister, "注册失败"));
    });
  }
}