package com.example.farrel_party

import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.farrel_party.databinding.ActivityWebViewBinding

class WebViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWebViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup Toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Fasilitas Desa dan Peminjaman Ruang"
            setDisplayHomeAsUpEnabled(true)
        }

        // Setup WebView
        binding.webView.settings.javaScriptEnabled = true

        // --- SOLUSI CACHE MISS ---
        // Memaksa WebView memuat dari jaringan internet, bukan dari memori sementara
        binding.webView.settings.cacheMode = WebSettings.LOAD_NO_CACHE

        binding.webView.webViewClient = WebViewClient() // Agar link tidak lari ke browser luar

        // IMPROVISASI WEBVIEW: Membaca progress loading website
        binding.webView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
                binding.progressBar.progress = newProgress
                // Sembunyikan garis loading jika web sudah 100% termuat
                if (newProgress == 100) {
                    binding.progressBar.visibility = View.GONE
                } else {
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
        }

        // Load URL
        binding.webView.loadUrl("https://fdpr.alwaysdata.net")

        // Agar Toolbar hide/show saat di-scroll
        binding.webView.setOnScrollChangeListener { _, _, scrollY, _, oldScrollY ->
            if (scrollY > oldScrollY) {
                binding.appBar.setExpanded(false, true) // sembunyikan
            } else if (scrollY < oldScrollY) {
                binding.appBar.setExpanded(true, true) // tampilkan
            }
        }
    }

    // Tombol Back untuk navigasi Web atau kembali ke Activity sebelumnya
    override fun onBackPressed() {
        if (binding.webView.canGoBack()) {
            binding.webView.goBack()
        } else {
            super.onBackPressed()
        }
    }

    // Tombol Back di Toolbar Atas
    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}