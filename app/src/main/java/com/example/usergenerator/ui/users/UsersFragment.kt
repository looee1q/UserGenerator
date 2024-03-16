package com.example.usergenerator.ui.users

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.usergenerator.R
import com.example.usergenerator.databinding.FragmentUsersBinding
import com.example.usergenerator.domain.models.UserBriefInfo
import com.example.usergenerator.presentation.state.State
import com.example.usergenerator.presentation.users.UsersViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class UsersFragment : Fragment() {

    private var _binding: FragmentUsersBinding? = null
    private val binding: FragmentUsersBinding get() = _binding!!

    private val viewModel by viewModel<UsersViewModel>()

    private lateinit var adapter: UsersAdapter

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

        adapter = UsersAdapter {  }
        binding.usersRecyclerView.adapter = adapter
        binding.usersRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        binding.uploadUsersButton.setOnClickListener {
            viewModel.getUsers()
        }

        viewModel.stateLiveData.observe(viewLifecycleOwner) {
            render(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun render(state: State<List<UserBriefInfo>>) {
        when(state) {
            is State.Content -> renderContent(state)
            is State.Error -> renderError()
            is State.Loading -> renderLoading()
            is State.NoInternet -> renderNoInternet()
        }
    }

    private fun renderContent(state: State<List<UserBriefInfo>>) {
        binding.usersRecyclerView.isVisible = true
        binding.errorHolder.isVisible = false
        binding.progressBar.isVisible = false
        adapter.setUsersList((state as State.Content).data)
    }

    private fun renderLoading() {
        binding.usersRecyclerView.isVisible = false
        binding.errorHolder.isVisible = false
        binding.progressBar.isVisible = true
    }

    private fun renderError() {
        binding.usersRecyclerView.isVisible = false
        binding.errorHolder.isVisible = true
        binding.progressBar.isVisible = false
        binding.imageError.setImageResource(R.drawable.png_error)
        binding.messageError.text = resources.getString(R.string.server_error)
        binding.checkInternetConnection.isVisible = false
    }

    private fun renderNoInternet() {
        binding.usersRecyclerView.isVisible = false
        binding.errorHolder.isVisible = true
        binding.progressBar.isVisible = false
        binding.imageError.setImageResource(R.drawable.png_no_internet)
        binding.messageError.text = resources.getString(R.string.no_internet)
        binding.checkInternetConnection.isVisible = true
    }
}