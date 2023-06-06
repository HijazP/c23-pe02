package com.amati.amatiapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.amati.amatiapp.R
import com.amati.amatiapp.database.UserPreferencesDatastore
import com.amati.amatiapp.databinding.ActivityProfilBinding
import com.amati.amatiapp.response.RequestProfil
import com.amati.amatiapp.viewmodel.ProfilViewModel
import com.amati.amatiapp.viewmodel.Session
import com.amati.amatiapp.viewmodel.SessionModelFactory

class ProfilActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfilBinding
    private val profilViewModel: ProfilViewModel by viewModels()

    //companion object {
        ///const val EXTRA_USERNAME = "extra_username"
        //const val EXTRA_TELEPON = "extra_telepon"
        // val EXTRA_LOKASI = "extra_lokasi"
    //}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfilBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Profil"

        profilAct()

        binding.btnLogout.setOnClickListener {
            showLogoutDialog()
        }
    }

    private fun profilAct() {
        binding.btnEditProfile.setOnClickListener {
            binding.apply {
                editName.clearFocus()
                editTelepon.clearFocus()
                editLokasi.clearFocus()

                val nama = editName.text.toString()
                val telepon = editTelepon.text.toString()
                val lokasi = editLokasi.text.toString()

                val requestProfil = RequestProfil(nama, telepon, lokasi)
                profilViewModel.edit(requestProfil)

                profilViewModel.dataUser.observe(this@ProfilActivity) {
                    Toast.makeText(
                        this@ProfilActivity,
                        getString(R.string.edit_success),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun showLogoutDialog() {
        val builder = AlertDialog.Builder(this)
        builder
            .setTitle(getString(R.string.logout))
            .setMessage(getString(R.string.alert_logout))
            .setPositiveButton(getString(R.string.yes)) { _, _ ->
                logout()
            }
            .setNegativeButton(getString(R.string.cancel), null)
            .show()
    }

    private fun logout() {
        val pref = UserPreferencesDatastore.getInstance(dataStore)
        val session = ViewModelProvider(this, SessionModelFactory(pref))[Session::class.java]

        session.logout()

        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finishAffinity()
    }
}