package com.example.lucasrezende.igor.api;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.lucasrezende.igor.Utils.Constants;
import com.example.lucasrezende.igor.model.Adventure;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by vzaffalon on 09/09/17.
 */

public class AdventureServiceImplentation {


    private String acess_token;
    private String client;
    private String expiry;
    private String uid;
    private Context context;
    private AdventureService service;

    public AdventureServiceImplentation(Context context){
        this.context = context;
        Retrofit retrofit = startRetrofit();
        service = retrofit.create(AdventureService.class);
    }

    public AdventureService getService() {
        return service;
    }

    public void setService(AdventureService service) {
        this.service = service;
    }

    private void getHeaderInformationFromSharedPreferences(){
        SharedPreferences sharedPref = context.getSharedPreferences(Constants.SharedPreferences,Context.MODE_PRIVATE);
        acess_token = sharedPref.getString("access-token", "");
        client = sharedPref.getString("client", "");
        expiry = sharedPref.getString("expiry", "");
        uid = sharedPref.getString("uid", "");
    }

    private Retrofit startRetrofit(){
        getHeaderInformationFromSharedPreferences();
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Interceptor.Chain chain) throws IOException {
                        Request original = chain.request();

                        Request request = original.newBuilder()
                                .header("access-token", acess_token)
                                .header("token-type", "Bearer")
                                .header("client",client)
                                .header("expiry",expiry)
                                .header("uid",uid)
                                .method(original.method(), original.body())
                                .build();

                        return chain.proceed(request);
                    }
                });

        OkHttpClient clientAux = httpClient.build();
        return new Retrofit.Builder()
                .baseUrl(Constants.BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(clientAux)
                .build();
    }

}
