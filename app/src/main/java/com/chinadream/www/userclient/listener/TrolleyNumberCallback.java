package com.chinadream.www.userclient.listener;

public interface TrolleyNumberCallback {
    // 给Activity提供数字变化后的回调接口
    //@param  number 数量
    // @param  price 单价
    //点击加时候监听
    void numberaddLoad(int number, double price);
    //点击减时候监听
    void numbersubLoad(int number, double price);
}
