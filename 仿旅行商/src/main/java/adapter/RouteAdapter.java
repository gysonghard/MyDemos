package adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sgy.lvxingshang.R;
import com.example.sgy.lvxingshang.RouteDetailActivity;
import com.example.sgy.lvxingshang.RouteTopCityListActivity;
import com.example.sgy.lvxingshang.RouteTopInListActivity;
import com.example.sgy.lvxingshang.RouteTopOutListActivity;
import com.example.sgy.lvxingshang.RouteTopParkListActivity;
import com.example.sgy.lvxingshang.RouteTopSonListActivity;
import com.example.sgy.lvxingshang.RouteTopSpaListActivity;
import com.example.sgy.lvxingshang.RouteTopSundayListActivity;
import com.example.sgy.lvxingshang.RouteTopWineListActivity;

import java.util.List;

/**
 * Created by sgy on 2017/2/10.
 * 路线的Adapter
 */

public class RouteAdapter extends RecyclerView.Adapter {
    private static final int TOP_TYPE_ITEM = 0;
    private static final int NORMAL_TYPE_ITEM = 1;

    private List<Integer> mTopImages; // 顶部图片信息
    private List<Integer> mNormalImages; // 下面图片信息
    private List<String> mNormalIntroductions; // 下面介绍信息
    private List<Integer> mPrices; // 下面价格
    // 路线-->顶部的item布局
    private View routeTopItem;
    private View viewWine;
    private View viewSunday;
    private View viewIn;
    private View viewSon;
    private View viewCity;
    private View viewSPA;
    private View viewPark;
    private View viewOut;
    private View.OnClickListener listener;
    // 初始化上下文,顶部点击跳转的时候用
    private Context context;

    public RouteAdapter(List<Integer> mTopImages, List<Integer> mNormalImages,
                        List<String> mNormalIntroductions, List<Integer> mPrices) {
        this.mTopImages = mTopImages;
        this.mNormalImages = mNormalImages;
        this.mNormalIntroductions = mNormalIntroductions;
        this.mPrices = mPrices;
    }

    /**
     * 根据条目类型判断
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TOP_TYPE_ITEM;
        } else {
            return NORMAL_TYPE_ITEM;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        if (viewType == TOP_TYPE_ITEM) { // 顶部item
            // 这样就能修复RecyclerView里面的item不能填满父窗体的问题
            LayoutInflater inflater = (LayoutInflater) parent.getContext().
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            routeTopItem = inflater.from(parent.getContext()).inflate(R.layout.item_route_top,
                    parent, false);
            initRouteTopView();
            context = parent.getContext();
            initRouteTopListener();
            final routeTopViewHolder topViewHolder = new routeTopViewHolder(routeTopItem);
            return topViewHolder;
        } else { // 下面的item
            View view = View.inflate(parent.getContext(), R.layout.item_route_nomal, null);
            final routeNomalViewHoler normalHolder = new routeNomalViewHoler(view);
            normalHolder.ivNormal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int normalPostion = normalHolder.getAdapterPosition();
                    Intent intent = new Intent(parent.getContext(),
                            RouteDetailActivity.class);
                    intent.putExtra("normalPosition", normalPostion);
                    parent.getContext().startActivity(intent);
                }
            });
            return normalHolder;
        }
    }

    /**
     * 初始化路线-->顶部的View
     */
    private void initRouteTopView() {
        viewWine = routeTopItem.findViewById(R.id.ll_wine);
        viewSunday = routeTopItem.findViewById(R.id.ll_sunday);
        viewIn = routeTopItem.findViewById(R.id.ll_in);
        viewSon = routeTopItem.findViewById(R.id.ll_son);
        viewCity = routeTopItem.findViewById(R.id.ll_city);
        viewSPA = routeTopItem.findViewById(R.id.ll_SPA);
        viewPark = routeTopItem.findViewById(R.id.ll_park);
        viewOut = routeTopItem.findViewById(R.id.ll_out);
    }

    /**
     * 初始化顶部item的点击监听
     */
    private void initRouteTopListener() {
        viewWine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchActivity(RouteTopWineListActivity.class);
            }
        });
        viewSunday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchActivity(RouteTopSundayListActivity.class);
            }
        });
        viewIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchActivity(RouteTopInListActivity.class);
            }
        });
        viewSon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchActivity(RouteTopSonListActivity.class);
            }
        });
        viewCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchActivity(RouteTopCityListActivity.class);
            }
        });
        viewSPA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchActivity(RouteTopSpaListActivity.class);
            }
        });
        viewPark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchActivity(RouteTopParkListActivity.class);
            }
        });
        viewOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchActivity(RouteTopOutListActivity.class);
            }
        });
    }

    /**
     * 点击跳转到相应的Activity
     * @param cls 目的Activity
     */
    private void switchActivity(Class<?> cls) {
        Intent intent = new Intent(context, cls);
        context.startActivity(intent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof routeTopViewHolder) {
            ((routeTopViewHolder) holder).ivTop.setImageResource(mTopImages.get(position));
        } else {
            ((routeNomalViewHoler) holder).ivNormal.setImageResource(mNormalImages.get(position));
            ((routeNomalViewHoler) holder).tvIntroduction.setText(mNormalIntroductions.get(position));
            ((routeNomalViewHoler) holder).tvPrice.setText(mPrices.get(position) + "");
        }
    }

    @Override
    public int getItemCount() {
        return mNormalIntroductions.size();
    }

    /**
     * 路线上面的ViewHolder
     */
    class routeTopViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public ImageView ivTop;
        public TextView tvTop;

        public routeTopViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ivTop = (ImageView) itemView.findViewById(R.id.iv_top);
            tvTop = (TextView) itemView.findViewById(R.id.tv_top);
        }
    }

    /**
     * 路线下面的ViewHolder
     */
    class routeNomalViewHoler extends RecyclerView.ViewHolder {
        View routeView;
        public ImageView ivNormal;
        public TextView tvIntroduction;
        public TextView tvPrice;

        public routeNomalViewHoler(View itemView) {
            super(itemView);
            routeView = itemView;
            ivNormal = (ImageView) itemView.findViewById(R.id.iv_item);
            tvIntroduction = (TextView) itemView.findViewById(R.id.tv_introduction);
            tvPrice = (TextView) itemView.findViewById(R.id.tv_price);
        }
    }
}
