package com.chinadream.www.userclient.bean;

/**
 * 商品详情实体类
 */
public class CommodityDetailsBean {
    /**
     * "gid":"3","gname":"\u5eb7\u5e08\u5085\u77ff\u6cc9\u6c34","gprice":"3",
     * "gstck":"70","gstd":"1000ml","gslg":"http:\/\/10.1.250.3:8091\/Uploads\/2017-01-19\/1.jpg"}
     */
    private String commId,commName,commPrice,commSrck,commStd,commSurl;

    public CommodityDetailsBean(String commId, String commName, String commPrice, String commSrck,
                                String commStd, String commSurl) {
        this.commId = commId;
        this.commName = commName;
        this.commPrice = commPrice;
        this.commSrck = commSrck;
        this.commStd = commStd;
        this.commSurl = commSurl;
    }

    public void setCommId(String commId) {
        this.commId = commId;
    }

    public void setCommName(String commName) {
        this.commName = commName;
    }

    public void setCommPrice(String commPrice) {
        this.commPrice = commPrice;
    }

    public void setCommSrck(String commSrck) {
        this.commSrck = commSrck;
    }

    public void setCommStd(String commStd) {
        this.commStd = commStd;
    }

    public void setCommSurl(String commSurl) {
        this.commSurl = commSurl;
    }

    public String getCommId() {
        return commId;
    }

    public String getCommName() {
        return commName;
    }

    public String getCommPrice() {
        return commPrice;
    }

    public String getCommSrck() {
        return commSrck;
    }

    public String getCommStd() {
        return commStd;
    }

    public String getCommSurl() {
        return commSurl;
    }
}
