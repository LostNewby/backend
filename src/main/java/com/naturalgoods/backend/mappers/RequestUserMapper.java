package com.naturalgoods.backend.mappers;

import com.naturalgoods.backend.account.UserEntity;
import com.naturalgoods.backend.dto.RequestUserDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RequestUserMapper {
    RequestUserMapper MAPPER=Mappers.getMapper(RequestUserMapper.class);

    UserEntity mapToEntity(RequestUserDto userDto);
}
