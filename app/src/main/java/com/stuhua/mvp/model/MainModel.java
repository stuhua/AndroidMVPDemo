package com.stuhua.mvp.model;

import com.stuhua.mvp.presenter.IMainPresenter;

/**
 * Created by llh on 2016/9/1.
 */
public class MainModel {
  IMainPresenter iMainPresenter;

  public MainModel(IMainPresenter iMainPresenter) {
    this.iMainPresenter = iMainPresenter;
  }

  public void loadData()  {
    UserModelBean bean=new UserModelBean("stuhua","21","man");
    iMainPresenter.loadDataSuccess(bean);
  }
}
