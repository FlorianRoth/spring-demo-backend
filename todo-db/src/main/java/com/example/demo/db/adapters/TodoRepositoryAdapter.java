package com.example.demo.db.adapters;

import com.example.demo.core.models.Todo;
import com.example.demo.core.ports.out.TodoRepository;
import com.example.demo.db.mapper.TodoEntityMapper;
import com.example.demo.db.repositories.TodoEntityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
@RequiredArgsConstructor
public class TodoRepositoryAdapter implements TodoRepository {

    private final TodoEntityRepository todoEntityRepository;
    private final TodoEntityMapper demoMapper;


    @Override
    public Optional<Todo> findById(long id) {
        log.info("findById(id = {})", id);

        var result = this.todoEntityRepository
                .findById(id)
                .map(this.demoMapper::toModel);

        log.info(result.isPresent() ? "Todo found" : "Todo not found");

        return result;
    }

    @Override
    public Todo save(Todo todo) {
        log.info("save(id = {})", todo.getId());

        var entity = this.demoMapper.toEntity(todo);

        var savedEntity = this.todoEntityRepository.save(entity);

        return this.demoMapper.toModel(savedEntity);
    }

    @Override
    public boolean delete(long id) {
        log.info("delete(id = {})", id);

        var count = this.todoEntityRepository.deleteById(id);

        log.info("deleted {} elements", count);

        return count > 0;
    }

    @Override
    public Collection<Todo> getAll() {
        log.info("getAll()");

        var result = StreamSupport
                .stream(this.todoEntityRepository.findAll().spliterator(), false)
                .map(this.demoMapper::toModel)
                .collect(Collectors.toList());

        log.info("Found {} elements", result.size());

        return result;
    }
}
