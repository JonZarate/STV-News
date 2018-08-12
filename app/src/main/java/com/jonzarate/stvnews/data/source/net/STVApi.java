package com.jonzarate.stvnews.data.source.net;

import com.jonzarate.stvnews.data.model.ImageUrl;
import com.jonzarate.stvnews.data.model.NewsList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface STVApi {

    @GET("articles/?count=50&navigationLevelId=1219&orderBy=ranking+DESC%2C+createdAt+DESC&full=1&count=20")
    Call<NewsList> getNews();

    @GET("images/{id}/rendition/?width=640&height=360")
    Call<ImageUrl> getImageUrl(@Path("id") int imageId);

}
