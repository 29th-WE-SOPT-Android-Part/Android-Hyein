package org.sopt.androidassingment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.sopt.androidassingment.databinding.ActivityHomeBinding
import org.sopt.androidassingment.fragment.CameraFragment
import org.sopt.androidassingment.fragment.HomeFragment
import org.sopt.androidassingment.fragment.ProfileFragment

class HomeActivity : AppCompatActivity() {
    private lateinit var binding : ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)

        initBottomNavigation()
        setContentView(binding.root)
    }


    private fun initBottomNavigation(){
        val profileFragment = ProfileFragment()
        val homeFragment = HomeFragment()
        val cameraFragment = CameraFragment()

        supportFragmentManager.beginTransaction().add(R.id.container_home, profileFragment).commit()
        binding.bnvHome.setOnItemSelectedListener {
            when(it.itemId){
                R.id.menu_home -> {
                    val transaction = supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.container_home, homeFragment).commit()
                    return@setOnItemSelectedListener true
                }
                R.id.menu_person -> {
                    val transaction = supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.container_home, profileFragment).commit()
                    return@setOnItemSelectedListener true
                }
                else->{
                    val transaction = supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.container_home, cameraFragment).commit()
                    return@setOnItemSelectedListener true
                }
            }
        }
    }

}