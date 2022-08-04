package com.example.demo.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Schema(name = "ApiError")
public class ApiErrorDto {

    @Schema(description = "The type of the error")
    private String error;

    @Schema(description = "The error description")
    private String description;

    @Schema(description = "The stack trace of the exception - if enabled", hidden = true)
    private List<String> stackTrace;
}
