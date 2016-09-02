package com.stuhua.mvp.ui;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.stuhua.mvp.R;
import com.stuhua.mvp.model.UserModelBean;
import com.stuhua.mvp.presenter.MainPresenter;
import com.stuhua.mvp.view.IMainView;
import com.stuhua.rxjava.RxBus;

public class MainActivity extends AppCompatActivity implements IMainView {
  private TextView mText;
  private Button mbtn;
  private MainPresenter mMainPresenter;
  private ProgressBar mProgressBar;
  private String TAG = "MainActivity:";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    initView();
    mbtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent intent=new Intent(MainActivity.this,RxBusActivity.class);
        startActivity(intent);
      }
    });
  }

  @Override
  protected void onDestroy() {
    mMainPresenter.detachView();
    super.onDestroy();
  }

  private void initView() {
    mText = getViewById(R.id.tv_text);
    mbtn = getViewById(R.id.button);
    mProgressBar = getViewById(R.id.progressBar);
    mMainPresenter = new MainPresenter(this);
    mMainPresenter.loadData();
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
//    Toast.makeText(this,"请检查网络或代码！",Toast.LENGTH_SHORT).show();
    Log.v(TAG, "-----dismissProgress----->");
  }


}
