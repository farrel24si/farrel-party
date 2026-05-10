package com.example.farrel_party.Home.Pertemuan4

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.farrel_party.AuthActivity
import com.example.farrel_party.Home.BangunRuangActivity
import com.example.farrel_party.SixthActivity // Import file Pertemuan 6
import com.example.farrel_party.WebViewActivity
import com.example.farrel_party.databinding.ActivityDashboardBinding
import com.google.android.material.snackbar.Snackbar

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // --- 1. SETUP TOOLBAR ---
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Dashboard Utama"
            setDisplayHomeAsUpEnabled(true) // Memunculkan tombol panah back
        }

        // LOG LIFECYCLE: onCreate
        Log.e("onCreate", "DashboardActivity dibuat pertama kali")

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // --- PENERIMAAN DATA INTENT (Materi Modul 4) ---
        val name = intent.getStringExtra("name") ?: "Admin FDPR" // Default diganti biar keren
        val from = intent.getStringExtra("from") ?: "-"
        val age = intent.getIntExtra("age", 0)

        Log.e("Data Intent", "Nama: $name , Usia: $age, Asal: $from")
        binding.tvUserName.text = name
        // -----------------------------------------------

        // --- 2. AKSI TOMBOL MENU ---

        // Kotak 1: Buka Web Bina Desa (FDPR)
        binding.cvMenuHome.setOnClickListener {
            val intent = Intent(this, WebViewActivity::class.java)
            startActivity(intent)
        }

        // Kotak 2: Buka Kalkulator (Pertemuan 2)
        binding.cvMenuCalculator.setOnClickListener {
            val intent = Intent(this, BangunRuangActivity::class.java)
            startActivity(intent)
        }

        // Kotak 3: Buka Fragment (Tugas Pertemuan 6)
        binding.cvMenuFragment.setOnClickListener {
            val intent = Intent(this, SixthActivity::class.java)
            startActivity(intent)
        }

        // Kotak 4: Aksi Logout dengan AlertDialog & SharedPreferences
        binding.cvMenuLogout.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Konfirmasi Logout")
            builder.setMessage("Apakah kamu yakin ingin keluar dari akun ini?")

            // Tombol "Ya"
            builder.setPositiveButton("Ya, Keluar") { dialog, _ ->

                // Hapus sesi di SharedPreferences (Materi Modul 6)
                val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
                val editor = sharedPref.edit()
                editor.clear()
                editor.apply()

                val intent = Intent(this, AuthActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()
            }

            // Tombol "Batal"
            builder.setNegativeButton("Batal") { dialog, _ ->
                dialog.dismiss()
                Snackbar.make(binding.root, "Proses logout dibatalkan.", Snackbar.LENGTH_SHORT)
                    .setAction("Tutup") {
                        Log.e("Info Snackbar", "Snackbar ditutup")
                    }
                    .show()
            }

            builder.create().show()
        }
    }

    // --- 3. FUNGSI TOMBOL BACK DI TOOLBAR ---
    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

    // LOG LIFECYCLE: onStart
    override fun onStart() {
        super.onStart()
        Log.e("onStart", "onStart: DashboardActivity terlihat di layar")
    }

    // LOG LIFECYCLE: onDestroy
    override fun onDestroy() {
        super.onDestroy()
        Log.e("onDestroy", "DashboardActivity dihapus dari stack")
    }
}