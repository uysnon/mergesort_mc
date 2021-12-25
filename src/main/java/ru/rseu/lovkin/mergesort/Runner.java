package ru.rseu.lovkin.mergesort;


import ru.rseu.lovkin.mergesort.model.core.Array;
import ru.rseu.lovkin.mergesort.model.core.MultiThreadSorter;
import ru.rseu.lovkin.mergesort.model.core.ThreadSortersCounter;
import ru.rseu.lovkin.mergesort.model.layeres.ElementGroup;
import ru.rseu.lovkin.mergesort.model.layeres.MergeSortModel;
import ru.rseu.lovkin.mergesort.listeners.ListenerList;
import ru.rseu.lovkin.mergesort.model.params.ModelParams;
import ru.rseu.lovkin.mergesort.model.params.ModelParamsManager;
import ru.rseu.lovkin.mergesort.model.utility.ArraysCreator;
import ru.rseu.lovkin.mergesort.view.View;

import java.util.Arrays;

public class Runner {

    public static void main(String[] args) throws InterruptedException {
        ThreadSortersCounter counter = new ThreadSortersCounter(10);
        Array array = ArraysCreator.create(15);
        MultiThreadSorter multiThreadSorter = new MultiThreadSorter(new ElementGroup(array));
        multiThreadSorter.setThreadSortersCounter(counter);

        MergeSortModel mergeSortModel = new MergeSortModel();
        mergeSortModel.setInitialArrayCapacity(array.getArray().length);
        mergeSortModel.addElementGroup(multiThreadSorter.getUnsorted());

        multiThreadSorter.setListenerList(ListenerList.of(mergeSortModel));

        Thread thread = new Thread(multiThreadSorter);
        thread.start();

        thread.join();
        System.out.println(Arrays.toString(multiThreadSorter.getSorted().getElements().getArray()));
        //View view  = new View(null);
    }
}
