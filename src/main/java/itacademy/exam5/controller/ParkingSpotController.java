package itacademy.exam5.controller;

import itacademy.exam5.dto.ParkingSpotDto;
import itacademy.exam5.service.ParkingSpotService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/parking-spots")
public class ParkingSpotController {
    //Пока без обработки ошибок
    private final ParkingSpotService service;
    @PostMapping("/create")
    public ParkingSpotDto create(@RequestBody ParkingSpotDto model){
        return service.create(model);
    }

    @GetMapping("/{id}")
    public ParkingSpotDto getById(@PathVariable Long id){
        return service.getById(id);
    }

    @GetMapping()
    public List<ParkingSpotDto> getAll(){
        return service.getAll();
    }

    @PutMapping("/update")
    public ParkingSpotDto update(@RequestBody ParkingSpotDto model){
        return service.update(model);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam Long id){
        service.delete(id);
    }

    @PostMapping("/book")
    public ParkingSpotDto book(@RequestBody ParkingSpotDto model){
        return service.book(model);
    }

    @PostMapping("/release")
    public ParkingSpotDto release(@RequestBody ParkingSpotDto model){
        return service.book(model);
    }
}
