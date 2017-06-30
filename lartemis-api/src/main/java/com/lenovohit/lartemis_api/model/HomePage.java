package com.lenovohit.lartemis_api.model;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * Created by yuzhijun on 2017/6/30.
 */

public class HomePage implements MultiItemEntity {
    public static final int TOP_BANNER = 0;//顶部轮询
    public static final int TOP_MODULE = 1;//顶部模块
    public static final int RECOMMOND_HOS = 2;//推荐医院
    public static final int RECOMMOND_DOC = 3;//推荐医生
    public int itemType;

    private List<Doctor> CollectDoctors;
    private List<Doctor> RecommendDoctors;
    private List<Hospitals> CollectHospitals;
    private List<Hospitals> RecommendHospitals;
    private List<TopNew> TopNews;
    private List<IIndexPageModel>  IndexPageModels;

    @Override
    public int getItemType() {
        return itemType;
    }

    public int getSpanSize() {
        return 4;
    }

    public List<Doctor> getCollectDoctors() {
        return CollectDoctors;
    }

    public void setCollectDoctors(List<Doctor> collectDoctors) {
        CollectDoctors = collectDoctors;
    }

    public List<Doctor> getRecommendDoctors() {
        return RecommendDoctors;
    }

    public void setRecommendDoctors(List<Doctor> recommendDoctors) {
        RecommendDoctors = recommendDoctors;
    }

    public List<Hospitals> getCollectHospitals() {
        return CollectHospitals;
    }

    public void setCollectHospitals(List<Hospitals> collectHospitals) {
        CollectHospitals = collectHospitals;
    }

    public List<Hospitals> getRecommendHospitals() {
        return RecommendHospitals;
    }

    public void setRecommendHospitals(List<Hospitals> recommendHospitals) {
        RecommendHospitals = recommendHospitals;
    }

    public List<TopNew> getTopNews() {
        return TopNews;
    }

    public void setTopNews(List<TopNew> topNews) {
        TopNews = topNews;
    }

    public List<IIndexPageModel> getIndexPageModels() {
        return IndexPageModels;
    }

    public void setIndexPageModels(List<IIndexPageModel> indexPageModels) {
        IndexPageModels = indexPageModels;
    }

    public class IIndexPageModel{
        private String ModuleName;
        private String ModuleCode;
        private String IconURL;
        private String Des;

        public String getDes() {
            return Des;
        }

        public void setDes(String des) {
            Des = des;
        }


        public String getModuleName() {
            return ModuleName;
        }

        public void setModuleName(String moduleName) {
            ModuleName = moduleName;
        }

        public String getModuleCode() {
            return ModuleCode;
        }

        public void setModuleCode(String moduleCode) {
            ModuleCode = moduleCode;
        }

        public String getIconURL() {
            return IconURL;
        }

        public void setIconURL(String iconURL) {
            IconURL = iconURL;
        }
    }
}
