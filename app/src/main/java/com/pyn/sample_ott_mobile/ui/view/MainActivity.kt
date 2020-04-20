package com.pyn.sample_ott_mobile.ui.view

import android.content.SharedPreferences
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pyn.sample_ott_mobile.R
import com.pyn.sample_ott_mobile.model.ResponseContent
import com.pyn.sample_ott_mobile.ui.ItemOffsetDecoration
import com.pyn.sample_ott_mobile.ui.adapters.ContentListAdapter
import com.pyn.sample_ott_mobile.util.AppSessionManager
import com.pyn.sample_ott_mobile.util.StringConstants
import com.pyn.sample_ott_mobile.viewmodel.ContentGridViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var contentGridViewModel: ContentGridViewModel?=null
    private var adapter:ContentListAdapter?=null

    companion object{
        val TAG:String=MainActivity.javaClass.name
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        contentGridViewModel=ViewModelProvider.AndroidViewModelFactory(application).create(ContentGridViewModel::class.java)

        val configuaration=AppSessionManager(this).fetchConfiguration()
        if (configuaration==null) {
            contentGridViewModel?.loadConfigurations()
        }else{
            StringConstants.imageBaseURL=configuaration.images.base_url+"/"+configuaration.images.poster_sizes[2]
            contentGridViewModel?.loadContent()
        }

        contentGridViewModel?.getConfigurationLiveData()?.observe(this, Observer {
            Log.d(TAG,"Received configurations(Image base url):{${it.images.base_url}}")
            StringConstants.imageBaseURL=it.images.base_url+"/"+it.images.poster_sizes[2]
            AppSessionManager(this).saveConfiguration(it)
            contentGridViewModel?.loadContent()
        })

        contentGridViewModel?.getContentLiveData()?.observe(this, Observer {
            inflateContentRow(it)
        })
    }

    private fun inflateContentRow(it: ResponseContent) {
            adapter = ContentListAdapter(it.results)
            parentRecyclerView.adapter=adapter
            parentRecyclerView.layoutManager=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
            parentRecyclerView.addItemDecoration(ItemOffsetDecoration(8))
    }
}