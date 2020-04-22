package com.pyn.sample_ott_mobile.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pyn.sample_ott_mobile.R
import com.pyn.sample_ott_mobile.model.ContentRow
import com.pyn.sample_ott_mobile.ui.ItemOffsetDecoration
import kotlinx.android.synthetic.main.content_row.view.*

class MainListAdapter(var mainContentList:ArrayList<ContentRow>): RecyclerView.Adapter<MainListAdapter.CustomViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.content_row,null)
        return CustomViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mainContentList.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val adapter=ContentRowAdapter(mainContentList[position].list)
        holder.itemView.contentRowRecyclerViewId.layoutManager= LinearLayoutManager(holder.itemView.context, LinearLayoutManager.HORIZONTAL,false)
        holder.itemView.headerTitleView.text=mainContentList[position].title
        holder.itemView.contentRowRecyclerViewId.adapter=adapter
        holder.itemView.contentRowRecyclerViewId.addItemDecoration(ItemOffsetDecoration(8))
    }

    class CustomViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){}
}