package com.allannascimento.aulathreadscoroutines.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitHelper {
    companion object {

        const val API_KEY = "1c02e8b7288eecfbcc96bcb302b7f4d9"
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val BASE_URL_IMAGE = "https://image.tmdb.org/t/p/"

        val apiViaCEP = Retrofit.Builder()
            .baseUrl("https://viacep.com.br/ws/")
            .addConverterFactory(GsonConverterFactory.create() )//json ou XML
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create() )//json ou XML
            .build()

        val filmeAPI = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create() )//json ou XML
            .build()
            .create( FilmeAPI::class.java )

    }
}