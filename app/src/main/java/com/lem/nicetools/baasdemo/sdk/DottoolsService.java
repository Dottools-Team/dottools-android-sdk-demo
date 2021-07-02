package com.lem.nicetools.baasdemo.sdk;

import com.lem.nicetools.baasdemo.bean.InfoContent;
import com.lem.nicetools.baasdemo.bean.UserExtra;
import com.lem.nicetools.baasdemo.sdk.bean.DatatableBody;
import com.lem.nicetools.baasdemo.sdk.bean.RegUserInfo;
import com.lem.nicetools.baasdemo.sdk.bean.Result;
import com.lem.nicetools.baasdemo.sdk.bean.SimpleConfig;
import com.lem.nicetools.baasdemo.sdk.bean.Token;
import io.reactivex.rxjava3.core.Single;
import java.util.List;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 *
 */
public interface DottoolsService {
  /**
   * 登录
   */
  @FormUrlEncoded
  @POST("app/user/login")
  Single<Result<Token>> login(@Field("id") String id, @Field("passwd") String passwd,
      @Field("id_type") String idType);

  /**
   * 注册
   */
  @POST("app/user/register")
  Single<Result<Object>> register(@Body RegUserInfo body);

  /**
   * 注销
   */
  @POST(" /app/userinfo/logout")
  Single<Result<Object>> logout();

  //// 用户额外信息

  @GET("app/userinfo/extra")
  Single<Result<UserExtra>> getUserExtra();

  @GET("app/userinfo/extra/{extra_field}")
  Single<Result<String>> getUserExtra(@Path("extra_field") String field);

  @POST("app/userinfo/extra")
  Single<Result<String>> postUserExtra(@Body UserExtra userExtra);

  //// 远程配置

  /**
   * 获取远程配置
   */
  @GET("app/config/{config_name}")
  Single<Result<SimpleConfig>> getRemoteConfig(@Path("config_name") String configName);

  //// 数据表

  @POST("app/datatable/{table_name}")
  Single<Result<Object>> postDatatableContent(@Path("table_name") String tableName, @Body
      DatatableBody<InfoContent> body);

  @GET("app/datatable/all/{table_name}")
  Single<Result<List<InfoContent>>> getDatatableContent(@Path("table_name") String tableName);
}
