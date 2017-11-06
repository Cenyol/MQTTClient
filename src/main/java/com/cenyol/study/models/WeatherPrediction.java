package com.cenyol.study.models;

import com.cenyol.study.utils.HttpRequest;
import com.google.gson.*;

import java.util.Iterator;

/**
 * Created by cenyol on 11/06/2017.
 */
public class WeatherPrediction {
    private String date;
    private String text_day;
    private String code_day;
    private String text_night;
    private String code_night;
    private String high;
    private String low;
    private String precip;
    private String wind_direction;
    private String wind_direction_degree;
    private String wind_speed;
    private String wind_scale;

    // 使用心知天气预报API：https://www.seniverse.com/doc#daily
    public String getCurrent() {
        String string = HttpRequest.sendGet("https://api.seniverse.com/v3/weather/now.json", "key=9itmxkgmzcjcbyr4&location=ip&language=en&unit=c&start=0&days=5");
//        String string = "{\"results\":[{\"location\":{\"id\":\"WTMKQ069CCJ7\",\"name\":\"杭州\",\"country\":\"CN\",\"path\":\"杭州,杭州,浙江,中国\",\"timezone\":\"Asia/Shanghai\",\"timezone_offset\":\"+08:00\"},\"now\":{\"text\":\"小雨\",\"code\":\"13\",\"temperature\":\"21\"},\"last_update\":\"2017-06-11T20:35:00+08:00\"}]}";

        JsonArray jsonArray = new JsonParser().parse(string).getAsJsonObject().get("results").getAsJsonArray();
        JsonObject jsonObject = jsonArray.get(0).getAsJsonObject().get("now").getAsJsonObject();
        return jsonObject.toString();

//        JsonObject retJsonObj = new JsonObject();
//        retJsonObj.addProperty("text", jsonObject.get("text").getAsString());
//        retJsonObj.addProperty("code", jsonObject.get("code").getAsString());
//        retJsonObj.addProperty("temp", jsonObject.get("temperature").getAsString());
//        return retJsonObj.toString();
    }

    public String getNextThreeDaysSimple() {
        String string = HttpRequest.sendGet("https://api.seniverse.com/v3/weather/daily.json", "key=9itmxkgmzcjcbyr4&location=ip&language=zh-Hans&unit=c&start=0&days=5");
//        String string = "{\"results\":[{\"location\":{\"id\":\"WTMKQ069CCJ7\",\"name\":\"杭州\",\"country\":\"CN\",\"path\":\"杭州,杭州,浙江,中国\",\"timezone\":\"Asia/Shanghai\",\"timezone_offset\":\"+08:00\"},\"daily\":[{\"date\":\"2017-06-11\",\"text_day\":\"小雨\",\"code_day\":\"13\",\"text_night\":\"大雨\",\"code_night\":\"15\",\"high\":\"32\",\"low\":\"20\",\"precip\":\"\",\"wind_direction\":\"西南\",\"wind_direction_degree\":\"225\",\"wind_speed\":\"10\",\"wind_scale\":\"2\"},{\"date\":\"2017-06-12\",\"text_day\":\"大雨\",\"code_day\":\"15\",\"text_night\":\"大雨\",\"code_night\":\"15\",\"high\":\"23\",\"low\":\"19\",\"precip\":\"\",\"wind_direction\":\"东\",\"wind_direction_degree\":\"90\",\"wind_speed\":\"15\",\"wind_scale\":\"3\"},{\"date\":\"2017-06-13\",\"text_day\":\"中雨\",\"code_day\":\"14\",\"text_night\":\"阵雨\",\"code_night\":\"10\",\"high\":\"23\",\"low\":\"19\",\"precip\":\"\",\"wind_direction\":\"东\",\"wind_direction_degree\":\"90\",\"wind_speed\":\"10\",\"wind_scale\":\"2\"}],\"last_update\":\"2017-06-11T18:00:00+08:00\"}]}";

        JsonArray jsonArray = new JsonParser().parse(string).getAsJsonObject().get("results").getAsJsonArray();
        jsonArray = jsonArray.get(0).getAsJsonObject().get("daily").getAsJsonArray();

        JsonArray retJsonArray = new JsonArray();
        for(Iterator iterator = jsonArray.iterator(); iterator.hasNext();){
            JsonObject jsonObject=(JsonObject) iterator.next();
            JsonObject retJsonObj = new JsonObject();
            retJsonObj.addProperty("date", jsonObject.get("date").getAsString());
            retJsonObj.addProperty("day", jsonObject.get("text_day").getAsString());
            retJsonObj.addProperty("night", jsonObject.get("text_night").getAsString());
            retJsonObj.addProperty("temp", jsonObject.get("low").getAsString() + "~" + jsonObject.get("high").getAsString() + "°C");
            retJsonObj.addProperty("wind", jsonObject.get("wind_direction").getAsString() + "风 " + jsonObject.get("wind_scale").getAsString() + "级");
            retJsonArray.add(retJsonObj);
        }

        return retJsonArray.toString();
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getText_day() {
        return text_day;
    }

    public void setText_day(String text_day) {
        this.text_day = text_day;
    }

    public String getCode_day() {
        return code_day;
    }

    public void setCode_day(String code_day) {
        this.code_day = code_day;
    }

    public String getText_night() {
        return text_night;
    }

    public void setText_night(String text_night) {
        this.text_night = text_night;
    }

    public String getCode_night() {
        return code_night;
    }

    public void setCode_night(String code_night) {
        this.code_night = code_night;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getPrecip() {
        return precip;
    }

    public void setPrecip(String precip) {
        this.precip = precip;
    }

    public String getWind_direction() {
        return wind_direction;
    }

    public void setWind_direction(String wind_direction) {
        this.wind_direction = wind_direction;
    }

    public String getWind_direction_degree() {
        return wind_direction_degree;
    }

    public void setWind_direction_degree(String wind_direction_degree) {
        this.wind_direction_degree = wind_direction_degree;
    }

    public String getWind_speed() {
        return wind_speed;
    }

    public void setWind_speed(String wind_speed) {
        this.wind_speed = wind_speed;
    }

    public String getWind_scale() {
        return wind_scale;
    }

    public void setWind_scale(String wind_scale) {
        this.wind_scale = wind_scale;
    }
}
