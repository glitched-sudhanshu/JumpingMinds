package com.example.tmm.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tmm.databinding.CarouselItemBinding
import com.example.tmm.domain.model.ListViewItem
import com.smarteist.autoimageslider.SliderViewAdapter


class SliderAdapter(private val listViewItem: Array<ListViewItem>, private val fragment: Fragment) :
    SliderViewAdapter<SliderAdapter.Holder>() {

    inner class Holder(private val view: CarouselItemBinding) : ViewHolder(view.root) {
        fun bind(item: ListViewItem) {
            view.carouselImage.setImageResource(item.image)
            view.textView.setText(item.text)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup): Holder {
        val binding =
            CarouselItemBinding.inflate(LayoutInflater.from(fragment.activity), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(viewHolder: Holder?, position: Int) {
        val item = listViewItem[position]
        viewHolder?.bind(item)
    }

    override fun getCount(): Int {
        return listViewItem.size
    }


}