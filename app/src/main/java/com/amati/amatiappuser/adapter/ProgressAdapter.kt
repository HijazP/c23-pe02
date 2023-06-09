package com.amati.amatiappuser.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amati.amatiappuser.R
import com.amati.amatiappuser.data.Dummy
import com.amati.amatiappuser.databinding.RvprogressItemBinding
import com.amati.amatiappuser.network.response.AmbilKursus
import com.amati.amatiappuser.network.response.Kursus
import com.amati.amatiappuser.network.response.KursusItem
import com.amati.amatiappuser.network.response.Modul
import com.amati.amatiappuser.ui.ModulActivity

class ProgressAdapter(private val listKursus: List<Kursus>, private val listProgress: List<Modul>)
    : RecyclerView.Adapter<ProgressAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RvprogressItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = listKursus.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = listKursus[position]
        holder.bind(data, listProgress[position])
    }

    class ViewHolder(private var binding: RvprogressItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data1: Kursus, data2: Modul) {
            with(binding) {
//                Glide.with(itemView.context)
//                    .load(data.photoUrl)
//                    .apply(RequestOptions().centerCrop())
//                    .into(imgAvatar)
                bgProgress.setImageResource(R.drawable.progress_item_besar)
                tvName.text = data1.namaKursus
                tvDesc.text = data1.deskripsi
            }

            binding.root.setOnClickListener {
                val intentToDetail = Intent(itemView.context, ModulActivity::class.java)
                intentToDetail.putExtra(ModulActivity.EXTRA_ITEM, data2.id)
                intentToDetail.putExtra(ModulActivity.EXTRA_NAMA, data1.namaKursus)
//                intentToDetail.putExtra(ModulActivity.EXTRA_MODUL, data2.progress)
                itemView.context.startActivity(intentToDetail)
            }
        }
    }
}