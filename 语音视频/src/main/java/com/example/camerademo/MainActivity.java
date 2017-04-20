package com.example.camerademo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * 使用手机多媒体Demo
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnCamera;
    private Button btnGallary;
    private Button btnMedia;
    private Button btnVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        btnCamera = (Button) findViewById(R.id.btn_camera);
        btnCamera.setOnClickListener(this);

        btnGallary = (Button) findViewById(R.id.btn_gallary);
        btnGallary.setOnClickListener(this);

        btnMedia = (Button) findViewById(R.id.btn_media);
        btnMedia.setOnClickListener(this);

        btnVideo = (Button) findViewById(R.id.btn_video);
        btnVideo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_camera:
                Intent intent1 = new Intent(this,CameraActivity.class);
                startActivity(intent1);
                break;

            case R.id.btn_gallary:
                Intent intent2 = new Intent(this,GallaryActivity.class);
                startActivity(intent2);
                break;

            case R.id.btn_media:
                Intent intent3 = new Intent(this,MediaActivity.class);
                startActivity(intent3);
                break;

            case R.id.btn_video:
                Intent intent4 = new Intent(this,VideoActivity.class);
                startActivity(intent4);
                break;
        }
    }
}
