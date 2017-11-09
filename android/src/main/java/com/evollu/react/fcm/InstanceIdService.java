package com.evollu.react.fcm;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import io.intercom.android.sdk.push.IntercomPushClient;

public class InstanceIdService extends FirebaseInstanceIdService {

    private static final String TAG = "InstanceIdService";
    private final IntercomPushClient intercomPushClient = new IntercomPushClient();

    /**
     * Called if InstanceID token is updated. This may occur if the security of
     * the previous token had been compromised. This call is initiated by the
     * InstanceID provider.
     */
    // [START refresh_token]
    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        intercomPushClient.sendTokenToIntercom(getApplication(), refreshedToken);
        Log.d(TAG, "Refreshed token: " + refreshedToken);

        // Broadcast refreshed token

        Intent i = new Intent("com.evollu.react.fcm.FCMRefreshToken");
        Bundle bundle = new Bundle();
        bundle.putString("token", refreshedToken);
        i.putExtras(bundle);
        sendBroadcast(i);
    }
}