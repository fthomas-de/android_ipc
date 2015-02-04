package android.support.v4.widget;

import android.os.Build$VERSION;
import android.content.Context;
import android.graphics.Canvas;

public class EdgeEffectCompat
{
    private static final EdgeEffectCompat$EdgeEffectImpl IMPL;
    private Object mEdgeEffect;

    static
    {


        if (Build$VERSION.SDK_INT < 14)
        {
            IMPL = new EdgeEffectCompat$BaseEdgeEffectImpl();
        }
        else
        {
            IMPL = new EdgeEffectCompat$EdgeEffectIcsImpl();
        }
    }

    public EdgeEffectCompat(Context  r1)
    {


        this.<init>();
        mEdgeEffect = IMPL.newEdgeEffect(r1);
    }

    public boolean draw(Canvas  r1)
    {


        return IMPL.draw(mEdgeEffect, r1);
    }

    public void finish()
    {


        IMPL.finish(mEdgeEffect);
    }

    public boolean isFinished()
    {


        return IMPL.isFinished(mEdgeEffect);
    }

    public boolean onAbsorb(int  i0)
    {


        return IMPL.onAbsorb(mEdgeEffect, i0);
    }

    public boolean onPull(float  f0)
    {


        return IMPL.onPull(mEdgeEffect, f0);
    }

    public boolean onRelease()
    {


        return IMPL.onRelease(mEdgeEffect);
    }

    public void setSize(int  i0, int  i1)
    {


        IMPL.setSize(mEdgeEffect, i0, i1);
    }
}
