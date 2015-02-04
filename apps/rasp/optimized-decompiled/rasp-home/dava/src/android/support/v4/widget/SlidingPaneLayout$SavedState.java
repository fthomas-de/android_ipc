package android.support.v4.widget;

import android.view.View$BaseSavedState;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable$Creator;

class SlidingPaneLayout$SavedState extends View$BaseSavedState
{
    public static final Parcelable$Creator CREATOR;
    boolean isOpen;

    static
    {


        CREATOR = new SlidingPaneLayout$SavedState$1();
    }

    private SlidingPaneLayout$SavedState(Parcel  r1)
    {
        super(r1);

        boolean z0;
        this.<init>(r1);

        if (r1.readInt() == 0)
        {
            z0 = false;
        }
        else
        {
            z0 = true;
        }

        isOpen = z0;
    }

    SlidingPaneLayout$SavedState(Parcel  r1, SlidingPaneLayout$1  r2)
    {
        this(r1);


        this.<init>(r1);
    }

    SlidingPaneLayout$SavedState(Parcelable  r1)
    {
        super(r1);


        this.<init>(r1);
    }

    public void writeToParcel(Parcel  r1, int  i0)
    {

        byte b1;
        this.writeToParcel(r1, i0);

        if (isOpen == false)
        {
            b1 = (byte) (byte) 0;
        }
        else
        {
            b1 = (byte) (byte) 1;
        }

        r1.writeInt(b1);
    }
}
