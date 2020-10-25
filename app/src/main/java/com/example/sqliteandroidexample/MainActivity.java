package com.example.sqliteandroidexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.SQLException;

public class MainActivity extends AppCompatActivity {

    EditText etName, etCell;
    Button btnSubmit, btnShowData, btnEditData, btnDeleteData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = (EditText) findViewById(R.id.etName);
        etCell = (EditText) findViewById(R.id.etCell);

        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnShowData = (Button) findViewById(R.id.btnShowData);
        btnEditData = (Button) findViewById(R.id.btnEditData);
        btnDeleteData = (Button) findViewById(R.id.btnDeleteData);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etName.getText().toString().trim();
                String cell = etCell.getText().toString().trim();

                try {
                    ContactsDB db = new ContactsDB(MainActivity.this);
                    db.open();
                    db.createEntry(name, cell);
                    db.close();
                    Toast.makeText(MainActivity.this, "Successfully saved!", Toast.LENGTH_SHORT).show();
                    etName.setText("");
                    etCell.setText("");
                } catch (Exception ex) {
                    Toast.makeText(MainActivity.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                }


            }
        });

        btnShowData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Data.class));
            }
        });

        btnEditData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    ContactsDB db = new ContactsDB(MainActivity.this);
                    db.open();
                    db.updateEntry("1", "Johan Jurrius", "2784567355");
                    db.close();
                    Toast.makeText(MainActivity.this, "Successfully updated!", Toast.LENGTH_SHORT).show();

                } catch (Exception ex) {
                    Toast.makeText(MainActivity.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnDeleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    ContactsDB db = new ContactsDB(MainActivity.this);
                    db.open();
                    db.deleteEntry("1");
                    db.close();
                    Toast.makeText(MainActivity.this, "Successfully Deleted!", Toast.LENGTH_SHORT).show();
                } catch (Exception ex) {
                    Toast.makeText(MainActivity.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}