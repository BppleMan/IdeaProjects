/**
 *
 */
package com.flappybirdmap.event;

import com.flappybirdmap.document.Pipe;

import java.awt.geom.Point2D;

/**
 * @author BppleMan
 */
public class PipeEvent
{
    private Pipe pipe;
    private Point2D toPoint;

    /**
     *
     */
    public PipeEvent(Pipe pipe)
    {
        this.pipe = pipe;
    }

    public Point2D getToPoint()
    {
        return toPoint;
    }

    public void setToPoint(Point2D toPoint)
    {
        this.toPoint = toPoint;
    }

    public Pipe getSource()
    {
        return pipe;
    }
}
