package com.levent_j.timetable.net;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by levent_j on 16-11-9.
 */
public class Api {
    private static volatile ApiService INSTANCE;

    private static final String HOST = "119.29.100.225";
    private static final String PORT = "8080";
    private static final String BASE_URL = "http://"+HOST+":"+PORT+"/timetable/";

    private Api(){}

    public static ApiService getINSTANCE(){
        if (INSTANCE==null){
            synchronized (ApiService.class){
                if (INSTANCE==null){
                    INSTANCE = createApiService();
                }
            }
        }

        return INSTANCE;
    }

    private static ApiService createApiService() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .client(new OkHttpClient())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(ApiService.class);
    }
}
