package ru.rseu.lovkin.mergesort.model.utility;

import lombok.experimental.UtilityClass;
import ru.rseu.lovkin.mergesort.model.core.Array;

import java.util.concurrent.ThreadLocalRandom;

@UtilityClass
public class ArraysCreator {
    private static final int MIN_ELEMENT_VALUE = 0;
    private static final int MAX_ELEMENT_VALUE = 100;

    public Array create(int size) {
        int[] array = new int[size];
        for (int i = 0; i < array.length; i++){
            array[i] = generateNumber();
        }
        return new Array(array);
    }

    private int generateNumber(){
        return ThreadLocalRandom.current().nextInt(MIN_ELEMENT_VALUE, MAX_ELEMENT_VALUE + 1);
    }

}
