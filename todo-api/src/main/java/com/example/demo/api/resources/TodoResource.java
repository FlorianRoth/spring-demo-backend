package com.example.demo.api.resources;

import com.example.demo.api.dto.TodoDto;
import com.example.demo.api.openapi.AuthApiResponse;
import com.example.demo.api.openapi.DefaultApiResponses;
import com.example.demo.api.openapi.ValidationApiResponse;
import com.example.demo.api.validation.IdNotNullConstraint;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/api/v1/todos")
@Tag(name = "todos", description = "Todo List Demo API")
@Validated
public interface TodoResource {

    @GetMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(
            summary = "Gets a Todo object",
            description = "Gets the Todo object for the given id."
    )
    @ApiResponse(
            responseCode = "200",
            description = "The list of resources",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    array = @ArraySchema(schema = @Schema(implementation = TodoDto.class)))
    )
    @AuthApiResponse
    @DefaultApiResponses
    ResponseEntity<TodoDto> get(@PathVariable("id") long id);

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(
            summary = "Gets all Todo objects",
            description = "Gets all Todo objects."
    )
    @ApiResponse(
            responseCode = "200",
            description = "The list of resources",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    array = @ArraySchema(schema = @Schema(implementation = TodoDto.class)))
    )
    @AuthApiResponse
    @DefaultApiResponses
    ResponseEntity<List<TodoDto>> getAll();

    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Creates a new Todo object",
            description = "Creates a new Todo object"
    )
    @ApiResponse(
            responseCode = "201",
            description = "The new resource was created",
            headers = @Header(
                    name = HttpHeaders.LOCATION,
                    description = "The URI of the newly created resource"),
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = TodoDto.class))
    )
    @AuthApiResponse
    @ValidationApiResponse
    @DefaultApiResponses
    ResponseEntity<TodoDto> post(@RequestBody @Valid TodoDto body);

    @PutMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Creates or replaces a Todo object",
            description = "Creates or replaces an existing Todo object"
    )
    @ApiResponse(
            responseCode = "200",
            description = "The resource was updated",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = TodoDto.class))
    )
    @ApiResponse(
            responseCode = "201",
            description = "A new resource was created",
            headers = @Header(
                    name = HttpHeaders.LOCATION,
                    description = "The URI of the newly created resource"),
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = TodoDto.class))
    )
    @AuthApiResponse
    @ValidationApiResponse
    @DefaultApiResponses
    ResponseEntity<TodoDto> put(@RequestBody @Valid @IdNotNullConstraint TodoDto body);

    @DeleteMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Deletes a Todo object",
            description = "Deletes a Todo object"
    )
    @ApiResponse(
            responseCode = "204",
            description = "The resource was deleted",
            content = @Content
    )
    @AuthApiResponse
    @DefaultApiResponses
    ResponseEntity<Void> delete(@PathVariable("id") long id);
}
