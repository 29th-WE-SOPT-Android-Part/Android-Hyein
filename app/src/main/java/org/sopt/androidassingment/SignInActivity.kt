package org.sopt.androidassingment

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.sopt.androidassingment.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignInBinding.inflate(layoutInflater)

        loginClick()
        signClick()
        setContentView(binding.root)
    }

    private fun loginClick(){
        val intent = Intent(this, HomeActivity::class.java)
        binding.btnLogin.setOnClickListener {
            val id = binding.etId.text.toString()
            val password = binding.etPass.text.toString()
            if(id.isNotEmpty() && password.isNotEmpty()) {
                Toast.makeText(this, "${id}님 환영합니다", Toast.LENGTH_SHORT).show()
                startActivity(intent)
            } else { Toast.makeText(this, "로그인 실패", Toast.LENGTH_SHORT).show()}
        }
    }

    private fun signClick(){
        val intent = Intent(this, SignUpActivity::class.java)
        binding.btnSign.setOnClickListener { startActivity(intent) }
    }
}