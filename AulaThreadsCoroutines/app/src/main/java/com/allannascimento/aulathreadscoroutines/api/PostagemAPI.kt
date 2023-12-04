package com.allannascimento.aulathreadscoroutines.api

import com.allannascimento.aulathreadscoroutines.model.Comentario
import com.allannascimento.aulathreadscoroutines.model.Foto
import com.allannascimento.aulathreadscoroutines.model.Postagem
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface PostagemAPI {

    @GET("posts")
    suspend fun recuperarPostagens(): Response<List<Postagem>>

    @GET("posts/{id}")
    suspend fun recuperarPostagemUnica(
        @Path("id") id: Int
    ): Response<Postagem>

    @GET("posts/{id}/comments")
    suspend fun recuperarComentarioPostagem(
        @Path("id") id: Int
    ): Response<List<Comentario>>

    @GET("comments")
    suspend fun recuperarComentarioPostagemQuery(
        @Query("postId") id: Int
    ): Response<List<Comentario>>

    @POST("posts")
    suspend fun salvarPostagem(
        @Body postagem: Postagem
    ): Response<Postagem>

    @FormUrlEncoded
    @POST("posts")
    suspend fun salvarPostagemFormulario(
        @Field("userId") userId: Int,
        @Field("id") id: Int,
        @Field("title") title: String,
        @Field("body") body: String
    ): Response<Postagem>

    @PUT("posts/{id}")
    suspend fun atualizarPostagemPut(
        @Path("id") id: Int,
        @Body postagem: Postagem
    ): Response<Postagem>

    @PATCH("posts/{id}")
    suspend fun atualizarPostagemPatch(
        @Path("id") id: Int,
        @Body postagem: Postagem
    ): Response<Postagem>

    @DELETE("posts/{id}")
    suspend fun removerPostagem(
        @Path("id") id: Int,
    ): Response<Unit>

    @GET("photos/{id}")
    suspend fun recuperarFoto(
        @Path("id") id: Int
    ): Response<Foto>

}