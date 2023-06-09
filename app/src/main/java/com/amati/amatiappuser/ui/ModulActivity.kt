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
    private var progress: Int = 0
    private var jumlahModul: Int = 0
    private var idModul: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityModulBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pref = UserPreferencesDatastore.getInstance(dataStore)
        val session = ViewModelProvider(this, SessionModelFactory(pref))[Session::class.java]

        val idKursus = intent.getIntExtra(EXTRA_ITEM, 0)
        val namaKursus = intent.getStringExtra(EXTRA_NAMA)

        binding.judulCourse.text = namaKursus

        session.getToken().observe(this){
            if (it != "" && it != null) {
                token = it
                courseViewModel.getCourse("Bearer $token", idKursus)
            }
        }

        setStatusBarColorToMatchTopBar()
        setBackButtonClickListener()
        setModulList()

        courseViewModel.dataCourse.observe(this) {
            if (it != null) {
                setLanjutButton(idKursus)
            }
            idModul = courseViewModel.dataModul.value!!.id
            progress = courseViewModel.dataProgressCourse.value!!.modulSekarang
            jumlahModul = courseViewModel.dataCourse.value!!.jumlahModul
        }

        courseViewModel.detailModul("Bearer $token", idModul)
        courseViewModel.dataDetailModul.observe(this) {
            if (it != null) {
                setModulData(it)
            }
        }

    }

    private fun setModulData(listData: Modul) {
        binding.apply {
            judulModul.text = listData.namaModul
            gambar.setImageResource(R.drawable.amati_logo)
            desc.text = getString(R.string.detail_desc)
        }
    }


    private fun setLanjutButton(idModul: Int) {
        binding.lanjut.setOnClickListener {
            if (progress == jumlahModul) {
                val intentToDetail = Intent(this@ModulActivity, MainActivity::class.java)
                courseViewModel.progressCourse("Bearer $token", idModul, true)
                startActivity(intentToDetail)
            } else {
                val intentToDetail = Intent(this@ModulActivity, ModulActivity::class.java)
                intentToDetail.putExtra(EXTRA_ITEM, idModul+1)
                courseViewModel.progressCourse("Bearer $token", idModul, false)
                startActivity(intentToDetail)
            }
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