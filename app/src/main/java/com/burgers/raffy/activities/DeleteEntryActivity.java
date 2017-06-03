package com.burgers.raffy.activities;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.burgers.raffy.giftcerts.R;
import com.burgers.raffy.utils.Constants;
import com.burgers.raffy.utils.SQLHelper;

public class DeleteEntryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_entry);
        readFromDatabase();
    }

    private void readFromDatabase(){
        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.linearLayoutList);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        SQLHelper sqlHelper = new SQLHelper(this);
        SQLiteDatabase db = sqlHelper.getReadableDatabase();

        String[] projection = {
                Constants.TABLE_ID,
                Constants.COLUMN_NAME,
                Constants.COLUMN_AMOUNT,
                Constants.COLUMN_COLLECT
        };

        Cursor cursor = db.query(
                Constants.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );
        while(cursor.moveToNext()){
            String temp = "";
            temp = "" + cursor.getInt(cursor.getColumnIndex(Constants.TABLE_ID));
            temp +=  " " + cursor.getString(cursor.getColumnIndex(Constants.COLUMN_NAME));
            temp += " " + cursor.getString(cursor.getColumnIndex(Constants.COLUMN_AMOUNT));
            if(cursor.getInt(cursor.getColumnIndex(Constants.COLUMN_COLLECT)) == 1){
                temp += " claimed";
            }
            Button button = new Button(this);
            button.setText(temp);
            button.setTextSize(20);
            linearLayout.addView(button);
        }
    }
}
