package com.lenovohit.lartemis_api.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.lenovohit.lartemis_api.R;
import com.lenovohit.lartemis_api.utils.CommonUtil;

import java.util.Date;

/**
 * Created by SharkChao on 2017-07-10.
 * 一个用于倒计时的按钮控件
 */
public class TimeButton extends android.support.v7.widget.AppCompatButton implements View.OnClickListener{
    //正在进行时的背景
    private int timeingBg;
    //进行完成时的背景
    private int timedBg;
    //总的时间
    private String countTime;
    //时间间隔，一般为一秒
    private String intervalTime;
    private TimeCount timeCount;

    public TimeButton(Context context) {
        super(context);
    }

    public TimeButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.TimeButton);
        timedBg = array.getResourceId(R.styleable.TimeButton_tbTimedBackground, getResources().getColor(R.color.colorPrimary));
        timeingBg = array.getResourceId(R.styleable.TimeButton_tbTimeingBackground, getResources().getColor(R.color.grey_line_bg));
        countTime = array.getString(R.styleable.TimeButton_tbcountTime);
        intervalTime = array.getString(R.styleable.TimeButton_tbintervalTime);
        //先给一个默认的颜色，为红色
        setBackgroundColor(timedBg);
        if (TextUtils.isEmpty(countTime)){
            countTime = "60";
        }
        if (TextUtils.isEmpty(intervalTime)){
            intervalTime = "1";
        }
        this.setOnClickListener(this);
    }

    public TimeButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onClick(View v) {
        CommonUtil.setDate(new Date());
        timeCount.start();
    }

    class TimeCount extends CountDownTimer{

        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            setEnabled(false);
            setBackgroundColor(timedBg);
            setText((millisUntilFinished)/1000+"秒后重新获取");
            Log.e("tag",millisUntilFinished+"");
        }

        @Override
        public void onFinish() {
            setEnabled(true);
            setBackgroundColor(timeingBg);
            setText("重新获取验证码");
        }
    }
    public void initTime(){
        Date data = CommonUtil.getDate();
        if (data ==null){
            timeCount=new TimeCount(Long.parseLong(countTime)*1000,Long.parseLong(intervalTime)*1000);
        }else {
            long a = data.getTime()+Long.parseLong(countTime)*1000;
            long b=new Date().getTime();
            if (a-b>=0){
                countTime = String.valueOf(a - b);
                timeCount=new TimeCount(Long.parseLong(countTime),Long.parseLong(intervalTime)*1000);
                timeCount.start();
            }else {
                setEnabled(true);
                setBackgroundColor(timeingBg);
                setText("重新获取验证码");
                timeCount.cancel();
            }
        }
    }
}
