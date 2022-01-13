package com.example.a12_bookreview.api

import com.example.a12_bookreview.model.BestSellerDto
import com.example.a12_bookreview.model.SearchBookDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface BookService {

    @GET("/api/search.api?output=json")
    fun getBooksByName(
        @Query("key") apiKey: String,
        @Query("Query") keyword: String
    ): Call<SearchBookDto>

    @GET("/api/bestSeller.api?output=json&categoryId=100")
    fun getBestSellerBooks(
        @Query("key") apiKey: String
    ) : Call<BestSellerDto>
}