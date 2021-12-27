package ru.rseu.lovkin.mergesort;


import ru.rseu.lovkin.mergesort.controller.Controller;
import ru.rseu.lovkin.mergesort.model.core.Array;
import ru.rseu.lovkin.mergesort.model.core.MultiThreadSorter;
import ru.rseu.lovkin.mergesort.model.core.ThreadSortersCounter;
import ru.rseu.lovkin.mergesort.model.layeres.ElementGroup;
import ru.rseu.lovkin.mergesort.model.layeres.MergeSortModel;
import ru.rseu.lovkin.mergesort.listeners.ListenerList;
import ru.rseu.lovkin.mergesort.model.params.ModelParams;
import ru.rseu.lovkin.mergesort.model.params.ModelParamsManager;
import ru.rseu.lovkin.mergesort.model.utility.ArraysCreator;
import ru.rseu.lovkin.mergesort.model.utility.ModelCreator;
import ru.rseu.lovkin.mergesort.view.View;

import java.util.Arrays;

public class Runner {

    public static void main(String[] args) {
        MergeSortModel mergeSortModel = ModelCreator.createModel(ModelParamsManager.read());
        Controller controller = new Controller(mergeSortModel);
        View view  = new View(controller);
    }
}
