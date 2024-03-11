package com.karadyauran.conferenc.web;

import com.karadyauran.conferenc.api.UserApi;
import com.karadyauran.conferenc.dto.create.UserCreateDto;
import com.karadyauran.conferenc.dto.normal.UserDto;
import com.karadyauran.conferenc.page.UserPage;
import com.karadyauran.conferenc.service.interf.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Tag(name = "USERS", description = "Operations related to users in the system")
public class UserController implements UserApi, UserPage
{
    UserService service;

    @Operation(summary = "Saves user",
            description = "Stores UserDto and returns nothing",
            requestBody = @RequestBody(
                    description = "UserShortDto data for saving",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserDto.class)
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "All its great"),
                    @ApiResponse(responseCode = "404",
                            description = "User was not created, because username is already taken"),
                    @ApiResponse(responseCode = "500",
                            description = "Something wrong")
            }
    )
    @Override
    public ResponseEntity<String> create(UserCreateDto user)
    {
        service.create(user);
        return ResponseEntity.ok("Success");
    }

    @Operation(summary = "Finds user by user id",
            description = "Finds user, converts it to UserDto and returns",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "All its great"),
                    @ApiResponse(responseCode = "403",
                            description = "Not Autowired"),
                    @ApiResponse(responseCode = "404",
                            description = "User was not found"),
                    @ApiResponse(responseCode = "500",
                            description = "Something wrong")
            }
    )
    @Override
    public ResponseEntity<UserDto> findById(UUID id)
    {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @Operation(summary = "Finds user by username",
            description = "Finds the user, converts it to UserDto and returns",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "All its great"),
                    @ApiResponse(responseCode = "403",
                            description = "Not Autowired"),
                    @ApiResponse(responseCode = "404",
                            description = "User was not found"),
                    @ApiResponse(responseCode = "500",
                            description = "Something wrong")
            }
    )
    @Override
    public ResponseEntity<UserDto> findByUsername(String username)
    {
        return ResponseEntity.ok().body(service.findByUsername(username));
    }

    @Operation(summary = "Change username",
            description = "Changing user's username",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "All its great"),
                    @ApiResponse(responseCode = "403",
                            description = "Not Autowired"),
                    @ApiResponse(responseCode = "500",
                            description = "Something wrong")
            }
    )
    @Override
    public ResponseEntity<String> changeUsername(UUID id, String username)
    {
        service.changeUsername(id, username);
        return ResponseEntity.ok("Success");
    }

    @Operation(summary = "Delete user",
            description = "Deletes user from database by given id",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "All its great"),
                    @ApiResponse(responseCode = "403",
                            description = "Not Autowired"),
                    @ApiResponse(responseCode = "404",
                            description = "User was not found"),
                    @ApiResponse(responseCode = "500",
                            description = "Something wrong")
            }
    )
    @Override
    public ResponseEntity<String> deleteById(UUID id)
    {
        service.delete(id);
        return ResponseEntity.ok("Success");
    }
}
