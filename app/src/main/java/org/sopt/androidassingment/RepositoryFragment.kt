package org.sopt.androidassingment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.sopt.androidassingment.databinding.FragmentRepositoryBinding

class RepositoryFragment : Fragment() {
    private var _binding: FragmentRepositoryBinding? = null
    private val binding get() = _binding ?: error("Binding이 초기화 되지 않았습니다.")
    private var _repositoryAdapter: RepositoryAdapter? = null
    private val repositoryAdapter get() = _repositoryAdapter ?: error("repositoryAdapter이 초기화 되지 않았습니다.")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRepositoryBinding.inflate(layoutInflater, container, false)

        initAdapter()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initAdapter(){
        _repositoryAdapter = RepositoryAdapter()
        binding.rvFollower.adapter = repositoryAdapter

        repositoryAdapter.repositoryList.addAll(
            listOf(
                RepositoryData("안드로이드 과제 레포지토리", "안드로이드 파트 과제"),
                RepositoryData("안드로이드 과제 레포지토리", "안드로이드 파트 과제"),
                RepositoryData("안드로이드 과제 레포지토리", "안드로이드 파트 과제"),
                RepositoryData("안드로이드 과제 레포지토리", "안드로이드 파트 과제"),
                RepositoryData("안드로이드 과제 레포지토리", "안드로이드 파트 과제"),
                RepositoryData("안드로이드 과제 레포지토리", "안드로이드 파트 과제"),
                RepositoryData("안드로이드 과제 레포지토리", "안드로이드 파트 과제"),
                RepositoryData("안드로이드 과제 레포지토리", "안드로이드 파트 과제"),
                RepositoryData("안드로이드 과제 레포지토리", "안드로이드 파트 과제"),
                RepositoryData("안드로이드 과제 레포지토리", "안드로이드 파트 과제")
            )
        )

        repositoryAdapter.notifyDataSetChanged()
    }
}