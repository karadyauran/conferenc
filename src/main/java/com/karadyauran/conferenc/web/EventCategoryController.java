package com.karadyauran.conferenc.web;

import com.karadyauran.conferenc.api.EventCategoryApi;
import com.karadyauran.conferenc.dto.create.EventCategoryCreateDto;
import com.karadyauran.conferenc.dto.shorted.EventCategoryShortDto;
import com.karadyauran.conferenc.service.interf.EventCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Validated
@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Tag(name = "SESSION", description = "Operations related to session in the system")
public class EventCategoryController implements EventCategoryApi
{
    EventCategoryService service;

    @Operation(summary = "Saves EventCategory",
            description = "Stores EventCategoryCreateDto and returns nothing",
            requestBody = @RequestBody(
                    description = "EventCategoryCreateDto data for saving",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = EventCategoryCreateDto.class)
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
    public ResponseEntity<String> create(EventCategoryCreateDto category)
    {
        service.create(category);
        return ResponseEntity.ok("Success");
    }

    @Operation(summary = "Finds category by session id",
            description = "Finds the category, converts it to EventCategoryShortDto and returns",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "All its great"),
                    @ApiResponse(responseCode = "403",
                            description = "Not Autowired"),
                    @ApiResponse(responseCode = "404",
                            description = "Session was not found"),
                    @ApiResponse(responseCode = "500",
                            description = "Something wrong")
            }
    )
    @Override
    public ResponseEntity<EventCategoryShortDto> findById(UUID id)
    {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @Override
    public ResponseEntity<String> changeName(UUID id, String newName)
    {
        service.changeName(id, newName);
        return ResponseEntity.ok("Success");
    }

    @Override
    public ResponseEntity<String> changeDescription(UUID id, String newDescription)
    {
        service.changeDescription(id, newDescription);
        return ResponseEntity.ok("Success");
    }

    @Override
    public ResponseEntity<String> delete(UUID id)
    {
        service.delete(id);
        return ResponseEntity.ok("Success");
    }
}
