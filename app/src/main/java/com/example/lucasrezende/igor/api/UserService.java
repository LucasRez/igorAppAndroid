package com.example.lucasrezende.igor.api;

import com.example.lucasrezende.igor.model.Session;
import com.example.lucasrezende.igor.model.User;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by vzaffalon on 09/09/17.
 */

public interface UserService {

    @FormUrlEncoded
    @POST("auth")
    Call<ResponseBody> createUser(@Field("email") String email, @Field("password") String password,
                          @Field("password_confirmation") String password_confirmation,@Field("nickname") String nickname);

    @FormUrlEncoded
    @POST("auth/sign_in")
    Call<ResponseBody> logIn(@Field("email") String email, @Field("password") String password);

    @GET("users/find_by_nickname/{nickname}")
    Call<User> get_user_by_nickname(@Path("nickname") String nickname);


    @Headers({
            "Accept: application/json",
            "uid: {uid}",
            "client: {client}",
            "access-token: {access-token}"
    })
    @DELETE("auth/sign_out")
    Call<ResponseBody> logOut(@Path("uid") String uid,@Path("client") String client,@Path("access-token") String access_token);
}
