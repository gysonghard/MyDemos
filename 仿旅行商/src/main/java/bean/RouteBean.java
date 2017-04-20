package bean;

import java.util.List;

/**
 * Created by sgy on 2017/2/20.
 * 路线的bean,现在还没有用到!
 */

public class RouteBean {
    // 顶部的数据-->路线
    private List<Integer> mRouteTopImages;
    private List<String> mRouteTopInstructions;

    // 下面的数据-->路线
    private List<Integer> mRouteNormalImages;
    private List<String> mRouteNormalInstractions;
    private List<Integer> mRouteNormalPrices;

    // 构造方法
    public RouteBean(List<Integer> mRouteTopImages, List<String> mRouteTopInstructions,
                     List<Integer> mRouteNormalImages, List<String> mRouteNormalInstractions,
                     List<Integer> mRouteNormalPrices) {
        this.mRouteTopImages = mRouteTopImages;
        this.mRouteTopInstructions = mRouteTopInstructions;
        this.mRouteNormalImages = mRouteNormalImages;
        this.mRouteNormalInstractions = mRouteNormalInstractions;
        this.mRouteNormalPrices = mRouteNormalPrices;
    }

    public List<Integer> getmRouteTopImages() {
        return mRouteTopImages;
    }

    public void setmRouteTopImages(List<Integer> mRouteTopImages) {
        this.mRouteTopImages = mRouteTopImages;
    }

    public List<String> getmRouteTopInstructions() {
        return mRouteTopInstructions;
    }

    public void setmRouteTopInstructions(List<String> mRouteTopInstructions) {
        this.mRouteTopInstructions = mRouteTopInstructions;
    }

    public List<Integer> getmRouteNormalImages() {
        return mRouteNormalImages;
    }

    public void setmRouteNormalImages(List<Integer> mRouteNormalImages) {
        this.mRouteNormalImages = mRouteNormalImages;
    }

    public List<String> getmRouteNormalInstractions() {
        return mRouteNormalInstractions;
    }

    public void setmRouteNormalInstractions(List<String> mRouteNormalInstractions) {
        this.mRouteNormalInstractions = mRouteNormalInstractions;
    }

    public List<Integer> getmRouteNormalPrices() {
        return mRouteNormalPrices;
    }

    public void setmRouteNormalPrices(List<Integer> mRouteNormalPrices) {
        this.mRouteNormalPrices = mRouteNormalPrices;
    }

    @Override
    public String toString() {
        return "RouteBean{" +
                "mRouteTopImages=" + mRouteTopImages +
                ", mRouteTopInstructions=" + mRouteTopInstructions +
                ", mRouteNormalImages=" + mRouteNormalImages +
                ", mRouteNormalInstractions=" + mRouteNormalInstractions +
                ", mRouteNormalPrices=" + mRouteNormalPrices +
                '}';
    }
}
