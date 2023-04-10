package com.example.tmm.views.fragments

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tmm.data.data_source.database.MarvelDatabase
import com.example.tmm.databinding.FragmentFavouritesBinding
import com.example.tmm.domain.model.*
import com.example.tmm.ui.viewmodels.MarvelRoomViewModel
import com.example.tmm.ui.viewmodels.MarvelRoomViewModelFactory
import com.example.tmm.views.adapters.MarvelListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavouritesFragment : Fragment() {

    private var _binding: FragmentFavouritesBinding? = null
    private lateinit var viewModel: MarvelRoomViewModel

    private lateinit var rvCharacters: RecyclerView
    private lateinit var adapter: MarvelListAdapter<Character>
    private lateinit var layoutManager: GridLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        _binding = FragmentFavouritesBinding.inflate(inflater, container, false)


        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dao = MarvelDatabase.getDatabase(requireContext()).characterDao()
        val factory = MarvelRoomViewModelFactory(dao)

        rvCharacters = _binding!!.rvFavChars
        viewModel = ViewModelProvider(this, factory)[MarvelRoomViewModel::class.java]
        lifecycleScope.launch {
            viewModel.allCharacters.observe(viewLifecycleOwner) { characters ->
                // Update your UI with the latest list of characters
                Log.d(TAG, "onViewCreated: ${characters.size}")
                if (characters.size == 0) {
                    _binding!!.txtNothing.visibility = View.VISIBLE
                    _binding!!.rvFavChars.visibility = View.GONE
                    _binding!!.txtFavTitle.visibility = View.GONE
                } else {
                    _binding!!.txtNothing.visibility = View.GONE
                    _binding!!.rvFavChars.visibility = View.VISIBLE
                    _binding!!.txtFavTitle.visibility = View.VISIBLE
                    layoutManager = GridLayoutManager(requireContext(), 2)
                    adapter = MarvelListAdapter(requireContext(), ArrayList(), isSearch = true, this@FavouritesFragment)
                    rvCharacters.layoutManager = layoutManager
                    rvCharacters.adapter = adapter
                    adapter.addData(characters as ArrayList<Character>)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}