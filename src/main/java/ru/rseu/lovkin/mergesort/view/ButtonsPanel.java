package ru.rseu.lovkin.mergesort.view;

import lombok.Data;
import ru.rseu.lovkin.mergesort.model.params.ModelParams;
import ru.rseu.lovkin.mergesort.model.params.ModelParamsManager;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

@Data
public class ButtonsPanel extends JPanel {
    public static final String ARRAY_SIZE_TEXT = "Array size";
    public static final String THREAD_COUNT_TEXT = "Threads count";
    public static final String DELAY_TEXT = "Delay (ms)";

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
    private JTextField arraySizeTextField;
    private JTextField threadPullSizeTextField;
    private JTextField delayInMsTextField;
    private JLabel timeLabel;
    private long time;

    public void setCurrentStatus(CalculatingStatus currentStatus) {
        this.currentStatus = currentStatus;
        this.statusLabel.setText(" " + currentStatus.getText());
    }

    public void init(ButtonsClickListener buttonsClickListener) {
        ModelParams modelParams = ModelParamsManager.read();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.buttonsClickListener = buttonsClickListener;
        add(new JLabel(ARRAY_SIZE_TEXT));
        arraySizeTextField = createArraySizeTextField(
                String.valueOf(modelParams.getArraySize()));
        add(wrapTextFieldWithPanel(arraySizeTextField));
        add(new JLabel(THREAD_COUNT_TEXT));
        threadPullSizeTextField = createThreadPullSizeTextField(
                String.valueOf(modelParams.getThreadPullSize()));
        add(wrapTextFieldWithPanel(threadPullSizeTextField));
        add(new JLabel(DELAY_TEXT));
        delayInMsTextField = createDelayInMsTextField(
                String.valueOf(modelParams.getDelayInMs()));
        add(wrapTextFieldWithPanel(delayInMsTextField));
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
        timeLabel = new JLabel();
        add(timeLabel);
        updateTime(0);
    }

    public TextFieldsResult getFieldsValues() {
        TextFieldsResult textFieldsResult = new TextFieldsResult();
        textFieldsResult.setStatus(Statuses.OK);
        textFieldsResult.setResultMap(new HashMap<>());
        textFieldsResult.setMessage("");
        addArraySizeResult(textFieldsResult);
        addThreadsCountResult(textFieldsResult);
        addDelayInMsResult(textFieldsResult);
        return textFieldsResult;
    }

    public void updateTime(long timeInMs) {
        time = timeInMs;
        String time = String.valueOf(((double) timeInMs) / 1_000);
        timeLabel.setText("Time: "  + time + "s");
    }


    private void addArraySizeResult(TextFieldsResult result) {
        String arraySizeText = arraySizeTextField.getText();
        if (isPositiveNumber(arraySizeText)) {
            result.getResultMap().put(
                    ARRAY_SIZE_TEXT,
                    Integer.parseInt(arraySizeText));
        } else {
            result.setStatus(Statuses.ERROR);
            String message = result.getMessage();
            message += "Array size must be positive number; ";
            result.setMessage(message);
        }
    }

    private void addThreadsCountResult(TextFieldsResult result) {
        String threadsCountText = threadPullSizeTextField.getText();
        if (isPositiveNumber(threadsCountText)) {
            result.getResultMap().put(
                    THREAD_COUNT_TEXT,
                    Integer.parseInt(threadsCountText));
        } else {
            result.setStatus(Statuses.ERROR);
            String message = result.getMessage();
            message += "Threads count must be positive number; ";
            result.setMessage(message);
        }
    }

    private void addDelayInMsResult(TextFieldsResult result) {
        String delayText = delayInMsTextField.getText();
        if (isPositiveNumber(delayText)) {
            result.getResultMap().put(
                    DELAY_TEXT,
                    Integer.parseInt(delayText));
        } else {
            result.setStatus(Statuses.ERROR);
            String message = result.getMessage();
            message += "Delay must be positive number; ";
            result.setMessage(message);
        }
    }


    private boolean isPositiveNumber(String string) {
        if (string == null || "".equals(string.trim())) {
            return false;
        }
        try {
            int value = Integer.parseInt(string);
            return value >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
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

    private JTextField createArraySizeTextField(String initialValue) {
        JTextField textField = new JTextField(2);
        textField.setToolTipText(ARRAY_SIZE_TEXT);
        textField.setText(initialValue);
        return textField;
    }

    private JTextField createThreadPullSizeTextField(String initialValue) {
        JTextField textField = new JTextField(4);
        textField.setToolTipText(THREAD_COUNT_TEXT);
        textField.setText(initialValue);
        return textField;
    }

    private JTextField createDelayInMsTextField(String initialValue) {
        JTextField textField = new JTextField(4);
        textField.setToolTipText(DELAY_TEXT);
        textField.setText(initialValue);
        return textField;
    }

    private JPanel wrapTextFieldWithPanel(JTextField jTextField) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setPreferredSize(new Dimension(300, 30));
        panel.setMaximumSize(new Dimension(300, 30));
        panel.add(jTextField);
        return panel;
    }
}
