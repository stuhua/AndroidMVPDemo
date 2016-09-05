package com.stuhua.retrofit;

import android.util.Log;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.RxJavaCallAdapterFactory;

/**
 * Created by llh on 2016/9/1.
 */
public class AppClient {
  private static Retrofit mRetrofit;

  public static Retrofit retrofit() {
    if (mRetrofit == null) {
      mRetrofit = new Retrofit.Builder().baseUrl("http://api.kanzhihu.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .build();
    }
    return mRetrofit;
  }
}
