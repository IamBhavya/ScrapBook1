package com.example.chkee.ScrapBook;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.example.chkee.ScrapBook.R;

public class NewActivity extends AppCompatActivity {

    Button upload;
    int arr[] = new int[500];
    int i=0;
@Override
    public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.content_new);
    try {
            GridView gridview = (GridView) findViewById(R.id.gridview);
            gridview.setAdapter(new ImageAdapter(this));
            final Bundle bundle = new Bundle();
            gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View v,
                                        int position, long id) {
                    Toast.makeText(NewActivity.this, "" + position,
                            Toast.LENGTH_SHORT).show();
                    arr[i++] = position;

                }
            });
            final Intent intent = new Intent(this, SharingActivity.class);
            upload = (Button) findViewById(R.id.uploadButton);
            upload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    bundle.putIntArray("FilesToUpload", arr);
                    intent.putExtras(bundle);

                    startActivity(intent);
                }
            });

        } catch (Exception e) {

        }
    }
}
