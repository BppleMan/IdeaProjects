package com.bppleman.level;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by BppleMan on 17/6/1.
 */
public class NavigationView extends JPanel
{
    private JButton leftNavigation;
    private JButton rightNavigation;
    private BufferedImage leftNavigationImage;
    private BufferedImage rightNavigationImage;

    public NavigationView()
    {
        try
        {
            leftNavigationImage = ImageIO
                    .read(ClassLoader.getSystemResourceAsStream("source/image/level/left_dark.png"));
            rightNavigationImage = ImageIO
                    .read(ClassLoader.getSystemResourceAsStream("source/image/level/right_light.png"));

            leftNavigation = new JButton();
            leftNavigation.setBorderPainted(false);
            leftNavigation.setIcon(new ImageIcon(leftNavigationImage));

            rightNavigation = new JButton();
            rightNavigation.setBorderPainted(false);
            rightNavigation.setIcon(new ImageIcon(rightNavigationImage));

            setLayout(null);
            add(leftNavigation);
            add(rightNavigation);
            setOpaque(false);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        int w = leftNavigationImage.getWidth();
        int h = leftNavigationImage.getHeight();
        leftNavigation.setBounds(0, (int) ((getHeight() - h) / 2.0), w, h);
        rightNavigation.setBounds(getWidth() - w, (int) ((getHeight() - h) / 2.0), w, h);
    }
}
