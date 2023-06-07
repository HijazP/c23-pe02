package com.amati.amatiapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.amati.amatiapp.R
import com.amati.amatiapp.databinding.ActivityModulBinding

class ModulActivity : AppCompatActivity() {
    private lateinit var binding: ActivityModulBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityModulBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val idmodul = intent.getStringExtra(EXTRA_ITEM)

    }

    companion object {
        const val EXTRA_ITEM = "key_item"
    }
}