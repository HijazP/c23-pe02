package com.amati.amatiappuser.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.core.content.ContextCompat
import com.amati.amatiappuser.R
import com.amati.amatiappuser.data.DataDummy
import com.amati.amatiappuser.databinding.ActivityModulBinding

class ModulActivity : AppCompatActivity() {
    private lateinit var binding: ActivityModulBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityModulBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setStatusBarColorToMatchTopBar()
        setBackButtonClickListener()
        setModulList()

        val idModul = intent.getIntExtra(EXTRA_ITEM, 0)

        setModulData(idModul)

        setLanjutButton(idModul)
    }

    private fun setModulData(idmodul: Int) {
        binding.apply {
            when (idmodul) {
                1 -> {
                    judulModul.text = DataDummy.dummyList[0].kursus
                    gambar.setImageResource(R.drawable.modul)
                    desc.text = getString(R.string.detail_desc)
                }
                2 -> {
                    judulModul.text = DataDummy.dummyList[1].kursus
                    gambar.setImageResource(R.drawable.modul)
                    desc.text = getString(R.string.detail_desc)
                }
            }
        }
    }


    private fun setLanjutButton(idModul: Int) {
        binding.lanjut.setOnClickListener {
            if (idModul == 2) {
                val intentToDetail = Intent(this@ModulActivity, HomeFragment::class.java)
                startActivity(intentToDetail)
            } else  {
                val intentToDetail = Intent(this@ModulActivity, ModulActivity::class.java)
                intentToDetail.putExtra(EXTRA_ITEM, idModul+1)
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
    }
}