package com.example.demo.core.services;

import com.example.demo.core.ports.DemoRepository;
import com.example.demo.core.models.Demo;
import com.example.demo.core.ports.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
class DemoServiceImpl implements DemoService {

    private final DemoRepository demoRepository;

    @Autowired
    public DemoServiceImpl(DemoRepository demoRepository) {
        this.demoRepository = demoRepository;
    }

    public Demo createDemo() {
        var demo = new Demo();

        return this.demoRepository.save(demo);
    }

    public Collection<Demo> getDemos() {
        return this.demoRepository.getDemos();
    }
}
