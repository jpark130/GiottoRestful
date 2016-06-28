package com.giotto.visualizer;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.*;

public class MapRenderingPanel extends JPanel{
    private int width;
    private int height;

    public  MapRenderingPanel(int width, int height){
        this.width = width;
        this.height = height;
        JTextField currentTimeStep = new JTextField();
        JButton forwardStep = new JButton("Step Forward");
        JButton backwardStep = new JButton("Step Backwards");
        this.add(currentTimeStep);
        this.add(forwardStep);
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        setOpaque(false);
        this.setSize(width, height);
        this.setBackground(new Color(10,120,120));

        g.drawString("ROBOT STUFF", 20, 20);
        g.drawRect(100, 100, 100, 100);
    }
}
