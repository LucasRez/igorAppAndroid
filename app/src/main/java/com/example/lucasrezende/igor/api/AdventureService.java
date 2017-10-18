package com.example.lucasrezende.igor.api;

import com.example.lucasrezende.igor.model.Adventure;
import com.example.lucasrezende.igor.model.Session;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by vzaffalon on 09/09/17.
 */

public interface AdventureService {
    @GET("adventures/{id}")
    Call<Adventure> get(@Path("id") int id);

    @GET("adventures")
    Call<List<Adventure>> list();

    @GET("adventures_by_user/{user_id}")
    Call<List<Adventure>> list_user(@Path("user_id") String id);


    @POST("adventures")
    Call<Adventure> create(@Body Adventure adventure);

    @DELETE("adventures/{id}")
    Call<Adventure> delete(@Path("id") int id);

    @PUT("adventures/{id}")
    Call<Adventure> update(@Path("id") int id,@Body Adventure adventure);
}
