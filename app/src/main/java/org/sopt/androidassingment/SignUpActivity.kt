package org.sopt.androidassingment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import org.sopt.androidassingment.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    lateinit var binding : ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivitySignUpBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)

        signCompleteClick()
        setContentView(binding.root)
    }

    private fun signCompleteClick(){
        binding.btnLogin.setOnClickListener {
            val name = binding.etName.text.toString()
            val id = binding.etId.text.toString()
            val password = binding.etPass.text.toString()
            if(name.isNotEmpty() && id.isNotEmpty() && password.isNotEmpty()) { finish() }
            else { Toast.makeText(this, "입력되지 않은 정보가 있습니다", Toast.LENGTH_SHORT).show()}
        }
    }
}