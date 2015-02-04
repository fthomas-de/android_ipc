package android.support.v4.view;

import android.view.MotionEvent;

class MotionEventCompat$BaseMotionEventVersionImpl implements android.support.v4.view.MotionEventCompat$MotionEventVersionImpl
{

    MotionEventCompat$BaseMotionEventVersionImpl()
    {


        this.<init>();
    }

    public int findPointerIndex(MotionEvent  r1, int  i0)
    {

        byte b1;
        if (i0 != 0)
        {
            b1 = (byte) (byte) -1;
        }
        else
        {
            b1 = (byte) (byte) 0;
        }

        return b1;
    }

    public int getPointerCount(MotionEvent  r1)
    {


        return 1;
    }

    public int getPointerId(MotionEvent  r1, int  i0)
    {


        if (i0 != 0)
        {
            throw new IndexOutOfBoundsException("Pre-Eclair does not support multiple pointers");
        }
        else
        {
            return 0;
        }
    }

    public float getX(MotionEvent  r1, int  i0)
    {


        if (i0 != 0)
        {
            throw new IndexOutOfBoundsException("Pre-Eclair does not support multiple pointers");
        }
        else
        {
            return r1.getX();
        }
    }

    public float getY(MotionEvent  r1, int  i0)
    {


        if (i0 != 0)
        {
            throw new IndexOutOfBoundsException("Pre-Eclair does not support multiple pointers");
        }
        else
        {
            return r1.getY();
        }
    }
}
