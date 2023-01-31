package com.example.demo.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Schema(name = "Todo")
public class TodoDto implements HasId {

    @Schema(description = "The id of this resource", required = true)
    private Long id;

    @Schema(description = "Flag to indicate if this Todo item is done")
    private Boolean done;

    @Schema(description = "The description of the Todo Item")
    @Size(min = 1, message = "Description must not be empty")
    @Size(max = 50, message = "Description must not be longer than 50 characters")
    private String description;

    private Date date = new Date();

    private LocalDate localDate = LocalDate.now();

    private LocalDateTime localDateTime = LocalDateTime.now();

    private ZonedDateTime zonedDateTime = ZonedDateTime.now();
}
