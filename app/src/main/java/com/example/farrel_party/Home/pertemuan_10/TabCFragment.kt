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
import com.example.farrel_party.databinding.FragmentTabCBinding

class TabCFragment : Fragment() {
    private var _binding: FragmentTabCBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: FdprAdapter
    private var dataFasilitas = listOf<FdprModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentTabCBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. Isi 10 Master Data Fasilitas
        dataFasilitas = listOf(
            FdprModel("Gedung Serbaguna", "Rp 500.000 / Hari", "https://loremflickr.com/400/300/hall,building"),
            FdprModel("Lapangan Olahraga", "Rp 50.000 / Jam", "https://loremflickr.com/400/300/stadium,field"),
            FdprModel("Tenda & Kursi", "Rp 150.000 / Set", "https://loremflickr.com/400/300/tent,event"),
            FdprModel("Sound System", "Rp 200.000 / Acara", "https://loremflickr.com/400/300/speaker,audio"),
            FdprModel("Proyektor & Layar", "Rp 100.000 / Acara", "https://loremflickr.com/400/300/projector,screen"),
            FdprModel("Panggung Utama", "Rp 300.000 / Hari", "https://loremflickr.com/400/300/stage,concert"),
            FdprModel("Genset 5000 Watt", "Rp 250.000 / Hari", "https://loremflickr.com/400/300/generator,machine"),
            FdprModel("Kipas Blower", "Rp 50.000 / Unit", "https://loremflickr.com/400/300/fan,cooler"),
            FdprModel("Meja Prasmanan", "Rp 75.000 / Set", "https://loremflickr.com/400/300/table,buffet"),
            FdprModel("Kursi Lipat Ekstra", "Rp 2.000 / Pcs", "https://loremflickr.com/400/300/chair,folding")
        )

        // 2. Setup Adapter
        adapter = FdprAdapter(dataFasilitas) { item ->
            Toast.makeText(requireContext(), "Booking: ${item.title}", Toast.LENGTH_SHORT).show()
        }
        binding.rvFdpr.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvFdpr.adapter = adapter

        // 3. Logika Pencarian (Filter)
        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                val kataKunci = s.toString().lowercase()
                val dataTersaring = dataFasilitas.filter {
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