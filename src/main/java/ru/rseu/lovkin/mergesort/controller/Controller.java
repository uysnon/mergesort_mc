package ru.rseu.lovkin.mergesort.controller;

import ru.rseu.lovkin.mergesort.model.Model;
import ru.rseu.lovkin.mergesort.listeners.Listener;

public class Controller {
    private Model model;

    public Controller(Model model){
        this.model = model;
    }

    public void addListener(Listener listener){
 //       model.addListener(listener);
    }

}
