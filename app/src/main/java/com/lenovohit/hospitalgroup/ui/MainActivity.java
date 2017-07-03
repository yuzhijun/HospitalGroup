package com.lenovohit.hospitalgroup.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.widget.FrameLayout;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.lenovohit.hospitalgroup.R;
import com.lenovohit.hospitalgroup.ui.fragment.HospitalFragment;
import com.lenovohit.hospitalgroup.ui.fragment.MainHomeFragment;
import com.lenovohit.hospitalgroup.ui.fragment.MineFragment;
import com.lenovohit.hospitalgroup.ui.fragment.PraticalityFragment;
import com.lenovohit.lartemis_api.annotation.ContentView;
import com.lenovohit.lartemis_api.base.BaseController;
import com.lenovohit.lartemis_api.base.CoreActivity;
import com.lenovohit.lartemis_api.core.LArtemis;
import com.lenovohit.lartemis_api.ui.controller.MainController;
import com.lenovohit.lartemis_api.utils.CommonUtil;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

@ContentView(R.layout.activity_main)
public class MainActivity extends CoreActivity<MainController.MainUiCallbacks> implements MainController.MainUi,BottomNavigationBar.OnTabSelectedListener{
    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar bottomNavigationBar;
    @BindView(R.id.main_container)
    FrameLayout mainContainer;
    private FragmentManager mFragmentManager;
    private MainHomeFragment mMainHomeFragment;
    private HospitalFragment mHospitalFragment;
    private PraticalityFragment mPraticalityFragment;
    private MineFragment mMineFragment;

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        ButterKnife.bind(this);
        isShowToolBar(false);
        mFragmentManager = getSupportFragmentManager();

        mMainHomeFragment = (MainHomeFragment) mFragmentManager.findFragmentByTag("home_fg");
        mHospitalFragment = (HospitalFragment) mFragmentManager.findFragmentByTag("hopsital_fg");
        mPraticalityFragment = (PraticalityFragment) mFragmentManager.findFragmentByTag("praticality_fg");
        mMineFragment = (MineFragment) mFragmentManager.findFragmentByTag("mine_fg");

        if (null == mMineFragment){
            mMainHomeFragment = MainHomeFragment.newInstance();
            addFragment(R.id.main_container, mMainHomeFragment, "home_fg");
        }

        if(null == mHospitalFragment){
            mHospitalFragment = HospitalFragment.newInstance();
            addFragment(R.id.main_container,mHospitalFragment,"hopsital_fg");
        }

        if (null == mPraticalityFragment){
            mPraticalityFragment = PraticalityFragment.newInstance();
            addFragment(R.id.main_container,mPraticalityFragment,"praticality_fg");
        }

        if (null == mMineFragment){
            mMineFragment = MineFragment.newInstance();
            addFragment(R.id.main_container,mMineFragment,"mine_fg");
        }

        mFragmentManager.beginTransaction().show(mMainHomeFragment).hide(mHospitalFragment).hide(mPraticalityFragment).hide(mMineFragment)
                .commitAllowingStateLoss();

        initBottomNavigation();
    }

    private void initBottomNavigation() {
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);

        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.mipmap.lx_navi_main_bottom_press, "首页").setInactiveIconResource(R.mipmap.lx_navi_main_bottom_normal).setActiveColorResource(R.color.colorAccent))
                .addItem(new BottomNavigationItem(R.mipmap.lx_navi_hos_bottom_press, "医院").setInactiveIconResource(R.mipmap.lx_navi_hos_bottom_normal).setActiveColorResource(R.color.colorAccent))
                .addItem(new BottomNavigationItem(R.mipmap.lx_navi_practical_bottom_press, "实用").setInactiveIconResource(R.mipmap.lx_navi_practical_bottom_normal).setActiveColorResource(R.color.colorAccent))
                .addItem(new BottomNavigationItem(R.mipmap.lx_navi_mine_bottom_press, "我的").setInactiveIconResource(R.mipmap.lx_navi_mine_bottom_normal).setActiveColorResource(R.color.colorAccent))
                .setFirstSelectedPosition(0)
                .initialise();

        bottomNavigationBar.setTabSelectedListener(this);
    }

    @Override
    public void initEvent() {

    }

    @Override
    protected BaseController getController() {
        return  LArtemis.getInstance().getMainController();
    }


    @Override
    public void onTabSelected(int position) {
        if(position == 0){
            mFragmentManager.beginTransaction().hide(mHospitalFragment).hide(mPraticalityFragment).hide(mMineFragment).show(mMainHomeFragment)
                    .commitAllowingStateLoss();
        }
        else if(position == 1){
            mFragmentManager.beginTransaction().hide(mPraticalityFragment).hide(mMineFragment).hide(mMainHomeFragment).show(mHospitalFragment)
                    .commitAllowingStateLoss();
        }
        else if(position == 2){
            mFragmentManager.beginTransaction().hide(mHospitalFragment).hide(mMineFragment).hide(mMainHomeFragment).show(mPraticalityFragment)
                    .commitAllowingStateLoss();
        }else if(position == 3){
            mFragmentManager.beginTransaction().hide(mHospitalFragment).hide(mPraticalityFragment).hide(mMainHomeFragment).show(mMineFragment)
                    .commitAllowingStateLoss();
        }
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }

    /** 
      * 菜单、返回键响应 
      */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            exitBy2Click();//调用双击退出函数  
        }
        return false;
    }

    /** 
      * 双击退出函数 
      */
    private static Boolean isExit = false;
    private void exitBy2Click(){
        Timer tExit = null;
        if(isExit == false){
            isExit = true;// 准备退出  
            CommonUtil.showSnackBar(bottomNavigationBar,"再按一次退出程序");
            tExit = new Timer();
            tExit.schedule(new TimerTask(){
                @Override
                public void run() {
                    isExit = false;// 取消退出  
                }
            },2000);// 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务  
        } else {
            finish();
            System.exit(0);
        }
    }
}
