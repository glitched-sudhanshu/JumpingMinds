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
import com.example.tmm.domain.model.ListViewItem
import com.example.tmm.domain.model.Character
import com.example.tmm.domain.model.Creator
import com.example.tmm.ui.viewmodels.MarvelListViewModel
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
    var paginatedValueForCharacterList = 0;
    private lateinit var rvCharacters : RecyclerView
    private lateinit var characterListAdapter : CharactersListAdapter
    private lateinit var layoutManagerForCharacterList: LinearLayoutManager

    var flagForCreatorList = 3
    var paginatedValueForCreatorList = 0;
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
        viewModel.getAllSeriesData(0)
    }

    private fun setupCreatorRecyclerView() {
        rvCreator = _binding!!.rvCreators
        layoutManagerForCreatorList = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        creatorListAdapter = CreatorsListAdapter(requireContext(), ArrayList())
        rvCreator.layoutManager = layoutManagerForCreatorList
        rvCreator.adapter = creatorListAdapter
        viewModel.getAllCreatorsData(paginatedValueForCreatorList)
        callCreatorApi()
        rvCreator.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if(layoutManagerForCreatorList.findLastVisibleItemPosition() == layoutManagerForCreatorList.itemCount-1){
                    paginatedValueForCreatorList += 20;
                    viewModel.getAllCreatorsData(paginatedValueForCreatorList)
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
//                            _binding!!.pBarCreators.visibility = View.VISIBLE
                        }
                        it.error.isNotBlank()->{
//                            _binding!!.pBarCreators.visibility = View.GONE
                            flagForCreatorList = 0
                            Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT).show()
                        }
                        it.list.isNotEmpty()->{
//                            _binding!!.pBarCreators.visibility = View.GONE
                            flagForCreatorList = 0
                            creatorListAdapter.setData(it.list as ArrayList<Creator>)
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
        viewModel.getAllCharactersData(paginatedValueForCharacterList)
        callCharactersApi()
        rvCharacters.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if(layoutManagerForCharacterList.findLastVisibleItemPosition() == layoutManagerForCharacterList.itemCount-1){
                    paginatedValueForCharacterList += 20;
                    viewModel.getAllCharactersData(paginatedValueForCharacterList)
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
//                            _binding!!.pBarCharacters.visibility = View.VISIBLE
                        }
                        it.error.isNotBlank()->{
//                            _binding!!.pBarCharacters.visibility = View.GONE
                            flagForCharacterList = 0
                            Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT).show()
                        }
                        it.list.isNotEmpty()->{
                            //TODO: upon searching it is crashing because of this
//                            _binding!!.pBarCharacters.visibility = View.GONE
                            flagForCharacterList = 0
                            characterListAdapter.setData(it.list as ArrayList<Character>)
                        }
                    }
                    delay(1000)
                }

            }
        }
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