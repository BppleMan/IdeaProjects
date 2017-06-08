package com.bppleman.flappybird.layout;

import java.awt.*;

public class OverlappingLayout extends FlowLayout
{
    @Override public void layoutContainer(Container target)
    {
        super.layoutContainer(target);
        int count = target.getComponentCount();
        for (int i = 0; i < count; i++)
        {
            Component c = target.getComponent(i);
            c.setBounds(0, 0, target.getWidth(), target.getHeight());
        }
    }
}
