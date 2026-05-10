package com.example.farrel_party

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
// Import DashboardActivity sudah dihapus karena tidak dipakai lagi
import com.example.farrel_party.databinding.ActivityAuthBinding

class AuthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            // Ditambah .trim() agar lebih aman dari spasi yang tidak disengaja
            val user = binding.etUsername.text.toString().trim()
            val pass = binding.etPassword.text.toString().trim()

            if (user == pass && user.isNotEmpty()) {
                val sharedPref = getSharedPreferences("user_pref", Context.MODE_PRIVATE)
                val editor = sharedPref.edit()
                editor.putBoolean("isLogin", true)
                editor.putString("username", user)
                editor.apply()

                // TIKET DIUBAH: Sekarang mengarah ke BaseActivity
                startActivity(Intent(this, BaseActivity::class.java))
                finish()
            } else {
                AlertDialog.Builder(this)
                    .setMessage("Silahkan coba lagi")
                    .setPositiveButton("OK", null).show()
            }
        }
    }
}