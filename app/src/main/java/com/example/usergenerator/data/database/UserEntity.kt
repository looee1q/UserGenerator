package com.example.usergenerator.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val gender: String,
    @ColumnInfo(name = "first_name")
    val firstName: String,
    @ColumnInfo(name = "last_name")
    val lastName: String,
    @ColumnInfo(name = "street_number")
    val streetNumber: String,
    val street: String,
    val city: String,
    val state: String,
    val country: String,
    @ColumnInfo(name = "location_latitude")
    val locationLatitude: String,
    @ColumnInfo(name = "location_longitude")
    val locationLongitude: String,
    val email: String,
    val username: String,
    @ColumnInfo(name = "date_of_birthday")
    val dateOfBirthday: String,
    val age: String,
    val phone: String,
    @ColumnInfo(name = "cell_phone")
    val cellPhone: String,
    val picture: String,
    val nation: String
)