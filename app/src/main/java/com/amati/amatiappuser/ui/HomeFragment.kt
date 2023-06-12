package com.amati.amatiappuser.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.amati.amatiappuser.R
import com.amati.amatiappuser.adapter.ModulAdapter
import com.amati.amatiappuser.adapter.ProgressAdapter
import com.amati.amatiappuser.database.UserPreferencesDatastore
import com.amati.amatiappuser.databinding.FragmentHomeBinding
import com.amati.amatiappuser.network.response.KursusItem
import com.amati.amatiappuser.network.response.ProgressItem
import com.amati.amatiappuser.viewmodel.HomeViewModel
import com.amati.amatiappuser.viewmodel.Session
import com.amati.amatiappuser.viewmodel.SessionModelFactory

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val homeViewModel: HomeViewModel by viewModels()
    private var token: String? = null

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dataStore: DataStore<Preferences> = requireContext().dataStore

        val pref = UserPreferencesDatastore.getInstance(dataStore)
        val session = ViewModelProvider(this, SessionModelFactory(pref))[Session::class.java]

        session.getName().observe(viewLifecycleOwner){ nama ->
            if (nama != "") {
                binding.nama.text = nama
            }
        }

        setStatusBarColorToMatchTopBar()

        session.getToken().observe(viewLifecycleOwner){
            if (it != "" && it != null) {
                token = it
                homeViewModel.getAllProgress("Bearer $token")
                homeViewModel.getAllCourse("Bearer $token")
                val code = homeViewModel.code.value
                if (code == 401) {
                    session.logout()
                    val intent = Intent(requireActivity(), LoginActivity::class.java)
                    startActivity(intent)
                    requireActivity().finish()
                }
            }
        }

        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvProgress.layoutManager = layoutManager

        homeViewModel.dataAllProgress.observe(viewLifecycleOwner){
            progress(it)
        }

        homeViewModel.dataAllCourse.observe(viewLifecycleOwner){
            setCourse(it)
        }
    }

    private fun setCourse(data: List<KursusItem>) {
        val layoutManager = LinearLayoutManager(requireContext())
        binding.rvCourse.layoutManager = layoutManager

        val adapter = ModulAdapter(data)
        binding.rvCourse.adapter = adapter
    }
    private fun progress(data: List<ProgressItem>){
        if (!homeViewModel.dataAllProgress.value.isNullOrEmpty()){
            val adapter = ProgressAdapter(data, homeViewModel, token)
            binding.rvProgress.adapter = adapter
        } else {
            binding.lanjutBelajar.visibility = View.GONE
        }
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

    }
}