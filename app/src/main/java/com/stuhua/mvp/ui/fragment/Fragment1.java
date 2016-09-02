package com.stuhua.mvp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stuhua.mvp.R;

/**
 * Created by llh on 2016/9/2.
 */
public class Fragment1 extends Fragment {

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view= inflater.inflate(R.layout.activity_flash,container,false);
    return view;
  }
}
