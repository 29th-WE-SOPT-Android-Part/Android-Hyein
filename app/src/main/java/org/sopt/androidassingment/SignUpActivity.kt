package org.sopt.androidassingment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import org.sopt.androidassingment.databinding.ActivitySignUpBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
            initNetwork()
        }
    }

    private fun initNetwork(){
        val requestSignUpData = RequestSignUpData(
            id = binding.etId.text.toString(),
            name = binding.etName.text.toString(),
            password = binding.etPass.text.toString()
        )

        val call: Call<ResponseSignUpData> = ServiceCreator.singUpService.postSignUp(requestSignUpData)

        call.enqueue(object : Callback<ResponseSignUpData> {
            override fun onResponse(
                call: Call<ResponseSignUpData>,
                response: Response<ResponseSignUpData>
            ) {
                if(response.isSuccessful){
                    finish()
                } else
                    Toast.makeText(this@SignUpActivity, "입력되지 않은 정보가 있습니다", Toast.LENGTH_SHORT).show()

            }

            override fun onFailure(call: Call<ResponseSignUpData>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}