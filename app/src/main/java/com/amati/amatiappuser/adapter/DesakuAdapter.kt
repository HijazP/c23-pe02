package com.amati.amatiappuser.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amati.amatiappuser.R
import com.amati.amatiappuser.databinding.DesakuItemBinding
import com.amati.amatiappuser.network.response.MasalahdesaItem
import com.amati.amatiappuser.ui.DetailProblemActivity

class DesakuAdapter (private val listDesaku: List<MasalahdesaItem>, private val namaDesa: String): RecyclerView.Adapter<DesakuAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DesakuItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }
    override fun getItemCount(): Int = listDesaku.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = listDesaku[position]
        holder.bind(data, namaDesa)
    }

    class ViewHolder(private var binding: DesakuItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(data: MasalahdesaItem, namaDesa: String){
            with(binding){
                imgDesa.setImageResource(R.drawable.coba)
                tvName.text = data.namaMasalah
                tvProblem.text = data.deskripsi

                root.setOnClickListener{
                    val intentToDetail = Intent(itemView.context, DetailProblemActivity::class.java)
                    intentToDetail.putExtra(DetailProblemActivity.EXTRA_ID, data.id)
                    intentToDetail.putExtra(DetailProblemActivity.EXTRA_NAME, data.namaMasalah)
                    intentToDetail.putExtra(DetailProblemActivity.EXTRA_ID_DESA, namaDesa)
                    itemView.context.startActivity(intentToDetail)
                }
            }
        }
    }
}