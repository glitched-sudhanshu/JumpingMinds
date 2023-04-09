package com.example.tmm.views.fragments

//import com.example.tmm.R
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tmm.R
import com.example.tmm.databinding.FragmentHomeBinding
import com.example.tmm.domain.model.ListViewItem
import com.example.tmm.domain.model.Character
import com.example.tmm.domain.model.Creator
import com.example.tmm.ui.viewmodels.MarvelListViewModel
import com.example.tmm.utils.Constants.LIMIT_VALUE_FOR_CHARACTERS
import com.example.tmm.utils.Constants.LIMIT_VALUE_FOR_CREATORS
import com.example.tmm.utils.Constants.PAGINATED_VALUE_FOR_CHARACTERS
import com.example.tmm.utils.Constants.PAGINATED_VALUE_FOR_CREATORS
import com.example.tmm.views.adapters.MarvelListAdapter
import com.example.tmm.views.adapters.SliderAdapter
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.log

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null



    private val viewModel : MarvelListViewModel by activityViewModels()
    private lateinit var apiData: ApiData

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
        val listRecyclerView = arrayOf(_binding!!.rvCharacters, _binding!!.rvCreators, _binding!!.rvComics, _binding!!.rvEvents, _binding!!.rvSeries)
        apiData = ApiData(viewModel, requireContext(), listRecyclerView)

        apiData.setupCharacterRecyclerView()
        apiData.setupCreatorRecyclerView()
        apiData.setupComicRecyclerView()
        apiData.setupEventsRecyclerView()
        apiData.setupSeriesRecyclerView()
    }

    private fun setupSliderView() {
        val listViewItems = arrayOf(ListViewItem(R.string.get_characters,
            R.drawable.c_one),
            ListViewItem(R.string.know_writers, R.drawable.c_four),
            ListViewItem(R.string.heroes, R.drawable.c_two),
            ListViewItem(R.string.universe, R.drawable.c_three),
            ListViewItem(R.string.creators, R.drawable.c_five))
        val sliderView = _binding!!.carousel
        val sliderAdapter = SliderAdapter(listViewItems, this)

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