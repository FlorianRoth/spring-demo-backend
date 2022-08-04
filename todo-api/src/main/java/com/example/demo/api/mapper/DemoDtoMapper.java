package com.example.demo.api.mapper;

import com.example.demo.api.dto.TodoDto;
import com.example.demo.core.models.Todo;
import org.mapstruct.Mapper;

@Mapper
public interface DemoDtoMapper {

    TodoDto toDto(Todo todo);

}
