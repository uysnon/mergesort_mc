package ru.rseu.lovkin.mergesort.model.core;

import lombok.Data;
import ru.rseu.lovkin.mergesort.model.Model;
import ru.rseu.lovkin.mergesort.model.layeres.ElementGroup;
import ru.rseu.lovkin.mergesort.listeners.Event;
import ru.rseu.lovkin.mergesort.listeners.EventData;

@Data
public class OneThreadSorter extends Model implements Merger {
    private final static int MIN_ARRAY_SIZE = 1;
    private ElementGroup unsorted;
    private ElementGroup sorted;

    public OneThreadSorter(ElementGroup unsorted) {
        this.unsorted = unsorted;
    }

    public void sort() {
        if (unsorted.getElements().getArray().length > MIN_ARRAY_SIZE) {
            OneThreadSorter leftSort = SortersUtils.createNewOneThreadSorter(
                    new Array(unsorted.getElements().getLeftPart()),
                    this,
                    unsorted);
            OneThreadSorter rightSort = SortersUtils.createNewOneThreadSorter(
                    new Array(unsorted.getElements().getRightPart()),
                    this,
                    unsorted);

            getListenerList().notify(EventData.builder().event(Event.NEW_ELEMENT_GROUP).data(leftSort.unsorted).build());
            getListenerList().notify(EventData.builder().event(Event.NEW_ELEMENT_GROUP).data(rightSort.unsorted).build());

            leftSort.sort();
            rightSort.sort();

            sorted = SortersUtils.createNewElementGroupFromParent(
                    merge(leftSort.getSorted().getElements(), rightSort.getSorted().getElements()),
                    leftSort.sorted, rightSort.sorted);
            getListenerList().notify(EventData.builder().event(Event.NEW_ELEMENT_GROUP).data(sorted).build());
        } else {
            sorted = unsorted;
        }
    }

    public ElementGroup getSorted() {
        return sorted;
    }
}
