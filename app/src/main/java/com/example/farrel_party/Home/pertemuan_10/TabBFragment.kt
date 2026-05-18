package com.example.farrel_party.Home.pertemuan_10

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.farrel_party.databinding.FragmentTabBBinding

class TabBFragment : Fragment() {
    private var _binding: FragmentTabBBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: FdprAdapter
    private var dataPetugas = listOf<FdprModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentTabBBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. Isi 10 Master Data Petugas
        dataPetugas = listOf(
            FdprModel("Bapak Herman", "Ketua RT 01", "https://randomuser.me/api/portraits/men/78.jpg"),
            FdprModel("Mang Ucup", "Kepala Keamanan", "https://randomuser.me/api/portraits/men/45.jpg"),
            FdprModel("Mbak Ayu", "Admin Kebersihan", "https://randomuser.me/api/portraits/women/68.jpg"),
            FdprModel("Pak Slamet", "Teknisi Listrik", "https://randomuser.me/api/portraits/men/33.jpg"),
            FdprModel("Mas Bayu", "Admin Peminjaman", "https://randomuser.me/api/portraits/men/29.jpg"),
            FdprModel("Bu Ratna", "Bendahara", "https://randomuser.me/api/portraits/women/55.jpg"),
            FdprModel("Kang Asep", "Petugas Taman", "https://randomuser.me/api/portraits/men/81.jpg"),
            FdprModel("Bang Jali", "Supir Fasilitas", "https://randomuser.me/api/portraits/men/90.jpg"),
            FdprModel("Nita", "Customer Service", "https://randomuser.me/api/portraits/women/15.jpg"),
            FdprModel("Pak Sugeng", "Koordinator Lapangan", "https://randomuser.me/api/portraits/men/65.jpg")
        )

        // 2. Setup Adapter
        adapter = FdprAdapter(dataPetugas) { item ->
            Toast.makeText(requireContext(), "Kontak Petugas: ${item.title}", Toast.LENGTH_SHORT).show()
        }
        binding.rvFdpr.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvFdpr.adapter = adapter

        // 3. Logika Pencarian (Filter)
        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                val kataKunci = s.toString().lowercase()
                val dataTersaring = dataPetugas.filter {
                    it.title.lowercase().contains(kataKunci) ||
                            it.subtitle.lowercase().contains(kataKunci)
                }
                adapter.updateData(dataTersaring)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}