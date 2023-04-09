package com.example.tmm.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.Person.fromBundle
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tmm.R
import com.example.tmm.databinding.FragmentAllItemsBinding
import com.example.tmm.databinding.FragmentHomeBinding
import com.example.tmm.domain.model.*
import com.example.tmm.ui.viewmodels.MarvelListViewModel
import com.example.tmm.utils.Constants.SP_CHARACTERS
import com.example.tmm.utils.Constants.SP_COMICS
import com.example.tmm.utils.Constants.SP_CREATORS
import com.example.tmm.utils.Constants.SP_EVENTS
import com.example.tmm.utils.Constants.SP_SERIES
import com.example.tmm.views.adapters.MarvelListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllItemsFragment : Fragment() {
    private var _binding: FragmentAllItemsBinding? = null
    private val viewModel : MarvelListViewModel by activityViewModels()
    private lateinit var apiData: ApiDetailedData

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAllItemsBinding.inflate(inflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listType = arguments?.getString("listType")


        val listRecyclerView = arrayOf(_binding!!.listItems, _binding!!.listItems, _binding!!.listItems, _binding!!.listItems, _binding!!.listItems)
        val listDetailed = arrayOf(true, true, true, true, true)
        apiData = ApiDetailedData(viewModel, requireContext(), listRecyclerView, listDetailed, this)


        when(listType){
            SP_CHARACTERS ->{
                _binding!!.txtHeading.text = "Characters-"
                apiData.setupCharacterRecyclerView()
            }
            SP_CREATORS -> {

                _binding!!.txtHeading.text = "Creators-"
                apiData.setupCreatorRecyclerView()
            }
            SP_EVENTS ->{

                _binding!!.txtHeading.text = "Events-"
                apiData.setupEventsRecyclerView()
            }
            SP_COMICS ->{

                _binding!!.txtHeading.text = "Comics-"
                apiData.setupComicRecyclerView()
            }
            SP_SERIES ->{

                _binding!!.txtHeading.text = "Series-"
                apiData.setupSeriesRecyclerView()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}