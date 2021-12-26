package ru.rseu.lovkin.mergesort.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.rseu.lovkin.mergesort.model.layeres.ElementGroup;
import ru.rseu.lovkin.mergesort.model.layeres.Layer;
import ru.rseu.lovkin.mergesort.model.layeres.MergeSortModel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Data
public class ArrayPanel extends JPanel {
    MergeSortModel mergeSortModel;

    @Override
    public void paint(Graphics g) {
        ArrayDrawCharacteristics arrayDrawCharacteristics =
                ArrayDrawCharacteristics.create(mergeSortModel.getInitialArray().getArray(), 0, 15);
        drawArrayWithBorder(g, arrayDrawCharacteristics);
        mergeSortModel.getSortHistoryLayers().values()
                .forEach(layer -> drawLayer(g, layer));
        //      g.drawRect(5, 6, 50, 50);
    }

    private void drawLayer(Graphics g, Layer layer) {
        int depth = layer.getDepth();
        List<ArrayDrawCharacteristics> arrayDrawCharacteristicsList = new ArrayList<>();
        int x = 15;
        for (ElementGroup elementGroup : layer.getGroups()) {
            ArrayDrawCharacteristics arrayDrawCharacteristics =
                    ArrayDrawCharacteristics.create(
                            elementGroup.getElements().getArray(),
                            depth,
                            x);
            arrayDrawCharacteristicsList.add(arrayDrawCharacteristics);
            x += arrayDrawCharacteristics.getWidth() + 15;
        }
        arrayDrawCharacteristicsList.forEach(ac -> drawArrayWithBorder(g, ac));
    }

    private void drawArrayWithBorder(Graphics g, ArrayDrawCharacteristics arrayDrawCharacteristics) {
        drawArray(g, arrayDrawCharacteristics);
        drawBorder(g, arrayDrawCharacteristics);
    }

    private void drawArray(Graphics g, ArrayDrawCharacteristics arrayDrawCharacteristics) {
        Font font = new Font(Font.SERIF, Font.PLAIN, 10);
        g.setColor(Color.BLACK);
        g.setFont(font);
        g.drawString(
                getStringFromArray(arrayDrawCharacteristics.getArray()),
                arrayDrawCharacteristics.getX1(),
                arrayDrawCharacteristics.getY1());
    }

    private void drawBorder(Graphics g, ArrayDrawCharacteristics arrayDrawCharacteristics) {
        g.drawRect(
                arrayDrawCharacteristics.getX1() - 5,
                arrayDrawCharacteristics.getY1() - 10,
                arrayDrawCharacteristics.getWidth() + 10,
                arrayDrawCharacteristics.getHeight() + 10);
    }

    private String getStringFromArray(int[] array) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i : array) {
            stringBuilder.append(i).append(" ");
        }
        return stringBuilder.toString();
    }
}
