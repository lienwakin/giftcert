package com.burgers.raffy.utils;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.telephony.SmsManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import java.io.OutputStream;
import java.util.ArrayList;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.WHITE;

/**
 * Created by Neil on 6/3/2017.
 */

public class Utils {
    public static boolean isThereSameKey(Context context, String key){
        // Filter results WHERE "title" = 'My Title'
        String selection = Constants.COLUMN_KEY + " = ? ";
        String[] selectionArgs = { key };

        Cursor cursor = DBUtils.searchDB(context, selection, selectionArgs);

        if(cursor.getCount()==0){
            return false;
        }else return true;
    }

    public static void sendMessage(Context context, String message){
        try {
            SmsManager smsManager = SmsManager.getDefault();
            ArrayList<String> dividedMessage = smsManager.divideMessage(message);
            for(String indiMessage : dividedMessage){
                smsManager.sendTextMessage(Constants.SERVER_NUMBER, null, indiMessage, null, null);
            }
            Toast.makeText(context, Constants.CONFIRMATION_MESSAGE, Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
