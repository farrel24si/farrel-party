package com.example.farrel_party

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.farrel_party.databinding.ActivityRegisterBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Mengambil data email dari halaman sebelumnya (InputEmailActivity)
        val emailDariIntent = intent.getStringExtra("EXTRA_EMAIL")
        binding.etRegEmail.setText(emailDariIntent) // Set otomatis ke dalam EditText

        binding.btnSubmitRegister.setOnClickListener {
            val nama = binding.etRegNama.text.toString().trim()
            val email = binding.etRegEmail.text.toString().trim()
            val username = binding.etRegUsername.text.toString().trim()
            val password = binding.etRegPassword.text.toString().trim()

            // 1. Validasi: Semua field wajib diisi
            if (nama.isEmpty() || username.isEmpty() || password.isEmpty()) {
                showErrorDialog("Peringatan", "Semua kolom wajib diisi!")
                return@setOnClickListener
            }

            // 2. Validasi: Password minimal 6 karakter
            if (password.length < 6) {
                showErrorDialog("Password Lemah", "Password harus minimal 6 karakter!")
                return@setOnClickListener
            }

            // 3. Validasi: Username tidak boleh mengandung spasi
            if (username.contains(" ")) {
                showErrorDialog("Username Tidak Valid", "Username tidak boleh mengandung spasi!")
                return@setOnClickListener
            }

            // JIKA SEMUA VALIDASI LOLOS: Simpan ke SharedPreferences
            val sharedPref = getSharedPreferences("user_pref", Context.MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putString("reg_name", nama)
            editor.putString("reg_email", email)
            editor.putString("reg_username", username)
            editor.putString("reg_password", password)
            editor.apply() // Simpan data

            // Menampilkan informasi sukses menggunakan MaterialAlertDialog
            MaterialAlertDialogBuilder(this)
                .setTitle("Sukses")
                .setMessage("Registrasi Berhasil! Silakan login menggunakan username dan password yang telah didaftarkan.")
                .setCancelable(false)
                .setPositiveButton("Ke Halaman Login") { dialog, _ ->
                    dialog.dismiss()
                    // Arahkan kembali ke halaman Login (AuthActivity)
                    val intent = Intent(this, AuthActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    finish()
                }
                .show()
        }
    }

    private fun showErrorDialog(title: String, message: String) {
        MaterialAlertDialogBuilder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("Mengerti") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}