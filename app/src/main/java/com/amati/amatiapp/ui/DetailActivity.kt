package com.amati.amatiapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.amati.amatiapp.R
import com.amati.amatiapp.database.UserPreferencesDatastore
import com.amati.amatiapp.databinding.ActivityDetailBinding
import com.amati.amatiapp.network.response.DetailResponse
import com.amati.amatiapp.viewmodel.DetailViewModel
import com.amati.amatiapp.viewmodel.Session
import com.amati.amatiapp.viewmodel.SessionModelFactory

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private val detailViewModel: DetailViewModel by viewModels()

    private lateinit var token: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pref = UserPreferencesDatastore.getInstance(dataStore)
        val session = ViewModelProvider(this, SessionModelFactory(pref))[Session::class.java]

        session.getToken().observe(this) {
            token = it
            val id = intent.getIntExtra(EXTRA_ITEM, 0).toString()
            detailViewModel.displayDetail("Bearer $token", id)
        }

        detailViewModel.dataDetail.observe(this) { data ->
            Toast.makeText(this, data.message, Toast.LENGTH_SHORT).show()
            setDetailProblem(data)
        }

    }

    private fun setDetailProblem(data: DetailResponse) {
        val title = data.masalah?.get(0)?.namaMasalah
        val deskripsi = data.masalah?.get(0)?.deskripsi

        binding.apply {
            tvDetailTitle.text = title
            tvDetailDesc.text = deskripsi
        }
    }
    companion object {
        const val EXTRA_ITEM = "key_item"
    }
}