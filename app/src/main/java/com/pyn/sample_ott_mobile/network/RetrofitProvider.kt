package com.pyn.sample_ott_mobile.network

import com.pyn.sample_ott_mobile.util.StringConstants
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitProvider {
    companion object{
        var retrofit:Retrofit=Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(StringConstants.BASE_URL)
            .build()
    }
}