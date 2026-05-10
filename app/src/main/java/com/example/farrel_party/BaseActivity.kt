package com.example.farrel_party

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.farrel_party.About.AboutFragment
import com.example.farrel_party.Home.HomeFragment
import com.example.farrel_party.Profile.ProfileFragment
import com.example.farrel_party.databinding.ActivityBaseBinding

class BaseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Tampilkan HomeFragment secara default saat aplikasi dibuka
        replaceFragment(HomeFragment())

        // Logika saat menu navigasi bawah diklik
        binding.bottomNavView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    replaceFragment(HomeFragment())
                    true
                }
                R.id.nav_about -> {
                    replaceFragment(AboutFragment())
                    true
                }
                R.id.nav_profile -> {
                    replaceFragment(ProfileFragment())
                    true
                }
                else -> false
            }
        }
    }

    // Fungsi untuk mengganti Fragment
    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}