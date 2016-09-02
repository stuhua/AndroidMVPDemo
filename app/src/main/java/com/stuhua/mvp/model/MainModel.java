package com.stuhua.mvp.model;

import com.stuhua.mvp.presenter.IMainPresenter;
import com.stuhua.retrofit.ApiStores;
import com.stuhua.retrofit.AppClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by llh on 2016/9/1.
 */
public class MainModel {
  IMainPresenter iMainPresenter;

  public MainModel(IMainPresenter iMainPresenter) {
    this.iMainPresenter = iMainPresenter;
  }

  public void loadData() {
    ApiStores apiStores = AppClient.retrofit().create(ApiStores.class);
//    Observable<WeatherJson> observable = apiStores.getWeather("101010100");
    Observable<ZhiHuJson> observable = apiStores.getContent();
    observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<ZhiHuJson>() {
      @Override
      public void onCompleted() {
      }

      @Override
      public void onError(Throwable e) {
        iMainPresenter.loadDataFailure();
      }

      @Override
      public void onNext(ZhiHuJson zhiHuJson) {
        UserModelBean bean = new UserModelBean(zhiHuJson.getPosts().get(0).getDate(), zhiHuJson.getPosts().get(0).getCount(), zhiHuJson.getPosts().get(0).getExcerpt());
        iMainPresenter.loadDataSuccess(bean);
      }
    });

  }
}
