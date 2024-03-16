package com.example.usergenerator.ui.userdetails

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.usergenerator.databinding.FragmentUserDetailsBinding
import com.example.usergenerator.presentation.userdetails.UserDetailsViewModel
import com.example.usergenerator.presentation.userdetails.state.UserDetailsState
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserDetailsFragment : Fragment() {

    private var _binding: FragmentUserDetailsBinding? = null
    private val binding: FragmentUserDetailsBinding get() = _binding!!

    private val viewModel by viewModel<UserDetailsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userId = requireArguments().getInt(USER_ID)

        viewModel.getUserDetailsById(userId)

        viewModel.stateLiveData.observe(viewLifecycleOwner) {
            render(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun render(userDetailsState: UserDetailsState) {
        when(userDetailsState) {
            is UserDetailsState.Content -> Log.d("UserDetailsFragment", "Показываю детали о пользователе: ${userDetailsState.data}")
            UserDetailsState.Error -> Log.d("UserDetailsFragment", "Показываю ошибку на экране деталей пользователя!!!")
            UserDetailsState.Loading -> Log.d("UserDetailsFragment", "Показываю загрузку на экране деталей пользователя!!!")
        }
    }

    companion object {

        private const val USER_ID = "USER_ID"

        fun createArgs(userId: Int) : Bundle {
            return bundleOf(USER_ID to userId)
        }
    }
}