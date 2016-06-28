package com.giotto.visualizer;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;


public class MappingFrame extends JFrame{
    JButton forwardStep;
    JButton backwardStep;
    JLabel timeStepLabel;
    JTextField currentTimeStep;

    public MappingFrame() throws IOException{

        JPanel drawingWindow = new MapRenderingPanel(500,300);
        this.add(drawingWindow);
        this.add(new MapRenderingPanel(500,300));
        this.add(new JLabel("Current Timestep:"));
        currentTimeStep = new JTextField();
        forwardStep = new JButton("Step Forward");
        backwardStep = new JButton("Step Backwards");
        this.add(currentTimeStep);
        this.add(forwardStep);
//        this.add(backwardStep);
//        JLabel picLabel = new JLabel(new ImageIcon("/Users/Joonho/Documents/workspace/GiottoDB/map/Newell-Simon_GIS-A-unsh.png"));
//        System.out.println(picLabel.WIDTH);
//        this.add(picLabel);
        forwardStep.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {System.out.println("HI");}
          });
    }

}
