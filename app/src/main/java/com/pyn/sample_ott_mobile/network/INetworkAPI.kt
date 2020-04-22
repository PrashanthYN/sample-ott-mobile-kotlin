package com.pyn.sample_ott_mobile.network


import com.pyn.sample_ott_mobile.model.Configuration
import com.pyn.sample_ott_mobile.model.ResponseContent
import com.pyn.sample_ott_mobile.util.StringConstants
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface INetworkAPI {

    @GET("configuration")
    fun getConfigurations(@Query("api_key") apiKey: String): Observable<Configuration>

    @GET("movie/popular")
    fun getPopularMovies(
        @Query("page") page: Int,
        @Query("language") language: String,
        @Query("api_key") apiKey: String
    ): Observable<ResponseContent>

    @GET("movie/upcoming")
    fun getUpComingMovies(
        @Query("page") page: Int,
        @Query("language") language: String,
        @Query("api_key") apiKey: String
    ): Observable<ResponseContent>

    @GET("movie/latest")
    fun getLatestMovies(
        @Query("page") page: Int,
        @Query("language") language: String,
        @Query("api_key") apiKey: String
    ): Observable<ResponseContent>

    @GET("movie/now_playing")
    fun getNowPlayingMovies(
        @Query("page") page: Int,
        @Query("language") language: String,
        @Query("api_key") apiKey: String
    ): Observable<ResponseContent>

    @GET("movie/top_rated")
    fun getTopRatedMovies(
        @Query("page") page: Int,
        @Query("language") language: String,
        @Query("api_key") apiKey: String
    ): Observable<ResponseContent>


    @GET("tv/popular")
    fun getPopularTvShows(
        @Query("page") page: Int,
        @Query("language") language: String,
        @Query("api_key") apiKey: String
    ): Observable<ResponseContent>

    @GET("tv/airing_today")
    fun getAiringTodayTvShows(
        @Query("page") page: Int,
        @Query("language") language: String,
        @Query("api_key") apiKey: String
    ): Observable<ResponseContent>

    @GET("tv/on_the_air")
    fun getOnAirTvShows(
        @Query("page") page: Int,
        @Query("language") language: String,
        @Query("api_key") apiKey: String
    ): Observable<ResponseContent>

    @GET("tv/top_rated")
    fun getTopRatedTvShows(
        @Query("page") page: Int,
        @Query("language") language: String,
        @Query("api_key") apiKey: String
    ): Observable<ResponseContent>

    @GET("tv/latest")
    fun getLatestTvShows(
        @Query("page") page: Int,
        @Query("language") language: String,
        @Query("api_key") apiKey: String
    ): Observable<ResponseContent>

    companion object Factory{
        fun create():INetworkAPI{
            var retrofit: Retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(StringConstants.BASE_URL)
                .build()
            return retrofit.create(INetworkAPI::class.java)
        }
    }
}