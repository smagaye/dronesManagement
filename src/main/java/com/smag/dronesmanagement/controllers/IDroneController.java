package com.smag.dronesmanagement.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.smag.dronesmanagement.dto.CreateChargingItemDTO;
import com.smag.dronesmanagement.dto.DroneDTO;
import com.smag.dronesmanagement.enums.State;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Drones")
@RequestMapping(path = "/drones")
public interface IDroneController {

    @Operation(summary = "Register a drone")
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody DroneDTO drone);

    @Operation(summary = "Checking drones by state") // checking available drones for loading;
    @GetMapping
    public ResponseEntity<?> getDroneByState(@RequestParam("state") State state);

    @Operation(summary = "Check drone battery level for a given drone")
    @GetMapping("/{id}/battery")
    public ResponseEntity<?> checkDroneBatteryById(
            @PathVariable("id") long id);

    @Operation(summary = "Loading a drone with medication items")
    @PutMapping("/medications/updload/{id}")
    public ResponseEntity<?> updloadMedicationForDrone(
            @Parameter(description = "id of drone to be searched") @PathVariable("id") long id,
            @RequestBody List<CreateChargingItemDTO> medications);

    @Operation(summary = "Checking loaded medication items for a given drone")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the medication", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = DroneDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Drone not found", content = @Content) })
    @GetMapping("/{id}/medications")
    public ResponseEntity<?> getMedicationsByDroneId(
            @Parameter(description = "id of drone to be searched") @PathVariable("id") long id);

}
