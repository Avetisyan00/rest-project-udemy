package am.itspace.udemymicroservices.service;


import am.itspace.udemymicroservices.dto.UserDto;
import am.itspace.udemymicroservices.entity.User;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto user);
    UserDto findById(Long id);
    List<UserDto> findAll();
    UserDto updateUser(UserDto user);
    void deleteUserById(Long id);
}
