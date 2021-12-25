package ru.rseu.lovkin.mergesort.listeners;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class ListenerList {
    private List<Listener> listeners = new ArrayList<>();

    public static ListenerList of(Listener... listeners){
        ListenerList listenerList = new ListenerList();
        listenerList.listeners.addAll(List.of(listeners));
        return listenerList;
    }

    public boolean add(Listener e){
        return listeners.add(e);
    }

    public boolean remove(Listener o){
        return  listeners.remove(o);
    }

    public void notify(EventData eventData){
        ListIterator<Listener> iterator = listeners.listIterator();

        while (iterator.hasNext()){
            Listener listener = iterator.next();
            listener.handleEvent(eventData);
        }
    }
}
