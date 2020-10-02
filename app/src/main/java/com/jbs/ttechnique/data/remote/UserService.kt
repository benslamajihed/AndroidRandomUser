package com.jbs.ttechnique.data.remote

import com.jbs.ttechnique.data.model.User
import com.jbs.ttechnique.data.model.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UserService {

    //page=3&results=10&seed=abc
    @GET("api")
    suspend fun getAllUsers(@Query("page") page: Int, @Query("results") results: Int, @Query("seed") seed: String): Response<UserResponse>

    @GET("users/{id}")
    suspend fun getUsers(@Path("id") id: Int): Response<User>

}