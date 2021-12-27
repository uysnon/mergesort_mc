package ru.rseu.lovkin.mergesort.view;

import lombok.Data;
import ru.rseu.lovkin.mergesort.controller.Controller;
import ru.rseu.lovkin.mergesort.listeners.Listener;
import ru.rseu.lovkin.mergesort.model.layeres.MergeSortModel;
import ru.rseu.lovkin.mergesort.model.params.ModelParams;
import ru.rseu.lovkin.mergesort.model.params.ModelParamsManager;
import ru.rseu.lovkin.mergesort.model.time.Timer;

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

    public void refresh() {
        buttonsPanel.updateTime(Timer.INSTANCE.getTime());
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

    private boolean updateModelParamsFromView() {
        TextFieldsResult textFieldsResult = buttonsPanel.getFieldsValues();
        if (textFieldsResult.getStatus() == Statuses.OK) {
            ModelParams modelParams = new ModelParams();
            modelParams.setArraySize((Integer) textFieldsResult.getResultMap().get(ButtonsPanel.ARRAY_SIZE_TEXT));
            modelParams.setThreadPullSize((Integer) textFieldsResult.getResultMap().get(ButtonsPanel.THREAD_COUNT_TEXT));
            modelParams.setDelayInMs((Integer) textFieldsResult.getResultMap().get(ButtonsPanel.DELAY_TEXT));
            ModelParamsManager.write(modelParams);
            return true;
        } else {
            JOptionPane.showMessageDialog(this,
                    textFieldsResult.getMessage());
            return false;
        }
    }

    private ButtonsClickListener createButtonClickListener() {
        return new ButtonsClickListener() {
            @Override
            public void onStartButtonClicked() {
                if (updateModelParamsFromView()) {
                    controller.start();
                    Timer.INSTANCE.refresh();
                    Timer.INSTANCE.start();
                    setCurrentStatus(CalculatingStatus.CALCULATING);
                }
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
                if (updateModelParamsFromView()) {
                    Timer.INSTANCE.refresh();
                    MergeSortModel newModel = controller.generateNewModel();
                    controller.addListener(modelListener);
                    arrayPanel.setMergeSortModel(newModel);
                    setCurrentStatus(CalculatingStatus.READY_TO_CALCULATE);
                    formRefresher.refresh();
                }
            }
        };
    }
}
