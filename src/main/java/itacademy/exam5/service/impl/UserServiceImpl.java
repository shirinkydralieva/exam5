package itacademy.exam5.service.impl;

import itacademy.exam5.dto.UserDto;
import itacademy.exam5.entity.User;
import itacademy.exam5.repo.UserRepo;
import itacademy.exam5.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo repo;

    @Override
    public UserDto create(UserDto model) {
        User user = User.builder()
                .fullName(model.getFullName())
                .build();
        repo.save(user); //обработать ошибку
        return model;
    }

    @Override
    public UserDto getById(Long id) {
        User user = repo.findById(id).orElseThrow(()-> new EntityNotFoundException("User not found with id: " + id));
        UserDto model = UserDto.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .build();
        return model;
    }

    @Override
    public List<UserDto> getAll() {
        List<User> users = repo.findAll();
        List<UserDto> models = new ArrayList<>();
        for (User user: users){
            UserDto model = UserDto.builder()
                    .id(user.getId())
                    .fullName(user.getFullName())
                    .build();
            models.add(model);
        }
        return models;
    }

    @Override
    public UserDto update(UserDto model) {
        User user = repo.findById(model.getId()).orElseThrow(()-> new EntityNotFoundException("User not found with id: " + model.getId()));
        if (model.getFullName() != null){
            user.setFullName(model.getFullName());
        }
        repo.save(user);
        return model;
    }

    @Override
    public void delete(Long id) {
        User user = repo.findById(id).orElseThrow(()-> new EntityNotFoundException("User not found with id: " + id));
        repo.delete(user);
    }
}
