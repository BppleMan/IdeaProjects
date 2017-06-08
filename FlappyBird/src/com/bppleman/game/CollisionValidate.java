package com.bppleman.game;

import com.bppleman.module.Bird;
import com.bppleman.module.PipeParamConvert;
import com.flappybirdmap.document.Pipe;

import java.awt.geom.Rectangle2D;

public class CollisionValidate
{
    public static boolean isPassed(Bird bird, Pipe pipe, double backWidth, double backHeight, double scale, double bgS)
    {
        Rectangle2D rect = PipeParamConvert.DeConvertFromPipe(pipe, backWidth, backHeight, scale, bgS);
        return bird.getX() >= (rect.getX() + rect.getWidth()) ? true : false;
    }
}
