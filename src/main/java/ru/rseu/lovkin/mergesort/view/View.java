package ru.rseu.lovkin.mergesort.view;

import ru.rseu.lovkin.mergesort.listeners.Event;
import ru.rseu.lovkin.mergesort.listeners.EventData;
import ru.rseu.lovkin.mergesort.listeners.Listener;
import ru.rseu.lovkin.mergesort.listeners.Sender;

import ru.rseu.lovkin.mergesort.controller.Controller;

import javax.swing.*;
import java.awt.*;

public class View extends JFrame implements Listener {
    public static final String WINDOW_TITLE = "Визуализация сортировки методом слияния в несколько потоков";

    private Controller controller;
    private MainPage mainPage;

    private JPanel field = new JPanel();

    public View(final Controller controller) throws HeadlessException {
        this.controller = controller;
        this.controller.addListener(this);
        this.setFrameParameters();
        mainPage = new MainPage();
        mainPage.init();
        add(mainPage);
        this.setVisible(true);
    }

    public void setFrameParameters(){
        this.setLayout(new BorderLayout());
        this.setSize(1000, 1000);
        this.setTitle(WINDOW_TITLE);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    @Override
    public void handleEvent(EventData eventData) {
        Sender sender = eventData.getSender();
        Event event = eventData.getEvent();
        Object data = eventData.getData();

        /*if(sender == ) {
            initializeField(data)
        }

         */
    }

}
