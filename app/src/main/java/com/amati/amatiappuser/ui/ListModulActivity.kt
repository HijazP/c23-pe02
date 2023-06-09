package com.amati.amatiappuser.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.amati.amatiappuser.R
import com.amati.amatiappuser.databinding.ActivityListModulBinding

class ListModulActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListModulBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListModulBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setStatusBarColorToMatchTopBar()
        setBackButtonClickListener()

        val layoutManager = LinearLayoutManager(this)
        binding.rvListModul.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvListModul.addItemDecoration(itemDecoration)

//        val collapsibleLayout = findViewById<LinearLayout>(R.id.collapsibleLayout)
//        val expandCollapseButton = findViewById<ImageButton>(R.id.ib_list_modul)
//        var isExpanded = false
//
//        expandCollapseButton.setOnClickListener {
//            if (isExpanded) {
//                collapsibleLayout.visibility = View.GONE
//                expandCollapseButton.setImageResource(R.drawable.baseline_arrow_drop_down_24)
//            } else {
//                collapsibleLayout.visibility = View.VISIBLE
//                expandCollapseButton.setImageResource(R.drawable.baseline_arrow_drop_up_24)
//            }
//            isExpanded = !isExpanded
//        }
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

        binding.judulModul.text = intent.getStringExtra(EXTRA_ITEM)
    }

    companion object {
        const val EXTRA_ITEM = "key_item"
    }
}