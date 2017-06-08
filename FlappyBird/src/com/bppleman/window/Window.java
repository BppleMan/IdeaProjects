package com.bppleman.window;

import javax.swing.*;
import java.awt.*;

/**
 * Created by BppleMan on 17/6/1.
 */
public class Window extends JFrame
{
    private WindowPane wp;

    public Window(String title) throws HeadlessException
    {
        super(title);
        wp = new WindowPane();
        getContentPane().add(wp);
    }

    @Override public Component add(Component comp)
    {
        wp.add(comp);
        return comp;
    }

    @Override public void remove(Component comp)
    {
        wp.remove(comp);
    }
}
