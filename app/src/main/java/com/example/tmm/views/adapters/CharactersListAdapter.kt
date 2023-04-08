package com.example.tmm.views.adapters

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tmm.databinding.CarouselItemBinding
import com.example.tmm.databinding.MarvelItemBinding
import com.example.tmm.domain.model.Character

class CharactersListAdapter(private val context : Context, var itemList : ArrayList<Character>) : RecyclerView.Adapter<CharactersListAdapter.CharactersListViewHolder>(){

    inner class CharactersListViewHolder(binding : MarvelItemBinding) : RecyclerView.ViewHolder(binding.root)
    {
        val characterName = binding.txtCharName
        val characterImage = binding.characterImg
        val cardCharacter = binding.clItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersListViewHolder {
        val binding = MarvelItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return CharactersListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharactersListViewHolder, position: Int) {
        val item = itemList[position]
        holder.characterName.text = item.name
        val imageUrl = "${item.thumbnail.replace("http", "https")}/portrait_xlarge.${item.thumbnailExt}"
        Glide.with(context)
            .load(imageUrl)
            .into(holder.characterImage)
        holder.cardCharacter.setOnClickListener {
            Toast.makeText(context, "Clicked ${item.name}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun setData(characterList : ArrayList<Character>){
        this.itemList.addAll(characterList)
        notifyDataSetChanged()
    }
}