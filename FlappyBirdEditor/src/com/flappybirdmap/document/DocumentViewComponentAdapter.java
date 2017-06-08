/**
 *
 */
package com.flappybirdmap.document;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * @author BppleMan 这是一个继承了ComponentAdapter抽象类的一个具体实现类
 *         主要是为了通知DocumentView关于componentResized时应该做的改变
 */
public class DocumentViewComponentAdapter extends ComponentAdapter
{
    private DocumentView delegate;

    /**
     *
     */
    public DocumentViewComponentAdapter(DocumentView delegate)
    {
        this.delegate = delegate;
    }

    @Override public void componentResized(ComponentEvent e)
    {
        delegate.updateSliderBounds();
    }
}
