package com.example.totalpro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.totalpro.databinding.ActivityAppSplashBinding

class AppSplashActivity : AppCompatActivity() {
    lateinit var binding: ActivityAppSplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAppSplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1초 후에 MainActivity로 이동하기 위한 Intent 설정
        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivityForResult(intent, 1)
        }, 1000)
    }

    // Activity 종료 시, 현재 Activity 종료(App Splash 화면과 연결)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 || resultCode == RESULT_OK) {
            finish()
        }
    }
}