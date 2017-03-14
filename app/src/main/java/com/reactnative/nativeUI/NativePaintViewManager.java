package com.reactnative.nativeUI;

import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;


/**
 * Created by gechuanguang on 2017/3/13.
 * 邮箱：1944633835@qq.com
 */

public class NativePaintViewManager extends SimpleViewManager<MyPaintView> {

    public static final String REACT_CLASS = "RCTMyPaintView";


    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @Override
    protected MyPaintView createViewInstance(ThemedReactContext reactContext) {
        return new MyPaintView(reactContext);
    }

    @ReactProp(name = "color")
    public void setColor(MyPaintView paintView, String color) {
        paintView.setColor(color);
    }


    @ReactProp(name = "raduis")
    public void setRaduis(MyPaintView paintView, Integer raduis) {
        paintView.setRaduis(raduis);
    }

}
