package ru.rseu.lovkin.mergesort.controller;

import lombok.Data;
import ru.rseu.lovkin.mergesort.listeners.Listener;
import ru.rseu.lovkin.mergesort.model.layeres.MergeSortModel;
import ru.rseu.lovkin.mergesort.model.params.ModelParamsManager;
import ru.rseu.lovkin.mergesort.model.utility.ModelCreator;

@Data
public class Controller {
    private MergeSortModel mergeSortModel;

    public Controller(MergeSortModel mergeSortModel){
        this.mergeSortModel = mergeSortModel;
    }

    public void addListener(Listener listener){
           mergeSortModel.addListener(listener);
    }

    public void start(){
        mergeSortModel.start();
    }

    public MergeSortModel generateNewModel() {
        MergeSortModel newModel = ModelCreator.createModel(ModelParamsManager.read());
        this.mergeSortModel = newModel;
        return newModel;
    }
}
