package com.example.demo.api.resources;

import com.example.demo.api.dto.DemoDto;
import com.example.demo.api.mapper.DemoMapper;
import com.example.demo.core.ports.DemoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController()
@RequestMapping("/api/v1/demos")
public class DemoResource {

    private final DemoService demoService;
    private final DemoMapper demoMapper;

    public DemoResource(
            DemoService demoService,
            DemoMapper demoMapper) {
        this.demoService = demoService;
        this.demoMapper = demoMapper;
    }

    @GetMapping
    @Operation(
            summary = "Gets all Demo objects",
            description = "Gets all Demo objects.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Success",
                            content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = DemoDto.class)))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not found",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal error",
                            content = @Content
                    )
            }
    )
    public List<DemoDto> getAll() {
        var demos = this.demoService.getDemos();

        return demos
                .stream()
                .map(demoMapper::fromModel)
                .collect(Collectors.toList());
    }

    @PostMapping
    @Operation(
            summary = "Creates a new Demo object",
            description = "Creates a new Demo object",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Success",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = DemoDto.class))
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal error",
                            content = @Content
                    )
            }
    )
    public DemoDto create() {
        var demo = this.demoService.createDemo();

        return demoMapper.fromModel(demo);
    }

}
