package ru.rseu.lovkin.mergesort.model.time;

public enum Timer {
    INSTANCE;

    long startTime;
    boolean isRunning;
    long lastTime;

    public void refresh() {
        startTime = 0;
        lastTime = 0;
        isRunning = false;
    }

    public long getTime() {
        if (isRunning) {
            return System.currentTimeMillis() - startTime;
        } else {
            return lastTime - startTime;
        }
    }

    public void start() {
        startTime = System.currentTimeMillis();
        isRunning = true;
    }

    public void stop() {
        lastTime = System.currentTimeMillis();
        isRunning = false;
    }
}
