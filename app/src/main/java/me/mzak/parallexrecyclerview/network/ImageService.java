package me.mzak.parallexrecyclerview.network;


import java.util.List;

import me.mzak.parallexrecyclerview.model.Image;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by mohamedzakaria on 6/16/16.
 */
public interface ImageService {
    @GET("/images/{page}")
    Call<List<Image>> getImages(@Path("page") int page);
}
