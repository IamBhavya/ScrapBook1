package com.example.chkee.ScrapBook;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphRequestAsyncTask;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
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
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;

import static com.facebook.login.LoginManager.*;

public class SharingActivity extends AppCompatActivity {

    PhotoUploadHelper helper=new PhotoUploadHelper(this);
    private CallbackManager mCallbackManager;
    private LoginManager manager;
    int arr[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_sharing_actvity);
        try {

                Bundle extras = getIntent().getExtras();
                arr = extras.getIntArray("FilesToUpload");
                    uploadActivity();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
        catch (Exception e){

        }
        }

    private FacebookCallback<LoginResult> mCallback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
try {
    SharedPreferences shre = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
    SharedPreferences.Editor edit = shre.edit();

    AccessToken accessToken = loginResult.getAccessToken();

    edit.putString("accessToken", accessToken.toString());

    edit.commit();
    // LoginManager.getInstance().logInWithPublishPermissions(MainFragment, Arrays.asList("publish_actions"));
    // shareButton.setVisibility(View.VISIBLE);
    // shareButton.setOnClickListener(this);
            }catch(Exception e){

            }
        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onError(FacebookException error) {
            try{

            }catch(Exception e){

            }
        }
    };
    public void uploadActivity() throws JSONException {
        try {
            FacebookSdk.sdkInitialize(getApplicationContext());

            mCallbackManager = CallbackManager.Factory.create();
            manager = LoginManager.getInstance();
            manager.registerCallback(mCallbackManager, mCallback);

            Toast.makeText(this, "Uploading to Facebook", Toast.LENGTH_LONG).show();
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 3;
            Log.d("AccessToken", "AccessToken " + AccessToken.getCurrentAccessToken());
            File f = new File(Environment.getExternalStorageDirectory().toString() + "/ScrapBook");
            Bundle extras = getIntent().getExtras();
            arr = extras.getIntArray("FilesToUpload");
            if (f.exists()) {
                File[] files = f.listFiles();
                for (File inFile : files) {
                    int i = 0;
                    File k[] = files[i].listFiles();
                    for (i = 0; i < k.length + 1; i++) {
                        int b = arr[i];
                        Uri thumbnailUri = Uri.parse(k[b + 1].toString());//replace i with arr[i];
                        Uri fullImageUri = Uri.parse(k[b + 1].toString().replace("thumbnails/", ""));//Putting Dummy//String.valueOf(filelist[i]));
                        File inFile1 = new File(String.valueOf(fullImageUri));
                        if (helper.getPhotoInfo(String.valueOf(fullImageUri)) == null) {

                            //LoginClient.Request request= GraphRequest.newPostRequest(AccessToken.getCurrentAccessToken(),)

                            Bitmap image = BitmapFactory.decodeFile(String.valueOf(inFile1), options);
                            SharePhoto photo = new SharePhoto.Builder().setBitmap(image).setCaption("Columbus").build();
                            SharePhotoContent content = new SharePhotoContent.Builder().addPhoto(photo).build();
                            ShareApi.share(content, null);
                            Photo p = new Photo();
                            p.setImage_id(String.valueOf(fullImageUri));
                            p.setValue(true);
                            helper.insertPhotoInfo(p);
                        }
                    }
                }
            }

            Toast.makeText(this, "Upload Done", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, HomeActivity.class));

        }catch(Exception e){

        }
    }
 }
