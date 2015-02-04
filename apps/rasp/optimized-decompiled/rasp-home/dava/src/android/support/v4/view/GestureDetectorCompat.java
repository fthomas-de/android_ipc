package android.support.v4.view;

import android.content.Context;
import android.view.GestureDetector$OnGestureListener;
import android.os.Handler;
import android.os.Build$VERSION;
import android.view.MotionEvent;
import android.view.GestureDetector$OnDoubleTapListener;

public class GestureDetectorCompat
{
    private final GestureDetectorCompat$GestureDetectorCompatImpl mImpl;

    public GestureDetectorCompat(Context  r1, GestureDetector$OnGestureListener  r2)
    {
        this(r1, r2, null);


        this.<init>(r1, r2, null);
    }

    public GestureDetectorCompat(Context  r1, GestureDetector$OnGestureListener  r2, Handler  r3)
    {


        this.<init>();

        if (Build$VERSION.SDK_INT <= 17)
        {
            mImpl = new GestureDetectorCompat$GestureDetectorCompatImplBase(r1, r2, r3);
        }
        else
        {
            mImpl = new GestureDetectorCompat$GestureDetectorCompatImplJellybeanMr2(r1, r2, r3);
        }
    }

    public boolean isLongpressEnabled()
    {


        return mImpl.isLongpressEnabled();
    }

    public boolean onTouchEvent(MotionEvent  r1)
    {


        return mImpl.onTouchEvent(r1);
    }

    public void setIsLongpressEnabled(boolean  z0)
    {


        mImpl.setIsLongpressEnabled(z0);
    }

    public void setOnDoubleTapListener(GestureDetector$OnDoubleTapListener  r1)
    {


        mImpl.setOnDoubleTapListener(r1);
    }
}
