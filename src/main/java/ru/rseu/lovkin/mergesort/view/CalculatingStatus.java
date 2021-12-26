package ru.rseu.lovkin.mergesort.view;

public enum CalculatingStatus {
    READY_TO_CALCULATE("Ready to calculate"),
    CALCULATING("Calculating"),
    RESULT_FOUND("Result found!");

    private String text;

    CalculatingStatus(String text) {
        this.text = text;
    }

    public String getText(){
        return text;
    }
}
