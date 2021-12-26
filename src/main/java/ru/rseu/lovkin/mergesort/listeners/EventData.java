package ru.rseu.lovkin.mergesort.listeners;

import lombok.Builder;

@Builder
public class EventData {
    private Event event;
    private Object data;

    public EventData(Event event, Object data){
        this.event = event;
        this.data = data;
    }

    public Event getEvent() {
        return event;
    }

    public Object getData() {
        return data;
    }
}
