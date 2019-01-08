package com.github.wumke.RNImmediatePhoneCall;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import android.telecom.PhoneAccountHandle;
import android.telecom.TelecomManager;
import java.util.List;
import java.util.ArrayList;


public class RNImmediatePhoneCallModule extends ReactContextBaseJavaModule {

    private static RNImmediatePhoneCallModule rnImmediatePhoneCallModule;

    private ReactApplicationContext reactContext;
    private String number = "";
    private int slot;
	
    private static final int REQUEST_CODE_PERMISSION = 101;	
	
    private List<PhoneAccountHandle> phoneAccountHandleList;
	private final String simSlotName[] = {
            "extra_asus_dial_use_dualsim",
            "com.android.phone.extra.slot",
            "slot",
            "simslot",
            "sim_slot",
            "subscription",
            "Subscription",
            "phone",
            "com.android.phone.DialingMode",
            "simSlot",
            "slot_id",
            "simId",
            "simnum",
            "phone_type",
            "slotId",
            "slotIdx"
};

    public RNImmediatePhoneCallModule(ReactApplicationContext reactContext) {
        super(reactContext);
        if (rnImmediatePhoneCallModule == null) {
            rnImmediatePhoneCallModule = this;
        }
        rnImmediatePhoneCallModule.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "RNImmediatePhoneCall";
    }

    @ReactMethod
    public void immediatePhoneCall(String number, int slot) {
        rnImmediatePhoneCallModule.number = Uri.encode(number);
        rnImmediatePhoneCallModule.slot = slot;

		int callPhone = ContextCompat.checkSelfPermission(reactContext.getApplicationContext(), Manifest.permission.CALL_PHONE);
		int readPhoneState = ContextCompat.checkSelfPermission(reactContext.getApplicationContext(), Manifest.permission.READ_PHONE_STATE);

		ArrayList<String> permiss = new ArrayList<String>();
		if (callPhone != PackageManager.PERMISSION_GRANTED) {
            permiss.add(Manifest.permission.CALL_PHONE);
        }
		
		if (readPhoneState != PackageManager.PERMISSION_GRANTED) {
            permiss.add(Manifest.permission.READ_PHONE_STATE);
        }
		
		if (permiss.size() > 0) {
            String[] per = new String[permiss.size()];
            ActivityCompat.requestPermissions(getCurrentActivity(), permiss.toArray(per), REQUEST_CODE_PERMISSION);
        } else {
			call();
		}
    }
	
	@SuppressLint("MissingPermission")
    private static void call() {
        String url = "tel:" + rnImmediatePhoneCallModule.number;
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse(url));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.putExtra("com.android.phone.force.slot", true);
        for (String s : rnImmediatePhoneCallModule.simSlotName){
            intent.putExtra(s,rnImmediatePhoneCallModule.slot);
        }

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            intent.putExtra("Cdma_Supp", true);
            TelecomManager telecomManager = (TelecomManager) rnImmediatePhoneCallModule.reactContext.getSystemService(Context.TELECOM_SERVICE);
            rnImmediatePhoneCallModule.phoneAccountHandleList = telecomManager.getCallCapablePhoneAccounts();
            if (rnImmediatePhoneCallModule.phoneAccountHandleList != null && rnImmediatePhoneCallModule.phoneAccountHandleList.size() > 0)
            {
                intent.putExtra("android.telecom.extra.PHONE_ACCOUNT_HANDLE",
                        rnImmediatePhoneCallModule.phoneAccountHandleList.get(rnImmediatePhoneCallModule.slot));
            }
        }
        rnImmediatePhoneCallModule.reactContext.startActivity(intent);
    }

    public static void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_PERMISSION: {

				boolean isGrandAll = true;
                for (int i = 0; i < grantResults.length; i++) {
                    if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                        isGrandAll = false;
                        break;
                    }
                }
				
				if (!isGrandAll) {
					Toast.makeText(rnImmediatePhoneCallModule.reactContext.getCurrentActivity(), "you need to grant all permission", Toast.LENGTH_LONG).show();
				}else{
					call();
				}
				
            }
        }
    }
}
