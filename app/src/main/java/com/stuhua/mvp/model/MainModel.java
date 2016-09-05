package com.stuhua.mvp.model;

import android.util.Log;

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
  private String TAG="MainModel:";

  public MainModel(IMainPresenter iMainPresenter) {
    this.iMainPresenter = iMainPresenter;
  }

  public void loadData(String date) {
    ApiStores apiStores = AppClient.retrofit().create(ApiStores.class);
//    Observable<WeatherJson> observable = apiStores.getWeather("101010100");
    Observable<ZhihuContentJson> observable = apiStores.getContent(date);
    Log.d(TAG,"date:"+date);
    observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<ZhihuContentJson>() {
      @Override
      public void onCompleted() {
      }

      @Override
      public void onError(Throwable e) {
        iMainPresenter.loadDataFailure();
      }

      @Override
      public void onNext(ZhihuContentJson zhiHuContentJson) {
//        UserModelBean bean = new UserModelBean(zhiHuJson.getPosts().get(0).getDate(), zhiHuJson.getPosts().get(0).getCount(), zhiHuJson.getPosts().get(0).getExcerpt());
        iMainPresenter.loadDataSuccess(zhiHuContentJson);
      }
    });

  }
}
