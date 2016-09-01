package com.stuhua.mvp.presenter;

import com.stuhua.mvp.model.UserModelBean;

/**
 * Created by llh on 2016/9/1.
 */
public interface IMainPresenter {
  void loadDataSuccess(UserModelBean bean);
  void loadDataFailure();
}
