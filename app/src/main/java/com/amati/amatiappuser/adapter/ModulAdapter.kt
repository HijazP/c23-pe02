package com.amati.amatiappuser.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.amati.amatiappuser.R
import com.amati.amatiappuser.databinding.ModulItemBinding
import com.amati.amatiappuser.network.response.KursusItem
import com.amati.amatiappuser.ui.ModulActivity
import com.amati.amatiappuser.viewmodel.HomeViewModel

class ModulAdapter (private val listModul: List<KursusItem>, private val viewModel: HomeViewModel, private val token: String?): RecyclerView.Adapter<ModulAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ModulItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = listModul.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = listModul[position]
        holder.bind(data, viewModel, token)
    }

    class ViewHolder(private var binding: ModulItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: KursusItem, viewModel: HomeViewModel, token: String?) {
            viewModel.getAllProgress("Bearer $token")
            with(binding) {
                bgModul.setImageResource(R.drawable.modul)
                tvName.text = data.namaKursus
                tvDesc.text = data.dampak
                root.setOnClickListener {
                    val intentToDetail = Intent(itemView.context, ModulActivity::class.java)
                    viewModel.dataAllProgress.observe(itemView.context as LifecycleOwner) { progress ->
                        if (progress != null) {
                            val progressItem = progress.find { it.idKursus == data.id }
                            if (progressItem != null) {
                                intentToDetail.putExtra(ModulActivity.EXTRA_ID_MODUL, progressItem.modulSekarang)
                            }
                        }
                    }
                    intentToDetail.putExtra(ModulActivity.EXTRA_ITEM, data.id)
                    intentToDetail.putExtra(ModulActivity.EXTRA_NAMA, data.namaKursus)
                    itemView.context.startActivity(intentToDetail)
                }
            }
        }
    }
}