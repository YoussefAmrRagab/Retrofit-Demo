package com.example.retrofit_kotlin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.retrofit_kotlin.databinding.RecyclerviewItemBinding
import com.example.retrofit_kotlin.model.Post

class MyAdapter : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    private var list = emptyList<Post>()

    inner class MyViewHolder(val binding: RecyclerviewItemBinding) :
        ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            RecyclerviewItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.post = list[position]
    }

    fun setData(newList: List<Post>) {
        list = newList
        notifyDataSetChanged()
    }
}