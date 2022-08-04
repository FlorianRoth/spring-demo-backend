package com.example.demo.core.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@ToString
public class Todo {

    private Long id;

    private boolean done;

    private String description;
}
