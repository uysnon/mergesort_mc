package ru.rseu.lovkin.mergesort.model.layeres;

import lombok.Data;
import ru.rseu.lovkin.mergesort.model.core.Array;

import java.util.List;
import java.util.UUID;

@Data
public class ElementGroup {
    private UUID id;
    private Array elements;
    private List<ElementGroup> parentGroups;

    public ElementGroup() {
        id = UUID.randomUUID();
    }

    public ElementGroup(Array elements) {
        id = UUID.randomUUID();
        this.elements = elements;
    }
}
