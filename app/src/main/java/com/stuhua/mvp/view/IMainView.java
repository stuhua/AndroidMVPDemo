package com.stuhua.mvp.view;

import com.stuhua.mvp.model.UserModelBean;

/**
 * Created by llh on 2016/9/1.
 */
public interface IMainView {
  void showData(UserModelBean bean);

  void showProgress();

  void dismissProgress();
}
