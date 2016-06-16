package me.mzak.parallexrecyclerview;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.List;

import me.mzak.paralexrecyclerview.AbstractRecyclerViewAdapter;
import me.mzak.paralexrecyclerview.OnLoadMoreListener;
import me.mzak.parallexrecyclerview.model.Image;

/**
 * Created by mohamedzakaria on 6/16/16.
 */
public class RecyclerViewAdapter extends AbstractRecyclerViewAdapter<Image> {
    Activity activity;

    public RecyclerViewAdapter(Activity activity, RecyclerView recyclerView, List<Image> dataSet, OnLoadMoreListener onLoadMoreListener) {
        super(recyclerView, dataSet, onLoadMoreListener);
        this.activity = activity;
    }

    @Override
    public RecyclerView.ViewHolder onCreateBasicItemViewHolder(ViewGroup parent, int viewType) {
        return new BasicViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, null));
    }

    @Override
    public void onBindBasicItemView(RecyclerView.ViewHolder genericHolder, int position) {
        String url = Constants.IMAGE_VIEW_URL.replace("{id}", String.valueOf(getDataSet().get(position).getId()));
        BasicViewHolder holder = (BasicViewHolder) genericHolder;
        Glide.with(activity).load(url).into(holder.img);


        holder.img.reuse();
    }
}
