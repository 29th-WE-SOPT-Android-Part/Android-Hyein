package org.sopt.androidassingment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.sopt.androidassingment.databinding.ActivitySettingBinding
import org.sopt.androidassingment.util.extension.shortToast

class SettingActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySettingBinding.inflate(layoutInflater)
        initClickEvent()
        setContentView(binding.root)
    }

    private fun initClickEvent(){
        binding.tvAutoCancle.setOnClickListener{
            shortToast("자동 로그인 해제")
            SOPTSharedpreferences.removeAutoLogin(this)
            SOPTSharedpreferences.clearStorage(this)
        }
    }
}