package com.chinadream.www.userclient.listener;

/**
 * 用于监听第三方登录的成功与失败
 */
public interface OnLoginSuccessListener{
    void onSuccess(String result);
    void onError(Throwable ex, boolean isOnCallback);
}
