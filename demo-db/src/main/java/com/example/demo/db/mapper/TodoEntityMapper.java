package com.example.demo.db.mapper;

import com.example.demo.core.models.Todo;
import com.example.demo.db.entities.TodoEntity;
import org.mapstruct.Mapper;

@Mapper
public interface TodoEntityMapper {

    TodoEntity toEntity(Todo model);

    Todo toModel(TodoEntity entity);
}
