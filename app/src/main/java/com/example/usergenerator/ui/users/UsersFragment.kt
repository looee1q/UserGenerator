package com.example.usergenerator.ui.users

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.usergenerator.databinding.FragmentUsersBinding
import com.example.usergenerator.presentation.users.UsersViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class UsersFragment : Fragment() {

    private var _binding: FragmentUsersBinding? = null
    private val binding: FragmentUsersBinding get() = _binding!!

    private val viewModel by viewModel<UsersViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUsersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.uploadUsersButton.setOnClickListener {
            viewModel.getUsers()
        }

        viewModel.stateLiveData.observe(viewLifecycleOwner) {
            Log.d("UsersFragment","state is ${it}")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}