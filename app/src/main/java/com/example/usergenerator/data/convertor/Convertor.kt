package com.example.usergenerator.data.convertor

import com.example.usergenerator.data.dto.UserDto
import com.example.usergenerator.domain.models.UserBriefInfo

object Convertor {

    fun fromUserDtoToUserBriefInfo(userDto: UserDto): UserBriefInfo {
        return UserBriefInfo(
            firstName = userDto.name.first,
            lastName = userDto.name.last,
            streetNumber = userDto.location.street.number,
            street = userDto.location.street.name,
            city = userDto.location.city,
            state = userDto.location.state,
            country = userDto.location.country,
            smallPicture = userDto.picture.thumbnail,
        )
    }
}