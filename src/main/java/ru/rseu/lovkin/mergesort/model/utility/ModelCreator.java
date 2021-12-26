package ru.rseu.lovkin.mergesort.model.utility;

import lombok.experimental.UtilityClass;
import ru.rseu.lovkin.mergesort.listeners.ListenerList;
import ru.rseu.lovkin.mergesort.model.core.Array;
import ru.rseu.lovkin.mergesort.model.core.MultiThreadSorter;
import ru.rseu.lovkin.mergesort.model.core.ThreadSortersCounter;
import ru.rseu.lovkin.mergesort.model.layeres.ElementGroup;
import ru.rseu.lovkin.mergesort.model.layeres.MergeSortModel;
import ru.rseu.lovkin.mergesort.model.params.ModelParams;

@UtilityClass
public class ModelCreator {
    public MergeSortModel createModel(ModelParams modelParams) {
        MergeSortModel mergeSortModel = new MergeSortModel();
        Array array = ArraysCreator.create(modelParams.getArraySize());
        mergeSortModel.setInitialArrayCapacity(array.getArray().length);
        mergeSortModel.setInitialArray(array);
        MultiThreadSorter multiThreadSorter = createMultiThreadSorter(modelParams.getThreadPullSize(), array, mergeSortModel);
        mergeSortModel.setMultiThreadSorter(multiThreadSorter);
        multiThreadSorter.setDelayInMs(modelParams.getDelayInMs());
        return mergeSortModel;
    }

    private MultiThreadSorter createMultiThreadSorter(int capacity, Array array, MergeSortModel mergeSortModel){
        ThreadSortersCounter counter = new ThreadSortersCounter(capacity);
        MultiThreadSorter multiThreadSorter = new MultiThreadSorter(new ElementGroup(array));
        multiThreadSorter.setThreadSortersCounter(counter);
        multiThreadSorter.setListenerList(ListenerList.of(mergeSortModel));
        return multiThreadSorter;
    }
}
