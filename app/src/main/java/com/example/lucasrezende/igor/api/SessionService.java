package com.example.lucasrezende.igor.api;

import com.example.lucasrezende.igor.model.Adventure;
import com.example.lucasrezende.igor.model.Player;
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

public interface SessionService {

    @GET("sessions/{id}")
    Call<Session> get(@Path("id") int id);

    @GET("sessions")
    Call<List<Session>> list();

    @GET("sessions_by_adventure/{adventure_id}")
    Call<List<Session>> list_adventure_session(@Path("adventure_id") int adventure_id);

    @POST("sessions")
    Call<Session> create(@Body Session session);

    @DELETE("sessions/{id}")
    Call<Session> delete(@Path("id") int id);

    @PUT("sessions/{id}")
    Call<Session> update(@Path("id") int id,@Body Session session);
}
