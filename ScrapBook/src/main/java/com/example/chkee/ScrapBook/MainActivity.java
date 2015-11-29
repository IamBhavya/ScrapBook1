package com.example.chkee.ScrapBook;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

import com.facebook.AccessToken;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
            if (prefs != null && prefs.getString("accessToken", null) != null)
                startActivity(new Intent(this, HomeActivity.class));
            else
                startActivity(new Intent(this, Login.class));
        }catch(Exception e){

        }
    }

    public boolean onKeyDown(int keycode, KeyEvent event) {
        try {
            if (keycode == KeyEvent.KEYCODE_BACK) {
                moveTaskToBack(true);
            }
        }catch(Exception e){

        }

        return super.onKeyDown(keycode, event);
    }
}
