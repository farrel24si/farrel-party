package com.example.farrel_party.Home.Pertemuan3

import android.content.Intent // Jangan lupa import Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

// Mengimpor halaman Dashboard dari folder Pertemuan4
import com.example.farrel_party.Home.Pertemuan4.DashboardActivity
import com.example.farrel_party.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // --- REVISI DI BAGIAN INI ---
        binding.btnHome.setOnClickListener {
            // Memanggil Intent untuk pindah ke DashboardActivity
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)

            // finish() di sini sangat penting agar halaman "Success" ini ditutup.
            // Jadi kalau pengguna menekan tombol "Back" di HP dari Dashboard,
            // mereka tidak akan melihat halaman "You've Logged in!" lagi.
            finish()
        }
    }
}