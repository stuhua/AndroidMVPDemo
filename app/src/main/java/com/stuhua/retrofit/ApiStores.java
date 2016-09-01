package com.stuhua.retrofit;

import com.stuhua.mvp.model.WeatherJson;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by llh on 2016/9/1.
 */
public interface ApiStores {
  @GET("adat/sk/{cityId}.html")
  Observable<WeatherJson> getWeather(@Path("cityId") String cityId);
}