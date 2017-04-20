package adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sgy.lvxingshang.R;
import com.example.sgy.lvxingshang.RouteDetailActivity;

import java.util.List;

/**
 * Created by sgy on 2017/2/22.
 * 路线顶部列表的adapter
 * 注意:所有的列表都是RecyclerView,所以共用一个adapter
 */

public class RouteTopListAdapter extends RecyclerView.Adapter<RouteTopListAdapter.
        RouteTopListViewHolder> {

    private List<Integer> mTopListImages; // 下面图片信息
    private List<String> mTopListIntroductions; // 下面介绍信息
    private List<Integer> mTopListPrices; // 下面价格

    public RouteTopListAdapter(List<Integer> mTopListImages,
                               List<String> mTopListIntroductions, List<Integer> mTopListPrices) {
        this.mTopListImages = mTopListImages;
        this.mTopListIntroductions = mTopListIntroductions;
        this.mTopListPrices = mTopListPrices;
    }

    @Override
    public RouteTopListViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(),R.layout.item_route_nomal,null);
        final RouteTopListViewHolder holder = new RouteTopListViewHolder(view);
        holder.ivNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Intent intent = new Intent(parent.getContext(), RouteDetailActivity.class);
                intent.putExtra("wineListPosition",position);
                parent.getContext().startActivity(intent);
                Toast.makeText(parent.getContext(),"点击的位置:" + position,
                        Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(RouteTopListViewHolder holder, int position) {
        holder.ivNormal.setImageResource(mTopListImages.get(position));
        holder.tvIntroduction.setText(mTopListIntroductions.get(position));
        holder.tvPrice.setText(mTopListPrices.get(position) + "");
    }

    @Override
    public int getItemCount() {
        return mTopListImages.size();
    }

    class RouteTopListViewHolder extends RecyclerView.ViewHolder{
        View routeView;
        public ImageView ivNormal;
        public TextView tvIntroduction;
        public TextView tvPrice;

        public RouteTopListViewHolder(View itemView) {
            super(itemView);
            routeView = itemView;
            ivNormal = (ImageView) itemView.findViewById(R.id.iv_item);
            tvIntroduction = (TextView) itemView.findViewById(R.id.tv_introduction);
            tvPrice = (TextView) itemView.findViewById(R.id.tv_price);
        }
    }
}
