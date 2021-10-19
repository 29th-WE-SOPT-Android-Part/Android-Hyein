package org.sopt.androidassingment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.sopt.androidassingment.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding : ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)

        initTransactionEvent()

        setContentView(binding.root)
    }

    private fun initTransactionEvent(){
        val followerFragment = FollowerFragment()
        val repositoryFragment = RepositoryFragment()

        supportFragmentManager.beginTransaction().add(R.id.container_home, followerFragment).commit()
        val transaction = supportFragmentManager.beginTransaction()
        binding.btnRepository.setOnClickListener{
            transaction.replace(R.id.container_home, followerFragment)
        }
        binding.btnFollower.setOnClickListener{
            transaction.replace(R.id.container_home, repositoryFragment)
        }
    }
}