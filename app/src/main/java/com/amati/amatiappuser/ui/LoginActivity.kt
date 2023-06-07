package com.amati.amatiappuser.ui

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.amati.amatiappuser.R
import com.amati.amatiappuser.databinding.ActivityLoginBinding
import com.amati.amatiappuser.viewmodel.LoginViewModel
import androidx.lifecycle.ViewModelProvider
import com.amati.amatiappuser.database.UserPreferencesDatastore
import com.amati.amatiappuser.network.response.RequestLogin
import com.amati.amatiappuser.viewmodel.Session
import com.amati.amatiappuser.viewmodel.SessionModelFactory

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        loginAct()

        val pref = UserPreferencesDatastore.getInstance(dataStore)
        val session = ViewModelProvider(this, SessionModelFactory(pref))[Session::class.java]

        loginViewModel.code.observe(this) {
            val user = loginViewModel.dataUser.value
            if (user != null) {
                try {
                    inputSession(it, user.token, session)
                } catch (e: Exception) {
                    e.printStackTrace()
                    Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
            }
        }

        session.getToken().observe(this){ token ->
            if (token != "") {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, getString(R.string.go_login), Toast.LENGTH_SHORT).show()
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
                            loginViewModel.login(requestLogin)
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

    private fun inputSession(code : Int, token: String, session: Session) {
        when(code){
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
                val response = loginViewModel.dataUser.value
                if (response?.data  != null){
                    session.setSession(response.data.nama, response.data.id ,token)
                    Toast.makeText(this, getString(R.string.login_success), Toast.LENGTH_SHORT).show()
                } else{
                    Toast.makeText(this, "I dont have any idea", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}