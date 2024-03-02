package itacademy.exam5.service.impl;

import itacademy.exam5.dto.ParkingSpotDto;
import itacademy.exam5.entity.ParkingSpot;
import itacademy.exam5.entity.User;
import itacademy.exam5.enums.ParkingSpotStatus;
import itacademy.exam5.enums.ParkingSpotType;
import itacademy.exam5.repo.ParkingSpotRepo;
import itacademy.exam5.service.ParkingSpotService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ParkingSpotServiceImpl implements ParkingSpotService {
    private final ParkingSpotRepo repo;

    @Override
    public ParkingSpotDto create(ParkingSpotDto model) {
        ParkingSpot spot = ParkingSpot.builder()
                .spotNumber(model.getSpotNumber())
                .status(model.getStatus())
                .type(model.getType())
                .build();
        repo.save(spot); // нужно обработать ошибку
        return model;
    }

    @Override
    public ParkingSpotDto getById(Long id) {
        ParkingSpot spot = repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Parking spot not found with id: " + id));
        ParkingSpotDto model = ParkingSpotDto.builder()
                .id(spot.getId())
                .spotNumber(spot.getSpotNumber())
                .status(spot.getStatus())
                .type(spot.getType())
                .build();
        return model;
    }

    @Override
    public List<ParkingSpotDto> getAll() {
        List<ParkingSpot> spots = repo.findAll();
        List<ParkingSpotDto> models = new ArrayList<>();
        for (ParkingSpot spot : spots) {
            ParkingSpotDto model;
            if (spot.getUser() != null) {
                model = ParkingSpotDto.builder()
                        .id(spot.getId())
                        .spotNumber(spot.getSpotNumber())
                        .status(spot.getStatus())
                        .type(spot.getType())
                        .userId(spot.getUser().getId())
                        .build();
            } else {
                model = ParkingSpotDto.builder()
                        .id(spot.getId())
                        .spotNumber(spot.getSpotNumber())
                        .status(spot.getStatus())
                        .type(spot.getType())
                        .build();
            }
            models.add(model);
        }
        return models;
    }

    @Override
    public ParkingSpotDto update(ParkingSpotDto model) {
        ParkingSpot spot = repo.findById(model.getId()).orElseThrow(() -> new EntityNotFoundException("Parking spot not found with id: " + model.getId()));
        if (model.getSpotNumber() != null) {
            spot.setSpotNumber(model.getSpotNumber());
        }
        if (model.getStatus() != null) {
            spot.setStatus(model.getStatus());
        }
        if (model.getType() != null) {
            spot.setType(model.getType());
        }
        repo.save(spot);
        return model;
    }

    @Override
    public void delete(Long id) {
        ParkingSpot spot = repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Parking spot not found with id: " + id));
        if (spot.getUser() != null) {
            spot.setUser(null);
        }
        repo.delete(spot);
    }

    @Override
    public ParkingSpotDto book(ParkingSpotDto model) {
        ParkingSpot spot = repo.findBySpotNumber(model.getSpotNumber()).orElseThrow(() -> new EntityNotFoundException("Parking spot not found with spotNumber: " + model.getSpotNumber()));
        if (spot.getStatus().equals(ParkingSpotStatus.FREE)) {
            spot.setStatus(ParkingSpotStatus.OCCUPIED);
            spot.setUser(new User(model.getUserId()));
            repo.save(spot);
            model.setId(spot.getId());
            model.setStatus(spot.getStatus());
            model.setType(spot.getType());
            return model;
        } else {
            throw new IllegalArgumentException("Parking spot has already booked");
        }
    }

    @Override
    public ParkingSpotDto release(ParkingSpotDto model) {
        ParkingSpot spot = repo.findBySpotNumber(model.getSpotNumber()).orElseThrow(() -> new EntityNotFoundException("Parking spot not found with id: " + model.getSpotNumber()));
        if (spot.getStatus().equals(ParkingSpotStatus.OCCUPIED)) {
            spot.setStatus(ParkingSpotStatus.FREE);
            spot.setUser(null);
            repo.save(spot);
            model.setId(spot.getId());
            model.setStatus(spot.getStatus());
            model.setType(spot.getType());
            model.setUserId(null);
            return model;
        } else {
            throw new IllegalArgumentException("Parking spot has already free");//!!!
        }
    }

    @Override
    public List<ParkingSpotDto> getAllFilterByType(String type) {
        List<ParkingSpotDto> models = new ArrayList<>();
        List<ParkingSpot> spots;
        switch (type.toLowerCase()) {
            case "standard":
                spots = repo.findParkingSpotsByType(ParkingSpotType.STANDARD);
                break;
            case "disabled":
                spots = repo.findParkingSpotsByType(ParkingSpotType.DISABLED);
                break;
            case "family":
                spots = repo.findParkingSpotsByType(ParkingSpotType.FAMILY);
                break;
            case "electric vehicles":
                spots = repo.findParkingSpotsByType(ParkingSpotType.ELECTRIC_VEHICLES);
                break;
            default:
                throw new IllegalArgumentException("Type " + type + " not found");//!!!
        }
        if (spots.isEmpty()) {
            throw new EntityNotFoundException("List of spots with this type are empty");
        } else {
            for (ParkingSpot spot : spots) {
                ParkingSpotDto model;
                if (spot.getUser() != null) {
                    model = ParkingSpotDto.builder()
                            .id(spot.getId())
                            .spotNumber(spot.getSpotNumber())
                            .status(spot.getStatus())
                            .type(spot.getType())
                            .userId(spot.getUser().getId())
                            .build();
                } else {
                    model = ParkingSpotDto.builder()
                            .id(spot.getId())
                            .spotNumber(spot.getSpotNumber())
                            .status(spot.getStatus())
                            .type(spot.getType())
                            .build();
                }
                models.add(model);
            }
            return models;
        }
    }
}