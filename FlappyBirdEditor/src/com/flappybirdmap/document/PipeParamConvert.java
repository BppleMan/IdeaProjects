/**
 *
 */
package com.flappybirdmap.document;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * @author BppleMan
 */
public class PipeParamConvert
{
    public static int SCALE;
    public static int SLIDER;
    public static double CONVASY;
    public static double DIV;
    private static double backWidth = 0;
    private static double backHeight = 0;
    private static double pipeWidth = 0;
    private static double pipeHeight = 0;

    public static void setImageWidthAndHeight(double backWidth, double backHeight, double pipeWidth, double pipeHeight)
    {
        if (PipeParamConvert.pipeWidth == 0 && PipeParamConvert.pipeHeight == 0 && PipeParamConvert.backWidth == 0
                && PipeParamConvert.backHeight == 0)
        {
            PipeParamConvert.backWidth = backWidth;
            PipeParamConvert.backHeight = backHeight;
            PipeParamConvert.pipeWidth = pipeWidth;
            PipeParamConvert.pipeHeight = pipeHeight;
        }
    }

    /**
     * 利用Pipe返回一个实际绘制的矩形区域
     *
     * @param pipe pipe表示将要绘制的水管
     * @return
     */
    public static Rectangle2D DeConvertFromPipe(Pipe pipe)
    {
        Rectangle2D result = new Rectangle2D.Double();
        double x, y;
        double w, h;
        //        计算出背景图的缩放大小
        double scaleHeight = getScaleHeight();
        w = pipe.getWidth() * SCALE / 200.0;
        h = pipe.getHeight() * SCALE / 200.0;
        x = pipe.getX() * SCALE / 100.0 - SLIDER;
        y = CONVASY - scaleHeight * pipe.getPercent();
        result.setFrame(x, y, w, h);
        return result;
    }

    /**
     * 返回一个（下水管的镜像：上水管）的实际绘制区域
     *
     * @param rect rect标示上水管的实际绘制区域
     * @return
     */
    public static Rectangle2D DeconvertFromeRectangle(Rectangle2D rect)
    {
        Rectangle2D result = new Rectangle2D.Double();
        double x, y;
        double w, h;
        w = rect.getWidth();
        h = rect.getHeight();
        x = rect.getX();
        y = rect.getY() + h + getDIV();
        result.setFrame(x, y, w, h);
        return result;
    }

    /**
     * 利用Point构建出一个经过坐标转换的Pipe
     *
     * @param p 需要转换的点
     * @return
     * @throws Exception
     */
    public static Pipe ConvertToPipe(Point2D p) throws Exception
    {
        if (PipeParamConvert.pipeWidth == 0 || PipeParamConvert.pipeHeight == 0)
            throw new Exception("ImageWidthAndHeight not set");
        //        计算出背景图的缩放大小
        double scaleHeight = getScaleHeight();
        double percent = (CONVASY - p.getY()) / scaleHeight;
        double x = p.getX() / (SCALE / 100.0) + SLIDER;
        double y = (CONVASY - p.getY()) / (SCALE / 100.0);
        Pipe pipe = new Pipe(new Rectangle2D.Double(x, y, PipeParamConvert.pipeWidth, PipeParamConvert.pipeHeight),
                percent);
        return pipe;
    }

    public static void ConvertPipeWithNewPoint(Pipe pipe, Point2D newPoint) throws Exception
    {
        if (PipeParamConvert.pipeWidth == 0 || PipeParamConvert.pipeHeight == 0)
            throw new Exception("ImageWidthAndHeight not set");
        double scaleHeight = getScaleHeight();
        double percent = (CONVASY - newPoint.getY()) / scaleHeight;
        double x = newPoint.getX() / (SCALE / 100.0) + SLIDER;
        double y = (CONVASY - newPoint.getY()) / (SCALE / 100.0);
        double w = pipe.getWidth();
        double h = pipe.getHeight();
        pipe.setFrame(x, y, w, h);
        pipe.setPercent(percent);
    }

    /**
     * 通过pipe的x、y坐标转换其percent值
     *
     * @param pipe
     * @throws Exception
     */
    public static void ConvertPipe(Pipe pipe) throws Exception
    {
        if (PipeParamConvert.pipeWidth == 0 || PipeParamConvert.pipeHeight == 0)
            throw new Exception("ImageWidthAndHeight not set");
        double scaleHeight = getScaleHeight();
        double percent = (pipe.getY() * (SCALE / 100.0)) / scaleHeight;
        pipe.setPercent(percent);
    }

    /**
     * 获取一个经过坐标转换后的Pipe的副本
     *
     * @param pipe  需要转换的pipe
     * @param point 目的坐标
     * @return 返回一个转换好的clone副本
     * @throws Exception
     */
    public static Pipe GetCopyPipeWithPoint(Pipe pipe, Point2D point) throws Exception
    {
        Pipe copy = (Pipe) pipe.clone();
        if (PipeParamConvert.pipeWidth == 0 || PipeParamConvert.pipeHeight == 0)
            throw new Exception("ImageWidthAndHeight not set");
        double scaleHeight = getScaleHeight();
        double percent = (CONVASY - point.getY()) / scaleHeight;
        double x = point.getX() / (SCALE / 100.0) + SLIDER;
        double y = (CONVASY - point.getY()) / (SCALE / 100.0);
        double w = pipe.getWidth();
        double h = pipe.getHeight();
        copy.setFrame(x, y, w, h);
        copy.setPercent(percent);
        return copy;
    }

    public static double getDIV()
    {
        DIV = 60 * SCALE / 100.0;
        return DIV;
    }

    public static double getScaleHeight()
    {
        return PipeParamConvert.backHeight * SCALE / 200.0;
    }
}
