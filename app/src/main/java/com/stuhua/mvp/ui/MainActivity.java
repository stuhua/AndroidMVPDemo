package com.stuhua.mvp.ui;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.stuhua.mvp.R;
import com.stuhua.mvp.model.UserModelBean;
import com.stuhua.mvp.model.ZhihuContentJson;
import com.stuhua.mvp.presenter.MainPresenter;
import com.stuhua.mvp.ui.fragment.ContentFragment;
import com.stuhua.mvp.view.IMainView;

public class MainActivity extends AppCompatActivity implements IMainView, View.OnClickListener {
  private MainPresenter mMainPresenter;
  private ProgressBar mProgressBar;
  private Toolbar mToolbar;
  private DrawerLayout mDrawerLayout;
  private LinearLayout mContentLayout;

  private TextView mText;
  private Button mbtn;

  private String TAG = "MainActivity:";

  private void initView() {
    mProgressBar = getViewById(R.id.progressBar);
    mToolbar = getViewById(R.id.toolbar);
    mToolbar.setTitle("看知乎");
    mToolbar.setTitleTextColor(Color.parseColor("#ffffff"));
    // 设置toolbar支持actionbar
    setSupportActionBar(mToolbar);

    mDrawerLayout = getViewById(R.id.drawerLayout);
    mContentLayout = getViewById(R.id.drawerContent);

    mMainPresenter = new MainPresenter(this);
    //调用p层的load数据
//    mMainPresenter.loadData();
  }

  public <T extends View> T getViewById(int id) {
    return (T) findViewById(id);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    initView();
    ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.open, R.string.close);
    mDrawerToggle.syncState();
    mDrawerLayout.setDrawerListener(mDrawerToggle);

    Fragment mFragment = new ContentFragment();
    FragmentManager fragmentManager = getSupportFragmentManager();
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
    fragmentTransaction.replace(R.id.containerViewId, mFragment);
    fragmentTransaction.commit();

    TextView text1 = (TextView) findViewById(R.id.text1);
    TextView text2 = (TextView) findViewById(R.id.text2);

    text1.setOnClickListener(this);
    text2.setOnClickListener(this);
  }


  @Override
  protected void onDestroy() {
    mMainPresenter.detachView();
    super.onDestroy();
  }


  /**
   * 在界面上操作数据
   *
   * @param json
   */
  @Override
  public void showData(ZhihuContentJson json) {

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


  @Override
  public void onClick(View view) {

    // 关闭DrawerLayout
    mDrawerLayout.closeDrawer(mContentLayout);
    switch (view.getId()) {

      case R.id.text1:

        Toast.makeText(MainActivity.this, "我的收藏", Toast.LENGTH_SHORT).show();
        break;
      case R.id.text2:
        Toast.makeText(MainActivity.this, "我的关注", Toast.LENGTH_SHORT).show();
        break;
    }
  }
}
