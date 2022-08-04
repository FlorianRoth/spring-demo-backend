package com.example.demo.db.mapper;

import com.example.demo.core.models.Demo;
import com.example.demo.db.entities.DemoEntity;
import org.mapstruct.Mapper;

@Mapper
public interface DemoEntityMapper {

    DemoEntity toEntity(Demo model);

    Demo toModel(DemoEntity entity);
}
