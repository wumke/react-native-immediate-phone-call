package com.github.wumke.RNImmediatePhoneCall;

import android.support.annotation.NonNull;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RNImmediatePhoneCallPackage implements ReactPackage {

    @Override
    public List<NativeModule> createNativeModules(
            ReactApplicationContext reactContext) {
        List<NativeModule> modules = new ArrayList<>();

        modules.add(new RNImmediatePhoneCallModule(reactContext));

        return modules;
    }

    // override removed to be compatible with rn0.47+
    //@Override
    public List<Class<? extends JavaScriptModule>> createJSModules() {
        return Collections.emptyList();
    }

    @Override
    public List<ViewManager> createViewManagers(
            ReactApplicationContext reactContext) {
        return Collections.emptyList();
    }
	
	public static void onRequestPermissionsResult(int requestCode, @NonNull String permissions[],
                                                  @NonNull int[] grantResults) {
        RNImmediatePhoneCallModule.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

}
