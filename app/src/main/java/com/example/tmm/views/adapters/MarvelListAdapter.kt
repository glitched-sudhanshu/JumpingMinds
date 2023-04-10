package com.example.tmm.views.adapters

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tmm.R
import com.example.tmm.databinding.MarvelItemBinding
import androidx.navigation.findNavController
import com.example.tmm.domain.model.*
import com.example.tmm.views.fragments.*
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
                loadImage(holder.itemImage, item.thumbnail, item.thumbnailExt)
            }
            is Character -> {
                holder.itemTitle.text = item.name
                loadImage(holder.itemImage, item.thumbnail, item.thumbnailExt)
            }
            is MarvelComic -> {
                holder.itemTitle.text = item.title
                loadImage(holder.itemImage, item.thumbnail, item.thumbnailExt)
            }
            is MarvelEvent -> {
                holder.itemTitle.text = item.title
                loadImage(holder.itemImage, item.thumbnail, item.thumbnailExt)
            }
            is MarvelSeries -> {
                holder.itemTitle.text = item.title
                loadImage(holder.itemImage, item.thumbnail, item.thumbnailExt)
            }
        }

        holder.itemCard.setOnClickListener {
            when (item) {
                is Creator -> navigateToDestination(navController, item as Creator)
                is Character -> navigateToDestination(navController, item as Character)
                is MarvelComic -> navigateToDestination(navController, item as MarvelComic)
                is MarvelSeries -> navigateToDestination(navController, item as MarvelSeries)
                is MarvelEvent -> navigateToDestination(navController, item as MarvelEvent)
            }
        }


    }

    private fun navigateToDestination(navController: NavController?, item: Any) {
        when (fragment) {
            is HomeFragment -> {
                val action = when (item) {
                    is Creator -> HomeFragmentDirections.actionNavigationHomeToDetailedItemFragmentCreator(item)
                    is Character -> HomeFragmentDirections.actionNavigationHomeToDetailedItemFragmentCharacter(item)
                    is MarvelComic -> HomeFragmentDirections.actionNavigationHomeToDetailedItemFragmentComic(item)
                    is MarvelSeries -> HomeFragmentDirections.actionNavigationHomeToDetailedItemFragmentSeries(item)
                    else -> HomeFragmentDirections.actionNavigationHomeToDetailedItemFragmentSeries(item as MarvelSeries)
                }
                navController?.navigate(action)
            }
            is SearchFragment -> {
                val action = when (item) {
                    is Creator -> SearchFragmentDirections.actionNavigationSearchToDetailedItemFragmentCreator(item)
                    is Character -> SearchFragmentDirections.actionNavigationSearchToDetailedItemFragmentCharacter(item)
                    is MarvelComic -> SearchFragmentDirections.actionNavigationSearchToDetailedItemFragmentComic(item)
                    is MarvelEvent -> SearchFragmentDirections.actionNavigationSearchToDetailedItemFragmentEvent(item)
                    else -> SearchFragmentDirections.actionNavigationSearchToDetailedItemFragmentSeries(item as MarvelSeries)
                }
                navController?.navigate(action)
            }
            is AllItemsFragment -> {
                val action = when (item) {
                    is Creator -> AllItemsFragmentDirections.actionAllItemsFragmentToDetailedItemFragmentCreator(item)
                    is Character -> AllItemsFragmentDirections.actionAllItemsFragmentToDetailedItemFragmentCharacter(item)
                    is MarvelComic -> AllItemsFragmentDirections.actionAllItemsFragmentToDetailedItemFragmentComic(item)
                    is MarvelEvent -> AllItemsFragmentDirections.actionAllItemsFragmentToDetailedItemFragmentEvent(item)
                    else -> AllItemsFragmentDirections.actionAllItemsFragmentToDetailedItemFragmentSeries(item as MarvelSeries)
                }
                navController?.navigate(action)
            }
        }
    }

    private fun loadImage(imageView: ImageView, thumbnail: String, thumbnailExt: String) {
        val imageUrl = "${
            thumbnail.replace("http", "https")
        }/portrait_xlarge.$thumbnailExt"
        Glide.with(context)
            .load(imageUrl)
            .placeholder(context.getDrawable(R.drawable.placeholder))
            .into(imageView)
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
