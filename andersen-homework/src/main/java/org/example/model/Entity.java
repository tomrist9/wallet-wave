package org.example.model;

import org.example.annotations.NullableWarning;

import java.util.UUID;

public abstract class Entity {

    @NullableWarning
    private UUID id;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
    public void print(){
        System.out.println("print content in console");
    }
}
