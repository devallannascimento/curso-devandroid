package com.devallannascimento.testedeempregoimgur.api

import com.devallannascimento.testedeempregoimgur.api.model.Resultado
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ImgurAPI {

    @GET("gallery/search/")//?q=cats
    suspend fun pesquisarImagensGaleria(
        @Query("q") q : String
    ) : Response<Resultado>
}