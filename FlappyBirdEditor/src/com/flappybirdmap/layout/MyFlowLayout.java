package com.flappybirdmap.layout;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager2;
import java.util.ArrayList;

public class MyFlowLayout implements LayoutManager2
{
    ArrayList<Component> comps = new ArrayList<>();

    @Override public void addLayoutComponent(String name, Component comp)
    {
        // TODO 自动生成的方法存根

    }

    @Override public void removeLayoutComponent(Component comp)
    {
        // TODO 自动生成的方法存根

    }

    @Override public Dimension preferredLayoutSize(Container parent)
    {
        // TODO 自动生成的方法存根
        return null;
    }

    @Override public Dimension minimumLayoutSize(Container parent)
    {
        // TODO 自动生成的方法存根
        return null;
    }

    @Override public void layoutContainer(Container parent)
    {
        synchronized (parent)
        {
            Insets insets = parent.getInsets();
            int left = insets.left;
            int right = parent.getWidth() - insets.right;
            int top = insets.top;
            int buttom = parent.getHeight() - insets.bottom;
            int width = 50;
            int height = 50;
            int x = (parent.getWidth() - 110) / 2;
            int y = top + 10;
            for (int i = 0; i < comps.size(); i++)
            {
                Component comp = comps.get(i);
                comp.setBounds(x, y, 40, 40);
                if (i != 0 && (i + 1) % 2 == 0)
                {
                    y += 60;
                    x = (parent.getWidth() - 110) / 2;
                }
                else
                {
                    x += 60;
                }
            }
        }
    }

    @Override public void addLayoutComponent(Component comp, Object constraints)
    {
        synchronized (comp.getTreeLock())
        {
            comps.add(comp);
        }

    }

    @Override public Dimension maximumLayoutSize(Container target)
    {
        // TODO 自动生成的方法存根
        return null;
    }

    @Override public float getLayoutAlignmentX(Container target)
    {
        // TODO 自动生成的方法存根
        return 0;
    }

    @Override public float getLayoutAlignmentY(Container target)
    {
        // TODO 自动生成的方法存根
        return 0;
    }

    @Override public void invalidateLayout(Container target)
    {
        // TODO 自动生成的方法存根

    }

}
