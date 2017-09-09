package com.example.lucasrezende.igor.api;

import com.example.lucasrezende.igor.model.User;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by vzaffalon on 09/09/17.
 */

public interface UserService {

    @FormUrlEncoded
    @POST("auth")
    Call<User> createUser(@Field("email") String email, @Field("password") String password,
                          @Field("password_confirmation") String password_confirmation, @Field("nickname") String nickname);

    @FormUrlEncoded
    @POST("auth/sign_in")
    Call<User> logIn(@Field("email") String email, @Field("password") String password);


    @Headers({
            "Accept: application/json",
            "uid: {uid}",
            "client: {client}",
            "access-token: {access-token}"
    })
    @DELETE("auth/sign_out")
    Call<User> logOut(@Path("uid") String uid,@Path("client") String client,@Path("access-token") String access_token);
}
