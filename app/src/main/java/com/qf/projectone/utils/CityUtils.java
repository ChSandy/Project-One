package com.qf.projectone.utils;

import com.qf.projectone.bean.CityBean;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/22.
 */
public class CityUtils {

   public static final String[] lettes = {
            "A", "B", "C", "D", "E", "F",
            "G", "H", "I", "J", "K", "L",
            "M", "N", "O", "P", "Q", "R",
            "S", "T", "U", "V", "W", "X",
            "Y", "Z"};

    public static List<CityBean> getCityByJson(String url) throws Exception {
        ArrayList<CityBean> cities = new ArrayList<>();
        JSONObject json = new JSONObject(url);
        JSONObject citydatas = json.optJSONObject("cities");
        for (int i = 0; i < lettes.length; i++) {
            JSONArray ja = citydatas.optJSONArray(lettes[i]);
            if (ja != null) {
                for (int j = 0; j < ja.length(); j++) {
                    JSONObject tmp = ja.getJSONObject(j);
                    CityBean bean = new CityBean(tmp, i, lettes[i]);
                    cities.add(bean);
                }
            }
        }

        return cities;
    }
}
