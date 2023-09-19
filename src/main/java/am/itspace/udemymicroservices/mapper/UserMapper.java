package am.itspace.udemymicroservices.mapper;

import am.itspace.udemymicroservices.dto.UserDto;
import am.itspace.udemymicroservices.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper USER_MAPPER = Mappers.getMapper(UserMapper.class);
    User map(UserDto userDto);
    UserDto map(User user);
}
