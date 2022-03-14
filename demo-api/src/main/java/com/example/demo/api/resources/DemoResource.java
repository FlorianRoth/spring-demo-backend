package com.example.demo.api.resources;

import com.example.demo.api.dto.DemoDto;
import com.example.demo.api.mapper.DemoMapper;
import com.example.demo.core.ports.DemoService;
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
    public List<DemoDto> getAll() {
        var demos = this.demoService.getDemos();

        return demos
                .stream()
                .map(demoMapper::fromModel)
                .collect(Collectors.toList());
    }

    @PostMapping
    public DemoDto create() {
        var demo = this.demoService.createDemo();

        return demoMapper.fromModel(demo);
    }

}
