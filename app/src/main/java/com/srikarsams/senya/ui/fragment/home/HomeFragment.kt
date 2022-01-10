package com.srikarsams.senya.ui.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.srikarsams.senya.databinding.FragmentHomeBinding
import com.srikarsams.senya.ui.fragment.BaseFragment

class HomeFragment : BaseFragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = HomeFragmentAdapter{

        }

        binding.recyclerView.adapter = adapter
        adapter.setData(attractions)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}