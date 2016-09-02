package com.stuhua.mvp.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.stuhua.mvp.R;
import com.stuhua.mvp.model.UserModelBean;
import com.stuhua.rxjava.RxBus;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by llh on 2016/9/2.
 */
public class RxBusActivity extends AppCompatActivity {
  private CompositeSubscription mCompositeSubscription;
  private String TAG = "RxBusActivity";

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    rxBusObservers();
    rxBusPost();
  }

  private void rxBusPost() {
 /*   findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        RxBus.getInstance().post(new UserModelBean("5", "3", "2"));
      }
    });*/
  }

  public void addSubscription(Subscription subscription) {
    if (this.mCompositeSubscription == null) {
      this.mCompositeSubscription = new CompositeSubscription();
    }
    this.mCompositeSubscription.add(subscription);
  }

  private void rxBusObservers() {
    Subscription subscription = RxBus.getInstance()
      .toObserverable()
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe(new Subscriber<Object>() {
        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
        }

        @Override
        public void onNext(Object event) {
          if (event instanceof UserModelBean) {
            //do something
            Log.d(TAG, ((UserModelBean) event).getAge());
            Toast.makeText(RxBusActivity.this, ((UserModelBean) event).getName(), Toast.LENGTH_SHORT).show();
          }
        }
      });
    addSubscription(subscription);
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    Log.d("wxl", "onDestroy");
    if (this.mCompositeSubscription != null) {
      //取消注册，以避免内存泄露
      this.mCompositeSubscription.unsubscribe();
    }
  }
}
