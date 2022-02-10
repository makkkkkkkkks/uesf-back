package org.ua.uesf.mapper;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.ua.uesf.model.User;
import org.ua.uesf.model.dto.user.RegistrationDto;

@Mapper(builder = @Builder(disableBuilder = true))
public interface UserMapper {

    @Mapping(target = "userName", source = "registrationDto.userName")
    @Mapping(target = "firstName", source = "registrationDto.firstName")
    @Mapping(target = "lastName", source = "registrationDto.lastName")
    @Mapping(target = "email", source = "registrationDto.email")
    @Mapping(target = "img", source = "registrationDto.img")
    User fromDtoToUser(RegistrationDto registrationDto);
}
