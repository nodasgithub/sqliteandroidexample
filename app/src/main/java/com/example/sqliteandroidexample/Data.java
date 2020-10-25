package com.example.sqliteandroidexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class Data extends AppCompatActivity {

    TextView tvData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        tvData = (TextView) findViewById(R.id.tvData);

        try {
            ContactsDB db = new ContactsDB(this);
            db.open();
            tvData.setText(db.getData());
            db.close();
        } catch (Exception ex) {
            Toast.makeText(Data.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}