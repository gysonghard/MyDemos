package com.example.camerademo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by sgy on 2017/4/18.
 * 调用相机拍照
 */

public class CameraActivity extends AppCompatActivity {

    public static final int TAKE_PHOTO = 1;
    private Uri imageUri;
    private ImageView picture;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        picture = (ImageView) findViewById(R.id.picture);
        Button btnTakePhoto = (Button) findViewById(R.id.btn_take_photo);
        btnTakePhoto.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                File outputImage = new File(getExternalCacheDir(), "output_iamge.jpg");
                if (outputImage.exists()) {
                    outputImage.delete();
                }
                try {
                    outputImage.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (BuildConfig.VERSION_CODE >= 24) {
                    imageUri = FileProvider.getUriForFile(CameraActivity.this,
                            "aaa", outputImage);
                } else {
                    imageUri = Uri.fromFile(outputImage);
                }

                // 启动相机程序
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent, TAKE_PHOTO);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    try {
                        Bitmap bitmap = BitmapFactory.
                                decodeStream(getContentResolver().openInputStream(imageUri));
                        picture.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            default:

                break;
        }

    }
}
