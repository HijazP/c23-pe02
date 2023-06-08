package com.amati.amatiapp.ui

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Patterns
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.amati.amatiapp.R
import com.amati.amatiapp.database.UserPreferencesDatastore
import com.amati.amatiapp.databinding.ActivityRegisterBinding
import com.amati.amatiapp.response.RequestReg
import com.amati.amatiapp.viewmodel.RegisterViewModel
import com.amati.amatiapp.viewmodel.Session
import com.amati.amatiapp.viewmodel.SessionModelFactory

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private val registerViewModel: RegisterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        registerAct()

        registerViewModel.code.observe(this) {
            if (registerViewModel.code.value == 201) {
                Toast.makeText(
                    this,
                    getString(R.string.register_success),
                    Toast.LENGTH_SHORT
                ).show()

                Handler().postDelayed({
                    val moveIntent =
                        Intent(this, LoginActivity::class.java)
                    startActivity(moveIntent)
                    finish()
                }, 1000)
            }
            else if (registerViewModel.code.value == 401) {
                Toast.makeText(
                    this,
                    "Email Sudah Dipakai",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }


        binding.btnRegisterLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun registerAct() {
        binding.btnRegister.setOnClickListener {
            binding.apply {
                edRegisterEmail.clearFocus()
                edRegisterVillageName.clearFocus()
                edRegisterPhoneNumber.clearFocus()
                edRegisterRepassword.clearFocus()
                edRegisterPassword.clearFocus()

                val email = edRegisterEmail.text.toString()
                val namaDesa = edRegisterVillageName.text.toString()
                val noTelp = edRegisterPhoneNumber.text.toString()
                val password = edRegisterPassword.text.toString()
                val repassword = edRegisterRepassword.text.toString()

                when {
                    email.isEmpty() -> {
                        edRegisterEmail.error = getString(R.string.email_required)
                    }
                    !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                        edRegisterEmail.error = getString(R.string.invalid_email)
                    }
                    namaDesa.isEmpty() -> {
                        edRegisterVillageName.error = getString(R.string.register_village_name_required)
                    }
                    noTelp.isEmpty() -> {
                        edRegisterPhoneNumber.error = getString(R.string.register_village_phone_number_required)
                    }
                    password.isEmpty() -> {
                        edRegisterPassword.error = getString(R.string.password_required)
                    }
                    repassword.isEmpty() -> {
                        edRegisterRepassword.error = getString(R.string.repassword_required)
                    }
                    else -> {
                        if (password == repassword){
                            val requestReg = RequestReg(email, password, namaDesa, noTelp)
                            registerViewModel.register(requestReg)
                        }
                        else {
                            Toast.makeText(
                                this@RegisterActivity,
                                getString(R.string.password_not_match),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        }
    }

    private fun setupView() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }
}