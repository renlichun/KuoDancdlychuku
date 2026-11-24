package com.example.kuodan;

import com.example.kuodan.model.Login_Result;
import com.example.kuodan.model.CinvList_Reslult;
import com.example.kuodan.model.Outpositon_Reslut;
import com.example.kuodan.model.Outpositonsave_Reslut;
import com.example.kuodan.net_connection.ServerUtil;
import com.example.kuodan.net_connection.Server_Response;

import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * @author 孙贝贝
 * @packagename com.example.kuodan
 * @date on 2020/4/14 10:33
 * @wechat 18813158027
 */
public class NetUtils {
    public static void Login(Server_Response.OnGetSeverLister lister,String loginName,String password){
        Call<ResponseBody> responseBodyCall = ServerUtil.getInstance().getServer().login(loginName,password);
        Server_Response.getResponse(responseBodyCall,new Login_Result(),lister);

    }

    /**
     * 1.根据输入内容，获取规格信息列表
     * 2.确认规格
     * 3.输入数量
     * 4.按照先进先出查询出库列表
     * 5.扫描货位码出库
     */

    /** 获取规格信息列表 */
    public static void getCinvStdList(Server_Response.OnGetSeverLister lister,String cinvStd,String loginName){
        Call<ResponseBody> responseBodyCall = ServerUtil.getInstance().getServer().selcinvstdlist(cinvStd,loginName);
        Server_Response.getResponse(responseBodyCall,new CinvList_Reslult(),lister);
    }

//    /** 获取确认的规格信息 */
//    public static void getCinvStdinfo(Server_Response.OnGetSeverLister lister,String cDate,String loginName){
//        Call<ResponseBody> responseBodyCall = ServerUtil.getInstance().getServer().selcinvStdinfo(cDate,loginName);
//        Server_Response.getResponse(responseBodyCall,new OrderList_Reslult(),lister);
//    }
//
    /** 按照先进先出查询出库列表 */
    public static void getOutpositionList(Server_Response.OnGetSeverLister lister,String cinvCode,String loginName){
        Call<ResponseBody> responseBodyCall = ServerUtil.getInstance().getServer().seloutpositionList(cinvCode,loginName);
        Server_Response.getResponse(responseBodyCall,new Outpositon_Reslut(),lister);
    }

    /** 扫描货位码出库 */
    public static void saomaoutpositionsave(Server_Response.OnGetSeverLister lister,String loginName,String kfCode,String outQtyn,String cinvCode){
        Call<ResponseBody> responseBodyCall = ServerUtil.getInstance().getServer().saomaoutpositionsave(loginName,kfCode,outQtyn,cinvCode);
        Server_Response.getResponse(responseBodyCall,new Outpositonsave_Reslut(),lister);

    }


}
