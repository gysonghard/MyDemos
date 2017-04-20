package com.example.sgy.lvxingshang;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import fragment.ContactFragment;
import fragment.LvXingShangFragment;
import fragment.MineFragment;
import fragment.PlayFragment;
import fragment.SearchFragment;

/**
 * 旅行商主界面!!!
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnLv;
    private Button btnContact;
    private Button btnPlay;
    private Button btnMine;
    private Button btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        // 设置一开始就选中玩转!!!
        repalceFragment(new PlayFragment());
    }

    /**
     * 初始化View控件
     */
    private void init() {

        btnLv = (Button) findViewById(R.id.rb_lv);
        btnLv.setOnClickListener(this);

        btnContact = (Button) findViewById(R.id.rb_contact);
        btnContact.setOnClickListener(this);

        btnPlay = (Button) findViewById(R.id.rb_play);
        btnPlay.setOnClickListener(this);

        btnSearch = (Button) findViewById(R.id.rb_search);
        btnSearch.setOnClickListener(this);

        btnMine = (Button) findViewById(R.id.rb_mine);
        btnMine.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rb_lv:
                repalceFragment(new LvXingShangFragment());
                break;
            case R.id.rb_contact:
                repalceFragment(new ContactFragment());
                break;
            case R.id.rb_play:
                repalceFragment(new PlayFragment());
                break;
            case R.id.rb_search:
                repalceFragment(new SearchFragment());
                break;
            case R.id.rb_mine:
                repalceFragment(new MineFragment());
                break;
        }
    }

    /**
     * 替换为相应的Fragment
     *
     * @param fragment
     */
    private void repalceFragment(Fragment fragment) {

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        if (btnPlay.isFocused()) {
            transaction.replace(R.id.fl_main, new PlayFragment());
        } else {
            transaction.replace(R.id.fl_main, fragment);
        }
        transaction.commit();
    }
}
