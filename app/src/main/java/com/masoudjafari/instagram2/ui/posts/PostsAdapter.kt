package com.masoudjafari.instagram2.ui.posts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.masoudjafari.instagram2.data.DataItem
import com.masoudjafari.instagram2.databinding.ItemPostBinding

class PostsAdapter : RecyclerView.Adapter<MainViewHolder>() {

    var data = mutableListOf<DataItem>()

    fun setList(mainModels: List<DataItem>) {
        this.data = mainModels.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPostBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val item = data[position]
        holder.binding.tvLikeCount.text = item.likeCount.toString()
        holder.binding.tvComments.text = " تعداد کامنت : ${item.commentsCount}"

        Glide.with(holder.itemView.context)
            .load(item.mediaUrl)
            .into(holder.binding.ivMedia)
    }

    override fun getItemCount(): Int {
        return data.size
    }
}

class MainViewHolder(val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root)