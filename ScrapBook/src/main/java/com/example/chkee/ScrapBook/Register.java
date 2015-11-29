package com.example.chkee.ScrapBook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Calendar;

public class Register extends ActionBarActivity implements View.OnClickListener {

    Button bRegister;
    EditText name, userName, password;
    DataBaseHelper helper = new DataBaseHelper(this);

    @Override
    public void onClick(View v) {
        try {
            switch (v.getId()) {
                case R.id.bRegister:
                    Contact c = new Contact();
                    c.setName(name.getText().toString());
                    c.setUserName(userName.getText().toString());
                    c.setPassword(password.getText().toString());
                    helper.insertContact(c);
                    startActivity(new Intent(getApplicationContext(), Login.class));
                    break;
            }
        } catch (Exception e) {

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        try {
            name = (EditText) findViewById(R.id.etName);
            userName = (EditText) findViewById(R.id.etUserName);
            password = (EditText) findViewById(R.id.etPassword);
            bRegister = (Button) findViewById(R.id.bRegister);
            bRegister.setOnClickListener(this);

        } catch (Exception e) {

        }
    }
}
