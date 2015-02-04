package android.support.v4.view;

import android.view.View;
import android.graphics.Paint;

class ViewCompat$HCViewCompatImpl extends ViewCompat$GBViewCompatImpl
{

    ViewCompat$HCViewCompatImpl()
    {


        this.<init>();
    }

    public float getAlpha(View  r1)
    {


        return ViewCompatHC.getAlpha(r1);
    }

    long getFrameTime()
    {


        return ViewCompatHC.getFrameTime();
    }

    public int getLayerType(View  r1)
    {


        return ViewCompatHC.getLayerType(r1);
    }

    public int getMeasuredHeightAndState(View  r1)
    {


        return ViewCompatHC.getMeasuredHeightAndState(r1);
    }

    public int getMeasuredState(View  r1)
    {


        return ViewCompatHC.getMeasuredState(r1);
    }

    public int getMeasuredWidthAndState(View  r1)
    {


        return ViewCompatHC.getMeasuredWidthAndState(r1);
    }

    public int resolveSizeAndState(int  i0, int  i1, int  i2)
    {


        return ViewCompatHC.resolveSizeAndState(i0, i1, i2);
    }

    public void setLayerPaint(View  r1, Paint  r2)
    {


        this.setLayerType(r1, this.getLayerType(r1), r2);
        r1.invalidate();
    }

    public void setLayerType(View  r1, int  i0, Paint  r2)
    {


        ViewCompatHC.setLayerType(r1, i0, r2);
    }
}
