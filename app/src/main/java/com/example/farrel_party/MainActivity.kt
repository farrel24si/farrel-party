package com.example.farrel_party

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Mengambil referensi View untuk Segitiga
        val etAlasSegitiga = findViewById<TextInputEditText>(R.id.et_alas_segitiga)
        val etTinggiSegitiga = findViewById<TextInputEditText>(R.id.et_tinggi_segitiga)
        val btnHitungSegitiga = findViewById<Button>(R.id.btn_hitung_segitiga)
        val tvHasilSegitiga = findViewById<TextView>(R.id.tv_hasil_segitiga)

        // Mengambil referensi View untuk Balok
        val etPanjangBalok = findViewById<TextInputEditText>(R.id.et_panjang_balok)
        val etLebarBalok = findViewById<TextInputEditText>(R.id.et_lebar_balok)
        val etTinggiBalok = findViewById<TextInputEditText>(R.id.et_tinggi_balok)
        val btnHitungBalok = findViewById<Button>(R.id.btn_hitung_balok)
        val tvHasilBalok = findViewById<TextView>(R.id.tv_hasil_balok)

        // Logika saat tombol Hitung Luas Segitiga diklik
        btnHitungSegitiga.setOnClickListener {
            val alasStr = etAlasSegitiga.text.toString()
            val tinggiStr = etTinggiSegitiga.text.toString()

            if (alasStr.isEmpty() || tinggiStr.isEmpty()) {
                Toast.makeText(this, "Alas dan Tinggi tidak boleh kosong!", Toast.LENGTH_SHORT).show()
            } else {
                val alas = alasStr.toDouble()
                val tinggi = tinggiStr.toDouble()
                val luas = 0.5 * alas * tinggi
                tvHasilSegitiga.text = "Hasil Luas: $luas"
            }
        }

        // Logika saat tombol Hitung Volume Balok diklik
        btnHitungBalok.setOnClickListener {
            val panjangStr = etPanjangBalok.text.toString()
            val lebarStr = etLebarBalok.text.toString()
            val tinggiStr = etTinggiBalok.text.toString()

            if (panjangStr.isEmpty() || lebarStr.isEmpty() || tinggiStr.isEmpty()) {
                Toast.makeText(this, "Panjang, Lebar, dan Tinggi tidak boleh kosong!", Toast.LENGTH_SHORT).show()
            } else {
                val panjang = panjangStr.toDouble()
                val lebar = lebarStr.toDouble()
                val tinggi = tinggiStr.toDouble()
                val volume = panjang * lebar * tinggi
                tvHasilBalok.text = "Hasil Volume: $volume"
            }
        }
    }
}