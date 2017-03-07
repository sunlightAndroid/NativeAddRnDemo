package com.reactnative;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.uimanager.IllegalViewOperationException;

public class MyNativeModule extends ReactContextBaseJavaModule {


    private Context mContext;

    public MyNativeModule(ReactApplicationContext reactContext) {
        super(reactContext);

        mContext = reactContext;
    }

    @Override
    public String getName() {

        //返回的这个名字是必须的，在rn代码中需要这个名字来调用该类的方法。
        return "MyNativeModule";
    }


    //函数不能有返回值，因为被调用的原生代码是异步的，原生代码执行结束之后只能通过回调函数或者发送信息给rn那边。

    @ReactMethod
    public void rnCallNative(String msg) {

        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 创建给js调用的方法 将网络请求的结果以回调的方式传递给js
     *
     * @param url
     * @param callback
     */
    @ReactMethod
    public void getResult(String url, final Callback callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
//                    模拟网络请求数据的操作
                    String result = "我是请求结果";
                    callback.invoke(true, result);

                } catch (Exception e) {

                }
            }
        }).start();
    }


    @ReactMethod
    public void tryCallBack(String name, String psw, Callback errorCallback, Callback successCallback) {
        try {
            if (TextUtils.isEmpty(name) && TextUtils.isEmpty(psw)) {
                // 失败时回调
                errorCallback.invoke("user or psw  is empty");
            }
            // 成功时回调
            successCallback.invoke("add user success");
        } catch (IllegalViewOperationException e) {
            // 失败时回调
            errorCallback.invoke(e.getMessage());
        }
    }


    /**
     * 回调给android端的数据
     *
     * @param callback
     */
    @ReactMethod
    public void renderAndroidData(Callback callback) {
        callback.invoke("android data");
    }


}