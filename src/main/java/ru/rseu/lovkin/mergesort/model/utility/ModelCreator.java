package ru.rseu.lovkin.mergesort.model.utility;

import lombok.experimental.UtilityClass;
import ru.rseu.lovkin.mergesort.model.core.Array;
import ru.rseu.lovkin.mergesort.model.layeres.MergeSortModel;
import ru.rseu.lovkin.mergesort.model.params.ModelParams;

@UtilityClass
public class ModelCreator {
    public MergeSortModel createModel(ModelParams modelParams) {
        MergeSortModel mergeSortModel = new MergeSortModel();
        Array array = ArraysCreator.create(modelParams.getArraySize());
        mergeSortModel.setInitialArrayCapacity(array.getArray().length);
        mergeSortModel.setInitialArray(array);
        return mergeSortModel;
    }
}
