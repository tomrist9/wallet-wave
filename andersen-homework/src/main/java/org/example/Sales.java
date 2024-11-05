package org.example;

import java.util.UUID;

public abstract class Sales {
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
