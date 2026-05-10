package com.example.farrel_party.More // Sesuaikan dengan package kamu!

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SimpleAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.farrel_party.databinding.FragmentMoreBinding // Sesuaikan binding

class MoreFragment : Fragment() {

    private var _binding: FragmentMoreBinding? = null
    private val binding get() = _binding!!

    // Data List menggunakan Map untuk Title dan Deskripsi (Sesuai Modul)
    private val dataListWithDesc = listOf(
        mapOf("title" to "Profil Akun", "desc" to "Atur informasi data diri"),
        mapOf("title" to "Privasi & Keamanan", "desc" to "Ubah password dan autentikasi"),
        mapOf("title" to "Notifikasi", "desc" to "Atur pemberitahuan aplikasi"),
        mapOf("title" to "Bantuan & Dukungan", "desc" to "Pusat bantuan Bina Desa"),
        mapOf("title" to "Syarat dan Ketentuan", "desc" to "Kebijakan penggunaan aplikasi"),
        mapOf("title" to "Tentang Aplikasi", "desc" to "Versi aplikasi v1.0.0")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. Setup Toolbar
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            title = "Pengaturan & Lainnya"
        }

        // 2. Terapkan SimpleAdapter
        val adapter = SimpleAdapter(
            requireContext(),
            dataListWithDesc,
            android.R.layout.simple_list_item_2, // Bawaan Android
            arrayOf("title", "desc"),
            intArrayOf(android.R.id.text1, android.R.id.text2)
        )

        binding.listViewItems.adapter = adapter

        // 3. Aksi saat item ListView diklik
        binding.listViewItems.setOnItemClickListener { _, _, position, _ ->
            val selectedItem = dataListWithDesc[position]
            val title = selectedItem["title"]
            Toast.makeText(requireContext(), "Membuka menu: $title", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}