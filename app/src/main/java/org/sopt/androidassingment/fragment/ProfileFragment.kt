package org.sopt.androidassingment.fragment

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import org.sopt.androidassingment.R
import org.sopt.androidassingment.data.ResponseUserData
import org.sopt.androidassingment.databinding.FragmentProfileBinding
import org.sopt.androidassingment.server.ServiceCreator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
        initNetwork()

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
            with(binding) {
                btnFollower.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this.root.context,
                    R.color.butterscotch
                ))
                btnRepository.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this.root.context,
                    R.color.gray
                ))
                btnFollower.setTextColor(Color.WHITE)
                btnRepository.setTextColor(Color.BLACK)
            }
        }
        binding.btnRepository.setOnClickListener{
            if(position == FOLLOWER_POSITION) {
                val transaction = childFragmentManager.beginTransaction()
                transaction.replace(R.id.container_profile, repositoryFragment).commit()
                position = REPOSITORY_POSITION
            }

            with(binding) {
                btnFollower.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this.root.context,
                    R.color.gray
                ))
                btnRepository.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this.root.context,
                    R.color.butterscotch
                ))
                btnRepository.setTextColor(Color.WHITE)
                btnFollower.setTextColor(Color.BLACK)
            }
        }

    }

    private fun initNetwork(){
        val call: Call<ResponseUserData> = ServiceCreator.gitHubService.getUsers(USERNAME)

        call.enqueue(object : Callback<ResponseUserData>{
            override fun onResponse(
                call: Call<ResponseUserData>,
                response: Response<ResponseUserData>
            ) {
                if(response.isSuccessful){
                    response.body()?.let { setUserData(it) }
                } else{ Log.d("NetworkTest","response failed") }
            }

            override fun onFailure(call: Call<ResponseUserData>, t: Throwable) {
                Log.e("NetworkTest","error:$t")
            }
        })

    }


    private fun setUserData(user : ResponseUserData){
        Glide.with(this)
            .load(user.avatar_url)
            .circleCrop()
            .into(binding.imgSelf)

        binding.tvName.text = user.name
        binding.tvId.text = user.login
        binding.tvIntroduce.text = user.bio
    }

    companion object{
        const val FOLLOWER_POSITION = 1
        const val REPOSITORY_POSITION = 2
        const val USERNAME = "sdu07024"
    }
}