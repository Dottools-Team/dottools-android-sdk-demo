package com.lem.nicetools.baasdemo.sdk;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SDKClient {
  String BASE_URL = "http://api.dottools.top/api/";

  private Retrofit retrofit;
  private OkHttpClient client;
  private DottoolsService dottoolsService;

  private static SDKClient instance = new SDKClient();

  public static SDKClient getInstance() {
    return instance;
  }

  private SDKClient() {
    client = new OkHttpClient.Builder()
        .addInterceptor(new Interceptor() {
          @Override public Response intercept(Chain chain) throws IOException {
            Request original = chain.request();

            // Request customization: add request headers
            Request.Builder requestBuilder = original.newBuilder()
                .addHeader("app_key", SDK.key)
                .addHeader("app_sign", AppSignTool.getAppSign(SDK.secret));

            Request request = requestBuilder.build();
            return chain.proceed(request);
          }
        })
        .build();

    retrofit = new Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build();

    dottoolsService = retrofit.create(DottoolsService.class);
  }

  public DottoolsService getDottoolsService() {
    return dottoolsService;
  }
}
