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
import com.example.farrel_party.databinding.FragmentTabABinding

class TabAFragment : Fragment() {
    private var _binding: FragmentTabABinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: FdprAdapter
    private var dataWarga = listOf<FdprModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentTabABinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. Isi 10 Master Data Warga
        dataWarga = listOf(
            FdprModel("Bapak Budi Santoso", "Blok A No. 1", "https://randomuser.me/api/portraits/men/32.jpg"),
            FdprModel("Ibu Siti Aminah", "Blok B No. 4", "https://randomuser.me/api/portraits/women/44.jpg"),
            FdprModel("Rafif Zidane", "Blok C No. 7", "https://randomuser.me/api/portraits/men/57.jpg"),
            FdprModel("Mas Joko", "Blok D No. 2", "https://randomuser.me/api/portraits/men/22.jpg"),
            FdprModel("Kang Dadang", "Blok A No. 5", "https://randomuser.me/api/portraits/men/15.jpg"),
            FdprModel("Mbak Rini", "Blok B No. 12", "https://randomuser.me/api/portraits/women/12.jpg"),
            FdprModel("Pak Yanto", "Blok C No. 3", "https://randomuser.me/api/portraits/men/60.jpg"),
            FdprModel("Bu Tejo", "Blok D No. 9", "https://randomuser.me/api/portraits/women/68.jpg"),
            FdprModel("Bang Ali", "Blok E No. 1", "https://randomuser.me/api/portraits/men/40.jpg"),
            FdprModel("Neng Lilis", "Blok E No. 4", "https://randomuser.me/api/portraits/women/25.jpg")
        )

        // 2. Setup Adapter
        adapter = FdprAdapter(dataWarga) { item ->
            Toast.makeText(requireContext(), "Profil Warga: ${item.title}", Toast.LENGTH_SHORT).show()
        }
        binding.rvFdpr.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvFdpr.adapter = adapter

        // 3. Logika Pencarian (Filter)
        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                val kataKunci = s.toString().lowercase()
                val dataTersaring = dataWarga.filter {
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