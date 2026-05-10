package com.example.farrel_party.Home.pertemuan_9

import android.os.Bundle
import android.widget.SimpleAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.farrel_party.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Setup Toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Pengaturan & Lainnya"
            setDisplayHomeAsUpEnabled(true)
        }

        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        // 2. Siapkan Data ListView (Title dan Description)
        val settingsData = listOf(
            mapOf("title" to "Profil Akun", "desc" to "Atur informasi data diri"),
            mapOf("title" to "Privasi & Keamanan", "desc" to "Ubah password dan autentikasi"),
            mapOf("title" to "Notifikasi", "desc" to "Atur pemberitahuan aplikasi"),
            mapOf("title" to "Bantuan & Dukungan", "desc" to "Pusat bantuan Bina Desa"),
            mapOf("title" to "Syarat dan Ketentuan", "desc" to "Kebijakan penggunaan aplikasi"),
            mapOf("title" to "Tentang Aplikasi", "desc" to "Versi aplikasi v1.0.0")
        )

        // 3. Pasang SimpleAdapter
        val adapter = SimpleAdapter(
            this,
            settingsData,
            android.R.layout.simple_list_item_2, // Layout bawaan Android (Title + Desc)
            arrayOf("title", "desc"),
            intArrayOf(android.R.id.text1, android.R.id.text2)
        )

        binding.listViewSettings.adapter = adapter

        // 4. Aksi saat menu diklik
        binding.listViewSettings.setOnItemClickListener { _, _, position, _ ->
            val selectedItem = settingsData[position]
            val title = selectedItem["title"]
            Toast.makeText(this, "Membuka menu: $title", Toast.LENGTH_SHORT).show()
        }
    }
}