package ru.rseu.lovkin.mergesort.view;

import lombok.Data;

import javax.swing.*;

@Data
public class ButtonsPanel extends JPanel {
    private static final String START_BUTTON_TEXT = "Start";
    private static final String GENERATE_NEW_BUTTON_TEXT = "Generate new";
    private static final String STOP_BUTTON_TEXT = "Stop";
    private static final String RESUME_BUTTON_TEXT = "Resume";

    private ButtonsClickListener buttonsClickListener;


    public void init(ButtonsClickListener buttonsClickListener) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.buttonsClickListener = buttonsClickListener;
        add(createStartJButton());
        add(createStopJButton());
        add(createGenerateNewJButton());
        add(createResumeJButton());
    }

    private JButton createStartJButton() {
        JButton jButton = new JButton(START_BUTTON_TEXT);
        jButton.addActionListener(e -> {
            buttonsClickListener.onStartButtonClicked();
        });
        return jButton;
    }

    private JButton createGenerateNewJButton() {
        JButton jButton = new JButton(GENERATE_NEW_BUTTON_TEXT);
        jButton.addActionListener(e -> {
            buttonsClickListener.onGenerateNewButtonClicked();
        });
        return jButton;
    }

    private JButton createStopJButton() {
        JButton jButton = new JButton(STOP_BUTTON_TEXT);
        jButton.addActionListener(e -> {
            buttonsClickListener.onStopButtonClicked();
        });
        return jButton;
    }

    private JButton createResumeJButton() {
        JButton jButton = new JButton(RESUME_BUTTON_TEXT);
        jButton.addActionListener(e -> {
            buttonsClickListener.onResumeButtonClicked();
        });
        return jButton;
    }


}
