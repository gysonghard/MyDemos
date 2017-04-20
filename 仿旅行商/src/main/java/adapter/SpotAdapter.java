package adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sgy.lvxingshang.R;
import com.example.sgy.lvxingshang.SpotDetailActivity;

import java.util.List;

/**
 * Created by sgy on 2017/2/16.
 * 景点的Adapter
 */

public class SpotAdapter extends RecyclerView.Adapter {
    public List<Integer> mImages;
    public List<String> mSpotNames;
    public List<String> mSpotInstructions;
    public List<Integer> mPrices;
    public List<Integer> mDurations;

    public SpotAdapter(List<Integer> mImages, List<String> mSpotNames,
                       List<String> mSpotInstructions, List<Integer> mPrices,List<Integer> mDurations) {
        this.mImages = mImages;
        this.mSpotNames = mSpotNames;
        this.mSpotInstructions = mSpotInstructions;
        this.mPrices = mPrices;
        this.mDurations = mDurations;
    }

    @Override
    public RecyclerView.ViewHolder  onCreateViewHolder(final ViewGroup parent, int viewType) {
        View inflate = View.inflate(parent.getContext(), R.layout.item_spot, null);
        final spotViewHolder holder = new spotViewHolder(inflate);
        holder.spotView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Intent intent = new Intent(parent.getContext(), SpotDetailActivity.class);
                intent.putExtra("spotPosition",position);
                parent.getContext().startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((spotViewHolder)holder).ivSpot.setImageResource(mImages.get(position));
        ((spotViewHolder)holder).tvSpotName.setText(mSpotNames.get(position));
        ((spotViewHolder)holder).tvIntroduction.setText(mSpotInstructions.get(position));
        ((spotViewHolder)holder).tvPrice.setText(mPrices.get(position) + "");
        ((spotViewHolder)holder).tvDuration.setText(mDurations.get(position) + "");
    }

    @Override
    public int getItemCount() {
        return mImages.size();
    }

    class spotViewHolder extends RecyclerView.ViewHolder{
        public View spotView;
        public ImageView ivSpot;
        public TextView tvSpotName;
        public TextView tvIntroduction;
        public TextView tvPrice;
        public TextView tvDuration;
        public spotViewHolder(View itemView) {
            super(itemView);
            spotView = itemView;
            ivSpot = (ImageView) itemView.findViewById(R.id.iv_item);
            tvSpotName = (TextView) itemView.findViewById(R.id.tv_spotName);
            tvIntroduction = (TextView) itemView.findViewById(R.id.tv_introduction);
            tvPrice = (TextView) itemView.findViewById(R.id.tv_price);
            tvDuration = (TextView) itemView.findViewById(R.id.tv_duration);
        }
    }
}
