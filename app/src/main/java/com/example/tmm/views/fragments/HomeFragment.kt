package com.example.tmm.views.fragments

//import com.example.tmm.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.tmm.R
import com.example.tmm.databinding.FragmentHomeBinding
import com.example.tmm.domain.model.CarouselItem
import com.example.tmm.ui.viewmodels.MarvelListViewModel
import com.example.tmm.views.adapters.SliderAdapter
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val viewModel : MarvelListViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupSliderView()


    }

    private fun setupSliderView() {
        val carouselItems = arrayOf(CarouselItem(R.string.get_characters,
            R.drawable.c_one),
            CarouselItem(R.string.know_writers, R.drawable.c_four),
            CarouselItem(R.string.heroes, R.drawable.c_two),
            CarouselItem(R.string.universe, R.drawable.c_three),
            CarouselItem(R.string.creators, R.drawable.c_five))
        val sliderView = _binding!!.carousel
        val sliderAdapter = SliderAdapter(carouselItems, this)

        sliderView.setSliderAdapter(sliderAdapter)
        sliderView.setIndicatorAnimation(IndicatorAnimationType.THIN_WORM)
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION)
        sliderView.autoCycleDirection = SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH
        sliderView.startAutoCycle()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}