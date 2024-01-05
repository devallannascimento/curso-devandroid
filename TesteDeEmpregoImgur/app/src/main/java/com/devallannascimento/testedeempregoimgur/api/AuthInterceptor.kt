package com.devallannascimento.testedeempregoimgur.api

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val construtorRequisicao = chain.request().newBuilder()

        val request = construtorRequisicao.addHeader(
            "Authorization", "Client-ID 1ceddedc03a5d71"
        ).build()

        return chain.proceed(request)

    }
}