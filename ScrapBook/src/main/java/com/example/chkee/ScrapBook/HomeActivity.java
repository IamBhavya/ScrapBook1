package com.example.chkee.ScrapBook;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.chkee.ScrapBook.R;

import java.util.GregorianCalendar;
import java.util.SimpleTimeZone;
import java.util.TimeZone;
import java.util.Calendar;
import java.util.Timer;


public class HomeActivity extends ActionBarActivity implements View.OnClickListener {
    Button cameraButton,logoutButton,galleryButton;
    Button timerButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        cameraButton = (Button) findViewById(R.id.image_button);
        galleryButton = (Button) findViewById(R.id.gallery_button);
        logoutButton = (Button) findViewById(R.id.logout_button);
        timerButton = (Button) findViewById(R.id.schedule_timer);
        timerButton.setOnClickListener(this);
        cameraButton.setOnClickListener(this);
        galleryButton.setOnClickListener(this);
        logoutButton.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.image_button:
                startActivity(new Intent(this, ImageCapture.class));
                break;
            case R.id.gallery_button:
                startActivity(new Intent(this, AddNotes.class));
                break;
            case R.id.logout_button:
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
                SharedPreferences.Editor edit=prefs.edit();
                edit.putString("accessToken",null);

                //startActivity(new Intent(this, HomeActivity.class));
                startActivity(new Intent(this,Login.class));
                break;
            case R.id.schedule_timer:
                // Create a new calendar set to the date chosen
                // we set the time to midnight (i.e. the first minute of that day)
                // if no ids were returned, something is wrong. get out.
              //  if (ids.length == 0)
                //    System.exit(0);
                startActivity(new Intent(this,DateActivity.class));
                // begin output
             //   System.out.println("Current Time");
              //  Calendar c= Calendar.getInstance();
                //c.setTimeInMillis(System.currentTimeMillis());
                // Ask our service to set an alarm for that date, this activity talks to the client that talks to the service
                //scheduleClient.setAlarmForNotification(c);
                //Toast.makeText(this, "Notification set for: " + (c.get(Calendar.MONTH)+1) + "/" + c.get(Calendar.DAY_OF_MONTH) +  "/" + c.get(Calendar.YEAR),Toast.LENGTH_LONG).show();
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
