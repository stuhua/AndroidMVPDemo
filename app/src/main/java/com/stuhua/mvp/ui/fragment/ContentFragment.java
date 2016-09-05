package com.stuhua.mvp.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stuhua.mvp.R;
import com.stuhua.mvp.adapter.ContentAdapter;
import com.stuhua.mvp.model.UserModelBean;
import com.stuhua.mvp.model.ZhihuContentJson;
import com.stuhua.mvp.presenter.MainPresenter;
import com.stuhua.mvp.view.IMainView;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by llh on 2016/9/2.
 */
public class ContentFragment extends Fragment implements IMainView {
  SwipeRefreshLayout mSwipeRefreshLayout;
  RecyclerView mRecyclerView;
  private String dateTime;
  private MainPresenter mMainPresenter;
  private String TAG="ContentFragment:";

  public ContentFragment() {
    dateTime = getTodayTime(); //获取今天的日期
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_content, container, false);
    mRecyclerView= (RecyclerView) view.findViewById(R.id.recycler_view);
    mMainPresenter = new MainPresenter(this);
    mMainPresenter.loadData(dateTime);
    return view;
  }

  private String getTodayTime() {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
    Date curDate = new Date(System.currentTimeMillis());//获取当前时间
    return formatter.format(curDate);
  }

  @Override
  public void showData(ZhihuContentJson json) {
    mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));//这里用线性显示 类似于listview
    mRecyclerView.setAdapter(new ContentAdapter(getContext(), json));
    Log.d(TAG,json.getAnswers().get(0).getSummary());
  }

  @Override
  public void showProgress() {

  }

  @Override
  public void dismissProgress() {
    Log.d(TAG,"-------:网络或网址问题，加载失败");
  }
}
