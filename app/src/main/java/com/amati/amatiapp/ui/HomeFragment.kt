package com.amati.amatiapp.ui

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.amati.amatiapp.R
import com.amati.amatiapp.adapter.ModulAdapter
import com.amati.amatiapp.adapter.ProgressAdapter
import com.amati.amatiapp.data.DataDummy
import com.amati.amatiapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setStatusBarColorToMatchTopBar()

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

    @SuppressLint("ObsoleteSdkInt")
    private fun setStatusBarColorToMatchTopBar() {
        val topBarColor = ContextCompat.getColor(requireContext(), R.color.topbar_color)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = (activity as? AppCompatActivity)?.window
            window?.apply {
                addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                statusBarColor = topBarColor
            }
        }
        (activity as AppCompatActivity).supportActionBar?.hide()

    }
}