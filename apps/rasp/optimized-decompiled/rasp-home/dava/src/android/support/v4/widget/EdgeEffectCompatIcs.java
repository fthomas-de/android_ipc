package android.support.v4.widget;

import android.graphics.Canvas;
import android.widget.EdgeEffect;
import android.content.Context;

class EdgeEffectCompatIcs
{

    EdgeEffectCompatIcs()
    {


        this.<init>();
    }

    public static boolean draw(Object  r0, Canvas  r1)
    {


        return ((EdgeEffect) r0).draw(r1);
    }

    public static void finish(Object  r0)
    {


        ((EdgeEffect) r0).finish();
    }

    public static boolean isFinished(Object  r0)
    {


        return ((EdgeEffect) r0).isFinished();
    }

    public static Object newEdgeEffect(Context  r0)
    {


        return new EdgeEffect(r0);
    }

    public static boolean onAbsorb(Object  r0, int  i0)
    {


        ((EdgeEffect) r0).onAbsorb(i0);
        return true;
    }

    public static boolean onPull(Object  r0, float  f0)
    {


        ((EdgeEffect) r0).onPull(f0);
        return true;
    }

    public static boolean onRelease(Object  r0)
    {

        EdgeEffect r2;
        r2 = (EdgeEffect) r0;
        r2.onRelease();
        return r2.isFinished();
    }

    public static void setSize(Object  r0, int  i0, int  i1)
    {


        ((EdgeEffect) r0).setSize(i0, i1);
    }
}
