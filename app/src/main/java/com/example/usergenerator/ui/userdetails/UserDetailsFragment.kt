package com.example.usergenerator.ui.userdetails

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun render(userDetailsState: UserDetailsState) {
        when(userDetailsState) {
            is UserDetailsState.Content -> renderContent(userDetailsState.data)
            UserDetailsState.Error -> Log.d("UserDetailsFragment", "Показываю ошибку на экране деталей пользователя!!!")
            UserDetailsState.Loading -> Log.d("UserDetailsFragment", "Показываю загрузку на экране деталей пользователя!!!")
        }
    }

    private fun renderContent(userDetails: UserDetails) {
        with(binding) {
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

    companion object {

        private const val USER_ID = "USER_ID"

        fun createArgs(userId: Int) : Bundle {
            return bundleOf(USER_ID to userId)
        }
    }
}