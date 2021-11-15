package org.sopt.androidassingment.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import org.sopt.androidassingment.adapter.RepositoryAdapter
import org.sopt.androidassingment.data.RepositoryData
import org.sopt.androidassingment.data.ResponseRepoData
import org.sopt.androidassingment.databinding.FragmentRepositoryBinding
import org.sopt.androidassingment.server.ServiceCreator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositoryFragment : Fragment() {
    private var _binding: FragmentRepositoryBinding? = null
    private val binding get() = _binding ?: error("Binding이 초기화 되지 않았습니다.")
    private var _repositoryAdapter: RepositoryAdapter? = null
    private val repositoryAdapter get() = _repositoryAdapter ?: error("repositoryAdapter이 초기화 되지 않았습니다.")

    private val username = "sdu07024"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRepositoryBinding.inflate(layoutInflater, container, false)

        initAdapter()
        getRepoList()
        decorationView()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initAdapter(){
        _repositoryAdapter = RepositoryAdapter()
        binding.rvFollower.adapter = repositoryAdapter
    }

    private fun getRepoList(){
        val call: Call<List<ResponseRepoData>> = ServiceCreator.gitHubService.getRepos(username)
        Log.d("NetworkTest","call ok")

        var index = 0
        call.enqueue(object : Callback<List<ResponseRepoData>>{
            override fun onResponse(
                call: Call<List<ResponseRepoData>>,
                response: Response<List<ResponseRepoData>>
            ) {
                Log.d("NetworkTest","onResponse")
                if(response.isSuccessful){
                    Log.d("NetworkData", "response success")
                    response.body()?.let{
                        Log.d("NetworkData", "size : ${response.body()?.size}")
                        while(index < it.size) {
                            Log.d("NetworkData","it[index].name : ${it[index].name}")
                            Log.d("NetworkData","it[index].description : ${it[index].description}")
                            repositoryAdapter.repositoryList.add(RepositoryData(it[index].name, it[index].description))
                            repositoryAdapter.notifyDataSetChanged()
                            index++
                        }
                    }
                }
                else{
                    Log.d("NetworkTest","ResponseUserData failed")
                }
            }

            override fun onFailure(call: Call<List<ResponseRepoData>>, t: Throwable) {
                Log.e("NetworkTest","error:$t")
            }

        })
    }
    private fun decorationView(){
        binding.rvFollower.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
    }
}