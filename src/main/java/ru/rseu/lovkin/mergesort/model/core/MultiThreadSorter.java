package ru.rseu.lovkin.mergesort.model.core;


import lombok.Data;
import ru.rseu.lovkin.mergesort.model.Model;
import ru.rseu.lovkin.mergesort.model.layeres.ElementGroup;
import ru.rseu.lovkin.mergesort.listeners.Event;
import ru.rseu.lovkin.mergesort.listeners.EventData;

@Data
public class MultiThreadSorter extends Model implements Runnable, Merger {
    private final static int MIN_ARRAY_SIZE = 1;
    public static final int SPLIT_ARRAY_SIZE = 2;
    private ElementGroup unsorted;
    private ElementGroup sorted;
    private ThreadSortersCounter threadSortersCounter;

    public MultiThreadSorter(ElementGroup unsorted) {
        this.unsorted = unsorted;
    }

    public MultiThreadSorter() {
    }

    public ThreadSortersCounter getThreadSortersCounter() {
        return threadSortersCounter;
    }

    public void setThreadSortersCounter(ThreadSortersCounter threadSortersCounter) {
        this.threadSortersCounter = threadSortersCounter;
    }

    private Thread getCurrentThread() {
        return Thread.currentThread();
    }

    @Override
    public void run() {
        if (unsorted.getElements().getArray().length > MIN_ARRAY_SIZE) {
            if (threadSortersCounter.pop(SPLIT_ARRAY_SIZE)) {

                MultiThreadSorter leftSort = SortersUtils.createNewMultiThreadSorter(
                        new Array(unsorted.getElements().getLeftPart()),
                        this,
                        unsorted);
                MultiThreadSorter rightSort = SortersUtils.createNewMultiThreadSorter(
                        new Array(unsorted.getElements().getRightPart()),
                        this,
                        unsorted);

                getListenerList().notify(EventData.builder().event(Event.NEW_ELEMENT_GROUP).data(leftSort.unsorted).build());
                getListenerList().notify(EventData.builder().event(Event.NEW_ELEMENT_GROUP).data(rightSort.unsorted).build());


                Thread leftSortThread = new Thread(leftSort);
                Thread rightSortThread = new Thread(rightSort);

                System.out.println("Thread: "
                        + getCurrentThread().getName()
                        + " split into 2 threadsSorters: 1) "
                        + leftSortThread.getName() +
                        ", 2) "
                        + rightSortThread.getName());

                leftSortThread.start();
                rightSortThread.start();

                try {
                    leftSortThread.join();
                    rightSortThread.join();
                    threadSortersCounter.push(2);

                    sorted = SortersUtils.createNewElementGroupFromParent(
                            merge(leftSort.getSorted().getElements(), rightSort.getSorted().getElements()),
                            leftSort.sorted, rightSort.sorted);

                    getListenerList().notify(EventData.builder().event(Event.NEW_ELEMENT_GROUP).data(sorted).build());

                    System.out.println("Thread: "
                            + getCurrentThread().getName() +
                            " merge from 2 threadsSorters: 1) " + leftSortThread.getName() +
                            ", 2) " + rightSortThread.getName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                OneThreadSorter leftSort = SortersUtils.createNewOneThreadSorter(
                        new Array(unsorted.getElements().getLeftPart()),
                        this,
                        unsorted);
                OneThreadSorter rightSort = SortersUtils.createNewOneThreadSorter(
                        new Array(unsorted.getElements().getRightPart()),
                        this,
                        unsorted);

                getListenerList().notify(EventData.builder().event(Event.NEW_ELEMENT_GROUP).data(leftSort.getUnsorted()).build());
                getListenerList().notify(EventData.builder().event(Event.NEW_ELEMENT_GROUP).data(rightSort.getUnsorted()).build());

                leftSort.sort();
                rightSort.sort();

                sorted = SortersUtils.createNewElementGroupFromParent(
                        merge(leftSort.getSorted().getElements(), rightSort.getSorted().getElements()),
                        leftSort.getSorted(), rightSort.getSorted());
                getListenerList().notify(EventData.builder().event(Event.NEW_ELEMENT_GROUP).data(sorted).build());
            }
        } else {
            sorted = unsorted;
        }
    }
}
