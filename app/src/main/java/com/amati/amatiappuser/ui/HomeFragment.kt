package com.amati.amatiappuser.ui

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.amati.amatiappuser.R
import com.amati.amatiappuser.adapter.ModulAdapter
import com.amati.amatiappuser.adapter.ProgressAdapter
import com.amati.amatiappuser.data.DataDummy
import com.amati.amatiappuser.database.UserPreferencesDatastore
import com.amati.amatiappuser.databinding.FragmentHomeBinding
import com.amati.amatiappuser.viewmodel.Session
import com.amati.amatiappuser.viewmodel.SessionModelFactory

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
    private var nama : String = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dataStore: DataStore<Preferences> = requireContext().dataStore

        nama = "User"

//        val pref = UserPreferencesDatastore.getInstance(dataStore)
//        val session = ViewModelProvider(this, SessionModelFactory(pref))[Session::class.java]
//a
//        session.getName().observe(viewLifecycleOwner){
//            if (it != "") {
//                nama = it
//            }
//        }

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
//        }
        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvProgress.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(requireContext(), layoutManager.orientation)
        binding.rvProgress.addItemDecoration(itemDecoration)

        val adapter = ProgressAdapter(DataDummy.dummyList)
        binding.rvProgress.adapter = adapter

    }

    private fun setStatusBarColorToMatchTopBar() {
        val topBarColor = ContextCompat.getColor(requireContext(), R.color.topbar_color)

        val window = (activity as? AppCompatActivity)?.window
        window?.apply {
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            statusBarColor = topBarColor
        }

        (activity as AppCompatActivity?)?.setSupportActionBar(binding.toolbar)
        (activity as AppCompatActivity?)?.supportActionBar?.setDisplayShowTitleEnabled(false)

        binding.nama.text = nama

//
////        (activity as AppCompatActivity?)!!.setSupportActionBar(binding.toolbar)
//        (activity as AppCompatActivity?)!!.supportActionBar?.title = null
////            buildString {
//            append(getString(R.string.hi_home))
//            append(nama)
//        }
    }
}