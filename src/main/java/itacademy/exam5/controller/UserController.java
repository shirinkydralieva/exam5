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
import itacademy.exam5.dto.UserDto;
import itacademy.exam5.enums.ResultCode;
import itacademy.exam5.enums.ResultCodeAPI;
import itacademy.exam5.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "User", description = "API для пользователя")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService service;

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Пользователь успешно создан",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = UserDto.class))
                    }
            )
    }
    )
    @Operation(
            summary = "Создание пользователя",
            description = "Создание пользователя. Возвращает созданного пользователя в формате json."
    )
    @PostMapping("/create")
    public ResponseMessageAPI<UserDto> create(@RequestBody UserDto model){
        try {
            return new ResponseMessageAPI<>(
                    service.create(model),
                    ResultCodeAPI.SUCCESS,
                    null,
                    "Пользователь успешно создан!",
                    ResultCode.OK.getHttpCode()
            );
        } catch (Exception e){
            System.out.printf("UserController: create() %s%n", e);
            return new ResponseMessageAPI<>(
                    null,
                    ResultCodeAPI.EXCEPTION, e.getClass().getSimpleName(),
                    "Ошибка при создании пользователя.",
                    ResultCode.FAIL.getHttpCode()
            );
        }
    }

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Пользователь успешно получен по его id",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = UserDto.class))
                    }
            )
    }
    )
    @Operation(
            summary = "Получение пользователя по id",
            description = "Возвращает пользователя в формате json."
    )
    @GetMapping("/{id}")
    public ResponseMessageAPI<UserDto> getById(@PathVariable Long id){
        try {
            return new ResponseMessageAPI<>(
                    service.getById(id),
                    ResultCodeAPI.SUCCESS,
                    null,
                    "Пользователь успешно получен!",
                    ResultCode.OK.getHttpCode()
            );
        } catch (EntityNotFoundException exception) {
            System.out.println(exception.getMessage());
            return new ResponseMessageAPI<>(
                    null,
                    ResultCodeAPI.FAIL,
                    exception.getClass().getSimpleName(),
                    "Пользователь с таким id не найден",
                    ResultCode.NOT_FOUND.getHttpCode()
            );
        } catch (Exception e) {
            System.out.printf("UserController: getById() %s%n", e);
            return new ResponseMessageAPI<>(
                    null,
                    ResultCodeAPI.EXCEPTION, e.getClass().getSimpleName(),
                    "Ошибка при получении пользователя по id",
                    ResultCode.FAIL.getHttpCode()
            );
        }

    }
@ApiResponses(value = {
        @ApiResponse(
                responseCode = "200",
                description = "Список всех пользователей успешно получен",
                content = {
                        @Content(mediaType = "application/json",
                                array = @ArraySchema(schema = @Schema(implementation = UserDto.class))
                        )
                }
        )
}
)
@Operation(
        summary = "Получение всех пользователей",
        description = "Возвращает список пользователей в формате json."
)
    @GetMapping()
    public ResponseMessageAPI<List<UserDto>> getAll(){
        try {
            return new ResponseMessageAPI<>(
                service.getAll(),
                ResultCodeAPI.SUCCESS,
                null,
                "Список пользователей успешно получен!",
                ResultCode.OK.getHttpCode()
            );
        } catch (Exception e) {
            System.out.printf("UserController: getAll() %s%n", e);
            return new ResponseMessageAPI<>(
                null,
                ResultCodeAPI.EXCEPTION, e.getClass().getSimpleName(),
                "Ошибка при получении списка пользователей",
                ResultCode.FAIL.getHttpCode()
            );
        }
    }
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Данные пользователя успешно обновлены",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = UserDto.class))
                    }
            )
    }
    )
    @Operation(
            summary = "Обновление пользователя по представленной новой модели",
            description = "Возвращает обновленного пользователя в формате json."
    )
    @PutMapping("/update")
    public ResponseMessageAPI<UserDto> update(@RequestBody UserDto model){
        try {
            return new ResponseMessageAPI<>(
                    service.update(model),
                    ResultCodeAPI.SUCCESS,
                    null,
                    "Пользователь успешно обновлен!",
                    ResultCode.OK.getHttpCode()
            );
        } catch (EntityNotFoundException exception) {
            System.out.println(exception.getMessage());
            return new ResponseMessageAPI<>(
                    null,
                    ResultCodeAPI.FAIL,
                    exception.getClass().getSimpleName(),
                    "Пользователь не найден",
                    ResultCode.NOT_FOUND.getHttpCode()
            );
        } catch (Exception e) {
            System.out.printf("UserController: update() %s%n", e);
            return new ResponseMessageAPI<>(
                    null,
                    ResultCodeAPI.EXCEPTION, e.getClass().getSimpleName(),
                    "Ошибка при обновлении пользователя",
                    ResultCode.FAIL.getHttpCode()
            );
        }
    }
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Пользователь успешно удален"
            )
    }
    )
    @Operation(
            summary = "Удаление пользователя",
            description = "Удаление пользователя. Ничего не возвращает"
    )
    @DeleteMapping("/delete")
    public ResponseMessageAPI<Void> delete(@RequestParam Long id){
        try {
            service.delete(id);
            return new ResponseMessageAPI<>(
                    null,
                    ResultCodeAPI.SUCCESS,
                    null,
                    "Пользователь успешно удален!",
                    ResultCode.OK.getHttpCode()
            );
        } catch (EntityNotFoundException exception) {
            System.out.println(exception.getMessage());
            return new ResponseMessageAPI<>(
                    null,
                    ResultCodeAPI.FAIL,
                    exception.getClass().getSimpleName(),
                    "Пользователь не найден",
                    ResultCode.NOT_FOUND.getHttpCode()
            );
        } catch (Exception e) {
            System.out.printf("UserController: delete() %s%n", e);
            return new ResponseMessageAPI<>(
                    null,
                    ResultCodeAPI.EXCEPTION, e.getClass().getSimpleName(),
                    "Ошибка при удалении пользователя",
                    ResultCode.FAIL.getHttpCode()
            );
        }
    }
}
