package com.example.kuodan.ui;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kuodan.NetUtils;
import com.example.kuodan.R;
import com.example.kuodan.adapter.Wmsout_Adatper;
import com.example.kuodan.base.BaseActivity;
import com.example.kuodan.model.CinvList_Reslult;
import com.example.kuodan.model.Outpositon_Reslut;
import com.example.kuodan.model.Outpositonsave_Reslut;
import com.example.kuodan.net_connection.Server_Response;
import com.example.kuodan.tool.TLog;
import com.example.kuodan.tool.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 *
 * 出库待扫码验证20221209
 */
public class selposiout extends BaseActivity implements Server_Response.OnGetSeverLister {

    public String userName;
    @BindView(R.id.finish_back)//返回
    ImageView finishBack;

    @BindView(R.id.textView1)//出库待扫码验证清单（文字）
    TextView textView1;



    @BindView(R.id.cinvCode_tv2)//公司规格
    TextView cinvCodetv;
    String cinvCode;

    @BindView(R.id.cinvName_tv2)//公司规格
    TextView cinvNametv;
    String cinvName;

    @BindView(R.id.cinvStd_tv2)//公司规格
    TextView cinvStdtv;
    String cinvStd;

    TextView stdyztv;
    String stdyz;

    @BindView(R.id.addCode_tv2)//客户图号
    TextView addCodetv;
    String cinvaddCode;

    @BindView(R.id.outQtyn_tv2)//出库数量
    TextView outQtyntv;
    String outQtyn;

    TextView outQtyncgtv;//已出库数量
    String outQtyncg;

    @BindView(R.id.search_btn)//按钮
    Button searchBtn;

    @BindView(R.id.recycleview)
    RecyclerView recycleview;

    EditText foucasResulttwo;

    EditText EditText;

    private Context context;

    private boolean canScan = true;

    private Wmsout_Adatper adatper;
    private List<Outpositon_Reslut.ListBean> list;
    String loginName ;


    /**MediaPlayer类访问内置的媒体播放器的服务,如播放音频,视频等为了使用MediaPlayer,我们要调用这个类的静态create() 方法。此方法返回MediaPlayer类的一个实例。 */
    private static MediaPlayer mediaPlayer;

    @Override
    public int loadWindowLayout() {
        return R.layout.posiout_activity;
    }

    @Override
    public void initViews() throws Exception {
        context = this;
        foucasResulttwo = findViewById(R.id.foucas_result1);
        foucasResulttwo.addTextChangedListener(watcher2);//监听edittext的输入文本的变化
        
        cinvCode = getIntent().getStringExtra("cinvCode");
        cinvCodetv=findViewById(R.id.cinvCode_tv2);
        cinvCodetv.setText(cinvCode);

        cinvName = getIntent().getStringExtra("cinvName");
        cinvNametv=findViewById(R.id.cinvName_tv2);
        cinvNametv.setText(cinvName);

        cinvStd = getIntent().getStringExtra("cinvStd");
        cinvStdtv=findViewById(R.id.cinvStd_tv2);
        cinvStdtv.setText(cinvStd);


        stdyztv=findViewById(R.id.stdyz_tv);
        stdyztv.setText(stdyz);

        cinvaddCode = getIntent().getStringExtra("cinvaddCode");
        addCodetv=findViewById(R.id.addCode_tv2);
        addCodetv.setText(cinvaddCode);

        outQtyn = getIntent().getStringExtra("outQtyn");
        outQtyntv=findViewById(R.id.outQtyn_tv2);
        outQtyntv.setText(outQtyn);

        outQtyncg = "0";
        outQtyncgtv=findViewById(R.id.outQtyncg_tv2);
        outQtyncgtv.setText(outQtyncg);


        loginName = getIntent().getStringExtra("userName");
        ButterKnife.bind(this);
        recycleview.setLayoutManager(new LinearLayoutManager(this));
        adatper = new Wmsout_Adatper(this);
//        timeTv.setText(getCurrentTime());
//        getOrderList();
    }
    //
    private TextWatcher watcher2 = new TextWatcher() { // Android TextWatcher监控EditText中的输入内容
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            String scan_result2 = foucasResulttwo.getText().toString().trim();
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String scan_result2 = foucasResulttwo.getText().toString().trim();
        }

        @Override
        public void afterTextChanged(Editable s) {//扫描结果
            String qrCode  = foucasResulttwo.getText().toString().trim();
            TLog.e("==afterTextChanged扫描结果==", qrCode);
           // System.out.println("----------outQtyn----"+outQtyn);
//            E/扫描结果==: HG-NBR+PVC-D9X3-50~10~~A02959~0112~8356~12~34~HB0120230112002973
            String yz = stdyztv.getText().toString();
            if(yz.equals("未验证")){

                if (!TextUtils.isEmpty(qrCode)) {
                    // canScan = false;
                    if (10 == qrCode.length()) {

                        if (null != list && list.size() > 0) {
                            if (qrCode.equals(list.get(0).getPositionCode())) {

                                stdyztv.setText("已验证");
                                stdyztv.setTextColor(context.getResources().getColor(R.color.blue));
                            } else {
                                Utils.showDialogVersion2(context, "请按照新进先出顺序扫描正确的货位码。");
                            }
                        } else {
                            Utils.showDialogVersion2(context, "没找到货位信息。");
                        }
                    } else {
                        Utils.showDialogVersion2(context, "请扫描正确的货位码。");
                    }
                } else {
                    Utils.showDialogVersion2(context, "扫描结果不能是空字符串。");
                }

            }else {

                if(yz.equals("已验证")){
                    String[] atrarr = qrCode.split("~");
                    if(atrarr.length==9) {
                        String qrcinvStd = atrarr[0];
                        String wlcinvStd = cinvStdtv.getText().toString();

                        int lengqr = qrcinvStd.length();
                        int leng = wlcinvStd.length();
                        boolean qrtf = true;//规格对比
                        for(;;){
                            char charqr =qrcinvStd.charAt(--lengqr);
                            char chars = wlcinvStd.charAt(--leng);

                            if((charqr+"").equals(chars+"")){
                            }else{
                                qrtf = false;
                                break;
                            }
                            if(leng == 0){
                                break;
                            }
                        }

                        if (qrtf) {
//                            outQtyn = atrarr[1];

                            outpositon(list.get(0).getPositionCode());
                        } else {
                            Utils.showDialogVersion2(context, "物料规格对比失败！");
                        }
                    }else{
                        Utils.showDialogVersion2(context, "二维码不符合规则！");
                    }
                }
            }
            // 每次处理完扫描结果后清空输入框
            foucasResulttwo.removeTextChangedListener(watcher2);
            foucasResulttwo.setText("");
            foucasResulttwo.addTextChangedListener(watcher2);

        }
    };

    public static void playVoice(Context context){
        try {
            mediaPlayer = MediaPlayer.create(context, R.raw.sussful);
            mediaPlayer.start();
            mediaPlayer.setLooping(false);
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mediaPlayer.start();
                }
            });
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mediaPlayer.release();
                    mediaPlayer.stop();
                }

            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /** 获取列表 获取出库材料信息20221219 */
    private void  getOutpositionList(){

        outQtyntv=findViewById(R.id.outQtyn_tv2);
        outQtyn = ""+ outQtyntv.getText();
        if(null == outQtyn){
//           // System.out.println("---不能为null-");
            return;
        }
        if("".equals(outQtyn.replace(" ", ""))){
//           // System.out.println("---不能为空串--");
            return;
        }

        if(isNumeric(outQtyn)){
        }else{
            return;
        }
        if(Integer.parseInt(outQtyn)>0){

        }else{
            return;
        }
        outQtyntv.setKeyListener(null);
        foucasResulttwo.requestFocus();
        //showProgress();
        NetUtils.getOutpositionList(this,cinvCode,loginName);
    }
    /** 库房kf出库out验证yz生效sx */
    private void  outpositon(String kf){

        outQtyncgtv=findViewById(R.id.outQtyncg_tv2);

        Integer integer = Integer.parseInt(outQtyncgtv.getText()+"");
        Integer integer1 = Integer.parseInt(outQtyn);
        Integer cha = integer1 - integer;
        NetUtils.saomaoutpositionsave(this,loginName,kf,cha+"",cinvCode);
    }


    //                返回             日期           查询
    @OnClick({R.id.finish_back, R.id.search_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.finish_back://返回
                finish();
                break;
            case R.id.search_btn://查询
                getOutpositionList();
                break;
        }
    }

    @Override
    public void onSuccess(Object response) {

        hideProgress();
        if (response instanceof Outpositon_Reslut){
            stdyztv.setText("未验证");
//            stdyztv.setTextColor(context.getResources().getColor(R.color.));
            Outpositon_Reslut reslult = (Outpositon_Reslut) response;
            list = reslult.getList();
            adatper.setList(list);
            recycleview.setAdapter(adatper);

        }
        else
        if (response instanceof Outpositonsave_Reslut){
            Outpositonsave_Reslut reslult = (Outpositonsave_Reslut) response;
            if("t".equals(reslult.getTorf())){
                playVoice(context);//播放成功语音

                outQtyncgtv=findViewById(R.id.outQtyncg_tv2);

                Integer a = Integer.parseInt(outQtyncgtv.getText()+"");
               // System.out.println("--aaaaaaa-"+a);
                String rdoutqtyn = reslult.getOutRdQtyn();
                Integer b = Integer.parseInt(rdoutqtyn);
                Integer rdqty = 0;
                if(a == 0) {
                    rdqty = b;
                }else{
                     rdqty = a + b;
                }

                outQtyncgtv.setText(rdqty+"");

                getOutpositionList();
            }
            Utils.showDialogVersion2(context, ""+reslult.getMsg());

        }

    }

    @Override
    public void onFaire(String error) {
        hideProgress();
        tostShow(error);

    }

    public static boolean isNumeric(String str){

        Pattern pattern = Pattern.compile("[0-9]*");

        return pattern.matcher(str).matches();

    }

}