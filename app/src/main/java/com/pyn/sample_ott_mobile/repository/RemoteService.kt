package com.pyn.sample_ott_mobile.repository

import com.pyn.sample_ott_mobile.model.Configuration
import com.pyn.sample_ott_mobile.model.ResponseContent
import com.pyn.sample_ott_mobile.network.INetworkAPI
import com.pyn.sample_ott_mobile.network.RetrofitProvider
import com.pyn.sample_ott_mobile.util.StringConstants
import io.reactivex.Observable

class RemoteService {

    companion object{
       private val networkAPI:INetworkAPI= RetrofitProvider.retrofit?.create(INetworkAPI::class.java)
    }

    fun getPopularMovies(): Observable<ResponseContent> {
        val result=networkAPI.getPopularMovies(1, StringConstants.languageUS, StringConstants.API_KEY)
        return result
    }

    fun getConfiguration():Observable<Configuration>{
        val response=networkAPI.getConfigurations(StringConstants.API_KEY)
        return  response
    }

}