package com.amati.amatiapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amati.amatiapp.databinding.ModulItemBinding

class ModulAdapter (private val listModul: List<ListModul>): RecyclerView.Adapter<ModulAdapter.ViewHolder>() {

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
        fun bind(data: ListModul) {
            with(binding) {
//                Glide.with(itemView.context)
//                    .load(data.photoUrl)
//                    .apply(RequestOptions().centerCrop())
//                    .into(imgAvatar)
//                tvName.text = data.name
//                tvName2.text = data.name
//                tvDesc.text = data.description
            }

            binding.root.setOnClickListener {
//                val intentToDetail = Intent(itemView.context, DetailActivity::class.java)
//                intentToDetail.putExtra(DetailActivity.EXTRA_STORY, data.id)
//                itemView.context.startActivity(intentToDetail)
            }
        }
    }
}