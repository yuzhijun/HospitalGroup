package com.lenovohit.lartemis_api.model;

/**
 * Created by yuzhijun on 2016/12/5.
 */
public class AppVersion {
    private String ID;
    private String AppType;
    private String AppTag;
    private String VersionCode;
    private String DownloadUrl;
    private String PublicDate;
    private String UpdateType;
    private String AppIntro;
    private String AppData;
    private String CreateTime;


    public String getAppTag() {
        return AppTag;
    }

    public void setAppTag(String AppTag) {
        this.AppTag = AppTag;
    }

    public String getVersionCode() {
        return VersionCode;
    }

    public void setVersionCode(String VersionCode) {
        this.VersionCode = VersionCode;
    }

    public String getDownloadUrl() {
        return DownloadUrl;
    }

    public void setDownloadUrl(String DownloadUrl) {
        this.DownloadUrl = DownloadUrl;
    }

    public String getPublicDate() {
        return PublicDate;
    }

    public void setPublicDate(String PublicDate) {
        this.PublicDate = PublicDate;
    }

    public String getAppIntro() {
        return AppIntro;
    }

    public void setAppIntro(String AppIntro) {
        this.AppIntro = AppIntro;
    }

    public String getAppData() {
        return AppData;
    }

    public void setAppData(String AppData) {
        this.AppData = AppData;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String CreateTime) {
        this.CreateTime = CreateTime;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getAppType() {
        return AppType;
    }

    public void setAppType(String appType) {
        AppType = appType;
    }

    public String getUpdateType() {
        return UpdateType;
    }

    public void setUpdateType(String updateType) {
        UpdateType = updateType;
    }

    @Override
    public String toString() {
        return "AppVersion{" +
                "ID='" + ID + '\'' +
                ", AppType='" + AppType + '\'' +
                ", AppTag='" + AppTag + '\'' +
                ", VersionCode='" + VersionCode + '\'' +
                ", DownloadUrl='" + DownloadUrl + '\'' +
                ", PublicDate='" + PublicDate + '\'' +
                ", UpdateType='" + UpdateType + '\'' +
                ", AppIntro='" + AppIntro + '\'' +
                ", AppData='" + AppData + '\'' +
                ", CreateTime='" + CreateTime + '\'' +
                '}';
    }
}
