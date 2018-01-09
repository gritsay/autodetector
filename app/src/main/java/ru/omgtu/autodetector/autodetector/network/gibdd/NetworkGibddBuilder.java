package ru.omgtu.autodetector.autodetector.network.gibdd;

import android.util.Log;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by gritsay on 16.12.17.
 */

public class NetworkGibddBuilder {
    private static String url;
    private static Retrofit retrofit;

    public NetworkGibddBuilder(String url) {
        this.url = url;
    }

    public static void init(String url) {
        retrofit = new Retrofit.Builder().baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient())
                .build();
    }

    public static GibddService getGibddService() {
        return retrofit.create(GibddService.class);
    }


    public static Retrofit getRetrofit() {
        return retrofit;
    }
}
