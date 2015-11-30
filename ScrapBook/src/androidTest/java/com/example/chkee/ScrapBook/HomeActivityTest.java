package com.example.chkee.ScrapBook;

import android.app.Instrumentation;
import android.content.Intent;
import android.os.Environment;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;

import java.io.File;

/**
 * Created by chkee on 11/23/2015.
 */
public class HomeActivityTest extends ActivityInstrumentationTestCase2<HomeActivity> {

    private HomeActivity homeActivity;
    private Button Camera_Button;
    private Button Time_Button;

    public HomeActivityTest() {
        super(HomeActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        testOpenCamera();
        testOpenScrapBook();
      //  testopenDatePickActivity();
    }
    public void testOpenScrapBook() {
        File f = new File(Environment.getExternalStorageDirectory().toString() + "/ScrapBook");
        File[] files = f.listFiles();
        if (files != null) {
            Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor(AddNotes.
            class.getName(), null, false);

            // open current activity.
            HomeActivity myActivity = getActivity();
            final Button button = (Button) myActivity.findViewById(R.id.gallery_button);
            myActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    // click button and open next activity.
                    button.performClick();
                }
            });

            //Watch for the timeout
            //example values 5000 if in ms, or 5 if it's in seconds.
            AddNotes nextActivity = (AddNotes) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
            // next activity is opened and captured.
            assertNotNull(nextActivity);
            nextActivity.finish();
        }
    }

    public void testOpenCamera() {
        // register next activity that need to be monitored.
        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor(ImageCapture.class.getName(), null, false);

        // open current activity.
        HomeActivity myActivity = getActivity();
        final Button button = (Button) myActivity.findViewById(R.id.image_button);
        myActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // click button and open next activity.
                button.performClick();
            }
        });

        //Watch for the timeout
        //example values 5000 if in ms, or 5 if it's in seconds.
        ImageCapture nextActivity = (ImageCapture) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
        // next activity is opened and captured.
        assertNotNull(nextActivity);
        nextActivity .finish();
    }

}