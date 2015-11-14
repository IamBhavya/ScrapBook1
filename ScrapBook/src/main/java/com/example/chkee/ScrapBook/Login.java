package com.example.chkee.ScrapBook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends ActionBarActivity implements View.OnClickListener{
    Button login;
    EditText etPassword,etUserName;
    TextView registerLink;
    DataBaseHelper helper = new DataBaseHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etUserName = (EditText)findViewById(R.id.etUserName);
        etPassword =(EditText)findViewById(R.id.etPassword);
        registerLink = (TextView)findViewById(R.id.tvRegisterLink);
        login = (Button)findViewById(R.id.bLogin);
        login.setOnClickListener( this);
        registerLink.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.bLogin:
                String userName = etUserName.getText().toString();
                String password = etPassword.getText().toString();
                String pass = helper.searchPassword(userName);
                if(pass != null) {
                    if (pass.equals(password)) {
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                    } else {
                        Toast temp = Toast.makeText(Login.this, "INVALID_CREDENTIALS", Toast.LENGTH_SHORT);
                        temp.show();
                    }
                }
                else {
                    Toast temp = Toast.makeText(Login.this, "NOT_REGISTERED", Toast.LENGTH_SHORT);
                    temp.show();
                }
                break;
            case R.id.tvRegisterLink:
                startActivity(new Intent(getApplicationContext(),Register.class));
                break;
        }

    }
    public boolean onKeyDown(int keycode, KeyEvent event) {
        if (keycode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true);
        }
        return super.onKeyDown(keycode, event);
    }
}
