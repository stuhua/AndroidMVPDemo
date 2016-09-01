package com.stuhua.mvp.ui;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.stuhua.mvp.R;
import com.stuhua.mvp.model.UserModelBean;
import com.stuhua.mvp.presenter.MainPresenter;
import com.stuhua.mvp.view.MainView;

public class MainActivity extends AppCompatActivity implements MainView {
  private TextView tv_text;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    initView();
  }

  private void initView() {
    tv_text = getViewById(R.id.tv_text);
    final MainPresenter presenter=new MainPresenter(this);
    //延迟2s
    new Handler().postDelayed(new Runnable() {
      @Override
      public void run() {
        presenter.loadData();
      }
    }, 2000);
  }

  public <T extends View> T getViewById(int id) {
    return (T) findViewById(id);
  }

  @Override
  public void showData(UserModelBean bean) {
    tv_text.setText(bean.getName()+"\n"+bean.getAge()+"\n"+bean.getSex());
  }

  @Override
  public void showProgress() {

  }

  @Override
  public void dismissProgress() {

  }
}
