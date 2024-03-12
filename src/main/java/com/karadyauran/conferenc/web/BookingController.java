package com.karadyauran.conferenc.web;

import com.karadyauran.conferenc.api.BookingApi;
import com.karadyauran.conferenc.dto.create.BookingCreateDto;
import com.karadyauran.conferenc.dto.normal.BookingDto;
import com.karadyauran.conferenc.model.enums.Status;
import com.karadyauran.conferenc.service.interf.BookingService;
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

import java.util.List;
import java.util.UUID;

@Validated
@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Tag(name = "BOOKING", description = "Operations related to booking in the system")
public class BookingController implements BookingApi
{
    BookingService service;

    @Operation(summary = "Saves booking",
            description = "Stores BookingCreateDto and returns nothing",
            requestBody = @RequestBody(
                    description = "BookingCreateDto data for saving",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = BookingCreateDto.class)
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "All its great"),
                    @ApiResponse(responseCode = "404",
                            description = "Booking was not created"),
                    @ApiResponse(responseCode = "500",
                            description = "Something wrong")
            }
    )
    @Override
    public ResponseEntity<String> create(BookingCreateDto booking)
    {
        service.create(booking);
        return ResponseEntity.ok("Success");
    }

    @Operation(summary = "Finds booking by id",
            description = "Finds the booking, converts it to BookingDto and returns",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "All its great"),
                    @ApiResponse(responseCode = "403",
                            description = "Not Autowired"),
                    @ApiResponse(responseCode = "404",
                            description = "Booking was not found"),
                    @ApiResponse(responseCode = "500",
                            description = "Something wrong")
            }
    )
    @Override
    public ResponseEntity<BookingDto> findById(UUID id)
    {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @Operation(summary = "Finds bookings by user id",
            description = "Finds the bookings, converts it to List<BookingDto> and returns",
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
    public ResponseEntity<List<BookingDto>> findByUserId(UUID user)
    {
        return ResponseEntity.ok().body(service.findByUserId(user));
    }

    @Operation(summary = "Change status",
            description = "Changing status of booking",
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
    public ResponseEntity<String> changeStatus(UUID id, Status newStatus)
    {
        service.changeStatus(id, newStatus);
        return ResponseEntity.ok("Success");
    }

    @Operation(summary = "Delete booking",
            description = "Deletes booking from database by given id",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "All its great"),
                    @ApiResponse(responseCode = "403",
                            description = "Not Autowired"),
                    @ApiResponse(responseCode = "404",
                            description = "Booking was not found"),
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
