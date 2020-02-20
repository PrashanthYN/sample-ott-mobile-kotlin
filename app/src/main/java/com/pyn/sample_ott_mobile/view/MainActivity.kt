package com.pyn.sample_ott_mobile.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.pyn.sample_ott_mobile.R
import com.pyn.sample_ott_mobile.network.INetworkAPI
import com.pyn.sample_ott_mobile.network.RetrofitProvider
import com.pyn.sample_ott_mobile.util.StringConstants
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.internal.schedulers.IoScheduler
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       val popularMoviesAPI= RetrofitProvider.retrofit?.create(INetworkAPI::class.java)
        val result=popularMoviesAPI.getPopularMovies(1,StringConstants.languageUS,StringConstants.API_KEY)
        result.observeOn(AndroidSchedulers.mainThread()).subscribeOn(IoScheduler()).subscribe{
            Log.d(this.javaClass.simpleName,"Received response")
            pageNumTv.text=it.results.size.toString()
        }
    }
}
