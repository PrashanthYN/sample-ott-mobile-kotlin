package com.pyn.sample_ott_mobile.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import androidx.lifecycle.ViewModel
import com.pyn.sample_ott_mobile.model.Configuration
import com.pyn.sample_ott_mobile.model.ResponseContent
import com.pyn.sample_ott_mobile.repository.RemoteService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.internal.schedulers.IoScheduler
import kotlinx.android.synthetic.main.activity_main.*

class ContentGridViewModel : ViewModel() {

    private val content= MutableLiveData<ResponseContent>()
    private val configuration= MutableLiveData<Configuration>()
    companion object{
        private var remoteService:RemoteService?=null
    }

    fun loadConfigurations(){
        if (remoteService==null){
            remoteService= RemoteService()
        }
        val result=remoteService?.getConfiguration()
        result?.observeOn(AndroidSchedulers.mainThread())?.subscribeOn(IoScheduler())?.subscribe {
            configuration.value=it
        }
    }

    fun loadContent() {
        if (remoteService==null){
            remoteService=RemoteService()
        }
        val result=remoteService?.getPopularMovies()
        result?.observeOn(AndroidSchedulers.mainThread())?.subscribeOn(IoScheduler())?.subscribe {
            content.value = it
        }
    }

    fun getContentLiveData():LiveData<ResponseContent>{
      return content
  }

    fun getConfigurationLiveData():LiveData<Configuration>{
        return configuration
    }
}


