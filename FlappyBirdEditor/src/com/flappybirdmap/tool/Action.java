/**
 *
 */
package com.flappybirdmap.tool;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author BppleMan
 */
public class Action implements ActionListener
{
    private ToolBarController delegate;

    /**
     *
     */
    public Action(ToolBarController delegate)
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
        ToolBarController.BUTTONTYPE = delegate.getButtonType((ToolButton) e.getSource());
    }

}
