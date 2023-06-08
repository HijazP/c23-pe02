package com.amati.amatiappuser.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.amati.amatiappuser.R
import com.amati.amatiappuser.databinding.FragmentDesakuBinding
import com.amati.amatiappuser.databinding.FragmentProfilBinding
import com.amati.amatiappuser.viewmodel.DesakuViewModel
import com.amati.amatiappuser.viewmodel.ProfilViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class DesakuFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var binding: FragmentDesakuBinding
    private val DesakuViewModel: DesakuViewModel by viewModels()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDesakuBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        val inflater = menuInflater
//        inflater.inflate(R.menu.item_menu, menu)
//        return true
//    }
}