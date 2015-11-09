package com.example.chkee.ScrapBook;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.chkee.ScrapBook.R;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.LoginManager;
import com.facebook.share.ShareApi;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;

import static com.facebook.login.LoginManager.*;

public class SharingActivity extends AppCompatActivity {
    PhotoUploadHelper helper=new PhotoUploadHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sharing_actvity);
        try {
            uploadActivity(findViewById(R.id.uploadButton));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void uploadActivity(View view) throws JSONException {

        Toast.makeText(this, "Uploading to Facebook", Toast.LENGTH_LONG).show();
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 3;
        File f = new File(Environment.getExternalStorageDirectory().toString()+"/ScrapBook");
        File[] files = f.listFiles();
        for (File inFile : files) {
            int i=0;
                File k[] = files[i].listFiles();
                for (File inFile1 : k) {
                    if (!inFile1.isDirectory()) {
                        String s= inFile1.toString();
                        int c= String.valueOf(inFile1).lastIndexOf('/');
                        String imageName = s.substring(c) ;
                        if(helper.getPhotoInfo(imageName)== null) {
                            Bitmap image = BitmapFactory.decodeFile(String.valueOf(inFile1), options);
                            SharePhoto photo = new SharePhoto.Builder().setBitmap(image).setCaption("Test").build();
                            SharePhotoContent content = new SharePhotoContent.Builder().addPhoto(photo).build();
                            ShareApi.share(content, null);
                            Photo p = new Photo();
                            p.setImage_id(imageName);
                            p.setValue(true);
                            helper.insertPhotoInfo(p);
                        }
                    }
                }
            }
        JSONObject coordinates = new JSONObject();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        Map<String,?> keys = prefs.getAll();

        for(Map.Entry<String,?> entry : keys.entrySet()){
            Log.d("map values",entry.getKey() + ": " +
                    entry.getValue().toString());
        }


     //   String imagePath = prefs.getString("Columbus", null);
       // String[] obj = imagePath.split(",");
        //coordinates.put("latitude", Double.parseDouble(obj[0]));
        //coordinates.put("longitude", Double.parseDouble(obj[1]));
        //GraphRequest graphRequest = GraphRequest.newPostRequest(AccessToken.getCurrentAccessToken(), "me/posts",coordinates, new GraphRequest.Callback() {
          //  @Override
            //public void onCompleted(GraphResponse response) {

//            }
  //      });
    //    graphRequest.executeAsync();
        Toast.makeText(this,"Upload Done",Toast.LENGTH_LONG).show();
        startActivity(new Intent(this, Login.class));
    }
 }
