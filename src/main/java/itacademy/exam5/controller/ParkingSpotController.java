package itacademy.exam5.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import itacademy.exam5.dto.ParkingSpotDto;
import itacademy.exam5.dto.ResponseMessageAPI;
import itacademy.exam5.enums.ResultCode;
import itacademy.exam5.enums.ResultCodeAPI;
import itacademy.exam5.service.ParkingSpotService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "ParkingSpot", description = "API для парковочных мест")
@RestController
@RequiredArgsConstructor
@RequestMapping("/parking-spots")
public class ParkingSpotController {
    private final ParkingSpotService service;
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Парковочное место успешно создано",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ParkingSpotDto.class))
                    }
            )
    }
    )
    @Operation(
            summary = "Создание парковочного места",
            description = "Возвращает созданное парковочное место в формате json."
    )
    @PostMapping("/create")
    public ResponseMessageAPI<ParkingSpotDto> create(@RequestBody ParkingSpotDto model){
        try {
            return new ResponseMessageAPI<>(
                    service.create(model),
                    ResultCodeAPI.SUCCESS,
                    null,
                    "Парковочное место успешно создано!",
                    ResultCode.OK.getHttpCode()
            );
        } catch (Exception e){
            System.out.printf("ParkingSpotController: create() %s%n", e);
            return new ResponseMessageAPI<>(
                    null,
                    ResultCodeAPI.EXCEPTION, e.getClass().getSimpleName(),
                    "Ошибка при создании парковочного места.",
                    ResultCode.FAIL.getHttpCode()
            );
        }
    }
    // ----------------------------------------------------------------------------------
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Парковочное место успешно получено по id",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ParkingSpotDto.class))
                    }
            )
    }
    )
    @Operation(
            summary = "Получение парковочного места по id",
            description = "Возвращает парковочное место в формате json."
    )
    @GetMapping("/{id}")
    public ResponseMessageAPI<ParkingSpotDto> getById(@PathVariable Long id){
        try {
            return new ResponseMessageAPI<>(
                    service.getById(id),
                    ResultCodeAPI.SUCCESS,
                    null,
                    "Парковочное место успешно получено!",
                    ResultCode.OK.getHttpCode()
            );
        } catch (EntityNotFoundException exception) {
            System.out.println(exception.getMessage());
            return new ResponseMessageAPI<>(
                    null,
                    ResultCodeAPI.FAIL,
                    exception.getClass().getSimpleName(),
                    "Парковочное место с таким id не найдено",
                    ResultCode.NOT_FOUND.getHttpCode()
            );
        } catch (Exception e) {
            System.out.printf("ParkingSpotController: getById() %s%n", e);
            return new ResponseMessageAPI<>(
                    null,
                    ResultCodeAPI.EXCEPTION, e.getClass().getSimpleName(),
                    "Ошибка при получении парковочного места по id",
                    ResultCode.FAIL.getHttpCode()
            );
        }
    }
    // ----------------------------------------------------------------------------------
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Список всех парковочных мест успешно получен",
                    content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ParkingSpotDto.class))
                            )
                    }
            )
    }
    )
    @Operation(
            summary = "Получение всех парковочных мест",
            description = "Возвращает список  парковочных мест в формате json."
    )
    @GetMapping()
    public ResponseMessageAPI<List<ParkingSpotDto>> getAll(){
        try {
            return new ResponseMessageAPI<>(
                    service.getAll(),
                    ResultCodeAPI.SUCCESS,
                    null,
                    "Список парковочных мест успешно получен!",
                    ResultCode.OK.getHttpCode()
            );
        } catch (Exception e) {
            System.out.printf("ParkingSpotController: getAll() %s%n", e);
            return new ResponseMessageAPI<>(
                    null,
                    ResultCodeAPI.EXCEPTION, e.getClass().getSimpleName(),
                    "Ошибка при получении списка парковочных мест",
                    ResultCode.FAIL.getHttpCode()
            );
        }
    }
    // ----------------------------------------------------------------------------------
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Парковочное место успешно обновлено",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ParkingSpotDto.class))
                    }
            )
    }
    )
    @Operation(
            summary = "Обновление парковочного места",
            description = "Возвращает обновленное парковочное место в формате json."
    )
    @PutMapping("/update")
    public ResponseMessageAPI<ParkingSpotDto> update(@RequestBody ParkingSpotDto model){
        try {
            return new ResponseMessageAPI<>(
                    service.update(model),
                    ResultCodeAPI.SUCCESS,
                    null,
                    "Парковочное место успешно обновлено!",
                    ResultCode.OK.getHttpCode()
            );
        } catch (EntityNotFoundException exception) {
            System.out.println(exception.getMessage());
            return new ResponseMessageAPI<>(
                    null,
                    ResultCodeAPI.FAIL,
                    exception.getClass().getSimpleName(),
                    "Парковочное место не найдено",
                    ResultCode.NOT_FOUND.getHttpCode()
            );
        } catch (Exception e) {
            System.out.printf("ParkingSpotController: update() %s%n", e);
            return new ResponseMessageAPI<>(
                    null,
                    ResultCodeAPI.EXCEPTION, e.getClass().getSimpleName(),
                    "Ошибка при обновлении парковочного места",
                    ResultCode.FAIL.getHttpCode()
            );
        }
    }
    // ----------------------------------------------------------------------------------
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Парковочное место успешно удалено"
            )
    }
    )
    @Operation(
            summary = "Удаление парковочного места",
            description = "Ничего не возвращает"
    )
    @DeleteMapping("/delete")
    public ResponseMessageAPI<Void> delete(@RequestParam Long id){
        try {
            service.delete(id);
            return new ResponseMessageAPI<>(
                    null,
                    ResultCodeAPI.SUCCESS,
                    null,
                    "Парковочное место успешно удалено!",
                    ResultCode.OK.getHttpCode()
            );
        } catch (EntityNotFoundException exception) {
            System.out.println(exception.getMessage());
            return new ResponseMessageAPI<>(
                    null,
                    ResultCodeAPI.FAIL,
                    exception.getClass().getSimpleName(),
                    "Парковочное место не найдено",
                    ResultCode.NOT_FOUND.getHttpCode()
            );
        } catch (Exception e) {
            System.out.printf("ParkingSpotController: delete() %s%n", e);
            return new ResponseMessageAPI<>(
                    null,
                    ResultCodeAPI.EXCEPTION, e.getClass().getSimpleName(),
                    "Ошибка при удалении парковочного места",
                    ResultCode.FAIL.getHttpCode()
            );
        }
    }
    // ----------------------------------------------------------------------------------
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Парковочное место успешно забронировано",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ParkingSpotDto.class))
                    }
            )
    }
    )
    @Operation(
            summary = "Бронирование парковочного места",
            description = "Возвращает забронированное парковочное место с обновленным статусом в формате json."
    )
    @PostMapping("/book")
    public ResponseMessageAPI<ParkingSpotDto> book(@RequestBody ParkingSpotDto model){
        try {
            return new ResponseMessageAPI<>(
                    service.book(model),
                    ResultCodeAPI.SUCCESS,
                    null,
                    "Парковочное место успешно забронировано!",
                    ResultCode.OK.getHttpCode()
            );
        } catch (EntityNotFoundException exception) {
            System.out.println(exception.getMessage());
            return new ResponseMessageAPI<>(
                    null,
                    ResultCodeAPI.FAIL,
                    exception.getClass().getSimpleName(),
                    "Парковочное место не найдено",
                    ResultCode.NOT_FOUND.getHttpCode()
            );
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            return new ResponseMessageAPI<>(
                    null,
                    ResultCodeAPI.FAIL,
                    exception.getClass().getSimpleName(),
                    "Парковочное место уже забронировано!",
                    ResultCode.FAIL.getHttpCode()
            );
        } catch (Exception e) {
            System.out.printf("ParkingSpotController: book() %s%n", e);
            return new ResponseMessageAPI<>(
                    null,
                    ResultCodeAPI.EXCEPTION, e.getClass().getSimpleName(),
                    "Ошибка при бронировании парковочного места",
                    ResultCode.FAIL.getHttpCode()
            );
        }
    }
    // ----------------------------------------------------------------------------------
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Парковочное место успешно освобождено",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ParkingSpotDto.class))
                    }
            )
    }
    )
    @Operation(
            summary = "Освобождение парковочного места",
            description = "Возвращает освобожденное парковочное место с обновленным статусом в формате json."
    )
    @PostMapping("/release")
    public ResponseMessageAPI<ParkingSpotDto> release(@RequestBody ParkingSpotDto model){
        try {
            return new ResponseMessageAPI<>(
                    service.release(model),
                    ResultCodeAPI.SUCCESS,
                    null,
                    "Парковочное место успешно освобождено!",
                    ResultCode.OK.getHttpCode()
            );
        } catch (EntityNotFoundException exception) {
            System.out.println(exception.getMessage());
            return new ResponseMessageAPI<>(
                    null,
                    ResultCodeAPI.FAIL,
                    exception.getClass().getSimpleName(),
                    "Парковочное место не найдено",
                    ResultCode.NOT_FOUND.getHttpCode()
            );
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            return new ResponseMessageAPI<>(
                    null,
                    ResultCodeAPI.FAIL,
                    exception.getClass().getSimpleName(),
                    "Парковочное место уже свободно!",
                    ResultCode.FAIL.getHttpCode()
            );
        } catch (Exception e) {
            System.out.printf("ParkingSpotController: release() %s%n", e);
            return new ResponseMessageAPI<>(
                    null,
                    ResultCodeAPI.EXCEPTION, e.getClass().getSimpleName(),
                    "Ошибка при освобождении парковочного места",
                    ResultCode.FAIL.getHttpCode()
            );
        }
    }
    // ----------------------------------------------------------------------------------
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Список всех парковочных мест по выбранному типу успешно получен",
                    content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ParkingSpotDto.class))
                            )
                    }
            )
    }
    )
    @Operation(
            summary = "Получение всех парковочных мест по выбранному типу",
            description = "Возвращает список  парковочных мест по выбранному типу в формате json."
    )
    @GetMapping("/filter")
    public ResponseMessageAPI<List<ParkingSpotDto>> getAllFilterByType(@RequestParam String type){
        try {
            return new ResponseMessageAPI<>(
                    service.getAllFilterByType(type),
                    ResultCodeAPI.SUCCESS,
                    null,
                    "Список всех парковочных мест по выбранному типу успешно получен!",
                    ResultCode.OK.getHttpCode()
            );
        } catch (EntityNotFoundException exception) {
            System.out.println(exception.getMessage());
            return new ResponseMessageAPI<>(
                    null,
                    ResultCodeAPI.FAIL,
                    exception.getClass().getSimpleName(),
                    "Список парковочных мест не найден",
                    ResultCode.NOT_FOUND.getHttpCode()
            );
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            return new ResponseMessageAPI<>(
                    null,
                    ResultCodeAPI.FAIL,
                    exception.getClass().getSimpleName(),
                    "Тип парковочного места не найден!",
                    ResultCode.FAIL.getHttpCode()
            );
        } catch (Exception e) {
            System.out.printf("ParkingSpotController: getAllFilterByType() %s%n", e);
            return new ResponseMessageAPI<>(
                    null,
                    ResultCodeAPI.EXCEPTION, e.getClass().getSimpleName(),
                    "Ошибка при получении списка парковочных мест по выбранному типу",
                    ResultCode.FAIL.getHttpCode()
            );
        }
    }
}
