package com.example.usergenerator.data.repository

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.example.usergenerator.domain.repository.ExternalNavigationRepository

class ExternalNavigationRepositoryImpl(
    private val context: Context
) : ExternalNavigationRepository {

    override fun openContactsApp(phoneNumber: String) {
        val phoneIntent = Intent(Intent.ACTION_DIAL).also {
            it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            it.data = Uri.parse("tel:$phoneNumber")
        }
        context.startActivity(phoneIntent)
    }

    override fun openEmailApp(email: String) {
        val emailIntent = Intent(Intent.ACTION_SENDTO).also {
            it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            it.data = Uri.parse("mailto:$email")
        }
        context.startActivity(emailIntent)
    }

    override fun openMapApp(latitude: String, longitude: String) {
        val mapIntent = Intent(Intent.ACTION_VIEW).also {
            it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            it.data = Uri.parse("geo:${latitude.toDouble()},${longitude.toDouble()}")
        }
        context.startActivity(mapIntent)
    }
}