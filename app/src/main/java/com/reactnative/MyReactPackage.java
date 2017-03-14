package com.reactnative;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;
import com.reactnative.nativeUI.NativePaintViewManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MyReactPackage implements ReactPackage {

    @Override
    public List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {

        List<NativeModule> modules = new ArrayList<>();
        //将我们创建的类添加进原生模块列表中
        modules.add(new MyNativeModule(reactContext));
        return modules;
    }


    @Override
    public List<Class<? extends JavaScriptModule>> createJSModules() {

        //返回值需要修改
        return Collections.emptyList();
    }


    @Override
    public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {

        //返回值需要修改
//        return Collections.emptyList();

        // 因为自定义的原生View，需要返回native的ViewManager
        return Arrays.<ViewManager>asList(new NativePaintViewManager());

    }
}