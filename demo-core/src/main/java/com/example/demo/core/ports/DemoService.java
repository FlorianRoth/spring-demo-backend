package com.example.demo.core.ports;

import com.example.demo.core.models.Demo;

import java.util.Collection;

public interface DemoService {
    Demo createDemo();

    Collection<Demo> getDemos();
}
