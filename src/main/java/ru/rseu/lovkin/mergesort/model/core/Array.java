package ru.rseu.lovkin.mergesort.model.core;

public class Array {
    int[] array;

    public Array(int[] array) {
        this.array = array;
    }

    public int[] getArray() {
        return array;
    }

    public void setArray(int[] array) {
        this.array = array;
    }

    public int[] getLeftPart() {
        int[] left;

        left = new int[getMiddle()];
        System.arraycopy(array, 0, left, 0, getMiddle());

        return left;
    }

    public int[] getRightPart() {
        int[] right;
        right = new int[array.length - getMiddle()];
        System.arraycopy(array, getMiddle(), right, 0, array.length - getMiddle());

        return right;
    }

    private int getMiddle() {
        return (int) Math.ceil(((double) array.length) / 2);
    }
}
