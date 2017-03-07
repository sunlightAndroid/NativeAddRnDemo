package com.reactnative;

import com.facebook.react.ReactActivity;

// blog http://m.blog.csdn.net/article/details?id=52817016
//public class MyReactActivity extends Activity implements DefaultHardwareBackBtnHandler {
//    private ReactRootView mReactRootView;
//    private ReactInstanceManager mReactInstanceManager;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        mReactRootView = new ReactRootView(this);
//        mReactInstanceManager = ReactInstanceManager.builder()
//                .setApplication(getApplication())
//                .setBundleAssetName("index.android.bundle")
//                .setJSMainModuleName("index.android")
//                .addPackage(new MainReactPackage())
//                .setUseDeveloperSupport(BuildConfig.DEBUG)
//                .setInitialLifecycleState(LifecycleState.RESUMED)
//                .build();
//
//        // 注意这里的HelloWorld必须对应“index.android.js”中的
//        // “AppRegistry.registerComponent()”的第一个参数
//        mReactRootView.startReactApplication(mReactInstanceManager, "RNComponent", null);
//
//        setContentView(mReactRootView);
//    }
//
//    @Override
//    public void invokeDefaultOnBackPressed() {
//        super.onBackPressed();
//    }
//}

public class MyReactActivity extends ReactActivity {

    @Override
    protected String getMainComponentName() {
        return "RNComponent";
    }

}