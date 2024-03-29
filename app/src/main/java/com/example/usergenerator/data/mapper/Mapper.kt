package com.example.usergenerator.data.mapper

import com.example.usergenerator.data.database.UserEntity
import com.example.usergenerator.data.dto.UserDto
import com.example.usergenerator.domain.models.UserBriefInfo
import com.example.usergenerator.domain.models.UserDetails

class Mapper {

    fun fromUserDtoToUserEntity(userDto: UserDto): UserEntity {
        return UserEntity(
            gender = userDto.gender,
            firstName = userDto.name.first,
            lastName = userDto.name.last,
            streetNumber = userDto.location.street.number,
            street = userDto.location.street.name,
            city = userDto.location.city,
            state = userDto.location.state,
            country = userDto.location.country,
            locationLatitude = userDto.location.coordinates.latitude,
            locationLongitude = userDto.location.coordinates.longitude,
            email = userDto.email,
            username = userDto.login.username,
            dateOfBirthday = userDto.dob.date,
            age = userDto.dob.age,
            phone = userDto.phone,
            cellPhone = userDto.cell,
            picture = userDto.picture.large,
            nation = userDto.nat
        )
    }

    fun fromUserEntityToUserBriefInfo(userEntity: UserEntity): UserBriefInfo {
        return UserBriefInfo(
            id = userEntity.id,
            firstName = userEntity.firstName,
            lastName = userEntity.lastName,
            streetNumber = userEntity.streetNumber,
            street = userEntity.street,
            city = userEntity.city,
            state = userEntity.state,
            country = userEntity.country,
            cellPhone = userEntity.cellPhone,
            picture = userEntity.picture,
        )
    }

    fun fromUserEntityToUserDetails(userEntity: UserEntity): UserDetails {
        return UserDetails(
            id = userEntity.id,
            gender = userEntity.gender,
            firstName = userEntity.firstName,
            lastName = userEntity.lastName,
            streetNumber = userEntity.streetNumber,
            street = userEntity.street,
            city = userEntity.city,
            state = userEntity.state,
            country = userEntity.country,
            locationLatitude = userEntity.locationLatitude,
            locationLongitude = userEntity.locationLongitude,
            email = userEntity.email,
            username = userEntity.username,
            dateOfBirthday = userEntity.dateOfBirthday,
            age = userEntity.age,
            phone = userEntity.phone,
            cellPhone = userEntity.cellPhone,
            picture = userEntity.picture,
            nation = userEntity.nation
        )
    }
}