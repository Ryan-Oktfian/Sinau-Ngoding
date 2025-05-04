package com.example.lazycolumn.network

import com.example.lazycolumn.data.model.User
import retrofit2.http.GET
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface ApiService {
    @GET("users")
    suspend fun getUsers(): List<User>
}