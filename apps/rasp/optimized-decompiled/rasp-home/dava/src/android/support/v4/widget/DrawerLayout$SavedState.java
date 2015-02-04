package android.support.v4.widget;

import android.view.View$BaseSavedState;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable$Creator;

public class DrawerLayout$SavedState extends View$BaseSavedState
{
    public static final Parcelable$Creator CREATOR;
    int lockModeLeft;
    int lockModeRight;
    int openDrawerGravity;

    static
    {


        CREATOR = new DrawerLayout$SavedState$1();
    }

    public DrawerLayout$SavedState(Parcel  r1)
    {
        super(r1);


        this.<init>(r1);
        openDrawerGravity = 0;
        lockModeLeft = 0;
        lockModeRight = 0;
        openDrawerGravity = r1.readInt();
    }

    public DrawerLayout$SavedState(Parcelable  r1)
    {
        super(r1);


        this.<init>(r1);
        openDrawerGravity = 0;
        lockModeLeft = 0;
        lockModeRight = 0;
    }

    public void writeToParcel(Parcel  r1, int  i0)
    {


        this.writeToParcel(r1, i0);
        r1.writeInt(openDrawerGravity);
    }
}
