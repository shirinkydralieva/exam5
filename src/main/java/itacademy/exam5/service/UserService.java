package itacademy.exam5.service;

import itacademy.exam5.dto.ParkingSpotDto;
import itacademy.exam5.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto create(UserDto model);
    UserDto getById(Long id);
    List<UserDto> getAll();
    UserDto update(UserDto model);
    void delete(Long id);
}
