package com.pyn.sample_ott_mobile.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pyn.sample_ott_mobile.R
import com.pyn.sample_ott_mobile.model.Content
import com.pyn.sample_ott_mobile.util.StringConstants
import kotlinx.android.synthetic.main.image_cardview.view.*

class ContentRowAdapter(var contentList: List<Content>) :
    RecyclerView.Adapter<ContentRowAdapter.ContentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.image_cardview, null)
        return ContentViewHolder(view)
    }

    override fun getItemCount(): Int {
        return contentList.size
    }

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        Glide.with(holder.itemView.context)
            .load(StringConstants.imageBaseURL + contentList[position].posterPath)
            .into(holder.itemView.cardPoster)
        if (contentList[position].title != null) {
            holder.itemView.titleView.text = contentList[position].title
        } else if (contentList[position].name != null) {
            holder.itemView.titleView.text = contentList[position].name
        } else {
            holder.itemView.titleView.text = "TvShow"
        }
        if (contentList[position].originalLanguage.equals("en")) {
            holder.itemView.languageView.text = "English"
        } else {
            holder.itemView.languageView.text = contentList[position].originalLanguage
        }
    }

    class ContentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}