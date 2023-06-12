package com.amati.amatiappuser.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amati.amatiappuser.R
import com.amati.amatiappuser.data.Dummy
import com.amati.amatiappuser.databinding.DesakuItemBinding
import com.amati.amatiappuser.databinding.ModulItemBinding
import com.amati.amatiappuser.ui.DetailProblemActivity
import com.amati.amatiappuser.ui.ModulActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class DesakuAdapter (private val listDesaku: List<Dummy>): RecyclerView.Adapter<DesakuAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DesakuAdapter.ViewHolder {
        val binding = DesakuItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }
    override fun getItemCount(): Int = listDesaku.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = listDesaku[position]
        holder.bind(data)
    }

    class ViewHolder(private var binding: DesakuItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(data: Dummy){
            with(binding){
                imgDesa.setImageResource(R.drawable.coba)
                tvName.text = data.kursus
                tvProblem.text = data.desc
            }

            binding.root.setOnClickListener{
                val intentToDetail = Intent(itemView.context, DetailProblemActivity::class.java)
                intentToDetail.putExtra(DetailProblemActivity.EXTRA_ID, data.id)
                itemView.context.startActivity(intentToDetail)
            }
        }
    }
}