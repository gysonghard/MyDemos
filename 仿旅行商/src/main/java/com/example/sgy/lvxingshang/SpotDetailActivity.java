package com.example.sgy.lvxingshang;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by sgy on 2017/2/21.
 */

public class SpotDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spot_detail);
        Intent intent = getIntent();
        int spotPosition = intent.getIntExtra("spotPosition", 0);
        Toast.makeText(getApplicationContext(),"点击的景点位置为:" + spotPosition,
                Toast.LENGTH_SHORT).show();
    }
}
