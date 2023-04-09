package com.example.tmm.views.fragments

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tmm.domain.model.*
import com.example.tmm.ui.viewmodels.MarvelListViewModel
import com.example.tmm.utils.Constants
import com.example.tmm.views.adapters.MarvelListAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class ApiData(private val viewModel: MarvelListViewModel, private val context : Context, private val recyclerViews : Array<RecyclerView>) {

    private var flagForCharacterList = 3
    private var flagForCreatorList = 3
    private var flagForComicList = 3
    private var flagForEventList = 3
    private var flagForSeriesList = 3


    private lateinit var layoutManagerForCharacterList: LinearLayoutManager
    private lateinit var layoutManagerForCreatorList: LinearLayoutManager
    private lateinit var layoutManagerForComicList: LinearLayoutManager
    private lateinit var layoutManagerForEventsList: LinearLayoutManager
    private lateinit var layoutManagerForSeriesList: LinearLayoutManager

    private lateinit var characterAdapter: MarvelListAdapter<Character>
    private lateinit var creatorAdapter: MarvelListAdapter<Creator>
    private lateinit var comicAdapter: MarvelListAdapter<MarvelComic>
    private lateinit var eventsAdapter: MarvelListAdapter<MarvelEvent>
    private lateinit var seriesAdapter: MarvelListAdapter<MarvelSeries>


    private val rvCharacters = recyclerViews[0]
    private val rvCreator = recyclerViews[1]
    private val rvComics = recyclerViews[2]
    private val rvEvents = recyclerViews[3]
    private val rvSeries = recyclerViews[4]

    fun setupCharacterRecyclerView() {
        layoutManagerForCharacterList = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        characterAdapter = MarvelListAdapter(context, ArrayList(), false)
        rvCharacters.layoutManager = layoutManagerForCharacterList
        rvCharacters.adapter = characterAdapter
        viewModel.getAllCharactersData(Constants.PAGINATED_VALUE_FOR_CHARACTERS)
        callCharactersApi()
        rvCharacters.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if(layoutManagerForCharacterList.findLastVisibleItemPosition() == layoutManagerForCharacterList.itemCount-1){
                    Constants.PAGINATED_VALUE_FOR_CHARACTERS += Constants.LIMIT_VALUE_FOR_CHARACTERS.toInt();
                    viewModel.getAllCharactersData(Constants.PAGINATED_VALUE_FOR_CHARACTERS)
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
                            Toast.makeText(context, it.error, Toast.LENGTH_SHORT).show()
                        }
                        it.list.isNotEmpty()->{
                            //TODO: upon searching it is crashing because of this
//                            _binding!!.pBarCharacters.visibility = View.GONE
                            flagForCharacterList = 0
                            characterAdapter.addData(it.list as ArrayList<Character>)
                        }
                    }
                    delay(1000)
                }

            }
        }
    }

    fun setupCreatorRecyclerView() {
        layoutManagerForCreatorList = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        creatorAdapter = MarvelListAdapter(context = context, itemList =  ArrayList(), isSearch = false)
        rvCreator.layoutManager = layoutManagerForCreatorList
        rvCreator.adapter = creatorAdapter
        viewModel.getAllCreatorsData(Constants.PAGINATED_VALUE_FOR_CREATORS)
        callCreatorApi()
        rvCreator.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if(layoutManagerForCreatorList.findLastVisibleItemPosition() == layoutManagerForCreatorList.itemCount-1){
                    Constants.PAGINATED_VALUE_FOR_CREATORS += Constants.LIMIT_VALUE_FOR_CREATORS.toInt();
                    viewModel.getAllCreatorsData(Constants.PAGINATED_VALUE_FOR_CREATORS)
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
                            Toast.makeText(context, it.error, Toast.LENGTH_SHORT).show()
                        }
                        it.list.isNotEmpty()->{
//                            _binding!!.pBarCreators.visibility = View.GONE
                            flagForCreatorList = 0
                            creatorAdapter.addData(it.list as ArrayList<Creator>)
                        }
                    }
                    delay(1000)
                }

            }
        }
    }


    fun setupComicRecyclerView() {
        layoutManagerForComicList = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        comicAdapter = MarvelListAdapter(context, ArrayList(), false)
        rvComics.layoutManager = layoutManagerForComicList
        rvComics.adapter = comicAdapter
        viewModel.getAllComicsData(Constants.PAGINATED_VALUE_FOR_COMICS)
        callComicsApi()
        rvComics.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if(layoutManagerForComicList.findLastVisibleItemPosition() == layoutManagerForComicList.itemCount-1){
                    Constants.PAGINATED_VALUE_FOR_COMICS += Constants.LIMIT_VALUE_FOR_COMICS.toInt();
                    viewModel.getAllComicsData(Constants.PAGINATED_VALUE_FOR_COMICS)
                    callComicsApi()
                }
            }
        })
    }

    private fun callComicsApi() {
        CoroutineScope(Dispatchers.Main).launch {
            repeat(flagForComicList){
                viewModel.marvelComicValue.collect{
                    when{
                        it.isLoading->{
//                            _binding!!.pBarCharacters.visibility = View.VISIBLE
                        }
                        it.error.isNotBlank()->{
//                            _binding!!.pBarCharacters.visibility = View.GONE
                            flagForComicList = 0
                            Toast.makeText(context, it.error, Toast.LENGTH_SHORT).show()
                        }
                        it.list.isNotEmpty()->{
                            //TODO: upon searching it is crashing because of this
//                            _binding!!.pBarCharacters.visibility = View.GONE
                            flagForComicList = 0
                            comicAdapter.addData(it.list as ArrayList<MarvelComic>)
                        }
                    }
                    delay(1000)
                }

            }
        }
    }


    fun setupEventsRecyclerView() {
        layoutManagerForEventsList = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        eventsAdapter = MarvelListAdapter(context, ArrayList(), false)
        rvEvents.layoutManager = layoutManagerForEventsList
        rvEvents.adapter = eventsAdapter
        viewModel.getAllEventsData(offset = Constants.PAGINATED_VALUE_FOR_EVENTS)
        callEventsApi()
        rvEvents.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if(layoutManagerForEventsList.findLastVisibleItemPosition() == layoutManagerForEventsList.itemCount-1){
                    Constants.PAGINATED_VALUE_FOR_EVENTS += Constants.LIMIT_VALUE_FOR_EVENTS.toInt();
                    viewModel.getAllEventsData(Constants.PAGINATED_VALUE_FOR_EVENTS)
                    callEventsApi()
                }
            }
        })
    }

    private fun callEventsApi() {
        CoroutineScope(Dispatchers.Main).launch {
            repeat(flagForEventList){
                viewModel.marvelEventValue.collect{
                    when{
                        it.isLoading->{
//                            _binding!!.pBarCharacters.visibility = View.VISIBLE
                        }
                        it.error.isNotBlank()->{
//                            _binding!!.pBarCharacters.visibility = View.GONE
                            flagForEventList = 0
                            Toast.makeText(context, it.error, Toast.LENGTH_SHORT).show()
                        }
                        it.list.isNotEmpty()->{
                            //TODO: upon searching it is crashing because of this
//                            _binding!!.pBarCharacters.visibility = View.GONE
                            flagForEventList = 0
                            eventsAdapter.addData(it.list as ArrayList<MarvelEvent>)
                        }
                    }
                    delay(1000)
                }

            }
        }
    }

    fun setupSeriesRecyclerView() {
        layoutManagerForSeriesList = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        seriesAdapter = MarvelListAdapter(context, ArrayList(), false)
        rvSeries.layoutManager = layoutManagerForSeriesList
        rvSeries.adapter = seriesAdapter
        viewModel.getAllSeriesData(offset = Constants.PAGINATED_VALUE_FOR_SERIES)
        callSeriesApi()
        rvSeries.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if(layoutManagerForSeriesList.findLastVisibleItemPosition() == layoutManagerForSeriesList.itemCount-1){
                    Constants.PAGINATED_VALUE_FOR_SERIES += Constants.LIMIT_VALUE_FOR_SERIES.toInt();
                    viewModel.getAllSeriesData(Constants.PAGINATED_VALUE_FOR_SERIES)
                    callSeriesApi()
                }
            }
        })
    }

    private fun callSeriesApi() {
        CoroutineScope(Dispatchers.Main).launch {
            repeat(flagForSeriesList){
                viewModel.marvelSeriesValue.collect{
                    when{
                        it.isLoading->{
//                            _binding!!.pBarCharacters.visibility = View.VISIBLE
                        }
                        it.error.isNotBlank()->{
//                            _binding!!.pBarCharacters.visibility = View.GONE
                            flagForSeriesList = 0
                            Toast.makeText(context, it.error, Toast.LENGTH_SHORT).show()
                        }
                        it.list.isNotEmpty()->{
                            //TODO: upon searching it is crashing because of this
//                            _binding!!.pBarCharacters.visibility = View.GONE
                            flagForSeriesList = 0
                            seriesAdapter.addData(it.list as ArrayList<MarvelSeries>)
                        }
                    }
                    delay(1000)
                }

            }
        }
    }

}