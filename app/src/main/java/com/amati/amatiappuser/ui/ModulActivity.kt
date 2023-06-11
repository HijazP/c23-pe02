package com.amati.amatiappuser.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.amati.amatiappuser.R
import com.amati.amatiappuser.database.UserPreferencesDatastore
import com.amati.amatiappuser.databinding.ActivityModulBinding
import com.amati.amatiappuser.network.response.Modul
import com.amati.amatiappuser.viewmodel.CourseViewModel
import com.amati.amatiappuser.viewmodel.Session
import com.amati.amatiappuser.viewmodel.SessionModelFactory

class ModulActivity : AppCompatActivity() {
    private lateinit var binding: ActivityModulBinding
    private val courseViewModel: CourseViewModel by viewModels()
    private var token: String = ""
    private var idKursus: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityModulBinding.inflate(layoutInflater)
        setContentView(binding.root)

        idKursus = intent.getIntExtra(EXTRA_ITEM, 0)
        val namaKursus = intent.getStringExtra(EXTRA_NAMA)

        val pref = UserPreferencesDatastore.getInstance(dataStore)
        val session = ViewModelProvider(this, SessionModelFactory(pref))[Session::class.java]

        session.getToken().observe(this) { token ->
            if (!token.isNullOrEmpty()) {
                this.token = token
                courseViewModel.getCourse("Bearer $token", idKursus!!)
            }
        }

        binding.judulCourse.text = namaKursus

        setStatusBarColorToMatchTopBar()
        setBackButtonClickListener()
        setModulList()

        courseViewModel.dataCourse.observe(this) {
            if (it != null) {
                setLanjutButton(idKursus!!)
            }
//            progress = courseViewModel.dataProgressCourse.value!!.modulSekarang
        }

        courseViewModel.dataModul.observe(this) {
            setModulData(it)
        }

//        courseViewModel.dataProgressCourse.observe(this) {
//            idModul = it.id
//            if (progress == jumlahModul) {
//                binding.lanjut.text = getString(R.string.selesai)
//            }
//        }

//        courseViewModel.detailModul("Bearer $token", idModul!!)
        courseViewModel.dataDetailModul.observe(this) {
            setModulData(it)
        }
    }

    private fun setModulData(modul: Modul) {
        binding.apply {
            judulModul.text = modul.namaModul
            gambar.setImageResource(R.drawable.amati_logo)
            desc.text = getString(R.string.detail_desc)
        }
    }

    private fun setLanjutButton(idKursus: Int) {
        binding.lanjut.setOnClickListener {
            courseViewModel.progressCourse("Bearer $token", idKursus, "next")
//            courseViewModel.detailModul("Bearer $token", idModul+1)

            val intentToDetail = Intent(this@ModulActivity, ModulActivity::class.java)
            intentToDetail.putExtra(EXTRA_ITEM, idKursus)
            intentToDetail.putExtra(EXTRA_NAMA, binding.judulCourse.text.toString())
            startActivity(intentToDetail)
            finish()
        }
    }

    private fun setModulList() {
        binding.listModul.setOnClickListener {
            val intent = Intent(this@ModulActivity, ListModulActivity::class.java)
            intent.putExtra(ListModulActivity.EXTRA_ITEM, binding.judulModul.text.toString())
            startActivity(intent)
        }
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
    }

    companion object {
        const val EXTRA_ITEM = "key_item"
        const val EXTRA_NAMA = "key_nama"
    }
}