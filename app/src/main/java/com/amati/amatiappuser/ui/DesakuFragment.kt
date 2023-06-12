package com.amati.amatiappuser.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
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
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.amati.amatiappuser.R
import com.amati.amatiappuser.data.DataDummy
import com.amati.amatiappuser.database.UserPreferencesDatastore
import com.amati.amatiappuser.databinding.FragmentDesakuBinding
import com.amati.amatiappuser.viewmodel.Session
import com.amati.amatiappuser.viewmodel.SessionModelFactory
import androidx.datastore.preferences.core.Preferences
import com.amati.amatiappuser.adapter.DesakuAdapter
import com.amati.amatiappuser.viewmodel.DesakuViewModel


class DesakuFragment : Fragment() {
    private lateinit var binding: FragmentDesakuBinding
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
    private val desakuViewModel: DesakuViewModel by viewModels()
    private val nama : String = "Desaku"
    private var token: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDesakuBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dataStore: DataStore<Preferences> = requireContext().dataStore

        setStatusBarColorToMatchTopBar()

        val pref = UserPreferencesDatastore.getInstance(dataStore)
        val session = ViewModelProvider(this, SessionModelFactory(pref))[Session::class.java]

        session.getToken().observe(viewLifecycleOwner){
            if (it != "" && it != null) {
                token = it
                desakuViewModel.getAllProgress("Bearer $token")
                desakuViewModel.dataAllProgress.observe(viewLifecycleOwner){ progress ->
                    if (progress.isNotEmpty()) {
                        val latestProgress = progress.last() // Mengambil data terbaru dari daftar progress
                        val id = latestProgress.idKursus
                        desakuViewModel.getDetailCourse("Bearer $token", id)
                    }
                }
                val code = desakuViewModel.code.value
                if (code == 401) {
                    session.logout()
                    val intent = Intent(requireActivity(), LoginActivity::class.java)
                    startActivity(intent)
                    requireActivity().finish()
                }
            }
        }


        val layoutManager = LinearLayoutManager(requireContext())
        binding.rvDesaku.layoutManager = layoutManager

        setCourse()
    }

    private fun setCourse(){
        val adapter = DesakuAdapter(DataDummy.dummyList)
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
    }
}