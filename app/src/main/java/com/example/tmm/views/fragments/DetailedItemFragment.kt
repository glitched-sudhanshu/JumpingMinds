package com.example.tmm.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.tmm.R
import com.example.tmm.databinding.FragmentDetailedItemBinding
import com.example.tmm.domain.model.*


class DetailedItemFragment : Fragment() {

    private var _binding: FragmentDetailedItemBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        _binding = FragmentDetailedItemBinding.inflate(inflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val itemType = arguments?.get("item")

        when (itemType) {
            is Character -> {
                val imageUrl = "${
                    itemType.thumbnail.replace("http",
                        "https")
                }/portrait_xlarge.${itemType.thumbnailExt}"
                Glide.with(requireContext())
                    .load(imageUrl)
                    .placeholder(requireContext().getDrawable(R.drawable.placeholder))
                    .into(_binding!!.imgItem)
                _binding!!.txtTitle.text = itemType.name
                var descText = itemType.description
                if(descText.isEmpty())descText = "N/A"
                _binding!!.txtDesc.text = "Description:\n${descText}"
                _binding!!.txtNoOfComics.visibility = View.VISIBLE
                _binding!!.txtNoOfComics.text = "No. of Comics : ${itemType.comics.size}"
            }
            is Creator -> {
                val imageUrl = "${
                    itemType.thumbnail.replace("http",
                        "https")
                }/portrait_xlarge.${itemType.thumbnailExt}"
                Glide.with(requireContext())
                    .load(imageUrl)
                    .placeholder(requireContext().getDrawable(R.drawable.placeholder))
                    .into(_binding!!.imgItem)
                _binding!!.txtTitle.text = itemType.firstName
                _binding!!.txtNoOfComics.text = "No. of Comics : ${itemType.comics.size}"
                _binding!!.txtNoOfCharacters.text = "No. of Series : ${itemType.series.size}"
                _binding!!.txtNoOfComics.visibility = View.VISIBLE
                _binding!!.txtNoOfCharacters.visibility = View.VISIBLE
                _binding!!.txtDesc.text =
                    "Description:\nFull Name : ${itemType.firstName} ${itemType.middleName} ${itemType.lastName}."
            }
            is MarvelComic -> {
                val imageUrl = "${
                    itemType.thumbnail.replace("http",
                        "https")
                }/portrait_xlarge.${itemType.thumbnailExt}"
                Glide.with(requireContext())
                    .load(imageUrl)
                    .placeholder(requireContext().getDrawable(R.drawable.placeholder))
                    .into(_binding!!.imgItem)
                _binding!!.txtTitle.text = itemType.title
                var descText = itemType.description
                if(descText.isEmpty())descText = "N/A"
                _binding!!.txtDesc.text = "Description:\n${descText}"
                _binding!!.txtPrice.text = "Price: $ ${itemType.price.toString()}"
                _binding!!.txtPrice.visibility = View.VISIBLE
            }

            is MarvelEvent -> {
                val imageUrl = "${
                    itemType.thumbnail.replace("http",
                        "https")
                }/portrait_xlarge.${itemType.thumbnailExt}"
                Glide.with(requireContext())
                    .load(imageUrl)
                    .placeholder(requireContext().getDrawable(R.drawable.placeholder))
                    .into(_binding!!.imgItem)
                _binding!!.txtTitle.text = itemType.title
                var descText = itemType.description
                if(descText.isEmpty())descText = "N/A"
                _binding!!.txtDesc.text = "Description:\n${descText}"
            }
            is MarvelSeries -> {
                val imageUrl = "${
                    itemType.thumbnail.replace("http",
                        "https")
                }/portrait_xlarge.${itemType.thumbnailExt}"
                Glide.with(requireContext())
                    .load(imageUrl)
                    .placeholder(requireContext().getDrawable(R.drawable.placeholder))
                    .into(_binding!!.imgItem)
                _binding!!.txtTitle.text = itemType.title
                var descText = itemType.description
                if(descText.isEmpty())descText = "N/A"
                _binding!!.txtDesc.text = "Description:\n${descText}"
                _binding!!.txtNoOfComics.text = "No. of Comics : ${itemType.noOfComics.toString()}"
                _binding!!.txtNoOfCharacters.text = "No. of Characters : ${itemType.noOfCharacters.toString()}"
                _binding!!.txtNoOfComics.visibility = View.VISIBLE
                _binding!!.txtNoOfCharacters.visibility = View.VISIBLE

            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}