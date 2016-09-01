package com.stuhua.mvp.presenter;

/**
 * Created by llh on 2016/9/1.
 */
public interface Presenter<V> {
  void attachView(V view);
  void detachView();
}
