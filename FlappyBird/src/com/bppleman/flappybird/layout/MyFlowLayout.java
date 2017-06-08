package com.bppleman.flappybird.layout;

import java.awt.*;

public class MyFlowLayout extends FlowLayout
{
    private Dimension itemSize;
    private int rowCount;
    private int colCount;

    /**
     * MyFlowLayout的构造器
     * 需要传入一个默认的item的size作为参数
     * 和行数、列数
     *
     * @param itemSize  默认的item的sizi
     * @param rowCountm 每行的item数量
     * @param colCount  每列的item数量
     */
    public MyFlowLayout(Dimension itemSize, int rowCountm, int colCount)
    {
        super();
        this.itemSize = itemSize;
        this.rowCount = rowCountm;
        this.colCount = colCount;
    }

    @Override public void layoutContainer(Container target)
    {
        super.layoutContainer(target);
        Insets insets = target.getInsets();
        int hgap = 120;
        int vgap = 0;
        int x = (int) ((target.getWidth() - (itemSize.getWidth() * rowCount + (rowCount - 1) * hgap)) / 2.0);
        int y = (int) ((target.getHeight() - (itemSize.getHeight() * colCount + (colCount - 1) * vgap)) / 2.0);
        int top = y;
        int left = x;
        int count = target.getComponentCount();
        boolean useBaseline = getAlignOnBaseline();
        for (int i = 0; i < count; i++)
        {
            Component m = target.getComponent(i);
            if (m.isVisible())
            {
                m.setSize(itemSize.width, itemSize.height);
                m.setLocation(left, top);
                left += itemSize.getWidth() + hgap;
                if ((i + 1) % rowCount == 0)
                {
                    top += itemSize.getHeight() + vgap;
                    left = x;
                }
            }
        }
    }
}
