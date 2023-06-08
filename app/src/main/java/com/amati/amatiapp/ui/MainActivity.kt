package com.amati.amatiapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.amati.amatiapp.R
import com.amati.amatiapp.adapter.ProblemAdapter
import com.amati.amatiapp.data.DataDummy
import com.amati.amatiapp.database.UserPreferencesDatastore
import com.amati.amatiapp.databinding.ActivityMainBinding
import com.amati.amatiapp.viewmodel.MainViewModel
import com.amati.amatiapp.viewmodel.Session
import com.amati.amatiapp.viewmodel.SessionModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    private lateinit var token: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pref = UserPreferencesDatastore.getInstance(dataStore)
        val session = ViewModelProvider(this, SessionModelFactory(pref))[Session::class.java]

        session.getToken().observe(this) {
            if (it != null) {
                token = it
//                mainViewModel.getStories(token)
            } else  {
                Toast.makeText(this, getString(R.string.login_success), Toast.LENGTH_SHORT).show()
                finishAffinity()
            }
        }

        val layoutManager = LinearLayoutManager(this)
        binding.rvProblem.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvProblem.addItemDecoration(itemDecoration)

        setProblemData()
        addProblem()
    }

    private fun setProblemData() {
        val adapter = ProblemAdapter(DataDummy.dummyList)
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