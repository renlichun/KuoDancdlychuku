package com.example.kuodan.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kuodan.R;
import com.example.kuodan.model.CinvList_Reslult;
import com.example.kuodan.ui.selcinventer;
import com.example.kuodan.ui.selposiout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author 孙贝贝
 * @packagename com.example.kuodan.adapter
 * @date on 2020/4/13 17:56
 * @wechat 18813158027
 */
public class Cinv_Adatper extends RecyclerView.Adapter<Cinv_ViewHolder> {

    private Context context;
    private String userName;



    private List<CinvList_Reslult.ListBean> list;

    public Cinv_Adatper(Context context) {
        this.context = context;
    }
    public void setList(List<CinvList_Reslult.ListBean> list){
        this.list=list;

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @NonNull
    @Override
    public Cinv_ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.cinv_item, viewGroup, false);
        return new Cinv_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Cinv_ViewHolder holder, int i) {

//        System.out.println("------222-----"+context);
        String userName = ((selcinventer) context).userName;
//        System.out.println("------userName-222----"+userName);




        CinvList_Reslult.ListBean listBean = list.get(i);

        holder.tvCinvStd.setText(listBean.getCinvStd());
        holder.tvCinvaddCode.setText(listBean.getCinvaddCode());
        holder.tvCinvName.setText(listBean.getCinvName());
        holder.tvCinvCode.setText(listBean.getCinvCode());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("cinvCode", listBean.getCinvCode());
                intent.putExtra("cinvName", listBean.getCinvName());
                intent.putExtra("cinvStd", listBean.getCinvStd());
                intent.putExtra("cinvaddCode", listBean.getCinvaddCode());
                intent.putExtra("userName", userName);
                System.out.println("---"+listBean.getCinvCode());
                System.out.println("---"+listBean.getCinvName());
                System.out.println("---"+listBean.getCinvStd());
                System.out.println("---"+listBean.getCinvaddCode());
                intent.setClass(context, selposiout.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    private void setColors(Wmsout_ViewHolder holder, int color){
        holder.tvCinvStd.setTextColor(context.getResources().getColor(color));
        holder.tvCinvName.setTextColor(context.getResources().getColor(color));

    }
}


class Cinv_ViewHolder extends RecyclerView.ViewHolder {


    @BindView(R.id.tv_cinv_std)
    TextView tvCinvStd;

    @BindView(R.id.tv_cinvadd_code)
    TextView tvCinvaddCode;

    @BindView(R.id.tv_cinv_name)
    TextView tvCinvName;

    @BindView(R.id.tv_cinv_code)
    TextView tvCinvCode;


    public Cinv_ViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}
