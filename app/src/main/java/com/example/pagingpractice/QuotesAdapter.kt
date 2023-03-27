package com.example.pagingpractice

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.pagingpractice.models.Results

class QuotesAdapter : PagingDataAdapter<Results,QuotesAdapter.QuotesViewHolder>(COMPARATOR) {
    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Results>() {
            override fun areItemsTheSame(oldItem: Results, newItem: Results): Boolean {
              return oldItem._id == newItem._id
            }

            override fun areContentsTheSame(oldItem: Results, newItem: Results): Boolean {
               return oldItem == newItem
            }

        }
    }

    class QuotesViewHolder(items: View) : RecyclerView.ViewHolder(items){
        val itemText: TextView = itemView.findViewById(R.id.textQuoate)
    }

    override fun onBindViewHolder(holder: QuotesViewHolder, position: Int) {
       val item = getItem(position)
        if (item != null) {
            holder.itemText.text = item.content
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuotesViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_items, parent, false)
        return QuotesViewHolder(view)
    }
}