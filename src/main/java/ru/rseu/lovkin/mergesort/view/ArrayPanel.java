package ru.rseu.lovkin.mergesort.view;

import lombok.Data;
import ru.rseu.lovkin.mergesort.model.layeres.MergeSortModel;

import javax.swing.*;
import java.awt.*;

@Data
public class ArrayPanel extends JPanel {
    MergeSortModel mergeSortModel;

    @Override
    public void paint(Graphics g) {
            g.setColor(Color.BLACK);
            g.drawRect(5, 6, 50, 50);
    }
}
