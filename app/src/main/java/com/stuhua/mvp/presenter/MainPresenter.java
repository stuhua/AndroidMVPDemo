package com.stuhua.mvp.presenter;

import com.stuhua.mvp.model.MainModel;
import com.stuhua.mvp.model.UserModelBean;
import com.stuhua.mvp.view.IMainView;

/**
 * Created by llh on 2016/9/1.
 */
public class MainPresenter implements Presenter<IMainView>, IMainPresenter {
  IMainView mMainView;
  private MainModel mMainModel;

  public MainPresenter(IMainView mMainView) {
    attachView(mMainView);
    mMainModel = new MainModel(this);
  }

  @Override
  public void loadDataSuccess(UserModelBean bean) {
    mMainView.dismissProgress();
    mMainView.showData(bean);
  }

  @Override
  public void loadDataFailure() {
    mMainView.dismissProgress();
  }

  @Override
  public void attachView(IMainView view) {
    this.mMainView = view;
  }

  @Override
  public void detachView() {
    this.mMainView = null;
  }

  public void loadData() {
    mMainModel.loadData();
  }
}
