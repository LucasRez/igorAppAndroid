package com.example.lucasrezende.igor.api;

import android.content.Context;

import com.example.lucasrezende.igor.Utils.Constants;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by vzaffalon on 09/09/17.
 */

public class UserServiceImplentation {

    private Context context;

    private UserService service;

    public UserServiceImplentation(Context context){
        this.context = context;
        Retrofit retrofit = startRetrofit();
        service = retrofit.create(UserService.class);
    }

    public UserService getService() {
        return service;
    }


    private Retrofit startRetrofit(){
        return new Retrofit.Builder()
                .baseUrl(Constants.BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

}
