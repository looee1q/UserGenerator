package com.example.usergenerator.ui.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.usergenerator.R
import com.example.usergenerator.databinding.FragmentUsersBinding
import com.example.usergenerator.domain.models.UserBriefInfo
import com.example.usergenerator.presentation.state.State
import com.example.usergenerator.presentation.users.UsersViewModel
import com.example.usergenerator.ui.userdetails.UserDetailsFragment
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

        adapter = UsersAdapter {
            findNavController().navigate(
                R.id.action_usersFragment_to_userDetailsFragment,
                UserDetailsFragment.createArgs(it.id)
            )
        }
        binding.usersRecyclerView.adapter = adapter
        binding.usersRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        binding.uploadUsersButton.setOnClickListener {
            viewModel.getUsersFromNetwork()
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
            is State.Content -> renderContent(state.data)
            is State.ErrorNetwork -> renderErrorNetwork()
            is State.ErrorDatabase -> renderErrorDatabase()
            is State.Loading -> renderLoading()
            is State.NoInternet -> renderNoInternet()
            is State.FirstStart -> renderEmpty()
        }
    }

    private fun renderContent(users: List<UserBriefInfo>) {
        binding.usersRecyclerView.isVisible = true
        binding.errorHolder.isVisible = false
        binding.progressBar.isVisible = false
        adapter.setUsersList(users)
    }

    private fun renderLoading() {
        binding.usersRecyclerView.isVisible = false
        binding.errorHolder.isVisible = false
        binding.progressBar.isVisible = true
    }

    private fun renderEmpty() {
        binding.usersRecyclerView.isVisible = false
        binding.errorHolder.isVisible = true
        binding.progressBar.isVisible = false
        binding.imageError.setImageResource(R.drawable.png_greetings)
        binding.messageError.text = resources.getString(R.string.greetings)
        binding.messageErrorAnnotation.isVisible = false
    }

    private fun renderErrorNetwork() {
        binding.usersRecyclerView.isVisible = false
        binding.errorHolder.isVisible = true
        binding.progressBar.isVisible = false
        binding.imageError.setImageResource(R.drawable.png_error)
        binding.messageError.text = resources.getString(R.string.server_error)
        binding.messageErrorAnnotation.isVisible = false
    }

    private fun renderErrorDatabase() {
        binding.usersRecyclerView.isVisible = false
        binding.errorHolder.isVisible = true
        binding.progressBar.isVisible = false
        binding.imageError.setImageResource(R.drawable.png_error)
        binding.messageError.text = resources.getString(R.string.database_error)
        binding.messageErrorAnnotation.isVisible = true
        binding.messageErrorAnnotation.text = resources.getString(R.string.clear_cache)
    }

    private fun renderNoInternet() {
        binding.usersRecyclerView.isVisible = false
        binding.errorHolder.isVisible = true
        binding.progressBar.isVisible = false
        binding.imageError.setImageResource(R.drawable.png_no_internet)
        binding.messageError.text = resources.getString(R.string.no_internet)
        binding.messageErrorAnnotation.isVisible = true
        binding.messageErrorAnnotation.text = resources.getString(R.string.check_internet_connection)
    }
}