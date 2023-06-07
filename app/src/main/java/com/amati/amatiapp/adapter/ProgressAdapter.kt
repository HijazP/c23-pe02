package com.amati.amatiapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amati.amatiapp.R
import com.amati.amatiapp.data.Dummy
import com.amati.amatiapp.databinding.RvprogressItemBinding
import com.amati.amatiapp.ui.ModulActivity

class ProgressAdapter(private val listProgress: List<Dummy>): RecyclerView.Adapter<ProgressAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RvprogressItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = listProgress.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = listProgress[position]
        holder.bind(data)
    }

    class ViewHolder(private var binding: RvprogressItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Dummy) {
            with(binding) {
//                Glide.with(itemView.context)
//                    .load(data.photoUrl)
//                    .apply(RequestOptions().centerCrop())
//                    .into(imgAvatar)
                bgProgress.setImageResource(R.drawable.progress_item_besar)
                tvName.text = data.kursus
                tvDesc.text = data.desc
            }

            binding.root.setOnClickListener {
                val intentToDetail = Intent(itemView.context, ModulActivity::class.java)
                intentToDetail.putExtra(ModulActivity.EXTRA_ITEM, data.id)
                itemView.context.startActivity(intentToDetail)
            }
        }
    }
}