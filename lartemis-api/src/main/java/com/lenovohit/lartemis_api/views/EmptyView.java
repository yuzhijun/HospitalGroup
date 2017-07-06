package com.lenovohit.lartemis_api.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.lenovohit.lartemis_api.R;
import com.zhy.android.percent.support.PercentLinearLayout;

/**
 * Created by Administrator on 2017-07-06.
 *
 * 适用于 recycleView 的空布局
 * getview方法最后必须调用
 */

public class EmptyView extends View{

    private  View view;
    public static final  String TYPE_LOADING = "000";
    public static final  String TYPE_ERROR = "001";
    public static final  String TYPE_NO_DATA = "002";
    private  TextView tvTitle;
    private  ImageView ivEmpty;
    private  ProgressBar progressBar;
    private OnClickListener listener;
    private PercentLinearLayout plLayout;

    public EmptyView(Context context,ViewGroup viewGroup) {
        super(context);
       init(context,viewGroup);
    }
    public EmptyView(Context context,ViewGroup viewGroup,String type) {
        super(context);
        init(context,viewGroup);
        setType(type);
    }
    private void init(Context context,ViewGroup viewGroup){
        view = LayoutInflater.from(context).inflate(R.layout.lx_empty_view, viewGroup, false);
        ivEmpty = (ImageView) view.findViewById(R.id.ivEmpty);
        tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        plLayout= (PercentLinearLayout) view.findViewById(R.id.plLayout);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        if (listener!=null){
            plLayout.setOnClickListener(listener);
        }
    }

    public  void setType(String type){

        if (type.equals(TYPE_LOADING)){
            ivEmpty.setVisibility(View.GONE);
            tvTitle.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
        }else if (type.equals(TYPE_ERROR)){
            ivEmpty.setVisibility(View.VISIBLE);
            tvTitle.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
            ivEmpty.setImageResource(R.mipmap.lx_no_network_default);
        }else if (type.equals(TYPE_NO_DATA)){
            ivEmpty.setVisibility(View.VISIBLE);
            tvTitle.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
            ivEmpty.setImageResource(R.mipmap.lx_iv_no_data);
        }
    }
    public  void setMessage(String text){
        tvTitle.setText(text);
    }
    public void setRefreshListener(OnClickListener listener){
        this.listener=listener;
    }
    public View getView(){
        return  view;
    }
}
