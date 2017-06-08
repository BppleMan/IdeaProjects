package com.bppleman.window;

import javax.swing.*;
import java.awt.*;
import java.util.TimerTask;

public class WindowController
{
    private JFrame mainWinodw;
    private double windowX;
    private double windowY;
    private double windowWidth;
    private double windowHeight;
    private JPanel keyView;
    private TimerTask windowAnimationTask;
    private Rectangle defaultFrame;

    public WindowController(JPanel keyView)
    {
        initMianWindow();
        if (keyView != null)
        {
            setKeyView(keyView);
        }
    }

    public WindowController()
    {
        this(null);
    }

    private void initMianWindow()
    {
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        double scale = 4.0 / 5.0;
        windowWidth = screenSize.getWidth() * scale;
        windowHeight = screenSize.getHeight() * scale;
        windowX = (screenSize.getWidth() - windowWidth) / 2.0;
        windowY = (screenSize.getHeight() - windowHeight) / 2.0;
        mainWinodw = new Window("FlappyBird");
        mainWinodw.setBounds((int) windowX, (int) windowY, (int) windowWidth, (int) windowHeight);
        mainWinodw.setResizable(false);
        defaultFrame = mainWinodw.getBounds();
    }

    public void showWindow()
    {
        mainWinodw.setVisible(true);
    }

    public void setWindowBounds(Dimension size)
    {
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        double scale = windowHeight / (double) size.getHeight();
        windowWidth = (int) size.getWidth() * scale;
        windowX = (int) ((screenSize.getWidth() - windowWidth) / 2.0);
        mainWinodw.setBounds((int) windowX, (int) windowY, (int) windowWidth, (int) windowHeight);
    }

    public void setWindowDefaultFrame()
    {
        mainWinodw.setBounds(defaultFrame);
    }

    public void setKeyView(JPanel keyView)
    {
        if (this.keyView != null)
            mainWinodw.remove(this.keyView);
        this.keyView = keyView;
        mainWinodw.add(this.keyView);
    }

    public void repaintWindow()
    {
        mainWinodw.validate();
        mainWinodw.repaint();
    }
}