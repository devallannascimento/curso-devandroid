package com.devallannascimento.testedeempregoimgur.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

object RetrofitServices {

   val imgurAPI by lazy {
        Retrofit.Builder()
            .baseUrl( "https://api.imgur.com/3/" )
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(AuthInterceptor())
                    .build()
            )
            .build()
            .create(ImgurAPI::class.java)
    }

}