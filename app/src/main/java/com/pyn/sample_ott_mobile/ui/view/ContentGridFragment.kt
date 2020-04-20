package com.pyn.sample_ott_mobile.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider

import com.pyn.sample_ott_mobile.R
import com.pyn.sample_ott_mobile.viewmodel.ContentGridViewModel

class ContentGridFragment : Fragment() {

    companion object {
        fun newInstance() = ContentGridFragment()
    }

    private lateinit var viewModel: ContentGridViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.content_grid_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(activity!!.application).create(ContentGridViewModel::class.java)
    }

}
