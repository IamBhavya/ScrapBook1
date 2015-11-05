package com.example.chkee.ScrapBook;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.chkee.ScrapBook.R;

public class HomeActivity extends ActionBarActivity implements View.OnClickListener {
    Button cameraButton,galleryButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        cameraButton = (Button)findViewById(R.id.image_button);
        galleryButton = (Button)findViewById(R.id.gallery_button);
        cameraButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.image_button:
                startActivity(new Intent(this, ImageCapture.class));
                break;
            case R.id.gallery_button:
                startActivity(new Intent(this, ImageCapture.class));
                break;

        }
    }
}
