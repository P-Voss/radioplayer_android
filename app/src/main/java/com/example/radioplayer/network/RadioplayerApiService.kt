package com.example.radioplayer.network

import com.example.radioplayer.entity.Feedback
import com.example.radioplayer.entity.SongRequest
import com.example.radioplayer.network.request.*
import com.example.radioplayer.network.response.*
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

private const val BASE_URL = "https://test-pv.de"

private val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor()
    .apply { level = HttpLoggingInterceptor.Level.HEADERS }

val client = OkHttpClient.Builder()
    .addInterceptor(interceptor)
    .build()

val gson = GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create()

private val retrofit = Retrofit.Builder()
//    .addConverterFactory(GsonConverterFactory.create())
    .addConverterFactory(GsonConverterFactory.create(gson))
    .baseUrl(BASE_URL)
    .client(client)
    .build()

interface RadioplayerApiService {

    /**
     * Radio Requests
     */
    @GET("radio")
    fun getCurrentInformation(): Call<RadioplayerResponse>

    @POST("radio/playlist/feedback")
    fun postPlaylistFeedback(@Body payload: Feedback): Call<FeedbackResponse>

    @POST("radio/moderation/feedback")
    fun postModerationFeedback(@Body payload: Feedback): Call<FeedbackResponse>

    @POST("radio/songrequest")
    fun postSongrequest(@Body payload: SongRequest): Call<FeedbackResponse>

    /**
     * Moderator Requests
     */
    @POST("radio/moderation/login")
    fun login(@Body payload: LoginRequest): Call<LoginResponse>

    @GET("radio/moderation/dashboard")
    fun loadDashboard(): Call<DashboardResponse>

    @POST("radio/songrequests/accept")
    fun acceptSongrequest(@Body payload: Feedback): Call<FeedbackResponse>
}

object RadioplayerApi {

    val retrofitService: RadioplayerApiService by lazy {
        retrofit.create(RadioplayerApiService::class.java)
    }

}