package com.chinadream.www.userclient.bean;


import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.poisearch.Photo;

import java.util.List;

public class ShopBDPoiBean {
    int distance;
    List<Photo> photoList;
    String poiId;
    String telNum;
    String shopTitle;
    String typeCode;
    LatLonPoint latLonPoi;
    String address;
    String typeDes;
    String shopUrl;
    String evaluate;
    String openTime;
    String cityName;
    String email;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public List<Photo> getPhotoList() {
        return photoList;
    }

    public void setPhotoList(List<Photo> photoList) {
        this.photoList = photoList;
    }

    public String getPoiId() {
        return poiId;
    }

    public void setPoiId(String poiId) {
        this.poiId = poiId;
    }

    public String getTelNum() {
        return telNum;
    }

    public void setTelNum(String telNum) {
        this.telNum = telNum;
    }

    public String getShopTitle() {
        return shopTitle;
    }

    public void setShopTitle(String shopTitle) {
        this.shopTitle = shopTitle;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public LatLonPoint getLatLonPoi() {
        return latLonPoi;
    }

    public void setLatLonPoi(LatLonPoint latLonPoi) {
        this.latLonPoi = latLonPoi;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTypeDes() {
        return typeDes;
    }

    public void setTypeDes(String typeDes) {
        this.typeDes = typeDes;
    }

    public String getShopUrl() {
        return shopUrl;
    }

    public void setShopUrl(String shopUrl) {
        this.shopUrl = shopUrl;
    }

    public String getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(String evaluate) {
        this.evaluate = evaluate;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public ShopBDPoiBean(int distance, List<Photo> photoList, String poiId,
                         String telNum, String shopTitle, String typeCode,
                         LatLonPoint latLonPoi, String address, String typeDes,
                         String shopUrl, String evaluate, String openTime,String cityName,
                                 String email) {
        this.distance=distance;
        this.photoList=photoList;
        this.poiId=poiId;
        this.telNum=telNum;
        this.shopTitle=shopTitle;
        this.typeCode=typeCode;
        this.latLonPoi=latLonPoi;
        this.address=address;
        this.typeDes=typeDes;
        this.shopUrl=shopUrl;
        this.evaluate=evaluate;
        this.openTime=openTime;
        this.cityName=cityName;
        this.email=email;

    }

}
