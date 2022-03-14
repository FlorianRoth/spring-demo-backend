package com.example.demo.api.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class DemoDto {
    private Long id;
}
