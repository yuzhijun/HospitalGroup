package com.lenovohit.lartemis_api.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lenovohit.lartemis_api.R;


/**
 * 自定义Item样式一
 * 作者：LinHao
 * 邮箱：439224@qq.com
 * 创建时间：2016/7/5 10:28
 */
public class MyItemInfo extends LinearLayout {

    private TextView txRowTitle;
    private TextView txRowContent;
    private ImageView ivRight;
    private LinearLayout llRoot;
    public MyItemInfo(Context context) {
        super(context);
    }

    public MyItemInfo(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater mInflater = LayoutInflater.from(context);
        mInflater.inflate(R.layout.lx_list_row_three, this);

        txRowTitle = (TextView) findViewById(R.id.tvRowThreeTitle);
        txRowContent = (TextView) findViewById(R.id.tvRowThreeContent);
        ivRight= (ImageView) findViewById(R.id.ivRight);
        llRoot= (LinearLayout) findViewById(R.id.llRoot);

    }
    public void setItemInfo(String title,String content){
        txRowTitle.setText(title+"");
        txRowContent.setText(content+"");
    }
    public void showRightIcon(boolean b){
        if (b){
            ivRight.setVisibility(View.VISIBLE);
        }else {
            ivRight.setVisibility(View.INVISIBLE);
        }
    }
    public void setItemClickListener(OnClickListener listener){
        llRoot.setOnClickListener(listener);
    }
}
