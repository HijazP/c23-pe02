package com.amati.amatiapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.amati.amatiapp.R
import com.amati.amatiapp.databinding.ActivityAddProblemBinding
import com.amati.amatiapp.viewmodel.addProblemViewModel

class AddProblemActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddProblemBinding
//    private val addProblemViewModel: addProblemViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddProblemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        addProblemAct()
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
                        val intent = Intent(this@AddProblemActivity, MainActivity::class.java)
                        startActivity(intent) }
                }
            }
        }
    }
}