package com.example.demo.core.services;

import com.example.demo.core.models.Demo;
import com.example.demo.core.ports.in.CreateDemoUseCase;
import com.example.demo.core.ports.in.ListDemosUseCase;
import com.example.demo.core.ports.out.DemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
class DemoServiceImpl implements CreateDemoUseCase, ListDemosUseCase {

    private final DemoRepository demoRepository;

    public Demo createDemo() {
        var demo = new Demo();

        return this.demoRepository.save(demo);
    }

    public Collection<Demo> getDemos() {
        return this.demoRepository.getDemos();
    }
}
