package com.example.tmm.views.adapters

import android.R
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tmm.databinding.CarouselItemBinding
import com.example.tmm.models.CarouselItem
import com.smarteist.autoimageslider.SliderViewAdapter


class SliderAdapter(private val carouselItem: Array<CarouselItem>, private val fragment : Fragment) : SliderViewAdapter<SliderAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup): Holder {
        val binding = CarouselItemBinding.inflate(LayoutInflater.from(fragment.activity), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(viewHolder: Holder?, position: Int) {
        val item = carouselItem[position]
        viewHolder?.bind(item)
    }

    override fun getCount(): Int {
        return carouselItem.size
    }

    inner class Holder(private val view: CarouselItemBinding) : ViewHolder(view.root) {
        fun bind(item: CarouselItem) {
            view.citem = item
            view.carouselImage.setImageResource(item.image)
        }
    }
}