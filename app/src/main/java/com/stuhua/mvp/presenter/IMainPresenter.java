package com.stuhua.mvp.presenter;

import com.stuhua.mvp.model.UserModelBean;
import com.stuhua.mvp.model.ZhihuContentJson;

/**
 * Created by llh on 2016/9/1.
 */
public interface IMainPresenter {
  void loadDataSuccess(ZhihuContentJson zhihuContentJson);
  void loadDataFailure();
}
