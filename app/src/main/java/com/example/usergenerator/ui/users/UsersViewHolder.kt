package com.example.usergenerator.ui.users

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.usergenerator.R
import com.example.usergenerator.databinding.ItemUserBinding
import com.example.usergenerator.domain.models.UserBriefInfo

class UsersViewHolder(private val binding: ItemUserBinding) : ViewHolder(binding.root) {

    fun bind(userBriefInfo: UserBriefInfo) {
        binding.fullName.text = userBriefInfo.getFullName()
        binding.address.text = userBriefInfo.getAddress()
        Glide.with(binding.root)
            .load(userBriefInfo.picture)
            .placeholder(R.drawable.user_mock)
            .circleCrop()
            .into(binding.photo)
    }
}