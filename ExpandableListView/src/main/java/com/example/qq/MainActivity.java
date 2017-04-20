package com.example.qq;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.Map;

/**
 * 使用ExpandableListView控件,仿QQ分组的展示效果
 */
public class MainActivity extends AppCompatActivity {

    private ExpandableListView expandableListView;
    private String[] groups;
    private String[][] persons;
    private View.OnClickListener ivGoToChildClickListener;
    private boolean isExpand; // 判断分组是否展开

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        initData();
        initView();
    }

    private void initData() {
        groups = new String[]{"西游记", "水浒传", "三国演义", "红楼梦"};
        persons = new String[][]{
                {"孙悟空", "猪八戒", "沙和尚"},
                {"武松", "宋江", "卢俊义", "高俅"},
                {"曹操", "孙权", "诸葛亮", "刘备"},
                {"林黛玉", "贾宝玉", "薛宝钗", "王熙凤"}};
    }

    private void initView() {
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

        MyExpandableListViewAdapter adapter = new
                MyExpandableListViewAdapter(groups, persons, getApplicationContext(),
                ivGoToChildClickListener);
        expandableListView.setAdapter(adapter);

        // 分组的点击监听
        expandableListView.setOnGroupClickListener(
                new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                    Toast.makeText(getApplicationContext(), "点击的组名:" + groups[groupPosition],
                            Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        // 条目的点击监听
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition,
                                        int childPosition, long id) {
                Toast.makeText(getApplicationContext(), "选中的人物:" +groups[groupPosition] +
                        "组的" + persons[groupPosition][childPosition],
                        Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }
}
