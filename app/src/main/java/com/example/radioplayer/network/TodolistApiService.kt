package com.example.radioplayer.network

import com.example.radioplayer.network.request.*
import com.example.radioplayer.network.response.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

private const val BASE_URL = "https://test-pv.de"

private val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor()
    .apply { level = HttpLoggingInterceptor.Level.HEADERS }

val client = OkHttpClient.Builder()
    .addInterceptor(interceptor)
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .client(client)
    .build()

interface RadioplayerApiService {

    /*
    User Requests
     */

    @POST("login")
    fun login(@Body payload: LoginRequest): Call<LoginResponse>

}

object TodolistApi {

    val retrofitService: RadioplayerApiService by lazy {
        retrofit.create(RadioplayerApiService::class.java)
    }

}