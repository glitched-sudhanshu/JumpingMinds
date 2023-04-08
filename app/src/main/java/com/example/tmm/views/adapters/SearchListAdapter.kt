package com.example.tmm.views.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tmm.databinding.SearchMenuItemBinding
import com.example.tmm.domain.model.ListViewItem

class SearchListAdapter(private val context : Context, private val listItems : Array<ListViewItem>, onSearchByItemClickListener: OnSearchByItemClickListener) :RecyclerView.Adapter<SearchListAdapter.ViewHolder>() {

    private var onSearchByItemClickListener: OnSearchByItemClickListener? = null

    init {
        this.onSearchByItemClickListener = onSearchByItemClickListener
    }

    inner class ViewHolder(binding : SearchMenuItemBinding) : RecyclerView.ViewHolder(binding.root){
        val image = binding.imgSearchBy
        val searchBy = binding.clSearchBy
        val text = binding.txtSearchBy
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = SearchMenuItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listItems[position]
        holder.text.setText(item.text)
        Glide.with(context)
            .load(item.image)
            .into(holder.image)
        holder.searchBy.setOnClickListener {
            onSearchByItemClickListener?.onSearchByItemClick(position)
        }
    }

    override fun getItemCount(): Int {
        return listItems.size
    }

    interface OnSearchByItemClickListener {
        fun onSearchByItemClick(position: Int)
    }
}