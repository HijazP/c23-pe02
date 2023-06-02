package com.amati.amatiapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amati.amatiapp.data.DataDummy
import com.amati.amatiapp.databinding.ItemRowBinding
import com.amati.amatiapp.ui.DetailActivity

class ProblemAdapter (private val listProblem: List<DataDummy>): RecyclerView.Adapter<ProblemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = listProblem.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = listProblem[position]
        holder.bind(data)
    }

    class ViewHolder(private var binding: ItemRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: DataDummy) {
            with(binding) {
                tvName.text = data.name
                tvProblem.text = data.description
            }

            binding.root.setOnClickListener {
                val intentToDetail = Intent(itemView.context, DetailActivity::class.java)
                intentToDetail.putExtra(DetailActivity.EXTRA_ITEM, data.id)
                itemView.context.startActivity(intentToDetail)
            }
        }
    }
}