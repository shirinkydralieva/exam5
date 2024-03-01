package itacademy.exam5.service;

import itacademy.exam5.dto.ParkingSpotDto;

import java.util.List;

public interface ParkingSpotService {
    ParkingSpotDto create(ParkingSpotDto model);
    ParkingSpotDto getById(Long id);
    List<ParkingSpotDto> getAll();
    ParkingSpotDto update(ParkingSpotDto model);
    void delete(Long id);
    ParkingSpotDto book(ParkingSpotDto model);
    ParkingSpotDto release(ParkingSpotDto model);
}
