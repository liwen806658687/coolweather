package com.liyun.coolweather.util;

import android.text.TextUtils;

import com.liyun.coolweather.db.City;
import com.liyun.coolweather.db.County;
import com.liyun.coolweather.db.Province;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Utility {
    //解析和处理服务器返回的省级数据
    public static boolean handleProvinceResponse(String response) {
        if (!TextUtils.isEmpty(response)) {
            //如果不为空
            try {
                JSONArray allProvinces = new JSONArray(response);
                for (int i = 0; i < allProvinces.length(); i++) {
                    JSONObject provinceObject = allProvinces.getJSONObject(i);
                    Province province = new Province();
                    province.setProvinceCode(provinceObject.getInt("id"));
                    province.setProvinceName(provinceObject.getString("name"));
                    province.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
        return false;
    }

    //解析和处理服务器返回的市数据
    public static boolean handleCityResponse(String response,int provinceId) {
        try {
            JSONArray allCitys = new JSONArray(response);
            for (int i = 0; i < allCitys.length(); i++) {
                JSONObject citysJSONObject = allCitys.getJSONObject(i);
                City city = new City();
                city.setCityCode(citysJSONObject.getInt("id"));
                city.setCityName(citysJSONObject.getString("name"));
                city.setProvinceId(provinceId);
                city.save();
            }
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return false;
    }

    //解析和处理服务器返回的县数据
    public static boolean handleCountyResponse(String response, int cityId) {
        try {
            JSONArray allCountys = new JSONArray(response);
            for (int i = 0; i < allCountys.length(); i++) {
                JSONObject countysJSONObject = allCountys.getJSONObject(i);
                County county = new County();
                county.setCityId(cityId);
                county.setCountyName(countysJSONObject.getString("name"));
                county.setWeatherId(countysJSONObject.getString("weather_id"));
                county.save();
            }
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }
}
