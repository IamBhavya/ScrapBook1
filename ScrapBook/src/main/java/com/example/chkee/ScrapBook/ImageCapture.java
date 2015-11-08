package com.example.chkee.ScrapBook;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.hardware.Camera;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;
//import com.google.android.gms.maps.model.LatLng;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ImageCapture extends Activity implements NotesFragment.OnFragmentInteractionListener, ImageViewFragment.OnFragmentInteractionListener, SurfaceHolder.Callback, Camera.ShutterCallback, Camera.PictureCallback, BaseFragment.OnFragmentInteractionListener {

    Camera mCamera;
    SurfaceView mPreview;
    final String TAG = "CAMERA_ACTIVITY";

    public static final int SELECT_PHOTO_ACTION = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_capture);

        mPreview = (SurfaceView) findViewById(R.id.preview);
        mPreview.getHolder().addCallback(this);
        mPreview.getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);


        mCamera = Camera.open(getCameraId());


        Camera.Parameters params=mCamera.getParameters();
        List<Camera.Size> sizes = params.getSupportedPictureSizes();
        Camera.Size size = sizes.get(0);
        for(int i=0;i<sizes.size();i++)
        {
            if(sizes.get(i).width > size.width)
                size = sizes.get(i);
        }
        params.setPictureSize(size.width, size.height);
        params.setFlashMode(Camera.Parameters.FLASH_MODE_AUTO);
        params.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
        params.setSceneMode(Camera.Parameters.SCENE_MODE_AUTO);
        params.setWhiteBalance(Camera.Parameters.WHITE_BALANCE_AUTO);
        params.setExposureCompensation(0);
        params.setPictureFormat(ImageFormat.JPEG);
        params.setJpegQuality(100);
        params.setRotation(90);
//        params.setPreviewSize(size.width, size.height);
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        Camera.getCameraInfo(getCameraId(), cameraInfo);
        setCameraDisplayOrientation(this, getCameraId(), mCamera);

        mCamera.setParameters(params);


        Log.d(TAG, "onCreate()");
    }

    public static void setCameraDisplayOrientation(Activity activity,
                                                   int cameraId, android.hardware.Camera camera) {
        android.hardware.Camera.CameraInfo info =
                new android.hardware.Camera.CameraInfo();
        android.hardware.Camera.getCameraInfo(cameraId, info);
        int rotation = activity.getWindowManager().getDefaultDisplay()
                .getRotation();
        int degrees = 0;
        switch (rotation) {
            case Surface.ROTATION_0: degrees = 0; break;
            case Surface.ROTATION_90: degrees = 90; break;
            case Surface.ROTATION_180: degrees = 180; break;
            case Surface.ROTATION_270: degrees = 270; break;
        }

        int result;
        if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
            result = (info.orientation + degrees) % 360;
            result = (360 - result) % 360;  // compensate the mirror
        } else {  // back-facing
            result = (info.orientation - degrees + 360) % 360;
        }
        camera.setDisplayOrientation(result);
    }


    private int getCameraId() {
        int cameraId = -1;
        // Search for the front facing camera
        int numberOfCameras = Camera.getNumberOfCameras();
        for (int i = 0; i < numberOfCameras; i++) {
            Camera.CameraInfo info = new Camera.CameraInfo();
            Camera.getCameraInfo(i, info);
            if (info.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
                // Log.d(DEBUG_TAG, "Camera found");
                cameraId = i;
                break;
            }
        }
        return cameraId;
    }




    @Override
    public void onPause() {
        super.onPause();
        mCamera.stopPreview();

        Log.d(TAG, "onPause()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mCamera.release();
        Log.d(TAG, "Destroy");
    }




    @Override
    public void onResume() {
        super.onResume();
        mCamera.startPreview();
        Log.d(TAG, "onResume()");
    }

    @Override
    public void onStart() {
        super.onStart();

        Log.d(TAG, "onStart()");
    }

    @Override
    public void onStop() {
        super.onStop();

        Log.d(TAG, "onStop()");
    }


    public void onCancelClick(View v) {
        finish();
    }

    public void onSnapClick(View v) {


        mCamera.takePicture(this, null, null, this);

    }
//    FragmentManager fm1;
//    BaseFragment targetFragment1 = null;


//    public void slideShow(View v) {
//        //mCamera.takePicture(this, null, null, this);
//        //Intent intent = new Intent(this, TopListActivity.class);
//        startActivity(new Intent(this, AddNotes.class));   /////change
//
////        fm1 = this.getFragmentManager();
////        targetFragment1 = HorizontalPhotoGalleryFragment.newInstance(1);
////
////        fm1.beginTransaction()
////                .add(R.id.container, targetFragment1)
////                .commit();
//
//
//
//    }


//    public void inflatePicture(String imagePath) throws FileNotFoundException {
//
//
//        //BitmapFactory.decodePath(imagePath);
//
////        ByteArrayOutputStream byteArrayBitmapStream = new ByteArrayOutputStream();
////        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayBitmapStream);
////        byte[] b = byteArrayBitmapStream.toByteArray();
//        FragmentManager fm = getFragmentManager();
//        ImageViewFragment targetFragment = null;
//        targetFragment = ImageViewFragment.newInstance(imagePath,"abc");
//
//
//
//        FragmentTransaction ft=fm.beginTransaction();
//                ft.replace(R.id.ImageFrame, targetFragment)
//                .commit();
//
//        fm = getFragmentManager();
//        NotesFragment targetFragment2 = null;
//        targetFragment2 = NotesFragment.newInstance(imagePath,"abc");
//
//
//
//        FragmentTransaction ft1=fm.beginTransaction();
//        ft1.replace(R.id.Notes, targetFragment2)
//                .commit();
//
//
//        if (targetFragment.isHidden()) {
//            ft.show(targetFragment);
//            Log.d("hidden", "Show");
//        }
//
//
//        if (targetFragment1.isHidden()) {
//            ft.show(targetFragment1);
//            Log.d("hidden","Show");
//        } else {
//            ft.hide(targetFragment1);
//            Log.d("Shown","Hide");
//        }
//
//
//
//
//
//    }


    @Override
    public void onShutter() {
        Toast.makeText(this, "Click!", Toast.LENGTH_SHORT).show();
    }

    @Override





    public void onPictureTaken(byte[] data, Camera camera) {
        //Here, we chose internal storage

        double[] MyLoc = new double[2];

        double[] gps = new double[2];

        Location l = null;


            LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            List<String> providers = lm.getProviders(true);

            /* Loop over the array backwards, and if you get an accurate location, then break out the loop*/
        if ( Build.VERSION.SDK_INT >= 23 &&
                ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission( getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return  ;
        }

            for (int i = providers.size() - 1; i >= 0; i--) {
                try {

                    l = lm.getLastKnownLocation(providers.get(i));
                } catch (Exception e) {
                }
                if (l != null) break;
            }
            if (l != null) {
                gps[0] = l.getLatitude();
                gps[1] = l.getLongitude();
            }

        //LatLng abc=new LatLng(gps[0],gps[1]);
        try {
//            BitmapFactory.Options options = new BitmapFactory.Options();
//            options.inSampleSize = 3;
//            Bitmap original1 = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().toString()
//                    + File.separator + "DCIM"+ File.separator +"Camera"+File.separator+"IMG_20151029_204606_1.jpg",options);
            Geocoder gcd = new Geocoder(this, Locale.getDefault());
            List<Address> addresses = gcd.getFromLocation(gps[0], gps[1], 1);
            String s=addresses.get(0).getLocality();
            Toast toast = Toast.makeText(this, addresses.get(0).getLocality(), Toast.LENGTH_SHORT);
            toast.show();

            File direct = new File(Environment.getExternalStorageDirectory() + addresses.get(0).getLocality());

            if (!direct.exists()) {
                File wallpaperDirectory = new File(Environment.getExternalStorageDirectory().toString()+"/ScrapBook/"+addresses.get(0).getLocality());
                wallpaperDirectory.mkdirs();
            }

            File path=new File(Environment.getExternalStorageDirectory().toString()+"/ScrapBook/"+addresses.get(0).getLocality()+"/thumbnails");

            if (!path.exists()) {
                File wallpaperDirectory = new File(Environment.getExternalStorageDirectory().toString()+"/ScrapBook/"+addresses.get(0).getLocality()+"/thumbnails");
                wallpaperDirectory.mkdirs();
            }

            final int THUMBSIZE = 64;

            Bitmap original = BitmapFactory.decodeByteArray(data, 0, data.length);
            Bitmap resized = Bitmap.createScaledBitmap(original, THUMBSIZE, THUMBSIZE, true);
            ByteArrayOutputStream blob = new ByteArrayOutputStream();
            resized.compress(Bitmap.CompressFormat.JPEG, 100, blob);

            String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());

            //String fileName=(currentDateTimeString+System.currentTimeMillis()).replaceAll("\\s+","");
            String fileName=java.util.UUID.randomUUID().toString();
            File file = new File(new File(Environment.getExternalStorageDirectory().toString()+"/ScrapBook/"+addresses.get(0).getLocality()+"/thumbnails"), fileName+".PNG");
            FileOutputStream out = new FileOutputStream(file);
            out.write(blob.toByteArray());

            out.flush();
            out.close();

            file = new File(new File(Environment.getExternalStorageDirectory().toString()+"/ScrapBook/"+addresses.get(0).getLocality()), fileName+".PNG");
            out = new FileOutputStream(file);
            out.write(data);

            out.flush();
            out.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        camera.startPreview();
    }
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Camera.Parameters params = mCamera.getParameters();
        List<Camera.Size> sizes = params.getSupportedPreviewSizes();
        Camera.Size selected = sizes.get(0);
        params.setPreviewSize(selected.width, selected.height);
        mCamera.setParameters(params);

        mCamera.setDisplayOrientation(90);
        mCamera.startPreview();
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        try {
            mCamera.setPreviewDisplay(mPreview.getHolder());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.i("PREVIEW", "surfaceDestroyed");
    }


    @Override
    public void onFragmentInteraction(Uri uri) {
    }

    @Override
    public void onFragmentInteraction(String id) {
    }

    @Override
    public void onFragmentInteraction(int actionId) {
    }
}