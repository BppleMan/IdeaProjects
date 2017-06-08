package com.flappybirdmap.editor;

import javax.swing.*;
import java.awt.*;

public class MapEditorWindow extends JFrame
{
    public static final double SCALE = (5f / 3f);
    public static final int WIDTH = (int) (1920 / SCALE);
    public static final int HEIGHT = (int) (1080 / SCALE);
    public static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public MapEditorWindow()
    {
        int x = (int) ((screenSize.getWidth() - WIDTH) / 2);
        int y = (int) ((screenSize.getHeight() - HEIGHT) / 2);
        setBounds(x, y, WIDTH, HEIGHT);
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
    }

}
