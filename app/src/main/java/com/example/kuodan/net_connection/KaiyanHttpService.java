package com.example.kuodan.net_connection;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @author 孙贝贝
 * @packagename com.example.a93018.videodemo
 * @date on 2019/1/28 10:26
 * @wechat 18813158027
 */
public interface KaiyanHttpService {

    /**登录*/
    @POST("login")
    Call<ResponseBody> login(@Query("userName") String loginName, @Query("password") String passorwd);

    /**获取规格信息列表  */
    @POST("selcinvstdlist")
    Call<ResponseBody> selcinvstdlist(@Query("cinvStd")String cinvStd, @Query("loginName")String loginName);

    /**获取规格信息列表  */
    @POST("seloutpositionList")
    Call<ResponseBody> seloutpositionList(@Query("cinvCode")String cinvCode, @Query("loginName")String loginName);

    /** 出库-扫码出库 */
    @POST("saomaoutpositionsave2")
    Call<ResponseBody> saomaoutpositionsave(@Query("loginName") String loginName,@Query("kfCode") String kfCode, @Query("outQtyn") String outQtyn ,@Query("cinvCode") String cinvCode);


}
