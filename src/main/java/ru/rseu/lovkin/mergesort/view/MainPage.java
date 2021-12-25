package ru.rseu.lovkin.mergesort.view;

import lombok.Data;

import javax.swing.*;
import java.awt.*;

@Data
public class MainPage extends JPanel {
    private ButtonsPanel buttonsPanel;
    private ArrayPanel arrayPanel;

    public void init(){
        this.setLayout(new BorderLayout());
        buttonsPanel = new ButtonsPanel();
        buttonsPanel.init(createButtonClickListener());
        add(buttonsPanel,  BorderLayout.WEST);
        ArrayPanel arrayPanel = new ArrayPanel();
        add(arrayPanel);
    }

    private ButtonsClickListener createButtonClickListener(){
        return new ButtonsClickListener() {
            @Override
            public void onStartButtonClicked() {
                System.out.println("on start");
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
                System.out.println("on new game");
            }
        };
    }
}
