package com.example.cfwifine.sxk.BaseAC;

import android.content.Context;

import com.xiaomi.mipush.sdk.MiPushMessage;
import com.xiaomi.mipush.sdk.PushMessageReceiver;

import io.rong.push.notification.PushNotificationMessage;

/**
 * Created by cfwifine on 17/3/9.
 */

public class RongIMPushNotificationReceiver extends io.rong.push.notification.PushMessageReceiver {

    @Override
    public boolean onNotificationMessageArrived(Context context, PushNotificationMessage pushNotificationMessage) {
        return false;
    }

    @Override
    public boolean onNotificationMessageClicked(Context context, PushNotificationMessage pushNotificationMessage) {
        return false;
    }
}
