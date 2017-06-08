package com.bppleman.window;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by BppleMan on 17/6/1.
 */
public class WindowPane extends JPanel
{
    private BufferedImage backGroundImage;

    public WindowPane()
    {
        setLayout(new BorderLayout());
        initImage();
    }

    private void initImage()
    {
        try
        {
            backGroundImage = ImageIO.read(ClassLoader.getSystemResourceAsStream("source/image/level/bg.png"));
            MediaTracker mediaTracker = new MediaTracker(this);
            mediaTracker.addImage(backGroundImage, 0);
            mediaTracker.waitForAll();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    @Override protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(backGroundImage, 0, 0, getWidth(), getHeight(), null);
    }
}
