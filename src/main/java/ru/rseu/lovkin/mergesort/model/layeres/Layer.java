package ru.rseu.lovkin.mergesort.model.layeres;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class Layer {
    private UUID id;
    private int depth;
    private int capacity;
    private List<ElementGroup> groups;

    public Layer() {
        id = UUID.randomUUID();
        groups = new ArrayList<>();
    }

    public boolean isFull() {
        int sum = 0;
        for (ElementGroup group : groups) {
            sum += group.getElements().getArray().length;
        }
        return sum >= capacity;
    }
}
