package com.amati.amatiappuser.ui

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.amati.amatiappuser.R
import com.amati.amatiappuser.database.UserPreferencesDatastore
import com.amati.amatiappuser.databinding.ActivityRegisterBinding
import com.amati.amatiappuser.network.response.RequestReg
import com.amati.amatiappuser.viewmodel.RegisterViewModel
import com.amati.amatiappuser.viewmodel.Session
import com.amati.amatiappuser.viewmodel.SessionModelFactory

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private val registerViewModel: RegisterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        registerAct()

        val pref = UserPreferencesDatastore.getInstance(dataStore)
        val session = ViewModelProvider(this, SessionModelFactory(pref))[Session::class.java]

        session.getToken().observe(this) { token ->
            if (token != "" && token != null) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        binding.btnRegisterLogin.setOnClickListener {
            val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun registerAct() {
        binding.btnRegister.setOnClickListener {
            binding.apply {
                val nama = edRegisterNama.text.toString()
                val email = edRegisterEmail.text.toString().trim()
                val password = edRegisterPassword.text.toString().trim()
                val repassword = edRegisterRepassword.text.toString().trim()

                when {
                    nama.isEmpty() -> {
                        edRegisterNama.error = getString(R.string.register_nama_required)
                    }
                    email.isEmpty() -> {
                        edRegisterEmail.error = getString(R.string.register_email_required)
                    }
                    !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                        edRegisterEmail.error = getString(R.string.invalid_email)
                    }
                    password.isEmpty() -> {
                        edRegisterPassword.error = getString(R.string.register_invalid_pass)
                    }
                    password.length < MIN_PASS_LENGTH -> {
                        edRegisterPassword.error = getString(R.string.register_invalid_pass)
                    }
                    repassword.isEmpty() -> {
                        edRegisterRepassword.error = getString(R.string.register_invalid_repass)
                    }
                    else -> {
                        if (password == repassword) {
                            val requestReg = RequestReg(nama, email, password)
                            registerViewModel.register(requestReg)

                            registerViewModel.dataUser.observe(this@RegisterActivity) {
                                Toast.makeText(
                                    this@RegisterActivity,
                                    getString(R.string.register_success),
                                    Toast.LENGTH_SHORT
                                ).show()

                                if (registerViewModel.code.value == 201) {
                                    val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                                    startActivity(intent)
                                    finish()
                                }
                            }
                        }
                        else {
                            Toast.makeText(this@RegisterActivity, getString(R.string.register_invalid_repass), Toast.LENGTH_SHORT).show()
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

    companion object {
        private const val MIN_PASS_LENGTH = 6
    }
}