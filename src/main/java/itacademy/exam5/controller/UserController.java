package itacademy.exam5.controller;

import itacademy.exam5.dto.ParkingSpotDto;
import itacademy.exam5.dto.UserDto;
import itacademy.exam5.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService service;
    //Пока без обработки ошибок
    @PostMapping("/create")
    public UserDto create(@RequestBody UserDto model){
        return service.create(model);
    }

    @GetMapping("/{id}")
    public UserDto getById(@PathVariable Long id){
        return service.getById(id);
    }

    @GetMapping()
    public List<UserDto> getAll(){
        return service.getAll();
    }

    @PutMapping("/update")
    public UserDto update(@RequestBody UserDto model){
        return service.update(model);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam Long id){
        service.delete(id);
    }
}
