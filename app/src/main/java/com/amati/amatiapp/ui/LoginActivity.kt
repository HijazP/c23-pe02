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
import com.amati.amatiapp.network.RequestLogin
import com.amati.amatiapp.viewmodel.LoginViewModel
import androidx.activity.viewModels
import android.widget.Button;
import com.amati.amatiapp.network.response.Response
import com.amati.amatiapp.network.retrofit.ApiService
import com.amati.amatiapp.network.retrofit.Retro
import retrofit2.Callback

import androidx.lifecycle.ViewModelProvider
import com.amati.amatiapp.viewmodel.ViewModelFactory

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val viewModelFactory = ViewModelFactory()
        loginViewModel= ViewModelProvider(this, viewModelFactory).get(LoginViewModel::class.java)


        setupView()

        loginAct()
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
            val request = RequestLogin()
            val email = binding.edLoginEmail.text.toString().trim()
            val password = binding.edLoginPassword.text.toString().trim()

            val retro = Retro().getRetroClientInstance().create(ApiService::class.java)
            retro.login(request)

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