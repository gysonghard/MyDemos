package fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.example.sgy.lvxingshang.R;

import java.util.ArrayList;
import java.util.List;

import adapter.RouteAdapter;
import adapter.SpotAdapter;

/**
 * Created by sgy on 2017/2/9.
 * 玩转的Fragment
 */

public class PlayFragment extends Fragment implements View.OnClickListener {

    private View view;
    private Button btnRoute;
    private Button btnSpot;
    private Button btnHotel;
    private Button btnTicket;
    private Button btnBoat;
    private Button btnGoods;

    // 顶部的数据-->路线
    private List<Integer> mRouteTopImages;

    // 下面的数据-->路线
    private List<Integer> mRouteNormalImages;
    private List<String> mRouteNormalInstractions;
    private List<Integer> mRouteNormalPrices;

    // 数据-->景点
    private List<Integer> mSpotImages;
    private List<String> mSpotNames;
    private List<String> mSpotInstructions;
    private List<Integer> mSpotPrices;
    private List<Integer> mSpotDurations;

    private RecyclerView routeRecyclerView;
    private FrameLayout flRouteFragment;
    private TextView tvLocation;
    private EditText etSearch;
    private ImageView ivMessage;

    // 高德地图定位
    public AMapLocationClient locationClient = null;
    public AMapLocationClientOption locationOption = new AMapLocationClientOption();
    public String address;
    public String city;

    // 如果是第一次进入,默认显示路线的adapter
    public boolean flag = true;
    private AMapLocationListener locationListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_play, null);
        init();
        return view;
    }

    /**
     * 初始化
     */
    public void init() {
        initData();
        initView();
        initLocation();
    }
    /**
     * 初始化定位
     */
    public void initLocation() {
        locationClient = new AMapLocationClient(getContext());
        // 设置定位监听
        // 得到定位的城市信息
        locationListener = new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                if (aMapLocation != null) {
                    if (aMapLocation.getErrorCode() == 0) {
                        // 得到定位的城市信息
                        city = aMapLocation.getCity();
                        address = aMapLocation.getAddress();
                        Log.e("AAA",city + address);
                        tvLocation.setText(city);
                        destroyLocation();
                    }
                }
            }
        };
        // 设置定位监听
        locationClient.setLocationListener(locationListener);
        // 设置定位的参数
        locationClient.setLocationOption(getDefaultOption());
        // 开始定位
        locationClient.startLocation();
    }

    /**
     * 默认的定位参数
     * @author hongming.wang
     * @since 2.8.0
     */
    private AMapLocationClientOption getDefaultOption() {
        AMapLocationClientOption mOption = new AMapLocationClientOption();
        mOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);//可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
        mOption.setGpsFirst(false);//可选，设置是否gps优先，只在高精度模式下有效。默认关闭
        mOption.setHttpTimeOut(30000);//可选，设置网络请求超时时间。默认为30秒。在仅设备模式下无效
        mOption.setInterval(2000);//可选，设置定位间隔。默认为2秒
        mOption.setNeedAddress(true);//可选，设置是否返回逆地理地址信息。默认是true
        mOption.setOnceLocation(false);//可选，设置是否单次定位。默认是false
        mOption.setOnceLocationLatest(false);//可选，设置是否等待wifi刷新，默认为false.如果设置为true,会自动变为单次定位，持续定位时不要使用
        AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTP);//可选， 设置网络请求的协议。可选HTTP或者HTTPS。默认为HTTP
        mOption.setSensorEnable(false);//可选，设置是否使用传感器。默认是false
        mOption.setWifiScan(true); //可选，设置是否开启wifi扫描。默认为true，如果设置为false会同时停止主动刷新，停止以后完全依赖于系统刷新，定位位置可能存在误差
        mOption.setLocationCacheEnable(true); //可选，设置是否使用缓存定位，默认为true
        return mOption;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        destroyLocation();
    }

    /**
     * 销毁定位
     * @author hongming.wang
     * @since 2.8.0
     */
    private void destroyLocation() {
        if (null != locationClient) {
            /**
             * 如果AMapLocationClient是在当前Activity实例化的，
             * 在Activity的onDestroy中一定要执行AMapLocationClient的onDestroy
             */
            locationClient.onDestroy();
            locationClient = null;
            locationOption = null;
        }
    }

    /**
     * 初始化数据
     */
    private void initData() {
        initRouteData();
        initSpotData();
    }

    /**
     * 初始化景点的数据
     */
    private void initSpotData() {
        mSpotImages = new ArrayList<>();
        mSpotNames = new ArrayList<>();
        mSpotInstructions = new ArrayList();
        mSpotPrices = new ArrayList<>();
        mSpotDurations = new ArrayList<>();

        for (int i = 1; i < 100; i++) {
            mSpotImages.add(R.drawable.ic_launcher);
            mSpotNames.add("景点" + i);
            mSpotInstructions.add("这是景点" + i + "的介绍信息");
            mSpotPrices.add(i);
            mSpotDurations.add(i);
        }
    }

    /**
     * 初始化路线的数据
     */
    private void initRouteData() {
        mRouteTopImages = new ArrayList<>();
        mRouteNormalImages = new ArrayList<>();
        mRouteNormalInstractions = new ArrayList<>();
        mRouteNormalPrices = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            mRouteTopImages.add(R.drawable.ic_launcher);
            mRouteNormalImages.add(R.drawable.ic_launcher);
            mRouteNormalInstractions.add("这是下面的介绍信息" + i);
            mRouteNormalPrices.add(i);
        }
    }

    /**
     * 初始化View
     */
    private void initView() {
        btnRoute = (Button) view.findViewById(R.id.rb_route);
        btnRoute.setOnClickListener(this);

        btnSpot = (Button) view.findViewById(R.id.rb_spot);
        btnSpot.setOnClickListener(this);

        btnHotel = (Button) view.findViewById(R.id.rb_hotel);
        btnHotel.setOnClickListener(this);

        btnTicket = (Button) view.findViewById(R.id.rb_ticket);
        btnTicket.setOnClickListener(this);

        btnBoat = (Button) view.findViewById(R.id.rb_boat);
        btnBoat.setOnClickListener(this);

        btnGoods = (Button) view.findViewById(R.id.rb_goods);
        btnGoods.setOnClickListener(this);

        flRouteFragment = (FrameLayout) view.findViewById(R.id.fl_play);
        routeRecyclerView = (RecyclerView) view.findViewById(R.id.recycleview);

//        routeRecyclerView.setVisibility(View.VISIBLE);
        // 都是recyclerview,根据选择的条目不同,设置不同的Adapter
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        routeRecyclerView.setLayoutManager(manager);

        // 如果是第一次进入,默认显示路线的dapter
        if (flag) {
            RouteAdapter adapter = new RouteAdapter(mRouteTopImages, mRouteNormalImages,
                     mRouteNormalInstractions, mRouteNormalPrices);
            routeRecyclerView.setAdapter(adapter);
            flag = false;
        }

        tvLocation = (TextView) view.findViewById(R.id.tv_location);
        tvLocation.setOnClickListener(this);

        etSearch = (EditText) view.findViewById(R.id.et_search);
        etSearch.setOnClickListener(this);

        ivMessage = (ImageView) view.findViewById(R.id.iv_message);
        ivMessage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // 点击不同的按钮,加载不同的布局!!!
        // 注意:应该把所有的布局放到集合里面,这样的话,点击切换的时候判断比较就可以了.
        switch (v.getId()) {
            case R.id.rb_route: // 路线
                routeRecyclerView.setVisibility(View.VISIBLE);
                RouteAdapter adapter = new RouteAdapter(mRouteTopImages, mRouteNormalImages,
                         mRouteNormalInstractions, mRouteNormalPrices);
                routeRecyclerView.setAdapter(adapter);
                break;
            case R.id.rb_spot: // 景点
//                routeRecyclerView.setVisibility(View.GONE);
                SpotAdapter spotAdapter = new SpotAdapter(mSpotImages, mSpotNames,
                        mSpotInstructions, mSpotPrices, mSpotDurations);
                routeRecyclerView.setAdapter(spotAdapter);
                break;
            case R.id.rb_hotel: // 酒店-->recycleview

                break;
            case R.id.rb_ticket: // 票务,敬请期待

                break;
            case R.id.rb_boat: // 邮轮,敬请期待

                break;
            case R.id.rb_goods: // 商品(类比酒店!)-->recycleview

                break;
            case R.id.et_search: // 搜索
                Toast.makeText(getContext(), "跳转到搜索界面", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_message: // 消息
                Toast.makeText(getContext(), "跳转到消息界面", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_location:
                Toast.makeText(getContext(), city + address, Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
