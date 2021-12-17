package org.sopt.androidassingment.ui.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import org.sopt.androidassingment.R
import org.sopt.androidassingment.SignUpActivity
import org.sopt.androidassingment.databinding.FragmentBoarding3Binding

class BoardingFragment3 : Fragment() {
    private var _binding: FragmentBoarding3Binding? = null
    private val binding get() = _binding ?: error("Binding이 초기화 되지 않았습니다.")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBoarding3Binding.inflate(layoutInflater, container, false)

        startClick()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun startClick(){
        binding.btnStart.setOnClickListener{
            findNavController().navigate(R.id.action_boardingFragment3_to_signInActivity)
            requireActivity().finish()
        }
    }
}