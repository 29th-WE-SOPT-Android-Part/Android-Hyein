package org.sopt.androidassingment.ui.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import org.sopt.androidassingment.R
import org.sopt.androidassingment.databinding.FragmentBoarding2Binding

class BoardingFragment2 : Fragment() {
    private var _binding: FragmentBoarding2Binding? = null
    private val binding get() = _binding ?: error("Binding이 초기화 되지 않았습니다.")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBoarding2Binding.inflate(layoutInflater, container, false)

        clickBtn()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun clickBtn(){
        binding.btnNext2.setOnClickListener{
            findNavController().navigate(R.id.action_boardingFragment2_to_boardingFragment3)
        }
    }
}