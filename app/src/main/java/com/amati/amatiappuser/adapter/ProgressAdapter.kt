package com.amati.amatiappuser.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.amati.amatiappuser.R
import com.amati.amatiappuser.databinding.RvprogressItemBinding
import com.amati.amatiappuser.network.response.*
import com.amati.amatiappuser.ui.ModulActivity
import com.amati.amatiappuser.viewmodel.HomeViewModel

class ProgressAdapter(private val listProgress: List<ProgressItem>, private val viewModel: HomeViewModel, private val token: String?)
    : RecyclerView.Adapter<ProgressAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RvprogressItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = listProgress.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = listProgress[position]
        holder.bind(data, viewModel, token)
    }

    class ViewHolder(private var binding: RvprogressItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data : ProgressItem, viewModel: HomeViewModel, token: String?) {
            viewModel.getDetailCourse("Bearer $token", data.idKursus)
            viewModel.getCourse("Bearer $token", data.idKursus)
            val modul = viewModel.dataModul.value
            with(binding) {
//                Glide.with(itemView.context)
//                    .load(data.photoUrl)
//                    .apply(RequestOptions().centerCrop())
//                    .into(imgAvatar)

                viewModel.dataDetailCourse.observe(itemView.context as LifecycleOwner) { kursus ->
                    if (data.idKursus == kursus?.id) {
                        tvName.text = kursus.namaKursus
                        tvDesc.text = kursus.dampak
                    }
                }
            }

            binding.root.setOnClickListener {
                val intentToDetail = Intent(itemView.context, ModulActivity::class.java)
                intentToDetail.putExtra(ModulActivity.EXTRA_ITEM, data.idKursus)
                intentToDetail.putExtra(ModulActivity.EXTRA_NAMA, binding.tvName.text)
                itemView.context.startActivity(intentToDetail)
            }
        }
    }
}