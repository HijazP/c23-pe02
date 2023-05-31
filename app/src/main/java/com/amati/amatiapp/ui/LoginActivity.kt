package com.amati.amatiapp.ui

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import com.amati.amatiapp.R
import com.amati.amatiapp.databinding.ActivityLoginBinding
import com.amati.amatiapp.response.RequestLogin
import com.amati.amatiapp.viewmodel.LoginViewModel
import androidx.lifecycle.ViewModelProvider
import com.amati.amatiapp.network.response.LoginResponse
import com.amati.amatiapp.viewmodel.ViewModelFactory

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModelFactory = ViewModelFactory()
        loginViewModel= ViewModelProvider(this, viewModelFactory)[LoginViewModel::class.java]

        setupView()

        loginAct()

        loginViewModel.dataUser.observe(this) {
            if (it != null) {
                try {
                    inputSession(it, it.data.token)
                } catch (e: Exception) {
                    e.printStackTrace()
                    Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, getString(R.string.login_failed), Toast.LENGTH_SHORT).show()
            }
        }

        loginViewModel.getToken().observe(this){ token ->
            if (token != null) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, getString(R.string.go_login), Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun inputSession(response: LoginResponse, token: String) {
        when(response.code){
            400 -> {
                Toast.makeText(this, getString(R.string.login_failed), Toast.LENGTH_SHORT).show()
                binding.apply {
                    edLoginEmail.setText("")
                    edLoginPassword.setText("")
                }
            }
            401 -> {
                Toast.makeText(this, getString(R.string.unauthorized), Toast.LENGTH_SHORT).show()
            }
            404 -> {
                Toast.makeText(this, getString(R.string.data_not_found), Toast.LENGTH_SHORT).show()
            }
            500 ->{
                Toast.makeText(this, getString(R.string.server_error), Toast.LENGTH_SHORT).show()
            }
            200 -> {
                loginViewModel.setSession(response.data.user.name, response.data.user.id ,token)
                Toast.makeText(this, getString(R.string.login_success), Toast.LENGTH_SHORT).show()
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