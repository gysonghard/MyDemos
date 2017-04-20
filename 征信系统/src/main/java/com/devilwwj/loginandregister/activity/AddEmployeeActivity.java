package com.devilwwj.loginandregister.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.devilwwj.loginandregister.R;
import com.devilwwj.loginandregister.utils.ToastUtils;
import com.devilwwj.loginandregister.views.CleanEditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by sgy on 2017/4/6.
 * 添加员工信息
 */

public class AddEmployeeActivity extends AppCompatActivity {

    @Bind(R.id.cet_employee_name)
    CleanEditText cetEmployeeName;
    @Bind(R.id.cet_employee_cardNum)
    CleanEditText cetEmployeeCardNum;
    @Bind(R.id.cet_employee_sex)
    CleanEditText cetEmployeeSex;
    @Bind(R.id.cet_employee_education)
    CleanEditText cetEmployeeEducation;
    @Bind(R.id.cet_employee_telephone)
    CleanEditText cetEmployeeTelephone;
    @Bind(R.id.cet_employee_wetChat)
    CleanEditText cetEmployeeWetChat;
    @Bind(R.id.cet_employee_qq)
    CleanEditText cetEmployeeQQ;
    @Bind(R.id.cet_employee_email)
    CleanEditText cetEmployeeEmail;
    @Bind(R.id.cet_employee_position)
    CleanEditText cetEmployeePosition;
    @Bind(R.id.cet_employee_leader)
    CleanEditText cetEmployeeLeader;
    @Bind(R.id.cet_employee_part)
    CleanEditText cetEmployeePart;
    @Bind(R.id.cet_employee_time)
    CleanEditText cetEmployeeTime;
    @Bind(R.id.cet_employee_socialSecurityNum)
    CleanEditText cetEmployeeSocialSecurityNum;
    @Bind(R.id.cet_employee_providentFundNum)
    CleanEditText cetEmployeeProvidentFundNum;
    @Bind(R.id.cet_employee_responsibilities)
    CleanEditText cetEmployeeResponsibilities;
    @Bind(R.id.btn_add_employee)
    Button btnAddEmployee;
    private String emplyeeName;
    private String emplyeePart;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
    }

    /**
     * 获取新添加的员工信息
     */
    private void getEmployeeInfo() {
        emplyeeName = cetEmployeeName.getText().toString();
        String emplyeeCardNum = String.valueOf(cetEmployeeCardNum.getText());
        String emplyeeSex = String.valueOf(cetEmployeeSex.getText());
        String emplyeeEducation = String.valueOf(cetEmployeeEducation.getText());
        String emplyeeTelephone = String.valueOf(cetEmployeeTelephone.getText());
        String emplyeeWetChat = String.valueOf(cetEmployeeWetChat.getText());
        String emplyeeQQ = String.valueOf(cetEmployeeQQ.getText());
        String emplyeeEmail = String.valueOf(cetEmployeeEmail.getText());
        String emplyeePosition = String.valueOf(cetEmployeePosition.getText());
        String emplyeeLeader = String.valueOf(cetEmployeeLeader.getText());
        emplyeePart = cetEmployeePart.getText().toString().trim();
        String emplyeeTime = String.valueOf(cetEmployeeTime.getText());
        String emplyeeSocialSecurityNum = String.valueOf(cetEmployeeSocialSecurityNum.getText());
        String emplyeeProvidentFundNum = String.valueOf(cetEmployeeProvidentFundNum.getText());
    }

    @OnClick(R.id.btn_add_employee)
    public void onViewClicked() { // 提交添加的员工的信息,提交到服务器!!!
        getEmployeeInfo();
        ToastUtils.makeShortText("已将" + emplyeeName + "添加到" + emplyeePart,
                AddEmployeeActivity.this);
        finish();
    }
}
