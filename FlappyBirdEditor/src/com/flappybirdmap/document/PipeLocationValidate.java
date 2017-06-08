package com.flappybirdmap.document;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * Created by BppleMan on 17/5/29.
 */
public class PipeLocationValidate
{
    public Document datasource;

    public PipeLocationValidate(Document datasource)
    {
        this.datasource = datasource;
    }

    public boolean canAdd(Point2D point)
    {
        boolean result = true;
        try
        {
            Pipe virtual = PipeParamConvert.ConvertToPipe(point);
            Rectangle2D virtualRect = PipeParamConvert.DeConvertFromPipe(virtual);
            Rectangle2D virtualRect2 = PipeParamConvert.DeconvertFromeRectangle(virtualRect);
            double y1 = virtualRect.getY();
            double y2 = virtualRect2.getY() + virtualRect2.getHeight();
            if (!(y1 <= PipeParamConvert.CONVASY && y2 >= (PipeParamConvert.CONVASY + PipeParamConvert
                    .getScaleHeight())))
                return false;
            for (Pipe p : datasource.getPipes())
            {
                Rectangle2D rect = PipeParamConvert.DeConvertFromPipe(p);
                double x1, x2, x3, x4;
                x1 = rect.getX();
                x2 = rect.getX() + rect.getWidth();
                x3 = virtualRect.getX();
                x4 = virtualRect.getX() + virtualRect.getWidth();
                if ((x4 >= x1 && x4 <= x2) || (x3 <= x2 && x3 >= x1))
                {
                    result = false;
                    break;
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }

    public boolean canMove(Pipe pipe, Pipe copy)
    {
        boolean result = true;
        try
        {
            Rectangle2D copyRect = PipeParamConvert.DeConvertFromPipe(copy);
            Rectangle2D copyRect2 = PipeParamConvert.DeconvertFromeRectangle(copyRect);
            double y1 = copyRect.getY();
            double y2 = copyRect2.getY() + copyRect2.getHeight();
            if (!(y1 <= PipeParamConvert.CONVASY && y2 >= (PipeParamConvert.CONVASY + PipeParamConvert
                    .getScaleHeight())))
                return false;
            for (Pipe p : datasource.getPipes())
            {
                if (p != pipe)
                {
                    Rectangle2D rect = PipeParamConvert.DeConvertFromPipe(p);
                    double x1, x2, x3, x4;
                    x1 = rect.getX();
                    x2 = rect.getX() + rect.getWidth();
                    x3 = copyRect.getX();
                    x4 = copyRect.getX() + copyRect.getWidth();
                    if ((x4 >= x1 && x4 <= x2) || (x3 <= x2 && x3 >= x1))
                    {
                        result = false;
                        break;
                    }
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }
}
