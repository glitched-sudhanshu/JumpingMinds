package com.example.tmm.views.fragments

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
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


class ApiDetailedData(
    private val viewModel: MarvelListViewModel,
    private val context: Context,
    private val recyclerViews: Array<RecyclerView>,
    private val isSearch: Array<Boolean>,
    private val fragment: Fragment
) {
    private var flagForCharacterList = 3
    private var flagForCreatorList = 3
    private var flagForComicList = 3
    private var flagForEventList = 3
    private var flagForSeriesList = 3

    private lateinit var layoutManagerForCharacterList: GridLayoutManager
    private lateinit var layoutManagerForCreatorList: GridLayoutManager
    private lateinit var layoutManagerForComicList: GridLayoutManager
    private lateinit var layoutManagerForEventsList: GridLayoutManager
    private lateinit var layoutManagerForSeriesList: GridLayoutManager

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
        layoutManagerForCharacterList = GridLayoutManager(context, 2)
        characterAdapter = MarvelListAdapter(context, ArrayList(), isSearch = isSearch[0], fragment)
        rvCharacters.layoutManager = layoutManagerForCharacterList
        rvCharacters.adapter = characterAdapter
        viewModel.getAllCharactersData(Constants.PAGINATED_VALUE_FOR_CHARACTERS)
        callCharactersApi()
        rvCharacters.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (layoutManagerForCharacterList.findLastVisibleItemPosition() == layoutManagerForCharacterList.itemCount - 1) {
                    Constants.PAGINATED_VALUE_FOR_CHARACTERS += Constants.LIMIT_VALUE_FOR_CHARACTERS.toInt();
                    viewModel.getAllCharactersData(Constants.PAGINATED_VALUE_FOR_CHARACTERS)
                    callCharactersApi()
                }
            }
        })
    }

    private fun callCharactersApi() {
        CoroutineScope(Dispatchers.Main).launch {
            repeat(flagForCharacterList) {
                viewModel.marvelCharactersValue.collect {
                    when {
                        it.isLoading -> {
//                            _binding!!.pBarCharacters.visibility = View.VISIBLE
                        }
                        it.error.isNotBlank() -> {
//                            _binding!!.pBarCharacters.visibility = View.GONE
                            flagForCharacterList = 0
                            Toast.makeText(context, it.error, Toast.LENGTH_SHORT).show()
                        }
                        it.list.isNotEmpty() -> {
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
        layoutManagerForCreatorList =
            GridLayoutManager(context, 2)
        creatorAdapter =
            MarvelListAdapter(context = context, itemList = ArrayList(), isSearch = isSearch[1], fragment)
        rvCreator.layoutManager = layoutManagerForCreatorList
        rvCreator.adapter = creatorAdapter
        viewModel.getAllCreatorsData(Constants.PAGINATED_VALUE_FOR_CREATORS)
        callCreatorApi()
        rvCreator.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (layoutManagerForCreatorList.findLastVisibleItemPosition() == layoutManagerForCreatorList.itemCount - 1) {
                    Constants.PAGINATED_VALUE_FOR_CREATORS += Constants.LIMIT_VALUE_FOR_CREATORS.toInt();
                    viewModel.getAllCreatorsData(Constants.PAGINATED_VALUE_FOR_CREATORS)
                    callCreatorApi()
                }
            }
        })
    }

    private fun callCreatorApi() {
        CoroutineScope(Dispatchers.Main).launch {
            repeat(flagForCreatorList) {
                viewModel.marvelCreatorsValue.collect {
                    when {
                        it.isLoading -> {
//                            _binding!!.pBarCreators.visibility = View.VISIBLE
                        }
                        it.error.isNotBlank() -> {
//                            _binding!!.pBarCreators.visibility = View.GONE
                            flagForCreatorList = 0
                            Toast.makeText(context, it.error, Toast.LENGTH_SHORT).show()
                        }
                        it.list.isNotEmpty() -> {
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
        layoutManagerForComicList =
            GridLayoutManager(context, 2)
        comicAdapter = MarvelListAdapter(context, ArrayList(), isSearch[2], fragment)
        rvComics.layoutManager = layoutManagerForComicList
        rvComics.adapter = comicAdapter
        viewModel.getAllComicsData(Constants.PAGINATED_VALUE_FOR_COMICS)
        callComicsApi()
        rvComics.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (layoutManagerForComicList.findLastVisibleItemPosition() == layoutManagerForComicList.itemCount - 1) {
                    Constants.PAGINATED_VALUE_FOR_COMICS += Constants.LIMIT_VALUE_FOR_COMICS.toInt();
                    viewModel.getAllComicsData(Constants.PAGINATED_VALUE_FOR_COMICS)
                    callComicsApi()
                }
            }
        })
    }

    private fun callComicsApi() {
        CoroutineScope(Dispatchers.Main).launch {
            repeat(flagForComicList) {
                viewModel.marvelComicValue.collect {
                    when {
                        it.isLoading -> {
//                            _binding!!.pBarCharacters.visibility = View.VISIBLE
                        }
                        it.error.isNotBlank() -> {
//                            _binding!!.pBarCharacters.visibility = View.GONE
                            flagForComicList = 0
                            Toast.makeText(context, it.error, Toast.LENGTH_SHORT).show()
                        }
                        it.list.isNotEmpty() -> {
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
        layoutManagerForEventsList =
            GridLayoutManager(context, 2)
        eventsAdapter = MarvelListAdapter(context, ArrayList(), isSearch[3], fragment)
        rvEvents.layoutManager = layoutManagerForEventsList
        rvEvents.adapter = eventsAdapter
        viewModel.getAllEventsData(offset = Constants.PAGINATED_VALUE_FOR_EVENTS)
        callEventsApi()
        rvEvents.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (layoutManagerForEventsList.findLastVisibleItemPosition() == layoutManagerForEventsList.itemCount - 1) {
                    Constants.PAGINATED_VALUE_FOR_EVENTS += Constants.LIMIT_VALUE_FOR_EVENTS.toInt();
                    viewModel.getAllEventsData(Constants.PAGINATED_VALUE_FOR_EVENTS)
                    callEventsApi()
                }
            }
        })
    }

    private fun callEventsApi() {
        CoroutineScope(Dispatchers.Main).launch {
            repeat(flagForEventList) {
                viewModel.marvelEventValue.collect {
                    when {
                        it.isLoading -> {
//                            _binding!!.pBarCharacters.visibility = View.VISIBLE
                        }
                        it.error.isNotBlank() -> {
//                            _binding!!.pBarCharacters.visibility = View.GONE
                            flagForEventList = 0
                            Toast.makeText(context, it.error, Toast.LENGTH_SHORT).show()
                        }
                        it.list.isNotEmpty() -> {
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
        layoutManagerForSeriesList =
            GridLayoutManager(context, 2)
        seriesAdapter = MarvelListAdapter(context, ArrayList(), isSearch[4], fragment)
        rvSeries.layoutManager = layoutManagerForSeriesList
        rvSeries.adapter = seriesAdapter
        viewModel.getAllSeriesData(offset = Constants.PAGINATED_VALUE_FOR_SERIES)
        callSeriesApi()
        rvSeries.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (layoutManagerForSeriesList.findLastVisibleItemPosition() == layoutManagerForSeriesList.itemCount - 1) {
                    Constants.PAGINATED_VALUE_FOR_SERIES += Constants.LIMIT_VALUE_FOR_SERIES.toInt();
                    viewModel.getAllSeriesData(Constants.PAGINATED_VALUE_FOR_SERIES)
                    callSeriesApi()
                }
            }
        })
    }

    private fun callSeriesApi() {
        CoroutineScope(Dispatchers.Main).launch {
            repeat(flagForSeriesList) {
                viewModel.marvelSeriesValue.collect {
                    when {
                        it.isLoading -> {
//                            _binding!!.pBarCharacters.visibility = View.VISIBLE
                        }
                        it.error.isNotBlank() -> {
//                            _binding!!.pBarCharacters.visibility = View.GONE
                            flagForSeriesList = 0
                            Toast.makeText(context, it.error, Toast.LENGTH_SHORT).show()
                        }
                        it.list.isNotEmpty() -> {
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