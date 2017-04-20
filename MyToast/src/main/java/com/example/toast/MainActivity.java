package com.example.toast;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 自定义Toast
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.btn);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn:
                View MyToast = getLayoutInflater().inflate(R.layout.my_toast,null);
                TextView tv = (TextView) MyToast.findViewById(R.id.tv_text);
                tv.setText("更改后的显示内容");
                Toast toast = new Toast(getApplicationContext());
                toast.setView(MyToast);
                toast.show();
                break;
        }
    }
}
