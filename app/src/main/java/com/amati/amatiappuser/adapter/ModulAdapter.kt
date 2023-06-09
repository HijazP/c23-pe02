package com.amati.amatiappuser.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amati.amatiappuser.R
import com.amati.amatiappuser.data.Dummy
import com.amati.amatiappuser.databinding.ModulItemBinding
import com.amati.amatiappuser.network.response.KursusItem
import com.amati.amatiappuser.ui.ModulActivity

class ModulAdapter (private val listModul: List<KursusItem>): RecyclerView.Adapter<ModulAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ModulItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = listModul.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = listModul[position]
        holder.bind(data)
    }

    class ViewHolder(private var binding: ModulItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: KursusItem) {
            with(binding) {
//                Glide.with(itemView.context)
//                    .load(data.photoUrl)
//                    .apply(RequestOptions().centerCrop())
//                    .into(imgAvatar)
                bgModul.setImageResource(R.drawable.modul)
                tvName.text = data.namaKursus
                tvDesc.text = data.dampak
            }

            binding.root.setOnClickListener {
                val intentToDetail = Intent(itemView.context, ModulActivity::class.java)
                intentToDetail.putExtra(ModulActivity.EXTRA_ITEM, data.id)
                intentToDetail.putExtra(ModulActivity.EXTRA_NAMA, data.namaKursus)
                itemView.context.startActivity(intentToDetail)
            }
        }
    }
}