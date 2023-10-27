package com.allannascimento.aulathreadscoroutines.api

import com.allannascimento.aulathreadscoroutines.model.Endereco
import retrofit2.Response
import retrofit2.http.GET

interface EnderecoAPI {

    //https://viacep.com.br/

    @GET("01001000/json/")
    suspend fun recuperarEndereco() : Response<Endereco>

}