package org.sopt.androidassingment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import org.sopt.androidassingment.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding ?: error("Binding이 초기화 되지 않았습니다.")
    private var _profileAdapter: FragmentProfileBinding? = null

    private var position = FOLLOWER_POSITION

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(layoutInflater, container, false)

        initTransactionEvent()
        initImage()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        _profileAdapter = null
    }

    private fun initTransactionEvent(){
        val followerFragment = FollowerFragment()
        val repositoryFragment = RepositoryFragment()

        childFragmentManager.beginTransaction().add(R.id.container_profile, followerFragment).commit()
        binding.btnFollower.setOnClickListener{
            if(position == REPOSITORY_POSITION) {
                val transaction = childFragmentManager.beginTransaction()
                transaction.replace(R.id.container_profile, followerFragment).commit()
                position = FOLLOWER_POSITION
            }
        }
        binding.btnRepository.setOnClickListener{
            if(position == FOLLOWER_POSITION) {
                val transaction = childFragmentManager.beginTransaction()
                transaction.replace(R.id.container_profile, repositoryFragment).commit()
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