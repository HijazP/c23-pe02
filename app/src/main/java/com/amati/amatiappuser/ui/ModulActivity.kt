package com.amati.amatiappuser.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.core.content.ContextCompat
import com.amati.amatiappuser.R
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

        val idmodul = intent.getIntExtra(EXTRA_ITEM, 0)

        when (idmodul) {
            1 -> {
                binding.judulModul.text = "Modul 1"
                binding.gambar.setImageResource(R.drawable.modul)
                binding.desc.text = getString(R.string.detail_desc)
            }
            2 -> {
                binding.judulModul.text = "Modul 2"
                binding.gambar.setImageResource(R.drawable.modul)
                binding.desc.text = getString(R.string.detail_desc)
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