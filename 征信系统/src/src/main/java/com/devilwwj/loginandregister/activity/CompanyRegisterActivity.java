package com.devilwwj.loginandregister.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.devilwwj.loginandregister.R;
import com.devilwwj.loginandregister.utils.ToastUtils;
import com.devilwwj.loginandregister.views.CleanEditText;

/**
 * Created by sgy on 2017/3/21.
 * 公司注册的界面
 */

public class CompanyRegisterActivity extends AppCompatActivity implements View.OnClickListener {

    public CleanEditText cetCompanyName;
    public CleanEditText cetCompanyNum;
    public CleanEditText cetTaxNum;
    public CleanEditText cetOrganizationNum;
    public CleanEditText cetLegalName;
    public CleanEditText cetPhoneNum;
    public CleanEditText cetTelephoneNum;
    public CleanEditText cetCompanyEmail;
    public Button btnCommit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_register);
        initView();
    }

    private void initView() {
        cetCompanyName = (CleanEditText) findViewById(R.id.cet_company_name);
        cetCompanyNum = (CleanEditText) findViewById(R.id.cet_company_num);
        cetTaxNum = (CleanEditText) findViewById(R.id.cet_tax_num);
        cetOrganizationNum = (CleanEditText) findViewById(R.id.cet_organization_num);
        cetLegalName = (CleanEditText) findViewById(R.id.cet_legal_name);
        cetPhoneNum = (CleanEditText) findViewById(R.id.cet_phone_num);
        cetTelephoneNum = (CleanEditText) findViewById(R.id.cet_telephone_num);
        cetCompanyEmail = (CleanEditText) findViewById(R.id.cet_company_email);
        btnCommit = (Button) findViewById(R.id.btn_commit);
        btnCommit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_commit:// 向服务器提交注册公司的信息,完成后跳转到登陆注册界面
                String companyName = cetCompanyName.getText().toString().trim();
                String companyNum = cetCompanyNum.getText().toString().trim();
                String taxNum = cetTaxNum.getText().toString().trim();
                String organizationNum = cetOrganizationNum.getText().toString().trim();
                String legalName = cetLegalName.getText().toString().trim();
                String phoneNum = cetPhoneNum.getText().toString().trim();
                String telephoneNum = cetTelephoneNum.getText().toString().trim();
                String companyEmail = cetCompanyEmail.getText().toString().trim();


                ToastUtils.makeShortText(companyName + "注册完成", CompanyRegisterActivity.this);

//                Intent intent = new Intent(this, CompanyActivity.class);
//                intent.putExtra("companyName", companyName);
//                startActivity(intent);
                finish();

                break;
        }
    }
}
