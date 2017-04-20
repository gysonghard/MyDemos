package com.devilwwj.loginandregister.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.devilwwj.loginandregister.R;
import com.devilwwj.loginandregister.adapter.CompanyExpandableListViewAdapter;
import com.devilwwj.loginandregister.utils.ToastUtils;

import java.util.Map;

/**
 * Created by sgy on 2017/4/6.
 * 公司登陆进去后的界面
 */

public class CompanyActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvCompanyName;
    private Button btnAdd;

    private ExpandableListView expandableListView;
    private String[] groups;
    private String[][] persons;
    private View.OnClickListener ivGoToChildClickListener;
    private boolean isExpand; // 判断分组是否展开

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company);
        init();
    }

    private void init() {
        initData();
        initView();
    }

    private void initData() {
        groups = new String[]{"部门1", "部门2", "部门3", "部门4"};
        persons = new String[][]{
                {"孙悟空", "猪八戒", "沙和尚"},
                {"武松", "宋江", "卢俊义", "高俅"},
                {"曹操", "孙权", "诸葛亮", "刘备"},
                {"林黛玉", "贾宝玉", "薛宝钗", "王熙凤"}};
    }

    private void initView() {
        tvCompanyName = (TextView) findViewById(R.id.tv_company_name);
        tvCompanyName.setText(getIntent().getStringExtra("companyName"));
        btnAdd = (Button) findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(this);

        // 打开或者收起的点击事件,position和isExpand都是通过tag传递的
        ivGoToChildClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Object> map = (Map<String, Object>) v.getTag();
                int groupPosition = (int) map.get("groupPosition");
                isExpand = expandableListView.isGroupExpanded(groupPosition);
                if (isExpand) { // 如果已经展开,点击就会收起;否则,点击展开
                    expandableListView.collapseGroup(groupPosition);
                } else {
                    expandableListView.expandGroup(groupPosition);
                }
            }
        };
        expandableListView = (ExpandableListView) findViewById(R.id.expand_listview);

        CompanyExpandableListViewAdapter adapter = new
                CompanyExpandableListViewAdapter(groups, persons, getApplicationContext(),
                ivGoToChildClickListener);
        expandableListView.setAdapter(adapter);

        // 分组的点击监听
//        expandableListView.setOnGroupClickListener(
//                new ExpandableListView.OnGroupClickListener() {
//                    @Override
//                    public boolean onGroupClick(ExpandableListView parent, View v,
//                                                int groupPosition, long id) {
////                        Toast.makeText(getApplicationContext(), "点击的组名:" + groups[groupPosition],
////                                Toast.LENGTH_SHORT).show();
//                        ToastUtils.makeShortText( "点击的组名:" + groups[groupPosition],getApplicationContext());
//                        return false;
//                    }
//                });

        // 条目的点击监听

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition,
                                        int childPosition, long id) {
//                Toast.makeText(getApplicationContext(), "选中的人物:" +groups[groupPosition] +
//                                "组的" + persons[groupPosition][childPosition],
//                        Toast.LENGTH_SHORT).show();

                ToastUtils.makeShortText("点击的人物:" + groups[groupPosition] +
                        "分组的" + persons[groupPosition][childPosition], getApplicationContext());
                return true;
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add: // 添加员工
                Intent intent = new Intent(this, AddEmployeeActivity.class);
                startActivity(intent);
//                ToastUtils.makeShortText("添加员工",this);
                break;
        }
    }
}
