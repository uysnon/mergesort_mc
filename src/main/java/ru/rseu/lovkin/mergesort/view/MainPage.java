package ru.rseu.lovkin.mergesort.view;

import lombok.Data;
import ru.rseu.lovkin.mergesort.controller.Controller;
import ru.rseu.lovkin.mergesort.listeners.Listener;
import ru.rseu.lovkin.mergesort.model.layeres.MergeSortModel;

import javax.swing.*;
import java.awt.*;

@Data
public class MainPage extends JPanel {
    private Controller controller;
    private ButtonsPanel buttonsPanel;
    private ArrayPanel arrayPanel;
    private CalculatingStatus currentStatus;
    private Refreshable formRefresher;
    private Listener modelListener;

    public void setCurrentStatus(CalculatingStatus currentStatus) {
        this.currentStatus = currentStatus;
        buttonsPanel.setCurrentStatus(currentStatus);
    }

    public void init() {
        this.setLayout(new BorderLayout());
        buttonsPanel = new ButtonsPanel();
        buttonsPanel.init(createButtonClickListener());
        add(buttonsPanel, BorderLayout.WEST);
        ArrayPanel arrayPanel = new ArrayPanel();
        arrayPanel.setMergeSortModel(controller.getMergeSortModel());
        add(arrayPanel);
        this.arrayPanel = arrayPanel;
    }

    private ButtonsClickListener createButtonClickListener() {
        return new ButtonsClickListener() {
            @Override
            public void onStartButtonClicked() {
                controller.start();
                setCurrentStatus(CalculatingStatus.CALCULATING);
            }

            @Override
            public void onResumeButtonClicked() {
                System.out.println("on resume");
            }

            @Override
            public void onStopButtonClicked() {
                System.out.println("on stop");
            }

            @Override
            public void onGenerateNewButtonClicked() {
                MergeSortModel newModel = controller.generateNewModel();
                controller.addListener(modelListener);
                arrayPanel.setMergeSortModel(newModel);
                setCurrentStatus(CalculatingStatus.READY_TO_CALCULATE);
                formRefresher.refresh();
            }
        };
    }
}
