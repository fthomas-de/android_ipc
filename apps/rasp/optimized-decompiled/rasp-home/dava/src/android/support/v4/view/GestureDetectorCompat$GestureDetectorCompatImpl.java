package android.support.v4.view;

import android.view.MotionEvent;
import android.view.GestureDetector$OnDoubleTapListener;

abstract interface GestureDetectorCompat$GestureDetectorCompatImpl
{

    public abstract boolean isLongpressEnabled();

    public abstract boolean onTouchEvent(android.view.MotionEvent  r0);

    public abstract void setIsLongpressEnabled(boolean  z0);

    public abstract void setOnDoubleTapListener(android.view.GestureDetector$OnDoubleTapListener  r0);
}
