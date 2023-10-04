package com.example.lab5

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
interface ApiInterface {
    @GET("random/quote")
    fun getRandomQuote(): Call<TrumpDump>

    @GET("random/meme")
    fun getRandomMeme() : Call<TrumpDump>
}