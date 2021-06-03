package com.ajayasija.appentus

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ajayasija.appentus.model.ApiResponseItem
import com.bumptech.glide.Glide

class ImageAdapter(var context: Context) :
    PagingDataAdapter<ApiResponseItem, ImageAdapter.MyViewHolder>(RESPONSE_COMPARATOR) {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
       val image:ImageView=itemView.findViewById(R.id.image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.adapter_image,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Glide.with(context).load(getItem(position)?.download_url!!).into(holder.image)
    }

    companion object{
        private val RESPONSE_COMPARATOR = object : DiffUtil.ItemCallback<ApiResponseItem>() {
            override fun areItemsTheSame(oldItem: ApiResponseItem, newItem: ApiResponseItem): Boolean =
                oldItem.id==newItem.id

            override fun areContentsTheSame(oldItem: ApiResponseItem, newItem: ApiResponseItem): Boolean =
                 oldItem.download_url==newItem.download_url
        }
    }
}