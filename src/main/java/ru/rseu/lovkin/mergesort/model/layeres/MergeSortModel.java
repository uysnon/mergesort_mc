package ru.rseu.lovkin.mergesort.model.layeres;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ru.rseu.lovkin.mergesort.listeners.Event;
import ru.rseu.lovkin.mergesort.listeners.EventData;
import ru.rseu.lovkin.mergesort.listeners.Listener;
import ru.rseu.lovkin.mergesort.listeners.ListenerList;
import ru.rseu.lovkin.mergesort.model.core.Array;
import ru.rseu.lovkin.mergesort.model.core.MultiThreadSorter;
import ru.rseu.lovkin.mergesort.model.time.Timer;

import java.util.*;

@Data
@Builder
@AllArgsConstructor
public class MergeSortModel implements Listener {
    private ListenerList listeners;
    private Map<Integer, Layer> sortHistoryLayers;
    private MultiThreadSorter multiThreadSorter;
    private int initialArrayCapacity;
    private Array initialArray;
    private Array resultArray;

    public void start() {
        Thread thread = new Thread(multiThreadSorter);
        thread.start();
    }

    public MergeSortModel() {
        listeners = new ListenerList();
        sortHistoryLayers = new HashMap<>();
    }

    public void addElementGroup(ElementGroup elementGroup) {
        int depth = findDepthOfGroup(elementGroup);
        if (sortHistoryLayers.get(depth) == null) {
            sortHistoryLayers.put(
                    depth,
                    Layer.builder()
                            .id(UUID.randomUUID())
                            .capacity(initialArrayCapacity)
                            .depth(depth)
                            .groups(new ArrayList<>())
                            .build());
        }
        sortHistoryLayers.get(depth).getGroups().add(elementGroup);

        System.out.println(Arrays.toString(elementGroup.getElements().getArray()) + "depth : " + depth);
    }

    @Override
    public synchronized void handleEvent(EventData eventData) {
        if (eventData.getEvent() == Event.NEW_ELEMENT_GROUP) {
            addElementGroup((ElementGroup) eventData.getData());
            listeners.notify(eventData);
            if (isElementGroupResult((ElementGroup) eventData.getData())) {
                Timer.INSTANCE.stop();
                EventData resultFoundEventData = new EventData(
                        Event.SORT_END,
                        ((ElementGroup) eventData.getData()).getElements().getArray());
                listeners.notify(resultFoundEventData);
            }
        }
    }

    public void addListener(Listener listener) {
        listeners.add(listener);
    }

    private int findDepthOfGroup(ElementGroup group) {
        int depth = 0;
        while (group.getParentGroups() != null) {
            group = group.getParentGroups().get(0);
            depth++;
        }
        return depth;
    }

    private boolean isElementGroupResult(ElementGroup elementGroup) {
        return elementGroup.getParentGroups() != null && elementGroup.getElements().getArray().length >= initialArrayCapacity;
    }
}
