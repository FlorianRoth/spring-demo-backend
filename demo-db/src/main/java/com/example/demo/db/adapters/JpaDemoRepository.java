package com.example.demo.db.adapters;

import com.example.demo.core.models.Demo;
import com.example.demo.core.ports.DemoRepository;
import com.example.demo.db.entities.DemoEntity;
import com.example.demo.db.repositories.DemoEntityRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class JpaDemoRepository implements DemoRepository {

    private final DemoEntityRepository demoEntityRepository;

    public JpaDemoRepository(DemoEntityRepository demoEntityRepository) {
        this.demoEntityRepository = demoEntityRepository;
    }

    @Override
    public Demo save(Demo demo) {
        var entity = toEntity(demo);

        var savedEntity = this.demoEntityRepository.save(entity);

        return toModel(savedEntity);
    }

    @Override
    public Collection<Demo> getDemos() {
        return StreamSupport
                .stream(this.demoEntityRepository.findAll().spliterator(), false)
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    private DemoEntity toEntity(Demo model) {
        return new DemoEntity().setId(model.getId());
    }

    private Demo toModel(DemoEntity entity) {
        return new Demo().setId(entity.getId());
    }
}
