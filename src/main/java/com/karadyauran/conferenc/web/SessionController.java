package com.karadyauran.conferenc.web;

import com.karadyauran.conferenc.api.SessionApi;
import com.karadyauran.conferenc.dto.create.SessionCreateDto;
import com.karadyauran.conferenc.dto.normal.SessionDto;
import com.karadyauran.conferenc.service.interf.SessionService;
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

import java.sql.Timestamp;
import java.util.UUID;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Tag(name = "SESSION", description = "Operations related to session in the system")
public class SessionController implements SessionApi
{
    SessionService service;

    @Operation(summary = "Saves session",
            description = "Stores SessionCreateDto and returns nothing",
            requestBody = @RequestBody(
                    description = "SessionCreateDto data for saving",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = SessionCreateDto.class)
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "All its great"),
                    @ApiResponse(responseCode = "500",
                            description = "Something wrong")
            }
    )
    @Override
    public ResponseEntity<String> create(SessionCreateDto session)
    {
        service.create(session);
        return ResponseEntity.ok("Success");
    }

    @Operation(summary = "Finds session by session id",
            description = "Finds the session, converts it to SessionDto and returns",
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
    public ResponseEntity<SessionDto> findById(UUID id)
    {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @Operation(summary = "Change start time",
            description = "Changing start time of session",
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
            description = "Changing end time of session",
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

    @Operation(summary = "Change speaker",
            description = "Changing speaker of session",
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
    public ResponseEntity<String> changeSpeaker(UUID id, String newSpeaker)
    {
        service.changeSpeaker(id, newSpeaker);
        return ResponseEntity.ok("Success");
    }

    @Operation(summary = "Change location",
            description = "Changing location of session",
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

    @Operation(summary = "Delete session",
            description = "Deletes session from database by given id",
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
    public ResponseEntity<String> delete(UUID id)
    {
        service.delete(id);
        return ResponseEntity.ok("Success");
    }
}
