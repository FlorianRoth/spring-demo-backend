package com.example.demo.db.adapters;

import com.example.demo.core.models.Todo;
import com.example.demo.core.ports.out.TodoRepository;
import com.example.demo.db.mapper.TodoEntityMapper;
import com.example.demo.db.repositories.TodoEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class TodoRepositoryAdapter implements TodoRepository {

    private final TodoEntityRepository todoEntityRepository;
    private final TodoEntityMapper demoMapper;


    @Override
    public Optional<Todo> findById(long id) {
        return this.todoEntityRepository
                .findById(id)
                .map(this.demoMapper::toModel);
    }

    @Override
    public Todo save(Todo todo) {
        var entity = this.demoMapper.toEntity(todo);

        var savedEntity = this.todoEntityRepository.save(entity);

        return this.demoMapper.toModel(savedEntity);
    }

    @Override
    public boolean delete(long id) {
        this.todoEntityRepository.deleteById(id);
        return true;
    }

    @Override
    public Collection<Todo> getAll() {
        return StreamSupport
                .stream(this.todoEntityRepository.findAll().spliterator(), false)
                .map(this.demoMapper::toModel)
                .collect(Collectors.toList());
    }
}
