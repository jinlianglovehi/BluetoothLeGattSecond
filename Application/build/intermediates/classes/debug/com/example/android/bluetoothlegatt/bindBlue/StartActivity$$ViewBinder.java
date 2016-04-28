// Generated code from Butter Knife. Do not modify!
package com.example.android.bluetoothlegatt.bindBlue;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class StartActivity$$ViewBinder<T extends com.example.android.bluetoothlegatt.bindBlue.StartActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131296261, "field 'startService' and method 'startService'");
    target.startService = finder.castView(view, 2131296261, "field 'startService'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.startService();
        }
      });
    view = finder.findRequiredView(source, 2131296262, "field 'rebackMessage' and method 'reBackData'");
    target.rebackMessage = finder.castView(view, 2131296262, "field 'rebackMessage'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.reBackData();
        }
      });
  }

  @Override public void unbind(T target) {
    target.startService = null;
    target.rebackMessage = null;
  }
}
