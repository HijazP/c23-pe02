package com.amati.amatiappuser.ui

import android.content.Context
import android.os.Bundle
import android.provider.ContactsContract.Data
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.amati.amatiappuser.R
import com.amati.amatiappuser.adapter.ModulAdapter
import com.amati.amatiappuser.data.DataDummy
import com.amati.amatiappuser.data.DataDummy.dummyList
import com.amati.amatiappuser.databinding.FragmentDesakuBinding
import java.util.prefs.PreferenceChangeEvent
import java.util.prefs.Preferences


class DesakuFragment : Fragment() {
    private lateinit var binding: FragmentDesakuBinding
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
    private var nama : String = ""



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDesakuBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        val inflater = menuInflater
//        inflater.inflate(R.menu.item_menu, menu)
//        return true
//    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dataStore: DataStore<Preferences> = requireContext().dataStore

        nama = "User"

        val layoutManager = LinearLayoutManager(requireContext())
        binding.rvDesaku.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(requireContext(), layoutManager.orientation)
        binding.rvDesaku.addItemDecoration(itemDecoration)

        setCourse()
    }

    private fun setCourse(){
        val adapter = ModulAdapter(DataDummy.dummyList)
        binding.rvDesaku.adapter = adapter
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