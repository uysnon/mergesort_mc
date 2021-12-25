package ru.rseu.lovkin.mergesort.model.core;

public interface Merger {
    default Array merge(Array leftPart, Array rightPart) {
        int cursorLeft = 0, cursorRight = 0, counter = 0;
        Array merged = new Array(new int[leftPart.getArray().length + rightPart.getArray().length]);

        while (cursorLeft < leftPart.getArray().length && cursorRight < rightPart.getArray().length) {
            if (leftPart.getArray()[cursorLeft] <= rightPart.getArray()[cursorRight]) {
                merged.getArray()[counter] = leftPart.getArray()[cursorLeft];
                cursorLeft++;
            } else {
                merged.getArray()[counter] = rightPart.getArray()[cursorRight];
                cursorRight++;
            }
            counter++;
        }

        if (cursorLeft < leftPart.getArray().length) {
            System.arraycopy(leftPart.getArray(), cursorLeft, merged.getArray(), counter, merged.getArray().length - counter);
        }
        if (cursorRight < rightPart.getArray().length) {
            System.arraycopy(rightPart.getArray(), cursorRight, merged.getArray(), counter, merged.getArray().length - counter);
        }
        return merged;
    }
}
