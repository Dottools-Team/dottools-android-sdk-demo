package com.lem.nicetools.baasdemo;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.lem.nicetools.baasdemo.base.BaseActivity;
import com.lem.nicetools.baasdemo.bean.InfoContent;
import com.lem.nicetools.baasdemo.databinding.ActivityAddInfoBinding;
import com.lem.nicetools.baasdemo.sdk.SDKClient;
import com.lem.nicetools.baasdemo.sdk.bean.DatatableBody;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class AddInfoActivity extends BaseActivity {

  private ActivityAddInfoBinding binding;

  @Override protected View getBindView() {
    binding = ActivityAddInfoBinding.inflate(LayoutInflater.from(this));
    return binding.getRoot();
  }

  @Override protected void init() {
    setBackToolBar(binding.toolbarLayout.toolbar, "添加信息");
    binding.btnPublish.setOnClickListener(view -> {
      if (TextUtils.isEmpty(binding.etTitle.getText().toString())) {
        binding.tilTitle.setError("请输入用户名");
        return;
      }
      if (TextUtils.isEmpty(binding.etContent.getText().toString())) {
        binding.tilContent.setError("请输入密码");
        return;
      }

      InfoContent infoContent = new InfoContent(binding.etTitle.getText().toString(),
          binding.etContent.getText().toString());

      SDKClient.getInstance().getDottoolsService()
          .postDatatableContent("info", new DatatableBody<InfoContent>(null, infoContent))
          .subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe(objectResult -> {
            if (objectResult.getCode() == 0) {
              Toast.makeText(this, "发布成功", Toast.LENGTH_SHORT).show();
              finish();
            } else {
              Toast.makeText(this, objectResult.getMsg(), Toast.LENGTH_SHORT).show();
            }
          }, throwable -> {
            Toast.makeText(this, "发布失败", Toast.LENGTH_SHORT).show();
          });
    });
  }
}