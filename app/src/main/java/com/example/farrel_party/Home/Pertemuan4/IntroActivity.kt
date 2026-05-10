package com.example.farrel_party.Home.Pertemuan4

import android.content.Intent
import android.os.Bundle
import android.util.Log // Import untuk mencetak Logcat
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.farrel_party.databinding.ActivityIntroBinding

class IntroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityIntroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // LOG LIFECYCLE: onCreate
        Log.e("onCreate", "IntroActivity dibuat pertama kali")

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 1. Aksi Tombol Hitam (Dashboard) -> MENGIRIM DATA
        binding.btnDashboard.setOnClickListener {
            val intent = Intent(this, DashboardActivity::class.java)

            // Mengirim data menggunakan putExtra (Materi Modul 4)
            intent.putExtra("name", "Farrel Aditya Nugraha")
            intent.putExtra("from", "Rumbai")
            intent.putExtra("age", 20)

            startActivity(intent)
            finish()
        }

        // 2. Aksi Tombol Teks (Continue as a guest) -> MENGIRIM DATA TAMU
        binding.tvGuest.setOnClickListener {
            val intent = Intent(this, DashboardActivity::class.java)

            // Mengirim data berbeda jika login sebagai tamu
            intent.putExtra("name", "Tamu (Guest)")
            intent.putExtra("from", "Tidak Diketahui")
            intent.putExtra("age", 0)

            startActivity(intent)
            finish()
        }
    }

    // LOG LIFECYCLE: onStart
    override fun onStart() {
        super.onStart()
        Log.e("onStart", "onStart: IntroActivity terlihat di layar")
    }

    // LOG LIFECYCLE: onDestroy
    override fun onDestroy() {
        super.onDestroy()
        Log.e("onDestroy", "IntroActivity dihapus dari stack")
    }
}