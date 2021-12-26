package ru.rseu.lovkin.mergesort.view;

import lombok.Data;

import javax.swing.*;

@Data
public class ButtonsPanel extends JPanel {
    private static final String START_BUTTON_TEXT = "Start";
    private static final String GENERATE_NEW_BUTTON_TEXT = "Generate new";
    private static final String STOP_BUTTON_TEXT = "Stop";
    private static final String RESUME_BUTTON_TEXT = "Resume";
    private CalculatingStatus currentStatus;
    private ButtonsClickListener buttonsClickListener;
    private JLabel statusLabel;
    private JButton startButton;
    private JButton generateButton;
    private JButton stopButton;
    private JButton resumeButton;

    public void setCurrentStatus(CalculatingStatus currentStatus) {
        this.currentStatus = currentStatus;
        this.statusLabel.setText(" " + currentStatus.getText());
    }

    public void init(ButtonsClickListener buttonsClickListener) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.buttonsClickListener = buttonsClickListener;
        startButton = createStartJButton();
        add(startButton);
        stopButton = createStopJButton();
        add(createStopJButton());
        generateButton = createGenerateNewJButton();
        add(generateButton);
        resumeButton = createResumeJButton();
        add(resumeButton);
        statusLabel = createStatusLabel();
        add(statusLabel);
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

    private JLabel createStatusLabel() {
        JLabel jLabel = new JLabel();
        jLabel.setText(currentStatus == null ? "" : currentStatus.getText());
        return jLabel;
    }
}
