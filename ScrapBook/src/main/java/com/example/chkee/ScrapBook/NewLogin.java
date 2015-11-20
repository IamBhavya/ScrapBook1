package com.example.chkee.ScrapBook;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.chkee.ScrapBook.R;

public class NewLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_login);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        if(prefs != null && prefs.getString("accessToken",null) !=null){
            startActivity(new Intent(this,NewActivity.class));
        }
    }

}
