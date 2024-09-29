package org.example.service.inter;

public interface SaveableProcess extends Process {
    public default void process() {
        processLogic();
        {
        }
    }
}