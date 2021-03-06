package com.burgers.raffy.utils;

import android.os.Environment;

/**
 * Created by Neil on 5/21/2017.
 */

public class Constants {
    public static final int GENERATE = 0;
    public static final int CHECK = 1;
    public static final String PATH = Environment.getExternalStorageDirectory()+"/RaffysBurger/";
    public static final String COLUMN_NAME="NAME";
    public static final String COLUMN_AMOUNT="AMOUNT";
    public static final String TABLE_NAME="GiftCerts";
    public static final String DATABASE_NAME = "Raffys.db";
    public static final String TABLE_ID = "ID";
    public static final String COLUMN_KEY = "KEY";
    public static final String COLUMN_COLLECT = "COLLECT";
    public static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
    public static final String ALLOWED_NUMBER = "+639162372702";
    public static final String ADD = "ADD";
    public static final String NAME = "name";
    public static final String AMOUNT = "amount";
}
