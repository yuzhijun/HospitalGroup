package com.lenovohit.lartemis_api.model;

import java.util.List;

/**
 * Created by yuzhijun on 2016/10/19.
 */
public class DoctorArrangeShow {
    private String AID;
    private String topLabel;
    private String bottomLabel;
    private String suplusNum;
    private boolean isShow;
    private String State;
    private String AppStart;
    private String AppDate;
    private double Money;
    private List<DoctorAppoint.SuplusInfo> Suplus;

    public DoctorArrangeShow(){}

    public DoctorArrangeShow(String AID, String topLabel, String bottomLabel, String suplusNum, String state, List<DoctorAppoint.SuplusInfo> Suplus,
                             String AppStart, String AppDate, double Money, boolean isShow){
        this.AID = AID;
        this.topLabel = topLabel;
        this.bottomLabel = bottomLabel;
        this.suplusNum = suplusNum;
        this.isShow = isShow;
        this.State = state;
        this.Suplus = Suplus;
        this.AppStart = AppStart;
        this.AppDate = AppDate;
        this.Money = Money;
    }

    public String getAID() {
        return AID;
    }

    public void setAID(String AID) {
        this.AID = AID;
    }

    public String getTopLabel() {
        return topLabel;
    }

    public void setTopLabel(String topLabel) {
        this.topLabel = topLabel;
    }

    public String getBottomLabel() {
        return bottomLabel;
    }

    public void setBottomLabel(String bottomLabel) {
        this.bottomLabel = bottomLabel;
    }

    public String getSuplusNum() {
        return suplusNum;
    }

    public void setSuplusNum(String suplusNum) {
        this.suplusNum = suplusNum;
    }

    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public List<DoctorAppoint.SuplusInfo> getSuplus() {
        return Suplus;
    }

    public void setSuplus(List<DoctorAppoint.SuplusInfo> suplus) {
        Suplus = suplus;
    }

    public String getAppStart() {
        return AppStart;
    }

    public void setAppStart(String appStart) {
        AppStart = appStart;
    }

    public String getAppDate() {
        return AppDate;
    }

    public void setAppDate(String appDate) {
        AppDate = appDate;
    }

    public double getMoney() {
        return Money;
    }

    public void setMoney(double money) {
        Money = money;
    }
}
