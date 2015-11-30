package com.example.chkee.ScrapBook;

import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.chkee.ScrapBook.Login;

import junit.framework.TestCase;

/**
 * Created by chkee on 11/23/2015.
 */
public class LoginActivityTest extends ActivityInstrumentationTestCase2<Login>{

    private Login mLoginTestActivity;
    private EditText mFirstTestText;
    private EditText mSecondTestText;

    public LoginActivityTest() {
        super(Login.class);
    }
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mLoginTestActivity = getActivity();
        mFirstTestText =
                (EditText) mLoginTestActivity
                        .findViewById(R.id.etUserName);
        mSecondTestText=(EditText)mLoginTestActivity.findViewById(R.id.etPassword);
        test_Login_text();
        testLoginButton();

    }
    public void test_Login_text() {
        assertNotNull(mFirstTestText);
        assertNotNull(mSecondTestText);
    }
    public void testLoginButton() {
        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor(HomeActivity.class.getName(), null, false);

        // open current activity.
        Login myActivity = getActivity();
        final Button button = (Button) myActivity.findViewById(R.id.logout_button);
        myActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // click button and open next activity.
                button.performClick();
            }
        });

        //Watch for the timeout
        //example values 5000 if in ms, or 5 if it's in seconds.
        HomeActivity nextActivity = (HomeActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
        // next activity is opened and captured.
        assertNotNull(nextActivity);
        nextActivity .finish();
    }
}
