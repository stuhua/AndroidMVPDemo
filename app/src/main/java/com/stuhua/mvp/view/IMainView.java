package com.stuhua.mvp.view;

import com.stuhua.mvp.model.UserModelBean;
import com.stuhua.mvp.model.ZhihuContentJson;

/**
 * Created by llh on 2016/9/1.
 */
public interface IMainView {
  void showData(ZhihuContentJson json);

  void showProgress();

  void dismissProgress();
}
