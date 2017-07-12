package com.lenovohit.hospitalgroup.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.lenovohit.hospitalgroup.R;
import com.lenovohit.lartemis_api.annotation.ContentView;
import com.lenovohit.lartemis_api.base.BaseController;
import com.lenovohit.lartemis_api.base.CoreActivity;
import com.lenovohit.lartemis_api.core.LArtemis;
import com.lenovohit.lartemis_api.model.MyAdvice;
import com.lenovohit.lartemis_api.ui.controller.MainController;
import com.lenovohit.lartemis_api.utils.CommonUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by SharkChao on 2017-07-11.
 */
@ContentView(R.layout.lx_mine_advice_detail_activity)
public class LX_MyAdviceDetailActivity extends CoreActivity<MainController.MainUiCallbacks> implements MainController.MainUi{
    @BindView(R.id.imgMyQuestion)
    ImageView mImgMyQuestion;
    @BindView(R.id.tvQuestion)
    TextView mTvQuestion;
    @BindView(R.id.tvAnswer)
    TextView mTvAnswer;
    public  static final String TAG="TAG";
    public  static final String ADVICE="advice";
    private Unbinder mBind;

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        isShowToolBar(true);
        setCenterTitle("反馈信息");
        mBind = ButterKnife.bind(this);
        MyAdvice advice = getIntent().getExtras().getParcelable(ADVICE);
        if (advice!=null){
            mTvQuestion.setText(CommonUtil.isStrEmpty(advice.getContent())?"":advice.getContent());
            mTvAnswer.setText(CommonUtil.isStrEmpty(advice.getReplyContent())?"暂无回复    ╮(╯▽╰)╭":advice.getReplyContent());
        }
    }

    @Override
    public void initEvent() {

    }

    @Override
    protected BaseController getController() {
        return LArtemis.getInstance().getMainController();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBind.unbind();
    }

    public static void startMyAdviceDetailActivity(Context context, MyAdvice advice){
        Intent intent=new Intent(context,LX_MyAdviceDetailActivity.class);
        Bundle bundle=new Bundle();
        bundle.putParcelable(ADVICE,advice);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}
