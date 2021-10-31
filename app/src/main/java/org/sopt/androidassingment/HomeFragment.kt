package org.sopt.androidassingment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import org.sopt.androidassingment.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding ?: error("Binding이 초기화 되지 않았습니다.")
    private lateinit var homeViewPagerAdapter: HomeTabViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        initAdapter()
        initTabLayout()

        return binding.root
    }

    private fun initAdapter(){
        val fragmentList = listOf(HomeFollowingFragment(), HomeFollowerFragment())

        homeViewPagerAdapter = HomeTabViewPagerAdapter(this)
        homeViewPagerAdapter.fragments.addAll(fragmentList)

        binding.vpHome.adapter = homeViewPagerAdapter
    }

    private fun initTabLayout(){
        val tabLabel = listOf("팔로잉", "팔로워")

        TabLayoutMediator(binding.tlHome, binding.vpHome) {
            tab, position -> tab.text = tabLabel[position]
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}