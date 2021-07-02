package com.lem.nicetools.baasdemo;

import android.view.LayoutInflater;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.lem.nicetools.baasdemo.base.BaseActivity;
import com.lem.nicetools.baasdemo.databinding.ActivityModifyPasswdBinding;

public class ModifyPasswdActivity extends BaseActivity {

  private ActivityModifyPasswdBinding binding;

  @Override protected View getBindView() {
    binding = ActivityModifyPasswdBinding.inflate(LayoutInflater.from(this));
    return binding.getRoot();
  }

  @Override protected void init() {

  }
}