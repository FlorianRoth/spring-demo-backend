package com.example.demo.api.mapper;

import com.example.demo.api.dto.DemoDto;
import com.example.demo.core.models.Demo;
import org.mapstruct.Mapper;

@Mapper
public interface DemoDtoMapper {

    DemoDto toDto(Demo demo);

}
