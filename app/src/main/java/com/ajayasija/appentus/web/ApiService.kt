package com.ajayasija.appentus.web

import com.ajayasija.appentus.model.ApiResponseItem
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    //fetch Page from server
    @GET("v2/list")
    suspend fun fetchImages(@Query("page") page:Int,@Query("limit") limit:Int):List<ApiResponseItem>

    companion object{
        private const val BASE_URL="https://picsum.photos/"

        private val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        fun getApiService():ApiService{
            //create api client
            val logger=HttpLoggingInterceptor()
            logger.level=HttpLoggingInterceptor.Level.BODY
            val client=OkHttpClient.Builder().addInterceptor(logger).build()
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
                .create(ApiService::class.java)
        }

    }
}