package com.pyn.sample_ott_mobile.ui.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.pyn.sample_ott_mobile.R
import com.pyn.sample_ott_mobile.model.ContentRow
import com.pyn.sample_ott_mobile.ui.adapters.MainListAdapter
import com.pyn.sample_ott_mobile.util.AppSessionManager
import com.pyn.sample_ott_mobile.util.StringConstants
import com.pyn.sample_ott_mobile.viewmodel.ContentGridViewModel
import kotlinx.android.synthetic.main.content_grid_main.*

class ContentFragment : Fragment() {

    private var contentGridViewModel: ContentGridViewModel? = null
    private var adapter: MainListAdapter? = null
    private var mainList: ArrayList<ContentRow>? = null
    var section: String? = null

    companion object {
        val TAG = ContentFragment.javaClass.name
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        section = arguments?.getString("section")
        Log.d(TAG, "content section:{$section}")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = activity?.layoutInflater?.inflate(R.layout.content_grid_main, null)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainList = ArrayList()
        adapter = MainListAdapter(mainList!!)
        parentRecyclerView.adapter = adapter
        parentRecyclerView.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        contentGridViewModel = ViewModelProvider.AndroidViewModelFactory(activity!!.application)
            .create(ContentGridViewModel::class.java)

        contentGridViewModel?.getError()?.observe(activity!!, Observer {
            if (it) {
                errorTextId.visibility = View.VISIBLE
                errorTextId.text =
                    "Not able to show content.\nTry again!!\n\n<Looks like service is down>"
            }
        })

        val configuaration = AppSessionManager(activity!!.applicationContext).fetchConfiguration()
        if (configuaration == null) {
            contentGridViewModel?.loadConfigurations()
        } else {
            Log.d(TAG, "Configurations already available")
            StringConstants.imageBaseURL =
                configuaration.images.base_url + "/" + configuaration.images.poster_sizes[2]
            loadContent()
        }

        contentGridViewModel?.getConfigurationLiveData()?.observe(activity!!, Observer {
            errorTextId.visibility = View.GONE
            Log.d(TAG, "Received configurations(Image base url):{${it.images.base_url}}")
            StringConstants.imageBaseURL = it.images.base_url + "/" + it.images.poster_sizes[2]
            AppSessionManager(activity!!.applicationContext).saveConfiguration(it)
        })

        contentGridViewModel?.getContentLiveData()?.observe(activity!!, Observer {
            errorTextId.visibility = View.GONE
            addContentRow(it)
        })
    }

    private fun loadContent() {
        if (section.equals("movies")) {
            section?.let { contentGridViewModel?.loadContent(it, "Now playing") }
            section?.let { contentGridViewModel?.loadContent(it, "Upcoming") }
            section?.let { contentGridViewModel?.loadContent(it, "Latest") }
            section?.let { contentGridViewModel?.loadContent(it, "Popular") }
            section?.let { contentGridViewModel?.loadContent(it, "Top rated") }
        } else if (section.equals("tvshows")) {
            section?.let { contentGridViewModel?.loadContent(it, "Airing today") }
            section?.let { contentGridViewModel?.loadContent(it, "On the air") }
            section?.let { contentGridViewModel?.loadContent(it, "Popular") }
            section?.let { contentGridViewModel?.loadContent(it, "Latest") }
            section?.let { contentGridViewModel?.loadContent(it, "Top rated") }

        }
    }

    private fun addContentRow(it: ContentRow) {
        mainList?.add(it)
        adapter?.notifyDataSetChanged()
    }
}