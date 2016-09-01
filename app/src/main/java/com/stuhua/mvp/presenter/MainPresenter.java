package com.stuhua.mvp.presenter;

import com.stuhua.mvp.model.MainModel;
import com.stuhua.mvp.model.UserModelBean;
import com.stuhua.mvp.view.MainView;

/**
 * Created by llh on 2016/9/1.
 */
public class MainPresenter implements Presenter<MainView>,IMainPresenter{
  MainView mainView;
  private MainModel mMainModel;

  public MainPresenter(MainView mainView) {
    this.mainView = mainView;
    mMainModel=new MainModel(this);
  }

  @Override
  public void loadDataSuccess(UserModelBean bean) {
    mainView.showData(bean);
  }

  @Override
  public void loadDataFailure() {
  }

  @Override
  public void attachView(MainView view) {

  }

  @Override
  public void detachView() {

  }
public void loadData(){
  mMainModel.loadData();
}
}
