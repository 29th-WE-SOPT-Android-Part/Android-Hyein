package org.sopt.androidassingment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import org.sopt.androidassingment.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private var position = FOLLOWER_POSITION
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

        binding.btnFollower.setOnClickListener{
            if(position == REPOSITORY_POSITION) {
                transaction.replace(R.id.container_home, followerFragment)
                position = FOLLOWER_POSITION
            }
        }
        binding.btnRepository.setOnClickListener{
            if(position == FOLLOWER_POSITION) {
                transaction.replace(R.id.container_home, repositoryFragment)
                position = REPOSITORY_POSITION
            }
        }
        transaction.commit()
    }

    companion object{
        const val FOLLOWER_POSITION = 1
        const val REPOSITORY_POSITION = 2
    }
}