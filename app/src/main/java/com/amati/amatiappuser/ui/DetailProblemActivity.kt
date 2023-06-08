package com.amati.amatiappuser.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.amati.amatiappuser.R
import com.amati.amatiappuser.databinding.ActivityDetailProblemBinding

class DetailProblemActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailProblemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailProblemBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}