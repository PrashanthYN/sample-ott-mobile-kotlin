package com.pyn.sample_ott_mobile.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pyn.sample_ott_mobile.model.Configuration
import com.pyn.sample_ott_mobile.model.ContentRow
import com.pyn.sample_ott_mobile.model.ResponseContent
import com.pyn.sample_ott_mobile.repository.RemoteService
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.internal.schedulers.IoScheduler

class ContentGridViewModel : ViewModel() {

    private val content = MutableLiveData<ContentRow>()
    private val configuration = MutableLiveData<Configuration>()
    private val error = MutableLiveData<Boolean>()

    companion object {
        val TAG=ContentGridViewModel.javaClass.name
        private var remoteService: RemoteService? = null
    }

    fun loadConfigurations() {
        if (remoteService == null) {
            remoteService = RemoteService()
        }
        val result = remoteService?.getConfiguration()
        result?.observeOn(AndroidSchedulers.mainThread())?.subscribeOn(IoScheduler())?.subscribe({
            configuration.value=it
        },{
            it.printStackTrace()
            error.value=true
        })
    }

    fun loadContent(section:String,rowType: String) {
        Log.d(TAG,"load content")
        if (remoteService == null) {
            remoteService = RemoteService()
        }
        var result: Observable<ResponseContent>? = null
        if (section.equals("movies")){
            when (rowType) {
                "Now playing" -> result = remoteService?.getNowPlayingMovies()
                "Upcoming" -> result = remoteService?.getUpcomingMovies()
                "Latest" -> result = remoteService?.getLatestMovies()
                "Popular" -> result = remoteService?.getPopularMovies()
                "Top rated" -> result = remoteService?.getTopRatedMovies()
            }
        }else if(section.equals("tvshows")){
            when (rowType) {
                "On the air" -> result = remoteService?.getOnAirTvShows()
                "Airing today" -> result = remoteService?.getAiringTodayTvShows()
                "Latest" -> result = remoteService?.getLatestTvShows()
                "Popular" -> result = remoteService?.getPopularTvShows()
                "Top rated" -> result = remoteService?.getTopRatedMovies()
            }
        }

        result?.observeOn(AndroidSchedulers.mainThread())?.subscribeOn(IoScheduler())?.subscribe({
            if (it.results != null) {
                content.value = ContentRow(rowType, it.results)
            }
        },{
            Log.d(TAG,"Error fetching in response")
          it.printStackTrace()
            error.value=true
        })
    }

    fun getContentLiveData(): LiveData<ContentRow> {
        return content
    }

    fun getConfigurationLiveData(): LiveData<Configuration> {
        return configuration
    }

    fun getError():LiveData<Boolean>{
        return error
    }
}


