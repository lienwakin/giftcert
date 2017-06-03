package com.burgers.raffy.activities;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.burgers.raffy.giftcerts.R;
import com.burgers.raffy.utils.Constants;
import com.burgers.raffy.utils.DBUtils;
import com.burgers.raffy.utils.SQLHelper;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        readFromDatabase();
    }

    private void readFromDatabase(){
        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.linearLayoutList);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        Cursor cursor = DBUtils.searchDB(this, null, null);

        while(cursor.moveToNext()){
            String temp = "";
            temp = "" + cursor.getInt(cursor.getColumnIndex(Constants.TABLE_ID));
            temp +=  " " + cursor.getString(cursor.getColumnIndex(Constants.COLUMN_NAME));
            temp += " " + cursor.getString(cursor.getColumnIndex(Constants.COLUMN_AMOUNT));
            if(cursor.getInt(cursor.getColumnIndex(Constants.COLUMN_COLLECT)) == 1){
                temp += " claimed";
            }
            TextView textView = new TextView(this);
            textView.setText(temp);
            textView.setTextSize(20);
            linearLayout.addView(textView);
        }
    }
}
