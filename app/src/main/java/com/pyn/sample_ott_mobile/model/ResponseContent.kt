package com.pyn.sample_ott_mobile.model


import com.google.gson.annotations.SerializedName

data class ResponseContent(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<Content>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)