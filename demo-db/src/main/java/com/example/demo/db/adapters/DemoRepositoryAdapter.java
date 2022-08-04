package com.example.demo.db.adapters;

import com.example.demo.core.models.Demo;
import com.example.demo.core.ports.out.DemoRepository;
import com.example.demo.db.mapper.DemoEntityMapper;
import com.example.demo.db.repositories.DemoEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class DemoRepositoryAdapter implements DemoRepository {

    private final DemoEntityRepository demoEntityRepository;
    private final DemoEntityMapper demoMapper;

    @Override
    public Demo save(Demo demo) {
        var entity = this.demoMapper.toEntity(demo);

        var savedEntity = this.demoEntityRepository.save(entity);

        return this.demoMapper.toModel(savedEntity);
    }

    @Override
    public Collection<Demo> getDemos() {
        return StreamSupport
                .stream(this.demoEntityRepository.findAll().spliterator(), false)
                .map(this.demoMapper::toModel)
                .collect(Collectors.toList());
    }
}
