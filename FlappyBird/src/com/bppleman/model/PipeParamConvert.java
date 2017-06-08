package com.bppleman.module;

import com.flappybirdmap.document.Pipe;

import java.awt.geom.Rectangle2D;

/**
 * Created by BppleMan on 17/6/2.
 */
public class PipeParamConvert
{
    private static double DIV;

    /**
     * 利用Pipe返回一个实际绘制的矩形区域
     *
     * @param pipe pipe表示将要绘制的水管
     * @return
     */
    public static Rectangle2D DeConvertFromPipe(Pipe pipe, double backWidth, double backHeight, double scale,
            double bgS)
    {
        Rectangle2D result = new Rectangle2D.Double();
        double x, y;
        double w, h;
        //        计算出背景图的大小
        //        double scaleHeight = getScaleHeight();
        w = pipe.getWidth() * scale;
        h = pipe.getHeight() * scale;
        x = pipe.getX() * scale * 3 - bgS + backWidth;
        y = -backHeight * pipe.getPercent();
        result.setFrame(x, y, w, h);
        return result;
    }

    /**
     * 返回一个（下水管的镜像：上水管）的实际绘制区域
     *
     * @param rect rect标示上水管的实际绘制区域
     * @return
     */
    public static Rectangle2D DeconvertFromeRectangle(Rectangle2D rect, double scale)
    {
        Rectangle2D result = new Rectangle2D.Double();
        double x, y;
        double w, h;
        w = rect.getWidth();
        h = rect.getHeight();
        x = rect.getX();
        y = rect.getY() + h + getDIV(scale);
        result.setFrame(x, y, w, h);
        return result;
    }

    public static double getDIV(double scale)
    {
        DIV = 120 * scale;
        return DIV;
    }
}
