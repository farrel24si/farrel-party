package com.example.farrel_party.Home.pertemuan_9

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.farrel_party.databinding.ActivityNinthBinding
import com.google.android.material.chip.Chip

class NinthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNinthBinding
    private var selectedFasilitas: String = "" // Variabel untuk menyimpan fasilitas yang dipilih

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNinthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Setup Toolbar dan Tombol Back
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }
        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        // 2. Logika Memilih Fasilitas (ChipGroup)
        binding.chipGroupFasilitas.setOnCheckedStateChangeListener { group, checkedIds ->
            val selectedChipId = checkedIds.firstOrNull()
            if (selectedChipId != null) {
                val chip = group.findViewById<Chip>(selectedChipId)
                selectedFasilitas = chip.text.toString()
                Toast.makeText(this, "Fasilitas dipilih: $selectedFasilitas", Toast.LENGTH_SHORT).show()
            } else {
                selectedFasilitas = "" // Dikosongkan jika tidak ada yang dipilih
            }
        }

        // 3. Logika Submit (MaterialButton & Validasi TextInputLayout)
        binding.btnSubmitPeminjaman.setOnClickListener {
            val namaKegiatan = binding.etNamaKegiatan.text.toString().trim()
            val tanggal = binding.etTanggal.text.toString().trim()

            // Validasi input
            if (namaKegiatan.isEmpty()) {
                Toast.makeText(this, "Nama Kegiatan tidak boleh kosong!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (selectedFasilitas.isEmpty()) {
                Toast.makeText(this, "Silakan pilih fasilitas terlebih dahulu!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Menerapkan fitur Error bawaan dari TextInputLayout
            if (tanggal.isEmpty()) {
                binding.tilTanggal.error = "Tanggal harus diisi!"
                return@setOnClickListener
            } else {
                binding.tilTanggal.error = null
            }

            // Jika semua validasi lolos
            Toast.makeText(this, "Berhasil! Mengajukan $selectedFasilitas untuk kegiatan $namaKegiatan.", Toast.LENGTH_LONG).show()

            // Tutup halaman dan kembali ke HomeFragment
            finish()
        }
    }
}