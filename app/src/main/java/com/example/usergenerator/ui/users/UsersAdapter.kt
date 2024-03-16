package com.example.usergenerator.ui.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.usergenerator.databinding.ItemUserBinding
import com.example.usergenerator.domain.models.UserBriefInfo

class UsersAdapter(
    private val onUserClickListener: (UserBriefInfo) -> Unit
) : Adapter<UsersViewHolder>() {

    private val users = mutableListOf<UserBriefInfo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemUserBinding.inflate(layoutInflater, parent, false)
        return UsersViewHolder(binding)
    }

    override fun getItemCount() = users.size

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        holder.bind(users[position])
        holder.itemView.setOnClickListener {
            onUserClickListener.invoke(users[position])
        }
    }

    fun setUsersList(newUserList: List<UserBriefInfo>) {
        users.clear()
        users.addAll(newUserList)
        notifyDataSetChanged()
    }
}