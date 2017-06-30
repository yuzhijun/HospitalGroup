package com.lenovohit.lartemis_api.utils;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lenovohit.lartemis_api.R;
import com.lenovohit.lartemis_api.core.LArtemis;

/**
 * Created by Administrator on 2017-06-29.
 */

public class CommonUtil {
   public static  int HINT= R.mipmap.lx_iv_common_snack_hint;
    public static int WARN=R.mipmap.lx_iv_common_snack_warn;
   public static  int PROBLEM=R.mipmap.lx_iv_common_snack_warn;
    /**
     *
     * @param view  只要是界面中的view即可
     * @param text  snackbar中的提示内容
     * @param bgColor 背景颜色，设置成0为默认颜色，即程序基色。
     */
    public static void showSnackBar(final View view,String text,int bgColor){
        Snackbar snackbar = Snackbar.make(view, text, Snackbar.LENGTH_LONG);
        View snackView = snackbar.getView();
        if(snackView!=null){
                snackView.setBackgroundColor(bgColor);//修改view的背景色
                ((TextView) snackView.findViewById(R.id.snackbar_text)).setTextColor(LArtemis.getInstance().getApplication().getResources().getColor(R.color.white));//获取Snackbar的message控件，修改字体颜色
        }
        snackbar.show();
    }
    public static void showSnackBar(final View view,String text){
        Snackbar snackbar = Snackbar.make(view, text, Snackbar.LENGTH_LONG);
        View snackView = snackbar.getView();
        if(snackView!=null){
                snackView.setBackgroundColor(LArtemis.getInstance().getApplication().getResources().getColor(R.color.colorPrimary));//修改view的背景色
                ((TextView) snackView.findViewById(R.id.snackbar_text)).setTextColor(LArtemis.getInstance().getApplication().getResources().getColor(R.color.white));//获取Snackbar的message控件，修改字体颜色
        }
        snackbar.show();
    }

    /**
     *
     * @param view  只要是界面中的view即可
     * @param text  snackbar中的提示内容
     * @param bgColor 背景颜色，设置成0为默认颜色，即程序基色。
     * @param resId  图片id
     */
    public static void showSnackBarToIcon(final  View view,String text,int bgColor,int type){
        Snackbar snackbar = Snackbar.make(view, null, Snackbar.LENGTH_LONG);
        View snackView = snackbar.getView();
        if(snackView!=null){
            snackView.setBackgroundColor(bgColor);//修改view的背景色
            Snackbar.SnackbarLayout snackbarLayout=(Snackbar.SnackbarLayout)snackView;//将获取的View转换成SnackbarLayout
            View add_view = LayoutInflater.from(snackView.getContext()).inflate(R.layout.lx_common_snack_view,null);//加载布局文件新建View
            LinearLayout linearLayout= (LinearLayout) add_view.findViewById(R.id.llLayout);
            ImageView ivSnack= (ImageView) add_view.findViewById(R.id.ivSnack);
            TextView tvSnack= (TextView) add_view.findViewById(R.id.tvSnack);

            linearLayout.setBackgroundColor(bgColor);
            ivSnack.setBackgroundResource(type);
            tvSnack.setText(text);
            LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);//设置新建布局参数
            p.gravity= Gravity.CENTER_VERTICAL;//设置新建布局在Snackbar内垂直居中显示

            snackbarLayout.addView(add_view,p);//将新建布局添加进snackbarLayout相应位置
        }
        snackbar.show();
    }
    public static void showSnackBarToIcon(final  View view,String text,int resId){
        Snackbar snackbar = Snackbar.make(view, null, Snackbar.LENGTH_LONG);
        View snackView = snackbar.getView();
        if(snackView!=null){
            snackView.setBackgroundColor(LArtemis.getInstance().getApplication().getResources().getColor(R.color.colorPrimary));//修改view的背景色
            Snackbar.SnackbarLayout snackbarLayout=(Snackbar.SnackbarLayout)snackView;//将获取的View转换成SnackbarLayout
            View add_view = LayoutInflater.from(snackView.getContext()).inflate(R.layout.lx_common_snack_view,null);//加载布局文件新建View
            LinearLayout linearLayout= (LinearLayout) add_view.findViewById(R.id.llLayout);
            ImageView ivSnack= (ImageView) add_view.findViewById(R.id.ivSnack);
            TextView tvSnack= (TextView) add_view.findViewById(R.id.tvSnack);

            linearLayout.setBackgroundColor(LArtemis.getInstance().getApplication().getResources().getColor(R.color.colorPrimary));
            ivSnack.setBackgroundResource(resId);
            tvSnack.setText(text);
            LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);//设置新建布局参数
            p.gravity= Gravity.CENTER_VERTICAL;//设置新建布局在Snackbar内垂直居中显示

            snackbarLayout.addView(add_view,p);//将新建布局添加进snackbarLayout相应位置
        }
        snackbar.show();
    }

    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
}
