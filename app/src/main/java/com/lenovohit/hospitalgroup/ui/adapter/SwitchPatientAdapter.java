package com.lenovohit.hospitalgroup.ui.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lenovohit.hospitalgroup.R;
import com.lenovohit.lartemis_api.model.CommonUser;
import com.lenovohit.lartemis_api.utils.CommonUtil;
import com.lenovohit.lartemis_api.utils.Constants;
import com.lenovohit.lartemis_api.views.swiperecyclerview.ItemTouchListener;
import com.lenovohit.lartemis_api.views.swiperecyclerview.SwipeItemLayout;

import java.util.List;

/**
 * Created by Administrator on 2017-07-06.
 */

public class SwitchPatientAdapter extends BaseItemDraggableAdapter<CommonUser,BaseViewHolder> {
    private ItemTouchListener mItemTouchListener;

    public SwitchPatientAdapter(@LayoutRes int layoutResId, @Nullable List<CommonUser> data, ItemTouchListener touchListener) {
        super(layoutResId, data);
        mItemTouchListener = touchListener;
    }

    @Override
    protected void convert(final BaseViewHolder helper, final CommonUser item) {
       helper.setText(R.id.tvHosName, CommonUtil.isStrEmpty(item.getHospitalName())?"":item.getHospitalName());
        helper.setText(R.id.tvPatientName,CommonUtil.isStrEmpty(item.getName())?"":item.getName());
        helper.setText(R.id.tvPatientSex,CommonUtil.isStrEmpty(item.getSex())?"":item.getSexName());
        helper.setText(R.id.tvPatientPhoneNumber,CommonUtil.isStrEmpty(item.getPhoneNumber())?"":item.getPhoneNumber());
        helper.setText(R.id.tvPatientJZCard,CommonUtil.isStrEmpty(item.getHospitalCard())?"":"就诊卡号:"+item.getHospitalCard());
        helper.setText(R.id.tvPatientIDCard,CommonUtil.isStrEmpty(item.getIDCard())?"":"身份证号:"+item.getIDCard());
        ImageView ivCheckBox = helper.getView(R.id.ivCheckedBox);
        if (item.isSelected()){
            ivCheckBox.setImageResource(R.mipmap.lx_iv_check_on);
        }else {
            ivCheckBox.setImageResource(R.mipmap.lx_iv_check_out);
        }

        final SwipeItemLayout swipeLayout = (SwipeItemLayout) helper.itemView;
        swipeLayout.setSwipeEnable(helper.getItemViewType() != Constants.Type.DISABLE_SWIPE_MENU);
        // 不使用helper设置点击事件是因为child点击的时候无法获取到ItemView……，无法关闭菜单
        // 希望BaseRecyclerViewAdapterHelper这个库的作者以后会加上……
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mItemTouchListener.onItemClick(item,helper.getAdapterPosition());
            }
        });

        final View rightMenu = helper.getView(R.id.right_menu);
        if (rightMenu != null) {
            rightMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mItemTouchListener.onRightMenuClick(helper.getAdapterPosition());
                    swipeLayout.close();
                }
            });
        }
    }
}
