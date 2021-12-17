package org.sopt.androidassingment.ui.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import org.sopt.androidassingment.R
import org.sopt.androidassingment.databinding.FragmentBoarding1Binding

class BoardingFragment1 : Fragment() {
    private var _binding: FragmentBoarding1Binding? = null
    private val binding get() = _binding ?: error("Binding이 초기화 되지 않았습니다.")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBoarding1Binding.inflate(layoutInflater, container, false)

        clickBtn()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun clickBtn(){
        binding.btnNext1.setOnClickListener{
            findNavController().navigate(R.id.action_boardingFragment1_to_boardingFragment2)
        }
    }
}