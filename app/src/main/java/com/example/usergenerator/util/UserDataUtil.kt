package com.example.usergenerator.util

import android.content.res.Resources
import com.example.usergenerator.R
import com.example.usergenerator.domain.models.UserBriefInfo
import com.example.usergenerator.domain.models.UserDetails
import java.time.LocalDate
import java.time.format.DateTimeFormatter


fun UserDetails.getFullName() = "$firstName $lastName"

fun UserBriefInfo.getFullName() = "$firstName $lastName"

fun UserBriefInfo.getAddress() = "$country, $state, $city, $street, $streetNumber"

fun convertDateOfBirthdayFormat(dateOfBirthdayFromApi: String): String {
    val formatterApi = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS")
    val date = LocalDate.parse(dateOfBirthdayFromApi.removeSuffix("Z"), formatterApi)
    val formatterToShow = DateTimeFormatter.ofPattern("dd.MM.yyyy")
    return date.format(formatterToShow)
}

fun convertGender(resources: Resources, gender: String): String {
    return if (gender == resources.getString(R.string.male)) {
        resources.getString(R.string.male_sex)
    } else if (gender == resources.getString(R.string.female)){
        resources.getString(R.string.female_sex)
    } else {
        ""
    }
}

fun getNationality(nationality: String): String {
    return if (nationalitiesApi.containsKey(nationality)) {
        nationalitiesApi.getValue(nationality)
    } else {
        nationality
    }
}

private val nationalitiesApi = mapOf(
    "AU" to "Австралия",
    "BR" to "Бразилия",
    "CA" to "Канада",
    "CH" to "Швейцария",
    "DE" to "Германия",
    "DK" to "Дания",
    "ES" to "Испания",
    "FI" to "Финдляндия",
    "FR" to "Франция",
    "GB" to "Соединенное Королевство",
    "IE" to "Ирландия",
    "IN" to "Индия",
    "IR" to "Иран",
    "MX" to "Мексика",
    "NL" to "Нидерланды",
    "NO" to "Норвегия",
    "NZ" to "Новая Зеландия",
    "RS" to "Сербия",
    "UA" to "Украина",
    "US" to "Соединные штаты",
)