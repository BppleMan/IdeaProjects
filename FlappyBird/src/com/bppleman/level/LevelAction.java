package com.bppleman.level;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by BppleMan on 17/6/2.
 */
public class LevelAction implements ActionListener
{
    public static String SETTING = "setting";
    public static String GAME = "game";
    private LevelController delegate;

    public LevelAction(LevelController delegate)
    {
        this.delegate = delegate;
    }

    @Override public void actionPerformed(ActionEvent e)
    {
        JButton button = (JButton) e.getSource();
        if (button.getActionCommand() == SETTING)
        {
            delegate.settingButtonDoClick();
        }
        else if (button.getActionCommand() == GAME)
        {
            delegate.levelButtonDoClick(button.getName());
        }
    }
}
