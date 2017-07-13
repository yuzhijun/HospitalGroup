package com.lenovohit.hospitalgroup.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lenovohit.hospitalgroup.R;
import com.lenovohit.lartemis_api.annotation.ContentView;
import com.lenovohit.lartemis_api.base.BaseController;
import com.lenovohit.lartemis_api.base.CoreActivity;
import com.lenovohit.lartemis_api.core.LArtemis;
import com.lenovohit.lartemis_api.model.AppVersion;
import com.lenovohit.lartemis_api.model.ResponseError;
import com.lenovohit.lartemis_api.ui.controller.MainController;
import com.lenovohit.lartemis_api.utils.CommonUtil;
import com.lenovohit.lartemis_api.utils.DataCleanUtil;
import com.lenovohit.lartemis_api.utils.UpdateUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by SharkChao on 2017-07-12.
 * 设置界面
 */
@ContentView(R.layout.lx_mine_setup)
public class LX_SetUpActivity extends CoreActivity<MainController.MainUiCallbacks> implements MainController.SettingUi{

    private Unbinder mBind;
    @BindView(R.id.lrvFS)
    View lrvFS;
    @BindView(R.id.lrvTK)
    View lrvTK;
    @BindView(R.id.lrvGY)
    View lrvGY;
    @BindView(R.id.lrvGX)
    View lrvGX;
    @BindView(R.id.lrvClean)
    View lrvClean;
    private TextView mTvTitleFS;
    private TextView mTvTitleTK;
    private TextView mTvTitleGY;
    private TextView mTvTitleGX;
    private TextView mTvTitleClean;
    private AlertDialog mDialog;

    @Override
    protected BaseController getController() {
        return LArtemis.getInstance().getMainController();
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        mBind = ButterKnife.bind(this);
        isShowToolBar(true);
        setCenterTitle("设置");
       initItemTitle();
    }

    @Override
    public void initEvent() {
        lrvFS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LX_MoreShareActivity.startMoreShareActivity(LX_SetUpActivity.this);
            }
        });
        lrvTK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    LX_DeclareActivity.startDeclareActivity(LX_SetUpActivity.this);
            }
        });
        lrvGY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LX_AboutUsActivity.startAboutUsActivity(LX_SetUpActivity.this);
            }
        });
        lrvGX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCallbacks().checkAppVersion("2","android-ehospital");
            }
        });
        lrvClean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataCleanUtil.cleanFilesData();
                CommonUtil.showSnackBar(mTvTitleClean,"图片缓存以清除!");
            }
        });
    }
    public void initItemTitle(){
        mTvTitleFS = (TextView) lrvFS.findViewById(R.id.tvRowThreeTitle);
        mTvTitleTK = (TextView) lrvTK.findViewById(R.id.tvRowThreeTitle);
        mTvTitleGY = (TextView) lrvGY.findViewById(R.id.tvRowThreeTitle);
        mTvTitleGX = (TextView) lrvGX.findViewById(R.id.tvRowThreeTitle);
        mTvTitleClean = (TextView) lrvClean.findViewById(R.id.tvRowThreeTitle);
        mTvTitleFS.setText("推荐给朋友");
        mTvTitleTK.setText("服务条款");
        mTvTitleGY.setText("关于我们");
        mTvTitleGX.setText("检查更新");
        mTvTitleClean.setText("清除图片缓存");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBind.unbind();
    }

    @Override
    public void getCheckVersionCallBack(final AppVersion result) {
        int versionCode = CommonUtil.getVersionCode(LX_SetUpActivity.this);
        Log.e("tag",versionCode+result.toString());
        if (result !=null){
            if (!UpdateUtil.isUpdate(LX_SetUpActivity.this,result)){
                CommonUtil.showSnackBar(mTvTitleClean,"当前已经是最新版本!");
            }else {
                //此时需要更新
                View view=View.inflate(LX_SetUpActivity.this,R.layout.lx_alert_update_app,null);
                TextView tvContent = (TextView) view.findViewById(R.id.tvUpdateContent);
                Button btnUpdate = (Button) view.findViewById(R.id.btnUpdate);
                Button btnCancle = (Button) view.findViewById(R.id.btnCancle);
                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        UpdateUtil.showDownloadDialog(LX_SetUpActivity.this,result);
                        mDialog.dismiss();
                    }
                });
                btnCancle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mDialog.dismiss();
                    }
                });
                if (!UpdateUtil.canCancle(result)){
                    btnCancle.setVisibility(View.GONE);
                }else {
                    btnCancle.setVisibility(View.VISIBLE);
                }
                tvContent.setText(CommonUtil.isStrEmpty(result.getAppIntro())?"":result.getAppIntro());
                mDialog = new AlertDialog.Builder(LX_SetUpActivity.this)
                        .setCancelable(false)
                        .setTitle("更新")
                        .setView(view).show();
            }
        }
    }

    @Override
    public void onResponseError(ResponseError error) {
        super.onResponseError(error);
        CommonUtil.showSnackBar(mTvTitleFS,error.getMessage());
    }
    public static void startSetupActivity(Context context){
        context.startActivity(new Intent(context,LX_SetUpActivity.class));
    }
}
