package com.allannascimento.aulathreadscoroutines.api

import com.allannascimento.aulathreadscoroutines.api.RetrofitHelper.Companion.API_KEY
import com.allannascimento.aulathreadscoroutines.model.Filme
import com.allannascimento.aulathreadscoroutines.model.FilmeDetalhes
import com.allannascimento.aulathreadscoroutines.model.FilmeResposta
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FilmeAPI {

    @GET("movie/popular?api_key=$API_KEY")
    suspend fun recuperarFilmesPopulares(): Response<FilmeResposta>

    @GET("movie/{id}?api_key=$API_KEY")
    suspend fun recuperarDetalhesFilme(
        @Path("id") id: Int
    ): Response<Filme>

    @GET("movie/{id}?api_key=$API_KEY")
    suspend fun recuperarFilmeDetalhes(
        @Path("id") id: Int
    ): Response<FilmeDetalhes>

    @GET("search/movie?api_key=$API_KEY")
    suspend fun recuperarFilmesPesquisa(
        @Query("query") pesquisa: String
    ): Response<FilmeResposta>

}