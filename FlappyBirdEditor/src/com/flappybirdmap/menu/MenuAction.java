package com.flappybirdmap.menu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author BppleMan
 */
public class MenuAction implements ActionListener
{
    private MenuController delegate;

    /**
     *
     */
    public MenuAction(MenuController delegate)
    {
        this.delegate = delegate;
    }

    /*
     * （非 Javadoc）
     *
     * @see
     * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override public void actionPerformed(ActionEvent e)
    {
        JMenuItem item = (JMenuItem) e.getSource();
        if (item == delegate.getOpenFileItem())
        {
            delegate.openFileDidClick();
        }
        else if (item == delegate.getSavaFileItem())
        {
            delegate.saveFileDidClick();
        }
    }

}
