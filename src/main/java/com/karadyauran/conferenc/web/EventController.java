package com.karadyauran.conferenc.web;

import com.karadyauran.conferenc.api.EventApi;
import com.karadyauran.conferenc.dto.create.EventCategoryCreateDto;
import com.karadyauran.conferenc.dto.create.EventCreateDto;
import com.karadyauran.conferenc.dto.normal.EventDto;
import com.karadyauran.conferenc.service.interf.EventService;
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

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Validated
@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Tag(name = "EVENT", description = "Operations related to event in the system")
public class EventController implements EventApi
{
    EventService service;

    @Operation(summary = "Saves event",
            description = "Stores EventCreateDto and returns nothing",
            requestBody = @RequestBody(
                    description = "EventCreateDto data for saving",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = EventCreateDto.class)
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "All its great"),
                    @ApiResponse(responseCode = "404",
                            description = "Event was not created"),
                    @ApiResponse(responseCode = "500",
                            description = "Something wrong")
            }
    )
    @Override
    public ResponseEntity<String> create(EventCreateDto event)
    {
        service.create(event);
        return ResponseEntity.ok("Success");
    }

    @Operation(summary = "Finds all created events",
            description = "Finds the events, converts it to List<EventDto> and returns",
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
    public ResponseEntity<List<EventDto>> findAll()
    {
        return ResponseEntity.ok().body(service.findAll());
    }

    @Operation(summary = "Finds event by event id",
            description = "Finds the event, converts it to EventDto and returns",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "All its great"),
                    @ApiResponse(responseCode = "403",
                            description = "Not Autowired"),
                    @ApiResponse(responseCode = "404",
                            description = "Event was not found"),
                    @ApiResponse(responseCode = "500",
                            description = "Something wrong")
            }
    )
    @Override
    public ResponseEntity<EventDto> findById(UUID id)
    {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @Operation(summary = "Finds event by user id",
            description = "Finds the events, converts it to List<EventDto> and returns",
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
    public ResponseEntity<List<EventDto>> findByUserId(UUID user)
    {
        return ResponseEntity.ok().body(service.findByUserId(user));
    }

    @Operation(summary = "Add category to event",
            description = "Adding category to event, multiple",
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
    public ResponseEntity<Void> addCategory(UUID id, UUID categoryId)
    {
        service.addExistCategory(id, categoryId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Change title",
            description = "Changing title of event",
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
    public ResponseEntity<String> changeTitle(UUID id, String newTitle)
    {
        service.changeTitle(id, newTitle);
        return ResponseEntity.ok("Success");
    }

    @Operation(summary = "Change description",
            description = "Changing description of event",
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
    public ResponseEntity<String> changeDescription(UUID id, String newDescription)
    {
        service.changeDescription(id, newDescription);
        return ResponseEntity.ok("Success");
    }

    @Operation(summary = "Change start time",
            description = "Changing description of event",
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
    public ResponseEntity<String> changeStartTime(UUID id, Timestamp newStart)
    {
        service.changeStartTime(id, newStart);
        return ResponseEntity.ok("Success");
    }

    @Operation(summary = "Change end time",
            description = "Changing end time of event",
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
    public ResponseEntity<String> changeEndTime(UUID id, Timestamp newEnd)
    {
        service.changeEndTime(id, newEnd);
        return ResponseEntity.ok("Success");
    }

    @Operation(summary = "Change location",
            description = "Changing location of event",
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
    public ResponseEntity<String> changeLocation(UUID id, String newLocation)
    {
        service.changeLocation(id, newLocation);
        return ResponseEntity.ok("Success");
    }

    @Operation(summary = "Change capacity",
            description = "Changing capacity of event",
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
    public ResponseEntity<String> changeCapacity(UUID id, Integer newCapacity)
    {
        service.changeCapacity(id, newCapacity);
        return ResponseEntity.ok("Success");
    }

    @Operation(summary = "Delete event",
            description = "Deletes event from database by given id",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "All its great"),
                    @ApiResponse(responseCode = "403",
                            description = "Not Autowired"),
                    @ApiResponse(responseCode = "404",
                            description = "Event was not found"),
                    @ApiResponse(responseCode = "500",
                            description = "Something wrong")
            }
    )
    @Override
    public ResponseEntity<String> delete(UUID id)
    {
        service.delete(id);
        return ResponseEntity.ok("Success");
    }
}
