package com.lenovohit.lartemis_api.model;

import java.util.List;

/**
 * Created by yuzhijun on 2016/8/4.
 */
public class HospitalMainPage {


    /**
     * HID : 1
     * HospitalName : sample string 2
     * FullName : sample string 3
     * HospitalLevel : sample string 4
     * HospitalLevelName : sample string 5
     * HospitalType : sample string 6
     * HospitalInfo : sample string 7
     * LogoUrl : sample string 8
     * IsCollection : 9
     * PositionE : 10.0
     * PositionN : 11.0
     * ProvinceID : 12
     * ProvinceName : sample string 13
     * CityID : 14
     * CityName : sample string 15
     * Focus : 16
     * Mode : 17
     * FullInfo : sample string 18
     * PanoramaUrl : sample string 19
     * OpenModule : null
     */

    private String HID;
    private String HospitalName;
    private String FullName;
    private String HospitalLevel;
    private String HospitalLevelName;
    private String HospitalType;
    private String HospitalInfo;
    private String LogoUrl;
    private String IsCollection;
    private String PositionE;
    private String PositionN;
    private String ProvinceID;
    private String ProvinceName;
    private String CityID;
    private String CityName;
    private int Focus;
    private String Mode;
    private String FullInfo;
    private String PanoramaUrl;
    private String Address;
    private String Phone;
    private String PublicTransport;
    private List<IHospitalModule> OpenModule;

    public String getPublicTransport() {
        return PublicTransport;
    }

    public void setPublicTransport(String publicTransport) {
        PublicTransport = publicTransport;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getHospitalName() {
        return HospitalName;
    }

    public void setHospitalName(String HospitalName) {
        this.HospitalName = HospitalName;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String FullName) {
        this.FullName = FullName;
    }

    public String getHospitalLevel() {
        return HospitalLevel;
    }

    public void setHospitalLevel(String HospitalLevel) {
        this.HospitalLevel = HospitalLevel;
    }

    public String getHospitalLevelName() {
        return HospitalLevelName;
    }

    public void setHospitalLevelName(String HospitalLevelName) {
        this.HospitalLevelName = HospitalLevelName;
    }

    public String getHospitalType() {
        return HospitalType;
    }

    public void setHospitalType(String HospitalType) {
        this.HospitalType = HospitalType;
    }

    public String getHospitalInfo() {
        return HospitalInfo;
    }

    public void setHospitalInfo(String HospitalInfo) {
        this.HospitalInfo = HospitalInfo;
    }

    public String getLogoUrl() {
        return LogoUrl;
    }

    public void setLogoUrl(String LogoUrl) {
        this.LogoUrl = LogoUrl;
    }

    public String getIsCollection() {
        return IsCollection;
    }

    public void setIsCollection(String isCollection) {
        IsCollection = isCollection;
    }

    public String getProvinceName() {
        return ProvinceName;
    }

    public void setProvinceName(String ProvinceName) {
        this.ProvinceName = ProvinceName;
    }



    public String getCityName() {
        return CityName;
    }

    public void setCityName(String CityName) {
        this.CityName = CityName;
    }

    public int getFocus() {
        return Focus;
    }

    public void setFocus(int Focus) {
        this.Focus = Focus;
    }



    public String getFullInfo() {
        return FullInfo;
    }

    public void setFullInfo(String FullInfo) {
        this.FullInfo = FullInfo;
    }

    public String getPanoramaUrl() {
        return PanoramaUrl;
    }

    public void setPanoramaUrl(String PanoramaUrl) {
        this.PanoramaUrl = PanoramaUrl;
    }

    public List<IHospitalModule> getOpenModule() {
        return OpenModule;
    }

    public void setOpenModule(List<IHospitalModule> openModule) {
        OpenModule = openModule;
    }

    public String getHID() {
        return HID;
    }

    public void setHID(String HID) {
        this.HID = HID;
    }

    public String getPositionE() {
        return PositionE;
    }

    public void setPositionE(String positionE) {
        PositionE = positionE;
    }

    public String getPositionN() {
        return PositionN;
    }

    public void setPositionN(String positionN) {
        PositionN = positionN;
    }

    public String getProvinceID() {
        return ProvinceID;
    }

    public void setProvinceID(String provinceID) {
        ProvinceID = provinceID;
    }

    public String getCityID() {
        return CityID;
    }

    public void setCityID(String cityID) {
        CityID = cityID;
    }

    public String getMode() {
        return Mode;
    }

    public void setMode(String mode) {
        Mode = mode;
    }

    public class  IHospitalModule{
        private String MID;
        private String ModuleCode;
        private String ModuleName;
        private String State;
        private String ModuleEvent;
        private String EventParam;
        private String ModuleType;
        private String IconURL;
        private String Des;

        public String getDes() {
            return Des;
        }

        public void setDes(String des) {
            Des = des;
        }

        public String getMID() {
            return MID;
        }

        public void setMID(String MID) {
            this.MID = MID;
        }

        public String getModuleCode() {
            return ModuleCode;
        }

        public void setModuleCode(String moduleCode) {
            ModuleCode = moduleCode;
        }

        public String getModuleName() {
            return ModuleName;
        }

        public void setModuleName(String moduleName) {
            ModuleName = moduleName;
        }

        public String getState() {
            return State;
        }

        public void setState(String state) {
            State = state;
        }

        public String getModuleEvent() {
            return ModuleEvent;
        }

        public void setModuleEvent(String moduleEvent) {
            ModuleEvent = moduleEvent;
        }

        public String getEventParam() {
            return EventParam;
        }

        public void setEventParam(String eventParam) {
            EventParam = eventParam;
        }

        public String getModuleType() {
            return ModuleType;
        }

        public void setModuleType(String moduleType) {
            ModuleType = moduleType;
        }

        public String getIconURL() {
            return IconURL;
        }

        public void setIconURL(String iconURL) {
            IconURL = iconURL;
        }
    }
}
