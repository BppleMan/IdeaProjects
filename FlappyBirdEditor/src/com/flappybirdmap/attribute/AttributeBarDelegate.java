/**
 *
 */
package com.flappybirdmap.attribute;

/**
 * @author BppleMan
 */
public interface AttributeBarDelegate
{
    public void shouldRemoveFocus(AttributeBarController controller);

    public void shouldRepaintPipe(double x, double y);
}
