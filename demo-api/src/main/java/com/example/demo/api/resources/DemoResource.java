package com.example.demo.api.resources;

import com.example.demo.api.dto.DemoDto;
import com.example.demo.api.openapi.DefaultApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/api/v1/demos")
@Tag(name = "demos", description = "Demo API")
public interface DemoResource {


    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(
            summary = "Gets all Demo objects",
            description = "Gets all Demo objects."
    )
    @ApiResponse(
            responseCode = "200",
            description = "The list of resources",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    array = @ArraySchema(schema = @Schema(implementation = DemoDto.class)))
    )
    @DefaultApiResponses
    public ResponseEntity<List<DemoDto>> getAll();

    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Creates a new Demo object",
            description = "Creates a new Demo object"
    )
    @ApiResponse(
            responseCode = "201",
            description = "The new resource was created",
            headers = @Header(
                    name = HttpHeaders.LOCATION,
                    description = "The URI of the newly created resource"),
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = DemoDto.class))
    )
    @DefaultApiResponses
    public ResponseEntity<DemoDto> post();

    @PutMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Creates or replaces new Demo object",
            description = "Creates or replaces an existing Demo object"
    )
    @ApiResponse(
            responseCode = "200",
            description = "The resource was updated",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = DemoDto.class))
    )
    @ApiResponse(
            responseCode = "201",
            description = "The new resource was created",
            headers = @Header(
                    name = HttpHeaders.LOCATION,
                    description = "The URI of the newly created resource"),
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = DemoDto.class))
    )
    @DefaultApiResponses
    public ResponseEntity<DemoDto> put(@RequestBody DemoDto body);

    @DeleteMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Deletes a Demo object",
            description = "Deletes a Demo object"
    )
    @ApiResponse(
            responseCode = "204",
            description = "The resource was deleted",
            content = @Content
    )
    @DefaultApiResponses
    public ResponseEntity<Void> delete(@PathVariable("id") String id);
}
