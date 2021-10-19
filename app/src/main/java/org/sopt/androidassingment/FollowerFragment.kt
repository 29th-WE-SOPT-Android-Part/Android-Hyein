package org.sopt.androidassingment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
                FollowerData("문다빈", "안드로이드 파트장"),
                FollowerData("김현아", "기획 파트장"),
                FollowerData("이성현", "디자인 파트장"),
                FollowerData("장혜령", "iOS 파트장"),
                FollowerData("김우영", "서버 파트장"),
                FollowerData("김의진", "웹 파트장")
            )
        )

        followerAdapter.notifyDataSetChanged()
    }
}