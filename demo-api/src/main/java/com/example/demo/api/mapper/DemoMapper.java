package com.example.demo.api.mapper;

import com.example.demo.api.dto.DemoDto;
import com.example.demo.core.models.Demo;
import org.springframework.stereotype.Service;

@Service
public class DemoMapper {

    public DemoDto fromModel(Demo demo) {
        return new DemoDto()
                .setId(demo.getId());
    }

}
