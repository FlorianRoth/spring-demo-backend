package com.example.demo.core.ports.out;

import com.example.demo.core.models.Demo;

import java.util.Collection;

public interface DemoRepository {

    Demo save(Demo demo);

    Collection<Demo> getDemos();
}
