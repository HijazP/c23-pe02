package com.amati.amatiappuser.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amati.amatiappuser.databinding.RvprogressItemBinding
import com.amati.amatiappuser.network.response.*
import com.amati.amatiappuser.ui.ModulActivity
class ProgressAdapter(private val listProgress: List<ProgressItem>, private val listDetail: List<KursusItem>)
    : RecyclerView.Adapter<ProgressAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RvprogressItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listProgress.count { !it.statusSelesai }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val progressItem = listProgress[position]
        if (!progressItem.statusSelesai) {
            holder.bind(progressItem, listDetail)
        }
    }

    class ViewHolder(private val binding: RvprogressItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(progressItem: ProgressItem, listDetail: List<KursusItem>) {
            val detailCourse = listDetail.find { it.id == progressItem.idKursus }
            detailCourse?.let {
                binding.tvName.text = it.namaKursus
                binding.tvDesc.text = it.dampak
            }
            binding.root.setOnClickListener {
                val intentToDetail = Intent(itemView.context, ModulActivity::class.java)
                intentToDetail.putExtra(ModulActivity.EXTRA_ITEM, progressItem.id)
                val kursusItem = listDetail.find { it.id == progressItem.idKursus }
                intentToDetail.putExtra(ModulActivity.EXTRA_NAMA, kursusItem?.namaKursus)
                itemView.context.startActivity(intentToDetail)
            }
        }
    }
}

//        fun bind(data : ProgressItem, viewModel: HomeViewModel, token: String?) {
//            viewModel.getDetailCourse("Bearer $token", data.idKursus)
//            viewModel.getCourse("Bearer $token", data.idKursus)
//            with(binding) {
//                viewModel.dataDetailCourse.observe(itemView.context as LifecycleOwner) { kursus ->
//                    if (data.idKursus == kursus?.id) {
//                        tvName.text = kursus.namaKursus
//                        tvDesc.text = kursus.dampak
//                    }
//                }
//            }
//
//            binding.root.setOnClickListener {
//                val intentToDetail = Intent(itemView.context, ModulActivity::class.java)
//                intentToDetail.putExtra(ModulActivity.EXTRA_ITEM, data.idKursus)
//                intentToDetail.putExtra(ModulActivity.EXTRA_NAMA, binding.tvName.text)
//                itemView.context.startActivity(intentToDetail)
//            }
//        }
