package com.amati.amatiappuser.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat.finishAffinity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.amati.amatiappuser.R
import com.amati.amatiappuser.database.UserPreferencesDatastore
import com.amati.amatiappuser.databinding.FragmentProfilBinding
import com.amati.amatiappuser.network.response.RequestProfil
import com.amati.amatiappuser.viewmodel.ProfilViewModel
import com.amati.amatiappuser.viewmodel.Session
import com.amati.amatiappuser.viewmodel.SessionModelFactory


class ProfilFragment : Fragment() {
    private lateinit var binding: FragmentProfilBinding
    private val profilViewModel: ProfilViewModel by viewModels()
    private var nama : String = ""
    private var telepon : String = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfilBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val datastore: DataStore<Preferences> = requireContext().dataStore

        //val pref = requireContext().dataStore
        //val session = ViewModelProvider(this, SessionModelFactory(pref))[Session::class.java]

        //

        //belom
        binding.editName.setText(nama)
        binding.editTelepon.setText(telepon)

        profilAct()
    }

    private fun profilAct() {
        binding.btnEditProfile.setOnClickListener {
            binding.apply {
                editName.clearFocus()
                editTelepon.clearFocus()

                val nama = editName.text.toString()
                val telepon = editTelepon.text.toString()

                val requestProfil = RequestProfil(nama, telepon)
                profilViewModel.edit(requestProfil)

                profilViewModel.dataUser.observe(viewLifecycleOwner, Observer {
                    Toast.makeText(
                        requireActivity(),
                        getString(R.string.edit_success),
                        Toast.LENGTH_SHORT
                        ).show()
                })
            }
        }
    }

    /*private fun logout() {
        val pref = requireContext().dataStore
        val session = ViewModelProvider(this, SessionModelFactory(pref))[Session::class.java]

        session.logout()

        val intent = Intent(activity, LoginActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }*/
}