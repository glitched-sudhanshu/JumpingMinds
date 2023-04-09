package com.example.tmm.views.adapters

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tmm.R
import com.example.tmm.databinding.MarvelItemBinding
import com.example.tmm.domain.model.*
import java.lang.Math.min

class MarvelListAdapter<T>(
    private val context: Context,
    var itemList: ArrayList<T>,
    val isSearch: Boolean,
) :
    RecyclerView.Adapter<MarvelListAdapter<T>.MarvelListViewHolder>() {

    inner class MarvelListViewHolder(binding: MarvelItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val itemTitle = binding.txtCharName
        val itemImage = binding.characterImg
        val itemCard = binding.clItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarvelListViewHolder {

        val binding = MarvelItemBinding.inflate(LayoutInflater.from(context), parent, false)
        if (isSearch) {
            val cardWidth = parent.width / 2 - (2 * 10)

            val layoutParams = binding.clItem.layoutParams as ViewGroup.MarginLayoutParams
            val cardHeight = layoutParams.height
            Log.d(TAG, "onCreateViewHolder: $cardHeight , $cardWidth")
            val cardSideDimension = min(cardWidth, cardHeight)
            layoutParams.height = cardSideDimension + 200
            layoutParams.width = cardSideDimension
            layoutParams.setMargins(10, 10, 10, 10)
        }

        return MarvelListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MarvelListViewHolder, position: Int) {
        val item = itemList[position]
        when (item) {
            is Creator -> {
                val fullName = "${item.firstName} ${item.lastName}"
                holder.itemTitle.text = fullName
                val imageUrl = "${
                    item.thumbnail.replace("http",
                        "https")
                }/portrait_xlarge.${item.thumbnailExt}"
                Glide.with(context)
                    .load(imageUrl)
                    .placeholder(context.getDrawable(R.drawable.placeholder))
                    .into(holder.itemImage)
                holder.itemCard.setOnClickListener {
                    Toast.makeText(context, "Clicked $fullName", Toast.LENGTH_SHORT).show()
                }
            }
            is Character -> {
                holder.itemTitle.text = item.name
                val imageUrl = "${
                    item.thumbnail.replace("http",
                        "https")
                }/portrait_xlarge.${item.thumbnailExt}"
                Glide.with(context)
                    .load(imageUrl)
                    .placeholder(context.getDrawable(R.drawable.placeholder))
                    .into(holder.itemImage)
                holder.itemCard.setOnClickListener {
                    Toast.makeText(context, "Clicked ${item.name}", Toast.LENGTH_SHORT).show()
                }
            }

            is MarvelComic -> {
                holder.itemTitle.text = item.title
                val imageUrl = "${
                    item.thumbnail.replace("http",
                        "https")
                }/portrait_xlarge.${item.thumbnailExt}"
                Glide.with(context)
                    .load(imageUrl)
                    .placeholder(context.getDrawable(R.drawable.placeholder))
                    .into(holder.itemImage)
                holder.itemCard.setOnClickListener {
                    Toast.makeText(context, "Clicked ${item.title}", Toast.LENGTH_SHORT).show()
                }
            }

            is MarvelEvent -> {
                holder.itemTitle.text = item.title
                val imageUrl = "${
                    item.thumbnail.replace("http",
                        "https")
                }/portrait_xlarge.${item.thumbnailExt}"
                Glide.with(context)
                    .load(imageUrl)
                    .placeholder(context.getDrawable(R.drawable.placeholder))
                    .into(holder.itemImage)
                holder.itemCard.setOnClickListener {
                    Toast.makeText(context, "Clicked ${item.title}", Toast.LENGTH_SHORT).show()
                }
            }

            is MarvelSeries -> {
                holder.itemTitle.text = item.title
                val imageUrl = "${
                    item.thumbnail.replace("http",
                        "https")
                }/portrait_xlarge.${item.thumbnailExt}"
                Glide.with(context)
                    .load(imageUrl)
                    .placeholder(context.getDrawable(R.drawable.placeholder))
                    .into(holder.itemImage)
                holder.itemCard.setOnClickListener {
                    Toast.makeText(context, "Clicked ${item.title}", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun setData(itemList: ArrayList<T>) {
        this.itemList = itemList
        notifyDataSetChanged()
    }

    fun addData(itemList: ArrayList<T>) {
        this.itemList.addAll(itemList)
        notifyDataSetChanged()
    }

    fun clearData(){
        this.itemList.clear()
        notifyDataSetChanged()
    }
}