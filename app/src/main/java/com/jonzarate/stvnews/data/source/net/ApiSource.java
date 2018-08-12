package com.jonzarate.stvnews.data.source.net;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jonzarate.stvnews.BuildConfig;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiSource {

    private static Gson gson = new GsonBuilder().create();

    public static STVApi api =  new Retrofit.Builder()
            .baseUrl(BuildConfig.STV_API)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(STVApi.class);

}
