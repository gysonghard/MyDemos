package com.example.camerademo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;

/**
 * Created by sgy on 2017/4/18.
 * 播放视频 -- VideoView
 */

public class VideoActivity extends AppCompatActivity implements View.OnClickListener{

    private VideoView videoView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        Button btnPlay = (Button) findViewById(R.id.btn_play);
        Button btnPause = (Button) findViewById(R.id.btn_pause);
        Button btnReplay = (Button) findViewById(R.id.btn_replay);
        videoView = (VideoView) findViewById(R.id.video_show);

        btnPlay.setOnClickListener(this);
        btnPause.setOnClickListener(this);
        btnReplay.setOnClickListener(this);

        if (ContextCompat.checkSelfPermission(VideoActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(VideoActivity.this,
                    new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        } else {
            initVideoView();
        }
    }

    private void initVideoView() {
        File file = new File(Environment.getExternalStorageDirectory(),"video_test.mp4");
        videoView.setVideoPath(file.getPath());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull
            String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0]
                        == PackageManager.PERMISSION_GRANTED) {
                    initVideoView();
                } else {
                    Toast.makeText(VideoActivity.this,"您决绝了权限!",Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_play:
                if (!videoView.isPlaying()) {
                    videoView.start();
                }
                break;

            case R.id.btn_pause:
                if (videoView.isPlaying()) {
                    videoView.pause();
                }
                break;

            case R.id.btn_replay:
                if (videoView.isPlaying()) {
                    videoView.resume();
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (videoView != null) {
            videoView.suspend();
        }
    }
}
