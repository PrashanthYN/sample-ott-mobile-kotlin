package com.pyn.sample_ott_mobile.network


import com.pyn.sample_ott_mobile.model.Configuration
import com.pyn.sample_ott_mobile.model.ResponseContent
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface INetworkAPI {

    @GET("movie/upcoming")
    fun getPopularMovies(
        @Query("page") page: Int,
        @Query("language") language: String,
        @Query("api_key") apiKey: String
    ): Observable<ResponseContent>

    @GET("configuration")
    fun getConfigurations(@Query("api_key") apiKey: String): Observable<Configuration>
}