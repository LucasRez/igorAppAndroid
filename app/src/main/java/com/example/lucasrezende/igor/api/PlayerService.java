package com.example.lucasrezende.igor.api;

import com.example.lucasrezende.igor.model.Adventure;
import com.example.lucasrezende.igor.model.Player;

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

public interface PlayerService {

    @GET("players/{id}")
    Call<Player> get(@Path("id") int id);

    @GET("players")
    Call<List<Player>> list();

    @GET("players_by_adventure/{adventure_id}")
    Call<List<Player>> list_adventure_player(@Path("adventure_id") int adventure_id);

    @POST("players")
    Call<Player> create(@Body Player player);

    @DELETE("players/{id}")
    Call<Player> delete(@Path("id") int id);

    @PUT("players/{id}")
    Call<Player> update(@Path("id") int id,@Body Player player);


}
