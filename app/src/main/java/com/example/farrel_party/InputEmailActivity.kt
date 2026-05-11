package com.example.farrel_party

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.farrel_party.databinding.ActivityInputEmailBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class InputEmailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInputEmailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputEmailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLanjutRegistrasi.setOnClickListener {
            val email = binding.etEmailGmail.text.toString().trim()

            // Validasi 1: Email tidak boleh kosong
            if (email.isEmpty()) {
                showErrorDialog("Peringatan", "Email tidak boleh kosong!")
                return@setOnClickListener
            }

            // Validasi 2: Harus menggunakan domain @gmail.com
            if (!email.endsWith("@gmail.com")) {
                showErrorDialog("Format Salah", "Email harus menggunakan domain @gmail.com!")
                return@setOnClickListener
            }

            // Jika Valid: Arahkan ke RegisterActivity dan bawa data emailnya
            val intent = Intent(this, RegisterActivity::class.java)
            intent.putExtra("EXTRA_EMAIL", email)
            startActivity(intent)
        }
    }

    // Fungsi bantuan untuk menampilkan MaterialAlertDialog
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