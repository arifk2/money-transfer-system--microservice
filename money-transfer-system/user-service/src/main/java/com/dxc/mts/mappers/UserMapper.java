package com.dxc.mts.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.dxc.mts.dto.UserDto;
import com.dxc.mts.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

	@Mapping(source = "user.userId", target = "id")
	@Mapping(source = "user.emailAddress", target = "emailAddress")
	@Mapping(source = "token", target = "token")
	UserDto toUserDto(User user, String token);
}
