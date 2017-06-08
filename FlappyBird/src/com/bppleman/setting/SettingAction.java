package com.bppleman.setting;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by BppleMan on 17/5/29.
 */
public class SettingAction implements ActionListener
{
    public static String LOGIN = "login";
    public static String REGISTER = "register";
    public static String EXIT = "exit";
    private SettingController delegate;

    public SettingAction(SettingController delegate)
    {
        this.delegate = delegate;
    }

    @Override public void actionPerformed(ActionEvent e)
    {
        JButton btn = (JButton) e.getSource();
        if (btn.getActionCommand() == LOGIN)
        {
            delegate.loginButtonDoClick();
        }
        else if (btn.getActionCommand() == REGISTER)
        {
            delegate.registButtonDoClick();
        }
        else if (btn.getActionCommand() == EXIT)
        {
            delegate.closeButtonDoClick();
        }
    }
}
