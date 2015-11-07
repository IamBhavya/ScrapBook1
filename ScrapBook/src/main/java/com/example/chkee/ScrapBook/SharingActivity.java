package com.example.chkee.ScrapBook;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.chkee.ScrapBook.R;
import com.facebook.share.ShareApi;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;

import java.io.File;

public class SharingActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sharing_actvity);
        uploadActivity(findViewById(R.id.uploadButton));
    }
    public void uploadActivity(View view) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 3;
        //  Bitmap image = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().toString()
        //          + File.separator + "DCIM" + File.separator + "Camera" + File.separator + "IMG_20151029_204606.jpg", options);
        Bitmap image =BitmapFactory.decodeResource(getResources(), R.drawable.camera);
        SharePhoto photo = new SharePhoto.Builder().setBitmap(image).setCaption("Test").build();
        SharePhotoContent content = new SharePhotoContent.Builder().addPhoto(photo).build();
        ShareApi.share(content, null);
    }
}
