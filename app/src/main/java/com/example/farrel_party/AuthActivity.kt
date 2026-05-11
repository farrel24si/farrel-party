package com.example.farrel_party

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.farrel_party.databinding.ActivityAuthBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class AuthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            val user = binding.etUsername.text.toString().trim()
            val pass = binding.etPassword.text.toString().trim()

            // 1. Panggil SharedPreferences untuk membaca data registrasi
            val sharedPref = getSharedPreferences("user_pref", Context.MODE_PRIVATE)
            val savedUsername = sharedPref.getString("reg_username", "")
            val savedEmail = sharedPref.getString("reg_email", "") // Tambahan: Ambil data email
            val savedPassword = sharedPref.getString("reg_password", "")

            // 2. Buat Kondisi Validasi
            // Kondisi A: username == password (syarat lama)
            val isDefaultValid = (user == pass && user.isNotEmpty())

            // Kondisi B: input (user) cocok dengan savedUsername ATAU savedEmail, DAN passwordnya benar
            val isRegisteredValid = ((user == savedUsername || user == savedEmail) && pass == savedPassword && user.isNotEmpty())

            // 3. Cek apakah salah satu kondisi terpenuhi (pakai operator OR / ||)
            if (isDefaultValid || isRegisteredValid) {
                // Simpan status login bahwa user sedang aktif
                val editor = sharedPref.edit()
                editor.putBoolean("isLogin", true)
                editor.putString("username", user)
                editor.apply()

                // Arahkan ke halaman utama (Home)
                startActivity(Intent(this, BaseActivity::class.java))
                finish()
            } else {
                // TRIK DEBUGGING: Kita tampilkan data yang ada di memori!
                MaterialAlertDialogBuilder(this)
                    .setTitle("Login Gagal (Mode Debug)")
                    .setMessage("Input Kamu:\nEmail/User: $user\nPass: $pass\n\nData di Memori HP:\nEmail Tersimpan: $savedEmail\nUsername Tersimpan: $savedUsername\nPass Tersimpan: $savedPassword")
                    .setPositiveButton("OK") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .show()
            }
        }

        // Tombol Register With Gmail
        binding.btnRegisterGmail.setOnClickListener {
            val intent = Intent(this, InputEmailActivity::class.java)
            startActivity(intent)
        }
    }
}