package com.example.demo.api.resources;

import com.example.demo.api.dto.DemoDto;
import com.example.demo.api.mapper.DemoDtoMapper;
import com.example.demo.core.ports.in.CreateDemoUseCase;
import com.example.demo.core.ports.in.ListDemosUseCase;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class DemoResourceImpl implements DemoResource {

    private final DemoDtoMapper demoMapper;
    private final CreateDemoUseCase createDemoUseCase;
    private final ListDemosUseCase listDemosUseCase;

    public ResponseEntity<List<DemoDto>> getAll() {
        var demos = this.listDemosUseCase.getDemos();

        var result = demos
                .stream()
                .map(demoMapper::toDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(result);
    }

    public ResponseEntity<DemoDto> post() {
        var demo = this.createDemoUseCase.createDemo();

        var result = demoMapper.toDto(demo);

        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(demo.getId())
                .toUri();

        return ResponseEntity.created(uri).body(result);
    }

    public ResponseEntity<DemoDto> put(DemoDto body) {
        // TODO create OR update ;)
        return post();
    }

    public ResponseEntity<Void> delete(String id) {
        throw new NotImplementedException();
    }
}
