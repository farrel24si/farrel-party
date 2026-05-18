package com.example.farrel_party.Home.pertemuan_10

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.farrel_party.databinding.ItemFdprBinding

class FdprAdapter(
    private var listData: List<FdprModel>, // Ubah 'val' menjadi 'var' agar datanya bisa diganti
    private val onItemClick: (FdprModel) -> Unit
) : RecyclerView.Adapter<FdprAdapter.FdprViewHolder>() {

    inner class FdprViewHolder(val binding: ItemFdprBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FdprViewHolder {
        val binding = ItemFdprBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FdprViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FdprViewHolder, position: Int) {
        val item = listData[position]
        with(holder.binding) {
            tvTitle.text = item.title
            tvSubtitle.text = item.subtitle

            Glide.with(holder.itemView.context)
                .load(item.imageUrl)
                .centerCrop()
                .into(imgFoto)

            root.setOnClickListener {
                onItemClick(item)
            }
        }
    }

    override fun getItemCount(): Int = listData.size

    // FUNGSI BARU: Untuk memperbarui list saat mencari data
    fun updateData(newList: List<FdprModel>) {
        listData = newList
        notifyDataSetChanged() // Refresh tampilan RecyclerView
    }
}