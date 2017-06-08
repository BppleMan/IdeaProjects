package com.flappybirdmap.tool;

import com.flappybirdmap.layout.MyFlowLayout;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;

public class ToolBarController
{
    public static final String PIPE = "pipe";
    public static final String SELECT = "select";
    public static final String REMOVE = "remove";
    public static String BUTTONTYPE = PIPE;
    private JPanel view;
    private ToolButton pipeButton;
    private ToolButton selectButton;
    private ToolButton removeButton;
    private ButtonGroup buttonGroup;
    private ArrayList<ToolButton> buttons;
    private Action action;

    public ToolBarController()
    {
        initMainView();
        initAction();
        initButton();
    }

    private void initMainView()
    {
        view = new JPanel();
        view.setLayout(new MyFlowLayout());
        view.setBackground(new Color(83, 83, 83, 255));
        TitledBorder titledBorder = BorderFactory.createTitledBorder("工具栏");
        titledBorder.setTitleColor(Color.WHITE);
        view.setBorder(titledBorder);
    }

    private void initAction()
    {
        action = new Action(this);
    }

    private void initButton()
    {
        pipeButton = new ToolButton(PIPE);
        selectButton = new ToolButton(SELECT);
        removeButton = new ToolButton(REMOVE);
        buttonGroup = new ButtonGroup();
        buttons = new ArrayList<>();
        buttons.add(pipeButton);
        buttons.add(selectButton);
        buttons.add(removeButton);
        for (ToolButton toolButton : buttons)
        {
            toolButton.addActionListener(action);
            buttonGroup.add(toolButton);
            view.add(toolButton);
        }
        if (BUTTONTYPE == SELECT)
        {
            selectButton.doClick();
        }
        else if (BUTTONTYPE == PIPE)
        {
            pipeButton.doClick();
        }
        else if (BUTTONTYPE == REMOVE)
        {
            removeButton.doClick();
        }
    }

    public JPanel getView()
    {
        return view;
    }

    public ToolButton getButton(String buttonType) throws Exception
    {
        if (buttonType == PIPE)
            return pipeButton;
        else if (buttonType == SELECT)
            return selectButton;
        else if (buttonType == REMOVE)
            return removeButton;
        else
        {
            throw new Exception("button type should be a ToolBarController's static");
        }
    }

    public String getButtonType(ToolButton toolButton)
    {
        if (toolButton == pipeButton)
            return PIPE;
        else if (toolButton == selectButton)
            return SELECT;
        else if (toolButton == removeButton)
            return REMOVE;
        return null;
    }
}
