package com.example.demo.core.ports.in;

import com.example.demo.core.models.Demo;

import java.util.Collection;

public interface ListDemosUseCase {
    Collection<Demo> getDemos();
}
