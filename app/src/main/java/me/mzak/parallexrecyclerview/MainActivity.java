package me.mzak.parallexrecyclerview;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.mzak.paralexrecyclerview.OnLoadMoreListener;
import me.mzak.paralexrecyclerview.ParallaxRecyclerView;
import me.mzak.parallexrecyclerview.model.Image;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.recyclerView)
    ParallaxRecyclerView mRecyclerView;
    @BindView(R.id.adView)
    AdView mAdView;

    RecyclerViewAdapter adapter;

    int page = 1;
    List<Image> dataset = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerViewAdapter(this, mRecyclerView, dataset, new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                getImages();
            }
        });
        mRecyclerView.setAdapter(adapter);
        getImages();
    }

    private void getImages() {
        ParallexRecyclerViewApp.getImageService().getImages(page).enqueue(new Callback<List<Image>>() {
            @Override
            public void onResponse(Call<List<Image>> call, Response<List<Image>> response) {
                adapter.addItems(response.body());
                page++;
            }

            @Override
            public void onFailure(Call<List<Image>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }


    @Override
    public void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        mAdView.loadAd(adRequest);

        if (mAdView != null) {
            mAdView.resume();
        }
    }

    @Override
    public void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_github:
                startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse(Constants.GITHUB_URL)));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
