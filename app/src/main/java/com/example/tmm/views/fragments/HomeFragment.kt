package com.example.tmm.views.fragments

//import com.example.tmm.R
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getDrawable
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.example.tmm.R
import com.example.tmm.databinding.FragmentHomeBinding
import com.example.tmm.models.CarouselItem
import com.example.tmm.views.adapters.SliderAdapter
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private var sliderView: SliderView? = null

    private val carouselItems = arrayOf<CarouselItem>(CarouselItem("Get your favourite MARVEL characters",
        R.drawable.c_one),
        CarouselItem("Know the writers", R.drawable.c_four),
        CarouselItem("THE HEROS", R.drawable.c_two),
        CarouselItem("THE UNIVERSE", R.drawable.c_three),
        CarouselItem("THE CREATERS", R.drawable.c_five))



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
        sliderView = _binding!!.carousel

        val sliderAdapter = SliderAdapter(carouselItems, this)

        sliderView!!.setSliderAdapter(sliderAdapter)
        sliderView!!.setIndicatorAnimation(IndicatorAnimationType.THIN_WORM)
        sliderView!!.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION)
        sliderView!!.autoCycleDirection = SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH
        sliderView!!.startAutoCycle()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}