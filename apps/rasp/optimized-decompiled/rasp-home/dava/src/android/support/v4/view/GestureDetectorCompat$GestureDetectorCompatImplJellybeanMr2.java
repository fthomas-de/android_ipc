package android.support.v4.view;

import android.content.Context;
import android.view.GestureDetector$OnGestureListener;
import android.os.Handler;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.GestureDetector$OnDoubleTapListener;

class GestureDetectorCompat$GestureDetectorCompatImplJellybeanMr2 implements android.support.v4.view.GestureDetectorCompat$GestureDetectorCompatImpl
{
    private final GestureDetector mDetector;

    public GestureDetectorCompat$GestureDetectorCompatImplJellybeanMr2(Context  r1, GestureDetector$OnGestureListener  r2, Handler  r3)
    {


        this.<init>();
        mDetector = new GestureDetector(r1, r2, r3);
    }

    public boolean isLongpressEnabled()
    {


        return mDetector.isLongpressEnabled();
    }

    public boolean onTouchEvent(MotionEvent  r1)
    {


        return mDetector.onTouchEvent(r1);
    }

    public void setIsLongpressEnabled(boolean  z0)
    {


        mDetector.setIsLongpressEnabled(z0);
    }

    public void setOnDoubleTapListener(GestureDetector$OnDoubleTapListener  r1)
    {


        mDetector.setOnDoubleTapListener(r1);
    }
}
