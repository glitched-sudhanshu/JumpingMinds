package com.example.tmm.views.fragments

//import com.example.tmm.R
import android.os.Bundle
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
import com.example.tmm.domain.model.CarouselItem
import com.example.tmm.domain.model.Character
import com.example.tmm.domain.model.Creator
import com.example.tmm.ui.viewmodels.MarvelListViewModel
import com.example.tmm.utils.Constants.LIMIT_VALUE_FOR_CHARACTERS
import com.example.tmm.utils.Constants.LIMIT_VALUE_FOR_CREATORS
import com.example.tmm.utils.Constants.PAGINATED_VALUE_FOR_CHARACTERS
import com.example.tmm.utils.Constants.PAGINATED_VALUE_FOR_COMICS
import com.example.tmm.utils.Constants.PAGINATED_VALUE_FOR_CREATORS
import com.example.tmm.utils.Constants.PAGINATED_VALUE_FOR_EVENTS
import com.example.tmm.utils.Constants.PAGINATED_VALUE_FOR_SERIES
import com.example.tmm.views.adapters.CharactersListAdapter
import com.example.tmm.views.adapters.CreatorsListAdapter
import com.example.tmm.views.adapters.SliderAdapter
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    var flagForCharacterList = 3
    private lateinit var rvCharacters : RecyclerView
    private lateinit var characterListAdapter : CharactersListAdapter
    private lateinit var layoutManagerForCharacterList: LinearLayoutManager

    var flagForCreatorList = 3
    private lateinit var rvCreator : RecyclerView
    private lateinit var creatorListAdapter : CreatorsListAdapter
    private lateinit var layoutManagerForCreatorList: LinearLayoutManager

    private val viewModel : MarvelListViewModel by activityViewModels()


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

        setupCharacterRecyclerView()
        setupCreatorRecyclerView()
    }

    private fun setupCreatorRecyclerView() {
        rvCreator = _binding!!.rvCreators
        layoutManagerForCreatorList = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        creatorListAdapter = CreatorsListAdapter(requireContext(), ArrayList())
        rvCreator.layoutManager = layoutManagerForCreatorList
        rvCreator.adapter = creatorListAdapter
        viewModel.getAllCreatorsData(PAGINATED_VALUE_FOR_CREATORS)
        callCreatorApi()
        rvCreator.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if(layoutManagerForCreatorList.findLastVisibleItemPosition() == layoutManagerForCreatorList.itemCount-1){
                    PAGINATED_VALUE_FOR_CREATORS += LIMIT_VALUE_FOR_CREATORS
                    viewModel.getAllCreatorsData(PAGINATED_VALUE_FOR_CREATORS)
                    callCreatorApi()
                }
            }
        })
    }

    private fun callCreatorApi() {
        CoroutineScope(Dispatchers.Main).launch {
            repeat(flagForCreatorList){
                viewModel.marvelCreatorsValue.collect{
                    when{
                        it.isLoading->{
                            _binding!!.pBarCreators.visibility = View.VISIBLE
                        }
                        it.error.isNotBlank()->{
                            _binding!!.pBarCreators.visibility = View.GONE
                            flagForCreatorList = 0
                            Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT).show()
                        }
                        it.creatorList.isNotEmpty()->{
                            _binding!!.pBarCreators.visibility = View.GONE
                            flagForCreatorList = 0
                            creatorListAdapter.setData(it.creatorList as ArrayList<Creator>)
                        }
                    }
                    delay(1000)
                }

            }
        }
    }

    private fun setupCharacterRecyclerView() {
        rvCharacters = _binding!!.rvCharacters
        layoutManagerForCharacterList = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        characterListAdapter = CharactersListAdapter(requireContext(), ArrayList())
        rvCharacters.layoutManager = layoutManagerForCharacterList
        rvCharacters.adapter = characterListAdapter
        callCharactersApi()
        rvCharacters.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if(layoutManagerForCharacterList.findLastVisibleItemPosition() == layoutManagerForCharacterList.itemCount-1){
                    PAGINATED_VALUE_FOR_CHARACTERS += LIMIT_VALUE_FOR_CHARACTERS;
                    viewModel.getAllCharactersData(PAGINATED_VALUE_FOR_CHARACTERS)
                    callCharactersApi()
                }
            }
        })
    }

    private fun callCharactersApi() {
        CoroutineScope(Dispatchers.Main).launch {
            repeat(flagForCharacterList){
                viewModel.marvelCharactersValue.collect{
                    when{
                        it.isLoading->{
                            _binding!!.pBarCharacters.visibility = View.VISIBLE
                        }
                        it.error.isNotBlank()->{
                            _binding!!.pBarCharacters.visibility = View.GONE
                            flagForCharacterList = 0
                            Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT).show()
                        }
                        it.characterList.isNotEmpty()->{
                            _binding!!.pBarCharacters.visibility = View.GONE
                            flagForCharacterList = 0
                            characterListAdapter.setData(it.characterList as ArrayList<Character>)
                        }
                    }
                    delay(1000)
                }

            }
        }
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

    override fun onStart() {
        super.onStart()
//        viewModel.getAllEventsData(PAGINATED_VALUE_FOR_EVENTS)
//        viewModel.getAllSeriesData(PAGINATED_VALUE_FOR_SERIES)
        viewModel.getAllCharactersData(PAGINATED_VALUE_FOR_CHARACTERS)
//        viewModel.getAllCreatorsData(PAGINATED_VALUE_FOR_CREATORS)
//        viewModel.getAllComicsData(PAGINATED_VALUE_FOR_COMICS)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}