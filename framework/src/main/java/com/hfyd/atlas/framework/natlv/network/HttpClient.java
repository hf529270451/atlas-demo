package com.hfyd.atlas.framework.natlv.network;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Url;

/**
 * Author: hfyd
 * Date: 2018/12/19
 * Description:
 */
public class HttpClient {

    private static Map<String, Retrofit> mClients = new HashMap<>();

    public static void registClient(String tag, String baseUrl) {
        if (mClients.containsKey(tag)) {
            throw new RuntimeException("client already exist");
        }
        mClients.put(tag, createClient(baseUrl));
    }

    public static Retrofit getClient(String tag) {
        return mClients.get(tag);
    }


    private static Retrofit createClient(String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(getOkHttpClient())
                .build();
    }

    private static OkHttpClient getOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.sslSocketFactory(SSLSocketClient.getSSLSocketFactory());
        builder.hostnameVerifier(SSLSocketClient.getHostnameVerifier());
        builder.connectTimeout(10, TimeUnit.SECONDS);//设置超时时间
        builder.readTimeout(10, TimeUnit.SECONDS);//设置读取超时时间
        builder.writeTimeout(10, TimeUnit.SECONDS);//设置写入超时时间

        return builder.build();
    }
}
