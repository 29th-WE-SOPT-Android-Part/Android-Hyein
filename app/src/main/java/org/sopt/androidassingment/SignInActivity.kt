package org.sopt.androidassingment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.sopt.androidassingment.databinding.ActivitySignInBinding
import org.sopt.androidassingment.server.RequestLoginData
import org.sopt.androidassingment.server.ResponseLoginData
import org.sopt.androidassingment.server.ServiceCreator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
        binding.btnLogin.setOnClickListener {
            initNetwork()
        }
    }

    private fun signClick(){
        val intent = Intent(this, SignUpActivity::class.java)
        binding.tvSign.setOnClickListener { startActivity(intent) }
    }

    private fun initNetwork(){
        val requestLoginData = RequestLoginData(
            id = binding.etId.text.toString(),
            password = binding.etPass.text.toString()
        )

        val call: Call<ResponseLoginData> = ServiceCreator.loginService.postLogin(requestLoginData)

        call.enqueue(object : Callback<ResponseLoginData> {
            override fun onResponse(
                call: Call<ResponseLoginData>,
                response: Response<ResponseLoginData>
            ) {
                if(response.isSuccessful){
                    Toast.makeText(this@SignInActivity, "${response.body()?.data?.name}님 반갑습니다", Toast.LENGTH_SHORT).show()

                    startActivity(Intent(this@SignInActivity, HomeActivity::class.java))
                } else
                    Toast.makeText(this@SignInActivity, "로그인에 실패하셨습니다", Toast.LENGTH_SHORT).show()

            }

            override fun onFailure(call: Call<ResponseLoginData>, t: Throwable) {
                Log.e("NetworkTest","error:$t")
            }
        })
    }
}