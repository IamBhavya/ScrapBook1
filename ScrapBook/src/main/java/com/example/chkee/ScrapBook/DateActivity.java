package com.example.chkee.ScrapBook;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.chkee.ScrapBook.R;

import java.util.Calendar;

public class DateActivity extends AppCompatActivity {
    DatePicker timerButton;
    private ScheduleClient scheduleClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);
        scheduleClient = new ScheduleClient(this);
        scheduleClient.doBindService();
        Calendar c = Calendar.getInstance();
        timerButton = (DatePicker)findViewById(R.id.date_picker);
        timerButton.init(
                c.get(Calendar.YEAR),
                c.get(Calendar.MONTH),
                c.get(Calendar.DAY_OF_MONTH),
                new DatePicker.OnDateChangedListener() {

                    @Override
                    public void onDateChanged(DatePicker view,
                                              int year, int monthOfYear, int dayOfMonth) {

                        Calendar c = Calendar.getInstance();
                      //  c.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                        //c.set(Calendar.YEAR,year);
                        //c.set(Calendar.MONTH,monthOfYear);
                        //c.set(Calendar.HOUR_OF_DAY,12);
                        //c.set(Calendar.MINUTE, 0);
                        //c.set(Calendar.SECOND,0);
                       // scheduleClient.setAlarmForNotification(c);
                        c.setTimeInMillis(System.currentTimeMillis());
                        scheduleClient.setAlarmForNotification(c);
                        Toast.makeText(getApplicationContext(), "Notification set for: " + (monthOfYear + 1) + "/" + dayOfMonth + "/" + year, Toast.LENGTH_LONG).show();
                    }
                });

    }

    public void backPress(View view)
    {
        startActivity(new Intent(this,HomeActivity.class));
    }

    @Override
    protected void onStop() {
        // When our activity is stopped ensure we also stop the connection to the service
        // this stops us leaking our activity into the system *bad*
       if(scheduleClient != null)
            scheduleClient.doUnbindService();
        super.onStop();
    }

}
