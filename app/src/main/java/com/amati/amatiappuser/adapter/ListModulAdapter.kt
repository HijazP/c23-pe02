package com.amati.amatiappuser.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amati.amatiappuser.databinding.ItemListModulBinding
import com.amati.amatiappuser.network.response.AllModulItem
import com.amati.amatiappuser.ui.ModulActivity

class ListModulAdapter (private val listModul: List<AllModulItem>, private val idKursus: Int): RecyclerView.Adapter<ListModulAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemListModulBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = listModul.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = listModul[position]
        holder.bind(data, idKursus)
    }

    class ViewHolder(private var binding: ItemListModulBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: AllModulItem, idKursus: Int) {
            with(binding) {
                tvListModul.text = data.namaModul

                root.setOnClickListener {
                    val intentToDetail = Intent(itemView.context, ModulActivity::class.java)
                    intentToDetail.putExtra(ModulActivity.EXTRA_ITEM, idKursus)
                    intentToDetail.putExtra(ModulActivity.EXTRA_ID_MODUL, data.id)
                    itemView.context.startActivity(intentToDetail)
                    (itemView.context as? Activity)?.finish()
                }
            }
        }
    }
}