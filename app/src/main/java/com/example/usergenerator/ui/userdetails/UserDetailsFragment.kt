package com.example.usergenerator.ui.userdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.usergenerator.R
import com.example.usergenerator.databinding.FragmentUserDetailsBinding
import com.example.usergenerator.domain.models.UserDetails
import com.example.usergenerator.presentation.userdetails.UserDetailsViewModel
import com.example.usergenerator.presentation.userdetails.state.UserDetailsState
import com.example.usergenerator.util.convertDateOfBirthdayFormat
import com.example.usergenerator.util.convertGender
import com.example.usergenerator.util.getFullName
import com.example.usergenerator.util.getNationality
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

        binding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.phoneValue.setOnClickListener {
            viewModel.openContactsApp(
                (viewModel.stateLiveData.value as UserDetailsState.Content).data.phone
            )
        }

        binding.cellPhoneValue.setOnClickListener {
            viewModel.openContactsApp(
                (viewModel.stateLiveData.value as UserDetailsState.Content).data.cellPhone
            )
        }

        binding.emailValue.setOnClickListener {
            viewModel.openEmailApp(
                (viewModel.stateLiveData.value as UserDetailsState.Content).data.email
            )
        }

        binding.addressValue.setOnClickListener {
            viewModel.openMapApp(
                latitude = (viewModel.stateLiveData.value as UserDetailsState.Content).data.locationLatitude,
                longitude = (viewModel.stateLiveData.value as UserDetailsState.Content).data.locationLongitude
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun render(userDetailsState: UserDetailsState) {
        when (userDetailsState) {
            is UserDetailsState.Content -> renderContent(userDetailsState.data)
            UserDetailsState.Error -> renderError()
            UserDetailsState.Loading -> renderLoading()
        }
    }

    private fun renderContent(userDetails: UserDetails) {
        with(binding) {
            userPhoto.isVisible = true
            scrollViewWithUserDetails.isVisible = true
            progressBar.isVisible = false
            errorHolder.isVisible = false
            Glide.with(this@UserDetailsFragment)
                .load(userDetails.picture)
                .placeholder(R.drawable.user_mock)
                .circleCrop()
                .into(userPhoto)
            fullName.text = userDetails.getFullName()
            usernameValue.text = userDetails.username
            dateOfBirthdayValue.text = convertDateOfBirthdayFormat(userDetails.dateOfBirthday)
            ageValue.text = userDetails.age
            nationValue.text = getNationality(userDetails.nation)
            genderValue.text = convertGender(resources, userDetails.gender)
            countryValue.text = userDetails.country
            regionValue.text = userDetails.state
            cityValue.text = userDetails.city
            addressValue.text = resources.getString(R.string.address_in_short_form)
                .format(userDetails.street, userDetails.streetNumber)
            emailValue.text = userDetails.email
            phoneValue.text = userDetails.phone
            cellPhoneValue.text = userDetails.cellPhone
        }
    }

    private fun renderLoading() {
        binding.userPhoto.isVisible = false
        binding.scrollViewWithUserDetails.isVisible = false
        binding.progressBar.isVisible = true
        binding.errorHolder.isVisible = false
    }

    private fun renderError() {
        binding.userPhoto.isVisible = false
        binding.scrollViewWithUserDetails.isVisible = false
        binding.progressBar.isVisible = false
        binding.errorHolder.isVisible = true
    }

    companion object {

        private const val USER_ID = "USER_ID"

        fun createArgs(userId: Int): Bundle {
            return bundleOf(USER_ID to userId)
        }
    }
}