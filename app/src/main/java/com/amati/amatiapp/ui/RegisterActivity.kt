package com.amati.amatiapp.ui

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import com.amati.amatiapp.R
import com.amati.amatiapp.databinding.ActivityRegisterBinding
import com.amati.amatiapp.viewmodel.RegisterViewModel

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var registerViewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()

        binding.btnRegister.setOnClickListener {
            binding.apply {
                val email = edRegisterEmail.text.toString()
                val password = edRegisterPassword.text.toString()
                val repassword = edRegisterRepassword.text.toString()

                if (email == repassword) {
                    Toast.makeText(this@RegisterActivity, "Email: $email, Password: $password", Toast.LENGTH_SHORT).show()
                }
                else {
                    Toast.makeText(this@RegisterActivity, "Please re-enter password correctly..", Toast.LENGTH_SHORT).show()
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