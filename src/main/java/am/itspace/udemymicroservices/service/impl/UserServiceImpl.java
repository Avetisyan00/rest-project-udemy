package am.itspace.udemymicroservices.service.impl;

import am.itspace.udemymicroservices.dto.UserDto;
import am.itspace.udemymicroservices.entity.User;
import am.itspace.udemymicroservices.exception.EmailAlreadyExistsException;
import am.itspace.udemymicroservices.exception.ResourceNotFoundException;
import am.itspace.udemymicroservices.mapper.UserMapper;
import am.itspace.udemymicroservices.repository.UserRepository;
import am.itspace.udemymicroservices.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto userDto) {
        Optional<User> optionalUser = userRepository.findUserByEmail(userDto.getEmail());
        if (optionalUser.isPresent()){
            throw new EmailAlreadyExistsException("User with this email already exists");
        }
        User user = UserMapper.USER_MAPPER.map(userDto);
        User savedUser = userRepository.save(user);
        return UserMapper.USER_MAPPER.map(savedUser);
    }

    @Override
    public UserDto findById(Long id) {
        User byId = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", id)
        );
        return UserMapper.USER_MAPPER.map(byId);
    }

    @Override
    public List<UserDto> findAll() {
        List<User> userList = userRepository.findAll();
        return userList.stream().map(UserMapper.USER_MAPPER::map).toList();
    }

    @Override
    public UserDto updateUser(UserDto user) {
        Optional<User> byId = userRepository.findById(user.getId());
        if (byId.isPresent()) {
            User update = byId.get();
            update.setFirstname(user.getFirstname());
            update.setLastname(user.getLastname());
            update.setEmail(user.getEmail());
            userRepository.save(update);
            return UserMapper.USER_MAPPER.map(update);
        }
        return null;
    }

    @Override
    public void deleteUserById(Long id) {
        Optional<User> byId = userRepository.findById(id);
        byId.ifPresent(userRepository::delete);
    }
}
