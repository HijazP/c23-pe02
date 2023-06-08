package com.amati.amatiapp.ui

import android.app.DownloadManager.Request
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.amati.amatiapp.R
import com.amati.amatiapp.database.UserPreferencesDatastore
import com.amati.amatiapp.databinding.ActivityAddProblemBinding
import com.amati.amatiapp.response.RequestProblem
import com.amati.amatiapp.viewmodel.AddProblemViewModel
import com.amati.amatiapp.viewmodel.Session
import com.amati.amatiapp.viewmodel.SessionModelFactory

class AddProblemActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddProblemBinding
    private val addProblemViewModel: AddProblemViewModel by viewModels()

    private lateinit var token: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddProblemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        addProblemAct()

        val pref = UserPreferencesDatastore.getInstance(dataStore)
        val session = ViewModelProvider(this, SessionModelFactory(pref))[Session::class.java]

        session.getToken().observe(this) {
            token = it
        }

        addProblemViewModel.code.observe(this) {
            val responseCode = addProblemViewModel.code.value
            val responseMessage = addProblemViewModel.dataProblem.value?.message
            when (responseCode) {
                201 -> {
                    Toast.makeText(this, responseMessage, Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@AddProblemActivity, MainActivity::class.java)
                    startActivity(intent)
                }
                401 -> {
                    Toast.makeText(this, responseMessage, Toast.LENGTH_SHORT).show()
                }
                404 -> {
                    Toast.makeText(this, responseMessage, Toast.LENGTH_SHORT).show()
                }
                500 -> {
                    Toast.makeText(this, responseMessage, Toast.LENGTH_SHORT).show()
                }
                else -> {
                    Toast.makeText(this, "Server is currently down", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun addProblemAct(){
        binding.btnProblem.setOnClickListener{
            binding.apply {
                inputProblem.clearFocus()
                inputDesc.clearFocus()

                val problem = inputProblem.text.toString()
                val generalDesc = inputDesc.text.toString()

                when{
                    problem.isEmpty() -> {
                        inputProblem.error = getString(R.string.tittle_req)
                    }
                    generalDesc.isEmpty() -> {
                        inputDesc.error = getString(R.string.desc_req)
                    }
                    else -> {
                        val requestProblem = RequestProblem(problem, generalDesc)
                        addProblemViewModel.add("Bearer $token", requestProblem)
                    }
                }
            }
        }
    }
}