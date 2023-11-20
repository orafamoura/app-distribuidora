package com.projetoapptabacaria.webtabaca.mapper;

import com.projetoapptabacaria.webtabaca.model.User;
import com.projetoapptabacaria.webtabaca.shared.UserDTO;
import com.projetoapptabacaria.webtabaca.view.controller.model.user.UserRequest;
import com.projetoapptabacaria.webtabaca.view.controller.model.user.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO userToUserDto(User user);

    User toUser(UserDTO userDTO);
    @Mapping(target = "id", ignore = true)
    void updateUserFromDto(UserDTO userDTO, @MappingTarget User user);

    UserResponse userDtoToUserResponse(UserDTO userDTO);

    UserDTO userRequestToUserDTO(UserRequest userRequest);

}
