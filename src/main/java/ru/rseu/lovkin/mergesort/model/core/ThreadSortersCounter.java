package ru.rseu.lovkin.mergesort.model.core;


public class ThreadSortersCounter {
    private int capacity;
    private int freeCount;

    public ThreadSortersCounter(int capacity) {
        this.capacity = capacity;
        this.freeCount = capacity;
    }

    public synchronized void push(int count) {
        freeCount += count;
        freeCount = Math.min(freeCount, capacity);
    }

    public synchronized int getSize() {
        return freeCount;
    }

    public synchronized boolean pop(int count) {
        if (count > freeCount) {
            return false;
        } else {
            freeCount = freeCount - count;
            return true;
        }
    }
}
