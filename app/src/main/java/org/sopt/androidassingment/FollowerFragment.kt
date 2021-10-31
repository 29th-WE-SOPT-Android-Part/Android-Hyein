package org.sopt.androidassingment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
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
                FollowerData("김혜인", "안드로이드 파트원"),
                FollowerData("김혜인", "안드로이드 파트원"),
                FollowerData("김혜인", "안드로이드 파트원"),
                FollowerData("김혜인", "안드로이드 파트원"),
                FollowerData("김혜인", "안드로이드 파트원"),
                FollowerData("김혜인", "안드로이드 파트원"),
                FollowerData("김혜인", "안드로이드 파트원"),
                FollowerData("김혜인", "안드로이드 파트원"),
                FollowerData("김혜인", "안드로이드 파트원"),
                FollowerData("김혜인", "안드로이드 파트원")
            )
        )

        followerAdapter.notifyDataSetChanged()
    }

    private fun decorationView(){
        binding.rvFollower.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
    }
}