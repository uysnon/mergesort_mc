package ru.rseu.lovkin.mergesort.view;

import lombok.Data;

@Data
public class ArrayDrawCharacteristics {
    private int x1;
    private int x2;
    private int y1;
    private int y2;
    private int[] array;

    private ArrayDrawCharacteristics() {
    }

    public static ArrayDrawCharacteristics create(int[] array, int depth, int x) {
        ArrayDrawCharacteristics createdObject = new ArrayDrawCharacteristics();
        createdObject.array = array;
        createdObject.x1 = x;
        createdObject.x2 = x + (int) (createdObject.getDigitsInStringArray() * 4.35);
        createdObject.y1 = 15 + depth * 25;
        createdObject.y2 = 20 + depth * 25;
        return createdObject;
    }

    public Coordinate getMiddleBottom() {
        return new Coordinate((x1 + x2) / 2, y2);
    }

    public Coordinate getMiddleTop() {
        return new Coordinate((x1 + x2) / 2, y1);
    }

    public int getHeight() {
        return y2 - y1;
    }

    public int getWidth() {
        return x2 - x1;
    }

    private int getDigitsInStringArray() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i : array) {
            stringBuilder.append(i).append(" ");
        }
        return stringBuilder.toString().length();
    }


}