package com.zibran.learningprojects.music_app.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.zibran.learningprojects.R
import com.zibran.learningprojects.databinding.FragmentSignUpGenderBinding


class SignUpGenderFragment : Fragment() {
    lateinit var binding: FragmentSignUpGenderBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSignUpGenderBinding.inflate(layoutInflater)

        binding.next.setOnClickListener {
            findNavController().navigate(R.id.action_signUpGenderFragment_to_signUpNameFragment)
        }
        return binding.root
    }
}
