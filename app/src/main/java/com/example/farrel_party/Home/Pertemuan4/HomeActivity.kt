package com.example.farrel_party.Home.Pertemuan4

import android.os.Bundle
import android.util.Log // Import untuk mencetak Logcat
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.farrel_party.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // LOG LIFECYCLE: onCreate
        Log.e("onCreate", "HomeActivity dibuat pertama kali")

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    // LOG LIFECYCLE: onStart
    override fun onStart() {
        super.onStart()
        Log.e("onStart", "onStart: HomeActivity terlihat di layar")
    }

    // LOG LIFECYCLE: onDestroy
    override fun onDestroy() {
        super.onDestroy()
        Log.e("onDestroy", "HomeActivity dihapus dari stack")
    }
}