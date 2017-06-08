package com.flappybirdmap.layout;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Insets;

import com.flappybirdmap.editor.MapEditorWindow;

public class MyBorderLayout extends BorderLayout
{
    private Component north;
    private Component south;
    private Component west;
    private Component east;
    private Component center;

    public MyBorderLayout()
    {
        this(0, 0);
    }

    public MyBorderLayout(int hgap, int vgap)
    {
        super(hgap, vgap);
    }

    @Override public void addLayoutComponent(Component comp, Object constraints)
    {
        synchronized (comp.getTreeLock())
        {
            if ((constraints == null) || (constraints instanceof String))
            {
                addLayoutComponent((String) constraints, comp);
            }
            else
            {
                throw new IllegalArgumentException("cannot add to layout: constraint must be a string (or null)");
            }
        }
    }

    @Override public void addLayoutComponent(String name, Component comp)
    {
        synchronized (comp.getTreeLock())
        {
            if (name == null)
                center = comp;
            else if (name.equals(WEST))
                west = comp;
            else if (name.equals(CENTER))
                center = comp;
            else if (name.equals(EAST))
                east = comp;
            else if (name.equals(NORTH))
                north = comp;
            else if (name.equals(SOUTH))
                south = comp;
            else
                throw new IllegalArgumentException("constraints must be ThreeLayout's N/S/W/E/C");
        }
    }

    @Override public void layoutContainer(Container parent)
    {
        synchronized (parent.getTreeLock())
        {
            Insets insets = parent.getInsets();
            int top = insets.top;
            int bottom = parent.getHeight() - insets.bottom;
            int left = insets.left;
            int right = (int) (parent.getSize().getWidth() - insets.right);
            Component c = null;
            if ((c = getChild(WEST)) != null)
            {
                int width = (int) (240 / MapEditorWindow.SCALE);
                int height = parent.getHeight();
                c.setBounds(left, top, width, bottom - top);
                left += width + getHgap();
            }
            if ((c = getChild(EAST)) != null)
            {
                int width = (int) (270 / MapEditorWindow.SCALE);
                int height = parent.getHeight();
                c.setBounds(right - width, top, width, bottom - top);
                right -= width + getHgap();
            }
            if ((c = getChild(NORTH)) != null)
            {
                int width = parent.getWidth();
                int height = (int) (100 / MapEditorWindow.SCALE);
                c.setBounds(left, top, right - left, height);
                top += height + getVgap();
            }
            if ((c = getChild(SOUTH)) != null)
            {
                int width = parent.getWidth();
                int height = (int) (115 / MapEditorWindow.SCALE);
                c.setBounds(left, bottom - height, right - left, height);
                bottom -= height + getVgap();
            }
            if ((c = getChild(CENTER)) != null)
            {
                c.setBounds(left, top, right - left, bottom - top);
            }
        }
    }

    private Component getChild(String key)
    {
        Component result = null;

        if (key == NORTH)
        {
            result = north;
        }
        else if (key == SOUTH)
        {
            result = south;
        }
        else if (key == WEST)
        {
            result = west;
        }
        else if (key == EAST)
        {
            result = east;
        }
        else if (key == CENTER)
        {
            result = center;
        }
        return result;
    }
}
