package ru.rseu.lovkin.mergesort.view;

import ru.rseu.lovkin.mergesort.controller.Controller;
import ru.rseu.lovkin.mergesort.listeners.Event;
import ru.rseu.lovkin.mergesort.listeners.EventData;
import ru.rseu.lovkin.mergesort.listeners.Listener;

import javax.swing.*;
import java.awt.*;

public class View extends JFrame implements Listener, Refreshable {
    public static final String WINDOW_TITLE = "Визуализация сортировки методом слияния в несколько потоков";

    private Controller controller;
    private MainPage mainPage;

    private JPanel field = new JPanel();

    public View(final Controller controller) throws HeadlessException {
        this.controller = controller;
        this.controller.addListener(this);
        this.setFrameParameters();
        initMainPage();
        add(mainPage);
        this.setVisible(true);
    }

    public void setFrameParameters() {
        this.setLayout(new BorderLayout());
        this.setSize(1500, 1000);
        this.setTitle(WINDOW_TITLE);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    @Override
    public void handleEvent(EventData eventData) {
        if (eventData.getEvent() == Event.NEW_ELEMENT_GROUP) {
            refresh();
        }
        if (eventData.getEvent() == Event.SORT_END) {
            mainPage.setCurrentStatus(CalculatingStatus.RESULT_FOUND);
            refresh();
        }
    }

    @Override
    public void refresh() {
        mainPage.refresh();
        revalidate();
        transferFocus();
        repaint();
    }

    private void initMainPage() {
        mainPage = new MainPage();
        mainPage.setController(controller);
        mainPage.init();
        mainPage.setFormRefresher(this);
        mainPage.setModelListener(this);
        mainPage.setCurrentStatus(CalculatingStatus.READY_TO_CALCULATE);
    }

}
