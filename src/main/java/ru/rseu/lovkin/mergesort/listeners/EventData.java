package ru.rseu.lovkin.mergesort.listeners;

import lombok.Builder;

@Builder
public class EventData {
    private Sender sender;
    private Event event;
    private Object data;

    public EventData(Sender sender, Event event, Object data){
        this.sender = sender;
        this.event = event;
        this.data = data;
    }

    public Sender getSender() {
        return sender;
    }

    public Event getEvent() {
        return event;
    }

    public Object getData() {
        return data;
    }
}
