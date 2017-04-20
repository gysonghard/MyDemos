package com.example.sgy.lvxingshang;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import fragment.PlayRouteCommentFragment;
import fragment.PlayRouteDetailFragment;

/**
 * Created by sgy on 2017/2/21.
 * 路线点击跳转过来的,路线详情界面
 */

public class RouteDetailActivity extends Activity implements View.OnClickListener {

    private ViewStub viewStub;
    private ImageView ivShowMore;
    Boolean showed = false; // 套餐详情是否已经显示
    private FrameLayout flContent;
    private TextView tvDetail;
    private TextView tvComment;
    private LinearLayout llDetailLine;
    private LinearLayout llCommentLine;
    private PlayRouteDetailFragment detailFragment;
    private PlayRouteCommentFragment commentFragment;
    private LinearLayout llCollect;
    private LinearLayout llCall;
    private LinearLayout llPlay;
    private LinearLayout llMoney;
    private Button btnOrder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_normal_detail);
        // 判断从哪个条目跳转过来的
//        Intent intent = getIntent();
//        int normalPosition = intent.getIntExtra("normalPosition", 0);
//        Toast.makeText(getApplicationContext(),"点击的下面的位置:" + normalPosition,
//                Toast.LENGTH_SHORT).show();
        init();
        // 默认开始显示详情Fragment
        setDefaultFragment();
    }

    private void init() {
        initView();
        initData();
    }


    private void initView() {
        ivShowMore = (ImageView) findViewById(R.id.iv_show);
        ivShowMore.setOnClickListener(this);
        viewStub = (ViewStub) findViewById(R.id.vs_detail);

        tvDetail = (TextView) findViewById(R.id.tvDetail);
        tvDetail.setOnClickListener(this);
        tvComment = (TextView) findViewById(R.id.tvComment);
        tvComment.setOnClickListener(this);
        llDetailLine = (LinearLayout) findViewById(R.id.ll_detail);
        llCommentLine = (LinearLayout) findViewById(R.id.ll_comment);
        flContent = (FrameLayout) findViewById(R.id.fl_content);

        // 预订
        btnOrder = (Button) findViewById(R.id.btn_order);

        // 底部的tab
        llCollect = (LinearLayout) findViewById(R.id.ll_collect);
        llCollect.setOnClickListener(this);
        llCall = (LinearLayout) findViewById(R.id.ll_call);
        llCall.setOnClickListener(this);
        llPlay = (LinearLayout) findViewById(R.id.ll_play);
        llPlay.setOnClickListener(this);
        llMoney = (LinearLayout) findViewById(R.id.ll_money);
        llMoney.setOnClickListener(this);
    }

    private void setDefaultFragment() {
        llDetailLine.setVisibility(View.VISIBLE);
        android.app.FragmentManager fm = getFragmentManager();
        android.app.FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fl_content, new PlayRouteDetailFragment());
        ft.commit();
    }

    private void initData() {

    }

    @Override
    public void onClick(View v) {
        android.app.FragmentManager fm = getFragmentManager();
        android.app.FragmentTransaction ft = fm.beginTransaction();
        switch (v.getId()) {
            case R.id.iv_show:
                if (showed) {
                    viewStub.setVisibility(View.GONE);
                    showed = false;
                } else {
                    viewStub.setVisibility(View.VISIBLE);
                    showed = true;
                }
                break;

            case R.id.tvDetail:
                if (detailFragment == null) {
                    detailFragment = new PlayRouteDetailFragment();
                }
                ft.replace(R.id.fl_content,detailFragment);
                ft.commit();
                llDetailLine.setVisibility(View.VISIBLE);
                llCommentLine.setVisibility(View.GONE);
                break;

            case R.id.tvComment:
                if (commentFragment == null) {
                    commentFragment = new PlayRouteCommentFragment();
                }
                ft.replace(R.id.fl_content,commentFragment);
                ft.commit();
                llCommentLine.setVisibility(View.VISIBLE);
                llDetailLine.setVisibility(View.GONE);
                break;

            case R.id.btn_order:
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
                break;

            case R.id.ll_collect:
                Intent intent2 = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent2);
                break;

            case R.id.ll_call:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                LayoutInflater layoutInflater = getLayoutInflater();
                View view = layoutInflater.inflate(R.layout.activity_route_detail_dialog,null);
                View call = view.findViewById(R.id.ll_call_out);
                call.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent callIntent = new Intent(Intent.ACTION_DIAL);
                        callIntent.setData(Uri.parse("tel:4001137010"));
                        startActivity(callIntent);
                    }
                });
                builder.setView(view);
                builder.show();
                break;

            case R.id.ll_play:
                Intent playIntent = new Intent(getApplicationContext(),RoutePlayActivity.class);
                startActivity(playIntent);
                break;

            case R.id.ll_money:
                AlertDialog.Builder moneyBuilder = new AlertDialog.Builder(this);
                LayoutInflater moneyInflater = getLayoutInflater();
                View moneyView = moneyInflater.inflate(R.layout.money_dialog,null);
                moneyBuilder.setView(moneyView);
                moneyBuilder.show();
                break;
        }
    }

}
