package com.amati.amatiapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.amati.amatiapp.R
import com.amati.amatiapp.databinding.ActivityLoginBinding
import com.amati.amatiapp.network.RequestLogin
import com.amati.amatiapp.viewmodel.LoginViewModel
import androidx.activity.viewModels

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loginAct()
    }

    private fun loginAct() {
        binding.btnLogin.setOnClickListener {
            val email = binding.edLoginEmail.text.toString().trim()
            val password = binding.edLoginPassword.text.toString().trim()

            try {
                when {
                    email.isEmpty() -> {
                        binding.edLoginEmail.error = getString(R.string.email_required)
                    }
                    !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                        binding.edLoginEmail.error = getString(R.string.invalid_email)
                    }
                    password.isEmpty() -> {
                        binding.edLoginEmail.error = getString(R.string.password_required)
                    }
                    password.length < 6 -> {
                        binding.edLoginPassword.error = getString(R.string.password_min)
                    }
                    else -> {
                        if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                            val requestLogin = RequestLogin(email, password)
                            loginViewModel.login(this, requestLogin)
//                            Toast.makeText(this, getString(R.string.login_success), Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this, getString(R.string.unauthorized), Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            } catch (e: Exception) {
                Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
            }
        }

        binding.edRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}