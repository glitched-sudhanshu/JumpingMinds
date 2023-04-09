package com.example.tmm.views.adapters

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tmm.R
import com.example.tmm.databinding.MarvelItemBinding
import androidx.navigation.findNavController
import com.example.tmm.domain.model.*
import com.example.tmm.views.fragments.HomeFragment
import com.example.tmm.views.fragments.HomeFragmentDirections
import com.example.tmm.views.fragments.SearchFragment
import com.example.tmm.views.fragments.SearchFragmentDirections
import java.lang.Math.min

class MarvelListAdapter<T>(
    private val context: Context,
    var itemList: ArrayList<T>,
    val isSearch: Boolean,
    val fragment : Fragment
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
        val navController = fragment.activity?.findNavController(R.id.nav_host_fragment_activity_home)
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
                    if(fragment is HomeFragment) {
                        val action =
                            HomeFragmentDirections.actionNavigationHomeToDetailedItemFragmentCreator(
                                item as Creator)
                        navController?.navigate(action)
                    }
                    if(fragment is SearchFragment) {
                        val action =
                            SearchFragmentDirections.actionNavigationSearchToDetailedItemFragmentCreator(
                                item as Creator,
                            )
                        navController?.navigate(action)
                    }
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
                    if(fragment is HomeFragment) {
                        val action =
                            HomeFragmentDirections.actionNavigationHomeToDetailedItemFragmentCharacter(
                                item as Character,
                            )
                        navController?.navigate(action)
                    }
                    if(fragment is SearchFragment){
                        val action =
                            SearchFragmentDirections.actionNavigationSearchToDetailedItemFragmentCharacter(
                                item as Character,
                            )
                        navController?.navigate(action)
                    }
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
                    if(fragment is HomeFragment){
                        val action =
                            HomeFragmentDirections.actionNavigationHomeToDetailedItemFragmentComic(
                                item as MarvelComic,
                            )
                        navController?.navigate(action)
                    }
                    if(fragment is SearchFragment){
                        val action =
                            SearchFragmentDirections.actionNavigationSearchToDetailedItemFragmentComic(
                                item as MarvelComic,
                            )
                        navController?.navigate(action)
                    }
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
                    if(fragment is HomeFragment){
                        val action =
                            HomeFragmentDirections.actionNavigationHomeToDetailedItemFragmentEvent(
                                item as MarvelEvent,
                            )
                        navController?.navigate(action)
                    }
                    if(fragment is SearchFragment){
                        val action =
                            SearchFragmentDirections.actionNavigationSearchToDetailedItemFragmentEvent(
                                item as MarvelEvent,
                            )
                        navController?.navigate(action)
                    }
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
                    if(fragment is HomeFragment){
                        val action =
                            HomeFragmentDirections.actionNavigationHomeToDetailedItemFragmentSeries(
                                item as MarvelSeries,
                            )
                        navController?.navigate(action)
                    }
                    if(fragment is SearchFragment){
                        val action =
                            SearchFragmentDirections.actionNavigationSearchToDetailedItemFragmentSeries(
                                item as MarvelSeries,
                            )
                        navController?.navigate(action)
                    }
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

interface OnItemClickListener {
    fun onItemClick(itemId: Int)
}