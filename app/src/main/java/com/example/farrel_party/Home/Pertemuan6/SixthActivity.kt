package com.example.farrel_party

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.farrel_party.databinding.ActivitySixthBinding

class SixthActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySixthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySixthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Default fragment
        replaceFragment(SatuFragment())

        binding.btnFragment1.setOnClickListener { replaceFragment(SatuFragment()) }
        binding.btnFragment2.setOnClickListener { replaceFragment(DuaFragment()) }
        binding.btnFragment3.setOnClickListener { replaceFragment(TigaFragment()) }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment) // .replace container id
            .addToBackStack(null) // Bisa kembali ke fragment sebelumnya
            .commit()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}