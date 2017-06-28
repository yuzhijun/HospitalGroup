package com.lenovohit.lartemis_api.utils;

import com.lenovohit.lartemis_api.core.LArtemis;

/**
 * Created by yuzhijun on 2017/6/27.
 */
public class StringFetcher {
    public static String getString(int id) {
        return LArtemis.getInstance().getApplication().getString(id);
    }
}
