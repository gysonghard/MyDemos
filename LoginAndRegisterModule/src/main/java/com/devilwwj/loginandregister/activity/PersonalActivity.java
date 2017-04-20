package com.devilwwj.loginandregister.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.devilwwj.loginandregister.R;
import com.devilwwj.loginandregister.utils.ToastUtils;

/**
 * Created by sgy on 2017/3/21.
 */

public class PersonalActivity extends AppCompatActivity implements View.OnClickListener {
    private boolean isLeader = false; // 领导or普通员工
    private LinearLayout llWorkExperience;
    private boolean isShowedWorkExperience = false; // 是否已经显示工作经历
    private ViewStub vsPersonalWorkExperience;
    private View workExperience; // 替代ViewStub的View,ViewStub调用inflate方法后就被移除了视图树!!!
    private ViewStub vsStaff;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        init();
    }

    private void init() {
        // 根据登录界面传递过来的值判断是普通员工还是领导
        Intent intent = getIntent();
        String account = intent.getStringExtra("account");

        llWorkExperience = (LinearLayout) findViewById(R.id.ll_work_experience);
        llWorkExperience.setOnClickListener(this);

        vsPersonalWorkExperience = (ViewStub) findViewById(R.id.vs_work_experience);
        vsStaff = (ViewStub) findViewById(R.id.vs_staff);

        if (account.compareTo("18811113333") == 0) { // 普通员工
//            ToastUtils.makeShortText("普通员工", PersonalActivity.this);
        } else if (account.compareTo("18811114444") == 0) { // 领导
            isLeader = true;
//            ToastUtils.makeShortText("我是领导", PersonalActivity.this);
        } else { // 登录账户有误
            ToastUtils.makeShortText("登录账号有误", PersonalActivity.this);
            finish();
        }

        // 根据是否为领导来控制下属的显示和隐藏
        if (isLeader) { // 领导
            try {
                View vsStaffView = vsStaff.inflate();
                Button btnStaff1 = (Button) vsStaffView.findViewById(R.id.btn_reviewed_staff1);
                btnStaff1.setOnClickListener(this);

                Button btnStaff2 = (Button) vsStaffView.findViewById(R.id.btn_reviewed_staff2);
                btnStaff2.setOnClickListener(this);

                Button btnStaff3 = (Button) vsStaffView.findViewById(R.id.btn_reviewed_staff3);
                btnStaff3.setOnClickListener(this);

                Button btnStaff4 = (Button) vsStaffView.findViewById(R.id.btn_reviewed_staff4);
                btnStaff4.setOnClickListener(this);

            } catch (Exception e) {
                vsStaff.setVisibility(View.VISIBLE);
            }
        } else { // 普通员工

        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.ll_work_experience: // 点击显示和隐藏工作经历
                if (isShowedWorkExperience) { // 已经显示,点击就隐藏
                    vsPersonalWorkExperience.setVisibility(View.GONE);
                    isShowedWorkExperience = false;
                } else { // 未显示,点击显示
                    try {
                        workExperience = vsPersonalWorkExperience.inflate();
//                        TextView tvCompany1Time  = (TextView) workExperience.
//                                findViewById(R.id.tv_company1_time);
//                        tvCompany1Time.setText("这是更改的时间!!!");
                    } catch (Exception e) {
                        vsPersonalWorkExperience.setVisibility(View.VISIBLE);
                    }
                    isShowedWorkExperience = true;
                }
                break;

            case R.id.btn_reviewed_staff1:
                showReviewDialog();
                break;

            case R.id.btn_reviewed_staff2:
                showReviewDialog();
                break;

            case R.id.btn_reviewed_staff3:
                showReviewDialog();
                break;

            case R.id.btn_reviewed_staff4:
                showReviewDialog();
                break;
        }
    }

    private void showReviewDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(PersonalActivity.this);
        LayoutInflater layoutInflater = getLayoutInflater();
        final View myDialog = layoutInflater.inflate(R.layout.review_dialog, null);

        builder.setTitle("评价");
        builder.setView(myDialog);

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.setPositiveButton("评价", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                EditText editViewReview = (EditText) myDialog.findViewById(R.id.et_review_message);
                // 获取到评价信息进行提交!
                String reviewMessage = String.valueOf(editViewReview.getText());
                ToastUtils.makeShortText("评价成功",PersonalActivity.this);
                dialog.dismiss();
            }
        });
        builder.show();
    }
}
