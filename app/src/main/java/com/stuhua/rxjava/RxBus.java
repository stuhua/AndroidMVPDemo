package com.stuhua.rxjava;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Created by llh on 2016/9/2.
 */
public class RxBus {
  private static volatile RxBus mRxBus;
  private Subject<Object, Object> mRxBusObserverable = new SerializedSubject<>(PublishSubject.create());

  public static RxBus getInstance() {
    if (mRxBus == null) {
      synchronized (RxBus.class) {
        if (mRxBus == null) {
          mRxBus = new RxBus();
        }
      }
    }
    return mRxBus;
  }

  public void post(Object o) {
    mRxBusObserverable.onNext(o);
  }

  public Observable<Object> toObserverable() {
    return mRxBusObserverable;
  }

  //判断是否订阅者
  public boolean hasObservers() {
    return mRxBusObserverable.hasObservers();
  }
}
