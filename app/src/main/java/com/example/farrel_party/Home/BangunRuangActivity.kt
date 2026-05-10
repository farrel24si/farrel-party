package com.example.farrel_party.Home

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.farrel_party.databinding.ActivityBangunRuangBinding // Menggunakan binding yang baru

class BangunRuangActivity : AppCompatActivity() {

    // Menggunakan ActivityBangunRuangBinding agar sesuai dengan nama file XML (activity_bangun_ruang.xml)
    private lateinit var binding: ActivityBangunRuangBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inisialisasi ViewBinding
        binding = ActivityBangunRuangBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Logika saat tombol Hitung Luas Segitiga diklik
        binding.btnHitungSegitiga.setOnClickListener {
            val alasStr = binding.etAlasSegitiga.text.toString()
            val tinggiStr = binding.etTinggiSegitiga.text.toString()

            if (alasStr.isEmpty() || tinggiStr.isEmpty()) {
                Toast.makeText(this, "Alas dan Tinggi tidak boleh kosong!", Toast.LENGTH_SHORT).show()
            } else {
                val alas = alasStr.toDouble()
                val tinggi = tinggiStr.toDouble()
                val luas = 0.5 * alas * tinggi
                binding.tvHasilSegitiga.text = "Hasil Luas: $luas"
            }
        }

        // Logika saat tombol Hitung Volume Balok diklik
        binding.btnHitungBalok.setOnClickListener {
            val panjangStr = binding.etPanjangBalok.text.toString()
            val lebarStr = binding.etLebarBalok.text.toString()
            val tinggiStr = binding.etTinggiBalok.text.toString()

            if (panjangStr.isEmpty() || lebarStr.isEmpty() || tinggiStr.isEmpty()) {
                Toast.makeText(this, "Panjang, Lebar, dan Tinggi tidak boleh kosong!", Toast.LENGTH_SHORT).show()
            } else {
                val panjang = panjangStr.toDouble()
                val lebar = lebarStr.toDouble()
                val tinggi = tinggiStr.toDouble()
                val volume = panjang * lebar * tinggi
                binding.tvHasilBalok.text = "Hasil Volume: $volume"
            }
        }

        // CATATAN: Logika tombol Logout, Pertemuan 6, Pertemuan 9, dan Settings
        // sudah kita hapus dari sini karena sekarang navigasi utama ada di HomeFragment.
    }
}