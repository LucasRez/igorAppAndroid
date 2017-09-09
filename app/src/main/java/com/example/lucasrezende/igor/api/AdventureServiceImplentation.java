package com.example.lucasrezende.igor.api;

import com.example.lucasrezende.igor.Utils.Constants;
import com.example.lucasrezende.igor.model.Adventure;

import java.io.IOException;
impot

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
    AdventureService service;

    public AdventureServiceImplentation(){

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
                };

        OkHttpClient clientAux = httpClient.build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(clientAux)
                .build();

        service = retrofit.create(AdventureService.class);
    }

   /* private Adventure create(Adventure adventure){
        Adventure adventure1 = service.create(adventure);
        service.
    }*/


}
