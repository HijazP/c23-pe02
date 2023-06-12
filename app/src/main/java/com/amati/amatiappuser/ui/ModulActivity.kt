package com.amati.amatiappuser.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
    private var idKursus: Int = 0
    private var idModul: Int? = null
    private val TAG = "YourActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityModulBinding.inflate(layoutInflater)
        setContentView(binding.root)

        idKursus = intent.getIntExtra(EXTRA_ITEM, 0)
        val namaKursus = intent.getStringExtra(EXTRA_NAMA)
        idModul = intent.getIntExtra(EXTRA_ID_MODUL, 0)

        val pref = UserPreferencesDatastore.getInstance(dataStore)
        val session = ViewModelProvider(this, SessionModelFactory(pref))[Session::class.java]

        session.getToken().observe(this) {
            token = it
            if (!it.isNullOrEmpty()) {
                courseViewModel.getCourseById("Bearer $token", idKursus)
                idKursus.let { id ->
                    courseViewModel.getDetailCourse("Bearer $token", id)
                    courseViewModel.dataDetailCourse.observe(this) { kursus ->
                        binding.judulCourse.text = kursus.namaKursus
                    }
                    setLanjutButton(idKursus)
                }
                idModul?.let { idModul ->
                    courseViewModel.getDetailModul("Bearer $token", idModul)
                    courseViewModel.dataDetailModul.observe(this) { modul ->
                        setModulData(modul)
                    }
                }
            }
        }

        binding.judulCourse.text = namaKursus

        setStatusBarColorToMatchTopBar()
        setBackButtonClickListener()
        setModulList()

        courseViewModel.dataDetailModul.observe(this) {
            setModulData(it)
        }


//        courseViewModel.dataProgressCourse.observe(this) {
//            idModul = it.id
//            if (progress == jumlahModul) {
//                binding.lanjut.text = getString(R.string.selesai)
//            }
//        }

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
            Log.e(TAG, "Isi token apa sih: $token")
            courseViewModel.putProgressCourse("Bearer $token", idKursus, "next")
//            courseViewModel.detailModul("Bearer $token", idModul+1)
            courseViewModel.dataProgressCourse.observe(this) {
                Log.e(TAG, "Isi ambil kursus apa sih: ${it.updateKursus}")
                if (it.updateKursus.statusSelesai) {
                    val intent = Intent(this@ModulActivity, MainActivity::class.java)
                    startActivity(intent)
                }else{
                    val intentToDetail = Intent(this@ModulActivity, ModulActivity::class.java)
                    intentToDetail.putExtra(EXTRA_ITEM, idKursus)
                    intentToDetail.putExtra(EXTRA_ID_MODUL, it.updateKursus.modulSekarang)
                    intentToDetail.putExtra(EXTRA_NAMA, binding.judulCourse.text.toString())
                    startActivity(intentToDetail)
                    finish()
                }
            }
        }
    }

    private fun setModulList() {
        binding.listModul.setOnClickListener {
            val intent = Intent(this@ModulActivity, ListModulActivity::class.java)
            intent.putExtra(ListModulActivity.EXTRA_ITEM, idKursus)
            intent.putExtra(ListModulActivity.EXTRA_NAMA, binding.judulCourse.text.toString())
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
        const val EXTRA_ID_MODUL = "key_id_modul"
    }
}