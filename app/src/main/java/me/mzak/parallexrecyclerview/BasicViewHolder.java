package me.mzak.parallexrecyclerview;

import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.mzak.paralexrecyclerview.ParallaxImageView;
import me.mzak.paralexrecyclerview.ParallaxViewHolder;

/**
 * Created by mohamedzakaria on 6/16/16.
 */
public class BasicViewHolder extends ParallaxViewHolder {
    @BindView(R.id.img)
    public ParallaxImageView img;

    @Override
    public int getParallaxImageId() {
        return R.id.img;
    }

    public BasicViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
