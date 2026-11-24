package com.example.kuodan.ui;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kuodan.NetUtils;
import com.example.kuodan.R;
import com.example.kuodan.adapter.Cinv_Adatper;
import com.example.kuodan.adapter.Wmsout_Adatper;
import com.example.kuodan.base.BaseActivity;
import com.example.kuodan.model.CinvList_Reslult;
import com.example.kuodan.net_connection.Server_Response;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 *
 * 出库待扫码验证20221209
 */
public class selcinventer extends BaseActivity implements Server_Response.OnGetSeverLister {

    public String userName;
    @BindView(R.id.finish_back)//返回
    ImageView finishBack;

    @BindView(R.id.textView1)//出库待扫码验证清单（文字）
    TextView textView1;



    @BindView(R.id.sinvStd_tv)//公司规格
    TextView sinvStdtv;
    String cinvStd;



    @BindView(R.id.search_btn)//按钮
    Button searchBtn;

    @BindView(R.id.recycleview)
    RecyclerView recycleview;

//    EditText foucasResulttwo;

    private Context context;

    private boolean canScan = true;

    private Cinv_Adatper adatper;
    private List<CinvList_Reslult.ListBean> list;
    String loginName ;


    /**MediaPlayer类访问内置的媒体播放器的服务,如播放音频,视频等为了使用MediaPlayer,我们要调用这个类的静态create() 方法。此方法返回MediaPlayer类的一个实例。 */
//    private static MediaPlayer mediaPlayer;

    @Override
    public int loadWindowLayout() {
        return R.layout.cinventer_activity;
    }

    @Override
    public void initViews() throws Exception {
        context = this;
//        foucasResulttwo = findViewById(R.id.foucas_result1);
//        foucasResulttwo.addTextChangedListener(watcher2);//监听edittext的输入文本的变化
        loginName = getIntent().getStringExtra("userName");
        userName = loginName;
        ButterKnife.bind(this);
        recycleview.setLayoutManager(new LinearLayoutManager(this));
        adatper = new Cinv_Adatper(this);








//        timeTv.setText(getCurrentTime());
        getCinvList();
    }



    /** 获取列表 获取出库材料信息20221219 */
    private void  getCinvList(){
        cinvStd = sinvStdtv.getText().toString();

        showProgress();
        NetUtils.getCinvStdList(this,cinvStd,loginName);
    }



    //                返回             日期           查询
    @OnClick({R.id.finish_back, R.id.search_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.finish_back://返回
                finish();
                break;
            case R.id.search_btn://查询
                getCinvList();
                break;
        }
    }

    @Override
    public void onSuccess(Object response) {
        hideProgress();
        if (response instanceof CinvList_Reslult){
            CinvList_Reslult reslult = (CinvList_Reslult) response;
            list = reslult.getList();
            adatper.setUserName(loginName);
            adatper.setList(list);
            recycleview.setAdapter(adatper);

        }else{


        }

    }

    @Override
    public void onFaire(String error) {
        hideProgress();
        tostShow(error);

    }
}
