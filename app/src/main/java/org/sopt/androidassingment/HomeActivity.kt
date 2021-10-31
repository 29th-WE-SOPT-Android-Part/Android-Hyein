package org.sopt.androidassingment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import org.sopt.androidassingment.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private var position = FOLLOWER_POSITION
    private lateinit var binding : ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)

        initTransactionEvent()
        initImage()
        setContentView(binding.root)
    }


    private fun initTransactionEvent(){
        val followerFragment = FollowerFragment()
        val repositoryFragment = RepositoryFragment()

        supportFragmentManager.beginTransaction().add(R.id.container_home, followerFragment).commit()
        binding.btnFollower.setOnClickListener{
            if(position == REPOSITORY_POSITION) {
                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.container_home, followerFragment).commit()
                position = FOLLOWER_POSITION
            }
        }
        binding.btnRepository.setOnClickListener{
            if(position == FOLLOWER_POSITION) {
                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.container_home, repositoryFragment).commit()
                position = REPOSITORY_POSITION
            }
        }
    }

    private fun initImage(){
        Glide.with(this)
            .load("https://avatars.githubusercontent.com/u/68214704?v=4")
            .circleCrop()
            .into(binding.imgSelf)
    }

    companion object{
        const val FOLLOWER_POSITION = 1
        const val REPOSITORY_POSITION = 2
    }
}