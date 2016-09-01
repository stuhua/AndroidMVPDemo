package com.stuhua.mvp.ui;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.stuhua.mvp.R;
import com.stuhua.mvp.model.UserModelBean;
import com.stuhua.mvp.presenter.MainPresenter;
import com.stuhua.mvp.view.IMainView;

public class MainActivity extends AppCompatActivity implements IMainView {
  private TextView mText;
  private MainPresenter mMainPresenter;
  private ProgressBar mProgressBar;
  private String TAG = "MainActivity:";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    initView();
  }

  @Override
  protected void onDestroy() {
    mMainPresenter.detachView();
    super.onDestroy();
  }

  private void initView() {
    mText = getViewById(R.id.tv_text);
    mProgressBar = getViewById(R.id.progressBar);
    mMainPresenter = new MainPresenter(this);
    //延迟2s
    new Handler().postDelayed(new Runnable() {
      @Override
      public void run() {
        mMainPresenter.loadData();
      }
    }, 2000);
  }

  public <T extends View> T getViewById(int id) {
    return (T) findViewById(id);
  }

  @Override
  public void showData(UserModelBean bean) {
    mText.setText(bean.getName() + "\n" + bean.getAge() + "\n" + bean.getSex());
  }

  @Override
  public void showProgress() {
    mProgressBar.setVisibility(View.VISIBLE);
    Log.v(TAG, "-----showProgress----->");
  }

  @Override
  public void dismissProgress() {
    mProgressBar.setVisibility(View.GONE);
    Log.v(TAG, "-----dismissProgress----->");
  }


}
