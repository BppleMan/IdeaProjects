package com.flappybirdmap.document;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

public class Pipe extends Rectangle2D.Double implements Serializable, Cloneable
{
    private double percent;

    public Pipe()
    {
        super();
        percent = 0;
    }

    public Pipe(Rectangle2D rect, double percent)
    {
        super(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
        this.percent = percent;
    }

    @Override public boolean contains(double x, double y)
    {
        boolean result = false;
        Rectangle2D rect = PipeParamConvert.DeConvertFromPipe(this);
        result = rect.contains(x, y);
        return result;
    }

    @Override public boolean contains(Point2D p)
    {
        boolean result = false;
        Rectangle2D rect = PipeParamConvert.DeConvertFromPipe(this);
        result = rect.contains(p);
        return result;
    }

    @Override public Object clone()
    {
        Pipe copy = (Pipe) super.clone();
        return copy;
    }

    public double getPercent()
    {
        return percent;
    }

    public void setPercent(double percent)
    {
        this.percent = percent;
    }

}
