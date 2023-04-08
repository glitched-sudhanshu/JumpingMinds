package com.example.tmm.views.fragments

import android.os.Bundle
import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tmm.R
import com.example.tmm.databinding.FragmentSearchBinding
import com.example.tmm.domain.model.ListViewItem
import com.example.tmm.ui.viewmodels.MarvelListViewModel
import com.example.tmm.utils.Constants.SEARCH_FOR_CHARACTERS
import com.example.tmm.utils.Constants.SEARCH_FOR_COMICS
import com.example.tmm.utils.Constants.SEARCH_FOR_CREATORS
import com.example.tmm.utils.Constants.SEARCH_FOR_EVENTS
import com.example.tmm.utils.Constants.SEARCH_FOR_SERIES
import com.example.tmm.views.adapters.SearchListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment(), SearchView.OnQueryTextListener {

    private var _binding: FragmentSearchBinding? = null
    private lateinit var searchView: SearchView
    private lateinit var searchTerm: String
    private val viewModel: MarvelListViewModel by activityViewModels()
    private var globalQueryHint: String = ""


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchView = _binding!!.searchView
        setupSearchByRecyclerView()
        getSearchResults(queryHint = SEARCH_FOR_CHARACTERS)
    }

    private fun setupSearchByRecyclerView() {
        val listItems = arrayOf(
            ListViewItem(R.string.search_creators, R.drawable.c_four),
            ListViewItem(R.string.search_comics, R.drawable.c_two),
            ListViewItem(R.string.search_events, R.drawable.c_three),
            ListViewItem(R.string.search_series, R.drawable.c_five),
            ListViewItem(R.string.search_characters,
                R.drawable.c_one),
        )
        val rvSearchBy = _binding!!.rvSearchMenu
        val layoutManger =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        val adapter = SearchListAdapter(requireContext(),
            listItems,
            object : SearchListAdapter.OnSearchByItemClickListener {
                override fun onSearchByItemClick(position: Int) {
                    when (position) {
                        0 -> {
                            getSearchResults(queryHint = SEARCH_FOR_CREATORS)
                        }
                        1 -> {
                            getSearchResults(queryHint = SEARCH_FOR_COMICS)
                        }
                        2 -> {
                            getSearchResults(queryHint = SEARCH_FOR_EVENTS)
                        }
                        3 -> {
                            getSearchResults(queryHint = SEARCH_FOR_SERIES)
                        }
                        4 -> {
                            getSearchResults(queryHint = SEARCH_FOR_CHARACTERS)
                        }
                        else -> return
                    }
                }
            })
        rvSearchBy.layoutManager = layoutManger
        rvSearchBy.adapter = adapter
    }

    private fun getSearchResults(queryHint: String) {
        searchView.queryHint = queryHint
        globalQueryHint = queryHint
        searchView.isSubmitButtonEnabled = true
        searchView.setOnQueryTextListener(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null) {
            searchTerm = query
        }

        if (searchTerm.isNotEmpty()) {
            search(queryHint = globalQueryHint)
        }
        return true
    }

    private fun search(queryHint: String) {

        when (queryHint) {
            SEARCH_FOR_CHARACTERS -> {
                viewModel.getAllSearchedCharacters(searchTerm)
                CoroutineScope(Dispatchers.Main).launch {
                    viewModel.marvelCharactersValue.collect {
                        when {
                            it.isLoading -> {
//                        _binding!!.pBarCharacters.visibility = View.VISIBLE
                            }
                            it.error.isNotBlank() -> {
//                        _binding!!.pBarCharacters.visibility = View.GONE
                                Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT)
                                    .show()
                            }
                            it.list.isNotEmpty() -> {
//                        _binding!!.pBarCharacters.visibility = View.GONE
//                        characterListAdapter.setData(it.characterList as ArrayList<Character>)
                                for (item in it.list) {
                                    Log.d(TAG, "search: ${item.name}")
                                }
                            }
                        }
                    }
                }
            }

            SEARCH_FOR_CREATORS -> {
                viewModel.getAllSearchedCreatorsData(searchTerm)
                CoroutineScope(Dispatchers.Main).launch {
                    viewModel.marvelCreatorsValue.collect {

                        when {
                            it.isLoading -> {
//                        _binding!!.pBarCharacters.visibility = View.VISIBLE
                            }
                            it.error.isNotBlank() -> {
//                        _binding!!.pBarCharacters.visibility = View.GONE
                                Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT)
                                    .show()
                            }
                            it.list.isNotEmpty() -> {
//                        _binding!!.pBarCharacters.visibility = View.GONE
//                        characterListAdapter.setData(it.characterList as ArrayList<Character>)
                                for (item in it.list) {
                                    Log.d(TAG, "search: ${item.firstName}")
                                }
                            }
                        }
                    }
                }
            }

            SEARCH_FOR_COMICS -> {
                viewModel.getAllSearchedComics(searchTerm)
                CoroutineScope(Dispatchers.Main).launch {
                    viewModel.marvelComicValue.collect {
                        when {
                            it.isLoading -> {
//                        _binding!!.pBarCharacters.visibility = View.VISIBLE
                            }
                            it.error.isNotBlank() -> {
//                        _binding!!.pBarCharacters.visibility = View.GONE
                                Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT)
                                    .show()
                            }
                            it.list.isNotEmpty() -> {
//                        _binding!!.pBarCharacters.visibility = View.GONE
//                        characterListAdapter.setData(it.characterList as ArrayList<Character>)
                                for (item in it.list) {
                                    Log.d(TAG, "search: ${item.title}")
                                }
                            }
                        }
                    }
                }
            }

            SEARCH_FOR_EVENTS -> {
                viewModel.getAllSearchedEvents(searchTerm)
                CoroutineScope(Dispatchers.Main).launch {
                    viewModel.marvelEventValue.collect {
                        when {
                            it.isLoading -> {
//                        _binding!!.pBarCharacters.visibility = View.VISIBLE
                            }
                            it.error.isNotBlank() -> {
//                        _binding!!.pBarCharacters.visibility = View.GONE
                                Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT)
                                    .show()
                            }
                            it.list.isNotEmpty() -> {
//                        _binding!!.pBarCharacters.visibility = View.GONE
//                        characterListAdapter.setData(it.characterList as ArrayList<Character>)
                                for (item in it.list) {
                                    Log.d(TAG, "search: ${item.title}")
                                }
                            }
                        }
                    }
                }
            }

            SEARCH_FOR_SERIES -> {
                viewModel.getAllSearchedSeries(searchTerm)
                CoroutineScope(Dispatchers.Main).launch {
                    viewModel.marvelSeriesValue.collect {
                        when {
                            it.isLoading -> {
//                        _binding!!.pBarCharacters.visibility = View.VISIBLE
                            }
                            it.error.isNotBlank() -> {
//                        _binding!!.pBarCharacters.visibility = View.GONE
                                Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT)
                                    .show()
                            }
                            it.list.isNotEmpty() -> {
//                        _binding!!.pBarCharacters.visibility = View.GONE
//                        characterListAdapter.setData(it.characterList as ArrayList<Character>)
                                for (item in it.list) {
                                    Log.d(TAG, "search: ${item.title}")
                                }
                            }
                        }
                    }
                }
            }

        }
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText != null) {
            searchTerm = newText
        }

        if (searchTerm.isNotEmpty()) {
            search(queryHint = globalQueryHint)
        }
        return true
    }
}