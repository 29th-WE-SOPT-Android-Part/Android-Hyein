package org.sopt.androidassingment.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import org.sopt.androidassingment.adapter.FollowerAdapter
import org.sopt.androidassingment.data.FollowerData
import org.sopt.androidassingment.databinding.FragmentFollowerBinding

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

        followerAdapter.followerList.addAll(
            listOf(
                FollowerData("https://avatars.githubusercontent.com/u/68214704?v=4" ,"김혜인", "안드로이드 파트원"),
                FollowerData("https://avatars.githubusercontent.com/u/70698151?v=4" ,"문다빈", "안드로이드 파트장"),
                FollowerData("https://avatars.githubusercontent.com/u/81508084?v=4" ,"한승현", "안드로이드 파트원"),
                FollowerData("https://avatars.githubusercontent.com/u/52950523?v=4" ,"최유림", "안드로이드 파트원"),
                FollowerData("https://avatars.githubusercontent.com/u/50603273?v=4" ,"이동기", "안드로이드 파트원")
            )
        )

        followerAdapter.notifyDataSetChanged()
    }

    private fun decorationView(){
        binding.rvFollower.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
    }
}