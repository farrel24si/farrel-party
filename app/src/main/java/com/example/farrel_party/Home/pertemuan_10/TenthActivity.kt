package com.example.farrel_party.Home.pertemuan_10

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.farrel_party.databinding.ActivityTenthBinding
import com.google.android.material.tabs.TabLayoutMediator

class TenthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTenthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTenthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Setup Toolbar dan Tombol Back
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        // 2. Pasang Adapter ke ViewPager2
        val tabsAdapter = TenthTabsAdapter(this)
        binding.viewPager.adapter = tabsAdapter

        // 3. Gabungkan TabLayout dan ViewPager2 dengan ikon keren
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Warga"
                    tab.setIcon(android.R.drawable.ic_menu_myplaces)
                }
                1 -> {
                    tab.text = "Petugas"
                    tab.setIcon(android.R.drawable.ic_menu_info_details)
                }
                2 -> {
                    tab.text = "Fasilitas"
                    tab.setIcon(android.R.drawable.ic_menu_agenda)
                }
            }
        }.attach()
    }
}