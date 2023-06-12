package com.amati.amatiappuser.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.amati.amatiappuser.R
import com.amati.amatiappuser.adapter.ListModulAdapter
import com.amati.amatiappuser.database.UserPreferencesDatastore
import com.amati.amatiappuser.databinding.ActivityListModulBinding
import com.amati.amatiappuser.network.response.AllModulItem
import com.amati.amatiappuser.viewmodel.ListModulViewModel
import com.amati.amatiappuser.viewmodel.Session
import com.amati.amatiappuser.viewmodel.SessionModelFactory

class ListModulActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListModulBinding
    private val listModulViewModel : ListModulViewModel by viewModels()
    private var idKursus: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListModulBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setStatusBarColorToMatchTopBar()
        setBackButtonClickListener()

        idKursus = intent.getIntExtra(EXTRA_ITEM, 0)

        val pref = UserPreferencesDatastore.getInstance(dataStore)
        val session = ViewModelProvider(this, SessionModelFactory(pref))[Session::class.java]

        session.getToken().observe(this){token ->
            if (token != "" && token != null) {
                listModulViewModel.getListModul("Bearer $token", idKursus)
            }
        }

        val layoutManager = LinearLayoutManager(this)
        binding.rvListModul.layoutManager = layoutManager

        listModulViewModel.dataListModul.observe(this) {
            setListModul(it)
        }
    }

    private fun setListModul(data: List<AllModulItem>) {
        val adapter = ListModulAdapter(data, idKursus)
        binding.rvListModul.adapter = adapter
    }

    private fun setBackButtonClickListener() {
        val backButton = binding.back
        backButton.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setStatusBarColorToMatchTopBar() {
        val topBarColor = ContextCompat.getColor(this, R.color.topbar_color)

        window?.apply {
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            statusBarColor = topBarColor
        }

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        binding.judulModul.text = intent.getStringExtra(EXTRA_NAMA)
    }

    companion object {
        const val EXTRA_ITEM = "key_item"
        const val EXTRA_NAMA = "key_nama"
    }
}