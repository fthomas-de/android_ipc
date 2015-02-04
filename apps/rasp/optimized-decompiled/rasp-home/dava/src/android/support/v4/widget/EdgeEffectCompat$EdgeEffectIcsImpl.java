package android.support.v4.widget;

import android.graphics.Canvas;
import android.content.Context;

class EdgeEffectCompat$EdgeEffectIcsImpl implements android.support.v4.widget.EdgeEffectCompat$EdgeEffectImpl
{

    EdgeEffectCompat$EdgeEffectIcsImpl()
    {


        this.<init>();
    }

    public boolean draw(Object  r1, Canvas  r2)
    {


        return EdgeEffectCompatIcs.draw(r1, r2);
    }

    public void finish(Object  r1)
    {


        EdgeEffectCompatIcs.finish(r1);
    }

    public boolean isFinished(Object  r1)
    {


        return EdgeEffectCompatIcs.isFinished(r1);
    }

    public Object newEdgeEffect(Context  r1)
    {


        return EdgeEffectCompatIcs.newEdgeEffect(r1);
    }

    public boolean onAbsorb(Object  r1, int  i0)
    {


        return EdgeEffectCompatIcs.onAbsorb(r1, i0);
    }

    public boolean onPull(Object  r1, float  f0)
    {


        return EdgeEffectCompatIcs.onPull(r1, f0);
    }

    public boolean onRelease(Object  r1)
    {


        return EdgeEffectCompatIcs.onRelease(r1);
    }

    public void setSize(Object  r1, int  i0, int  i1)
    {


        EdgeEffectCompatIcs.setSize(r1, i0, i1);
    }
}
