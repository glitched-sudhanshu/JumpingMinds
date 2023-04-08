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
import com.example.tmm.databinding.FragmentSearchBinding
import com.example.tmm.ui.viewmodels.MarvelListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment(), SearchView.OnQueryTextListener {

    private var _binding: FragmentSearchBinding? = null
    private lateinit var searchView: SearchView
    private lateinit var searchTerm : String
    private val viewModel : MarvelListViewModel by activityViewModels()

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
        getSearchResults()
    }

    private fun getSearchResults() {
        searchView.isSubmitButtonEnabled = true
        searchView.setOnQueryTextListener(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if(query!=null){
            searchTerm = query
        }

        if(searchTerm.isNotEmpty()){
            search()
        }
        return true
    }

    private fun search() {
        viewModel.getAllSearchedSeries(searchTerm)
        CoroutineScope(Dispatchers.Main).launch {
            viewModel.marvelSeriesValue.collect{
                when{
                    it.isLoading->{
//                        _binding!!.pBarCharacters.visibility = View.VISIBLE
                    }
                    it.error.isNotBlank()->{
//                        _binding!!.pBarCharacters.visibility = View.GONE
                        Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT).show()
                    }
                    it.seriesList.isNotEmpty()->{
//                        _binding!!.pBarCharacters.visibility = View.GONE
//                        characterListAdapter.setData(it.characterList as ArrayList<Character>)
                        for(item in it.seriesList){
                            Log.d(TAG, "search: ${item.title}")
                        }
                    }
                }
            }
        }
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if(newText!=null){
            searchTerm = newText
        }

        if(searchTerm.isNotEmpty()){
            search()
        }
        return true
    }
}