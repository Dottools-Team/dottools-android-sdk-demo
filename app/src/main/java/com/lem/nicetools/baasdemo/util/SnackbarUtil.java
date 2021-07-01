package com.lem.nicetools.baasdemo.util;

import android.view.View;
import com.google.android.material.snackbar.Snackbar;

public class SnackbarUtil {

  public static void showShort(View view, String txt) {
    Snackbar.make(view, txt, Snackbar.LENGTH_SHORT).show();
  }
}
