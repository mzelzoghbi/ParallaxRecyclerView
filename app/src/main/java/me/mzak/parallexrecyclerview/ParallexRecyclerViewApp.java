package me.mzak.parallexrecyclerview;

import android.app.Application;

import com.google.gson.Gson;

import me.mzak.parallexrecyclerview.network.ImageService;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mohamedzakaria on 6/16/16.
 */
public class ParallexRecyclerViewApp extends Application {

    private static Retrofit retrofit;
    private static ImageService imageService;

    @Override
    public void onCreate() {
        super.onCreate();
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor());
        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();

        imageService = retrofit.create(ImageService.class);
    }

    public static ImageService getImageService() {
        return imageService;
    }
}
