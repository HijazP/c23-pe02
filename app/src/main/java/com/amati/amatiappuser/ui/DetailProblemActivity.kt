package com.amati.amatiappuser.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.amati.amatiappuser.database.UserPreferencesDatastore
import com.amati.amatiappuser.databinding.ActivityDetailProblemBinding
import com.amati.amatiappuser.network.response.DetailProblemResponse
import com.amati.amatiappuser.viewmodel.DetailProblemViewModel
import com.amati.amatiappuser.viewmodel.Session
import com.amati.amatiappuser.viewmodel.SessionModelFactory

class DetailProblemActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailProblemBinding
    private val detailProblemViewModel: DetailProblemViewModel by viewModels()
    private var namaDesa: String = ""

    private lateinit var token: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailProblemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pref = UserPreferencesDatastore.getInstance(dataStore)
        val session = ViewModelProvider(this, SessionModelFactory(pref))[Session::class.java]

        namaDesa = intent.getStringExtra(EXTRA_ID_DESA).toString()
        Log.e("nama desa", "Isinya apa sih $namaDesa")

        session.getToken().observe(this) {
            token = it
            val id = intent.getIntExtra(EXTRA_ID, 0).toString()
            detailProblemViewModel.displayDetail("Bearer $token", id)
            detailProblemViewModel.getRekomendasiMasalah("Bearer $token", namaDesa)

        }

        detailProblemViewModel.dataDetail.observe(this) { data ->
            Toast.makeText(this, data?.message, Toast.LENGTH_SHORT).show()
            setDetailProblem(data)
        }
    }

    private fun setDetailProblem(data: DetailProblemResponse?) {
        val title = data?.masalah?.get(0)?.namaMasalah
        val deskripsi = data?.masalah?.get(0)?.deskripsi

        detailProblemViewModel.dataDesa.observe(this) {desa ->
            binding.apply {
                tvDetailTitle.text = title
                tvDetailDesc.text = deskripsi
                tvDetailLocation.text = desa[0].namaDesa
                tvStreetLocation.text = desa[0].lokasiDesa
                tvPhoneLocation.text = desa[0].telepon
            }
        }
    }

    companion object {
        const val EXTRA_ID = "id"
        const val EXTRA_NAME = "title"
        const val EXTRA_ID_DESA = "desc"
    }
}