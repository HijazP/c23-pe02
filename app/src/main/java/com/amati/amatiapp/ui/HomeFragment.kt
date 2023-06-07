package com.amati.amatiapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.amati.amatiapp.R
import com.amati.amatiapp.adapter.ModulAdapter
import com.amati.amatiapp.adapter.ProgressAdapter
import com.amati.amatiapp.data.DataDummy
import com.amati.amatiapp.databinding.FragmentHomeBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progress()

        val layoutManager = LinearLayoutManager(requireContext())
        binding.rvDesaku.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(requireContext(), layoutManager.orientation)
        binding.rvDesaku.addItemDecoration(itemDecoration)

        setCourse()

    }

    private fun setCourse() {
        val adapter = ModulAdapter(DataDummy.dummyList)
        binding.rvDesaku.adapter = adapter
    }

    private fun progress(){
//        if (){
//            val layoutManager = LinearLayoutManager(requireContext())
//            binding.rvProgress.layoutManager = layoutManager
//            val itemDecoration = DividerItemDecoration(requireContext(), layoutManager.orientation)
//            binding.rvProgress.addItemDecoration(itemDecoration)
//
//            val adapter = ProgressAdapter(DataDummy.dummyList)
//            binding.rvProgress.adapter = adapter
//        } else {
//
//        }
        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvProgress.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(requireContext(), layoutManager.orientation)
        binding.rvProgress.addItemDecoration(itemDecoration)

        val adapter = ProgressAdapter(DataDummy.dummyList)
        binding.rvProgress.adapter = adapter

    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}