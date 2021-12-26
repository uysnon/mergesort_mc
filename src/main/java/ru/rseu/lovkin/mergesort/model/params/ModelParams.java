package ru.rseu.lovkin.mergesort.model.params;

import lombok.Data;

@Data
public class ModelParams {
    private int arraySize;
    private int threadPullSize;
    private int delayInMs;
}
