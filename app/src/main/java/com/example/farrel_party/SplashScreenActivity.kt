package com.example.farrel_party

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val sharedPref = getSharedPreferences("user_pref", Context.MODE_PRIVATE)
        val isLogin = sharedPref.getBoolean("isLogin", false)

        lifecycleScope.launch {
            delay(2000) // Delay 2 detik

            val intent = if (isLogin) {
                // TIKET DIUBAH KE BaseActivity
                Intent(this@SplashScreenActivity, BaseActivity::class.java)
            } else {
                Intent(this@SplashScreenActivity, AuthActivity::class.java)
            }
            startActivity(intent)
            finish()
        }
    }
}