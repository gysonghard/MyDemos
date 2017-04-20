package com.example.materialdesign;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import adapter.FruitAdapter;

/**
 * 新特性-->MaterialDesign
 * 新特性:材料设计
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private DrawerLayout drawerLayout;
    private FloatingActionButton floatingActionButton;
    private RecyclerView recyclerView;
    private List<Fruit> fruitList;
    private SwipeRefreshLayout refreshLayout;
    private Fruit[] fruits;
    private FruitAdapter adapter;

    // 创建菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu); // 指定引入的菜单文件

        return true; // 返回true表示菜单要显示出来;返回false表示菜单隐藏
    }

    // 菜单中的条目被选中的事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.backup:
                Toast.makeText(getApplicationContext(), "点击了:backup",
                        Toast.LENGTH_SHORT).show();
                break;

            case R.id.delete:
                Toast.makeText(getApplicationContext(), "点击了:delete",
                        Toast.LENGTH_SHORT).show();
                break;

            case R.id.settings:
                Toast.makeText(getApplicationContext(), "点击了:settings",
                        Toast.LENGTH_SHORT).show();
                break;

            case android.R.id.home: // 点击顶部按钮就弹出左侧菜单,id永远是android.R.id.home
                drawerLayout.openDrawer(GravityCompat.START);
                break;
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true); // 让导航按钮显示出来
        }
        // 滑动菜单页面
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        // 设置默认选中菜单项
        navigationView.setCheckedItem(R.id.nav_call);
        // 设置菜单项选中事件的监听器
        navigationView.setNavigationItemSelectedListener(new NavigationView.
                OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // 关闭滑动菜单
                drawerLayout.closeDrawers();
                Toast.makeText(getApplicationContext(), "点击了" + item.getItemId(),
                        Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        // 悬浮按钮
        floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(this);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        // 创建Fruit对象
        fruits = new Fruit[]{new Fruit("apple", R.drawable.ic_launcher),
                new Fruit("Banana", R.drawable.next),
                new Fruit("Pear", R.drawable.a)};
        fruitList = new ArrayList<>();
        initFruits();
        GridLayoutManager manager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(manager);
        adapter = new FruitAdapter(fruitList);
        recyclerView.setAdapter(adapter);

        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        refreshLayout.setColorSchemeResources(R.color.colorPrimary); // 设置下拉刷新进度条的颜色
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() { // 下拉刷新的监听器
            @Override
            public void onRefresh() {
                refreshRecyclerData();
            }
        });
    }

    /**
     * 刷新RecyclerView的Adapter的数据
     */
    private void refreshRecyclerData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initFruits();
                        adapter.notifyDataSetChanged();
                        refreshLayout.setRefreshing(false); // 刷新结束后隐藏进度条
                    }
                });
            }
        }).start();
    }

    private void initFruits() {
        for (int i = 0; i < 50; i++) {
            Random random = new Random();
            int index = random.nextInt(fruits.length);
            fruitList.add(fruits[index]);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                // 使用Snackbar,可以比Toast有更好的用户体验
                Snackbar.make(v, "Data delete", Snackbar.LENGTH_SHORT)
                        .setAction("Undo", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(getApplicationContext(), "Data restore",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }).show();
                break;
        }
    }
}
