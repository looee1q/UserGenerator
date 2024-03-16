package com.example.usergenerator.ui.userdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.usergenerator.databinding.FragmentUserDetailsBinding

class UserDetailsFragment : Fragment() {

    private var _binding: FragmentUserDetailsBinding? = null
    private val binding: FragmentUserDetailsBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}