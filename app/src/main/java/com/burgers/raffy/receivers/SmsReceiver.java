package com.burgers.raffy.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

import com.burgers.raffy.utils.Constants;
import com.burgers.raffy.utils.DBUtils;

/**
 * Created by Neil on 5/28/2017.
 */

public class SmsReceiver extends BroadcastReceiver{

    public void onReceive(Context context, Intent intent){
        if (intent.getAction().equals(Constants.SMS_RECEIVED)) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                Object[] pdus = (Object[]) bundle.get("pdus");
                if (pdus.length == 0) {
                    return;
                }
                // large message might be broken into many
                SmsMessage[] messages = new SmsMessage[pdus.length];
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < pdus.length; i++) {
                    messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                    sb.append(messages[i].getMessageBody());
                }
                String sender = messages[0].getOriginatingAddress();
                String[] message = sb.toString().split(" ");
                if(Constants.ALLOWED_NUMBER.equals(sender)){
                    switch(message[0]){
                        case Constants.ADD:
                            DBUtils.addToDB(context, message[1], message[2], message[3]);
                    }
                }
                // prevent any other broadcast receivers from receiving broadcast
                abortBroadcast();
            }
        }
    }
}
