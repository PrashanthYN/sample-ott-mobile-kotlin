package com.pyn.sample_ott_mobile.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import androidx.lifecycle.ViewModel
import com.pyn.sample_ott_mobile.model.ResponseContent

class ContentGridViewModel : ViewModel() {

    private val content: MutableLiveData<ResponseContent> by lazy {
        MutableLiveData<ResponseContent>().also {
            loadContent()
        }
    }

    private fun loadContent() {

    }

    fun getContent():LiveData<ResponseContent>{
      return content
  }

}


