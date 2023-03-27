package com.example.pagingpractice.api

import com.example.pagingpractice.models.QuoteList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/quotes")
    suspend fun getQuotes(@Query("page") page: Int): QuoteList
}