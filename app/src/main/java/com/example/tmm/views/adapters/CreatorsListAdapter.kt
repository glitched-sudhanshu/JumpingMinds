package com.example.tmm.views.adapters

import android.content.ContentValues
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tmm.databinding.MarvelItemBinding
import com.example.tmm.domain.model.Creator

class CreatorsListAdapter(private val context: Context, var itemList: ArrayList<Creator>) :
    RecyclerView.Adapter<CreatorsListAdapter.CreatorsListViewHolder>() {

    inner class CreatorsListViewHolder(binding: MarvelItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val creatorName = binding.txtCharName
        val creatorImage = binding.characterImg
        val cardCreator = binding.clItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CreatorsListViewHolder {
        val binding = MarvelItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return CreatorsListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CreatorsListViewHolder, position: Int) {
        val item = itemList[position]
        val fullName = "${item.firstName} ${item.lastName}"
        holder.creatorName.text = fullName
        val imageUrl =
            "${item.thumbnail.replace("http", "https")}/portrait_xlarge.${item.thumbnailExt}"
        Log.d(ContentValues.TAG, "thumbnail: $imageUrl")
        Glide.with(context)
            .load(imageUrl)
            .into(holder.creatorImage)
        holder.cardCreator.setOnClickListener {
            Toast.makeText(context, "Clicked ${fullName}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun setData(creatorList: ArrayList<Creator>) {
        this.itemList.addAll(creatorList)
        notifyDataSetChanged()
    }
}