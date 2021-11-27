package org.sopt.androidassingment.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import org.sopt.androidassingment.adapter.FollowerAdapter
import org.sopt.androidassingment.data.FollowerData
import org.sopt.androidassingment.data.ResponseFollowerData
import org.sopt.androidassingment.data.ResponseUserData
import org.sopt.androidassingment.databinding.FragmentFollowerBinding
import org.sopt.androidassingment.server.GitHubService
import org.sopt.androidassingment.server.ServiceCreator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowerFragment : Fragment() {
    private var _binding: FragmentFollowerBinding? = null
    private val binding get() = _binding ?: error("Binding이 초기화 되지 않았습니다.")
    private var _followerAdapter: FollowerAdapter? = null
    private val followerAdapter get() = _followerAdapter ?: error("followerAdapter이 초기화 되지 않았습니다.")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFollowerBinding.inflate(layoutInflater, container, false)

        initAdapter()
        getFollowerList()
        decorationView()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        _followerAdapter = null
    }

    private fun initAdapter(){
        _followerAdapter = FollowerAdapter()
        binding.rvFollower.adapter = followerAdapter
    }

    private fun getFollowerList(){
        // follower들의 login(id)을 불러옴
        val call: Call<List<ResponseFollowerData>> = ServiceCreator.gitHubService.getFollowers(ProfileFragment.USERNAME)

        call.enqueue(object : Callback<List<ResponseFollowerData>> {
            override fun onResponse(
                call: Call<List<ResponseFollowerData>>,
                response: Response<List<ResponseFollowerData>>
            ) {
                if(response.isSuccessful){
                    response.body()?.let { setFollowerData(it) }
                } else{ Log.d("NetworkTest","response failed") }
            }

            override fun onFailure(call: Call<List<ResponseFollowerData>>, t: Throwable) {
                Log.e("NetworkTest","error:$t")
            }

        })
    }

    private fun setFollowerData(followers : List<ResponseFollowerData>){
        // followers에 들어있는 login을 가지고 user data를 불러온다
        var index = 0
        while(index < followers.size) {
            val call: Call<ResponseUserData> = ServiceCreator.gitHubService.getUsers(followers[index].login)

            call.enqueue(object : Callback<ResponseUserData> {
                override fun onResponse(
                    call: Call<ResponseUserData>,
                    response: Response<ResponseUserData>
                ) {
                    if(response.isSuccessful){
                        response.body()?.let {
                            followerAdapter.followerList.add(FollowerData(it.avatar_url, it.login, it.bio))
                            followerAdapter.notifyDataSetChanged()
                        }
                    } else{ Log.d("NetworkTest","response failed") }
                }

                override fun onFailure(call: Call<ResponseUserData>, t: Throwable) {
                    Log.e("NetworkTest","error:$t")
                }

            })
            index += 1
        }
    }
    private fun decorationView(){
        binding.rvFollower.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
    }
}