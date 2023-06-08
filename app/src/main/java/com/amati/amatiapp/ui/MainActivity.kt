package com.amati.amatiapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.amati.amatiapp.R
import com.amati.amatiapp.adapter.ProblemAdapter
import com.amati.amatiapp.database.UserPreferencesDatastore
import com.amati.amatiapp.databinding.ActivityMainBinding
import com.amati.amatiapp.network.response.MasalahItem
import com.amati.amatiapp.viewmodel.MainViewModel
import com.amati.amatiapp.viewmodel.Session
import com.amati.amatiapp.viewmodel.SessionModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    private lateinit var token: String
    private lateinit var session: Session

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pref = UserPreferencesDatastore.getInstance(dataStore)
        session = ViewModelProvider(this, SessionModelFactory(pref))[Session::class.java]

        session.getToken().observe(this) {
            if (it != null) {
                token = it
                mainViewModel.getProblem("Bearer $token")
            }
        }

        val layoutManager = LinearLayoutManager(this)
        binding.rvProblem.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvProblem.addItemDecoration(itemDecoration)

        mainViewModel.code.observe(this) {
            when (it) {
                401 -> {
                    Toast.makeText(this, getString(R.string.unauthorized2), Toast.LENGTH_SHORT).show()
                }
                404 -> {
                    Toast.makeText(this, getString(R.string.data_not_found), Toast.LENGTH_SHORT).show()
                }
                500 -> {
                    Toast.makeText(this, getString(R.string.server_error), Toast.LENGTH_SHORT).show()
                }
            }
        }

        mainViewModel.dataProblem.observe(this) { data ->
            val msg = mainViewModel.msg.value
            if (msg == "0" || msg == null || msg == "") {
                binding.tvMasalah.text = getString(R.string.masalah)
                showText(true)
            } else {
                showText(false)
            }

            if (data != null) {
                setProblemData(data)
            }
        }

        addProblem()
    }

    private fun showText(isAppear: Boolean) {
        if (isAppear) {
            binding.tvMasalah.visibility = View.VISIBLE
        } else {
            binding.tvMasalah.visibility = View.GONE
        }
    }

    private fun setProblemData(listProblem: List<MasalahItem>) {
        val adapter = ProblemAdapter(listProblem)
        binding.rvProblem.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.item_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.profile -> {
                val intentToAbout = Intent(this@MainActivity, ProfilActivity::class.java)
                startActivity(intentToAbout)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun addProblem(){
        binding.fabAdd.setOnClickListener{
            val intent = Intent (this@MainActivity, AddProblemActivity::class.java)
            startActivity(intent)
        }
    }
}