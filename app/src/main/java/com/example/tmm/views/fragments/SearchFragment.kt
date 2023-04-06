package com.example.tmm.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.tmm.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {


        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return _binding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}