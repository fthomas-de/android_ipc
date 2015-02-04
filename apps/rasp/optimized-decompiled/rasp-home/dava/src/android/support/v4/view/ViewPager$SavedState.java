package android.support.v4.view;

import android.view.View$BaseSavedState;
import android.support.v4.os.ParcelableCompat;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable$Creator;

public class ViewPager$SavedState extends View$BaseSavedState
{
    public static final Parcelable$Creator CREATOR;
    Parcelable adapterState;
    ClassLoader loader;
    int position;

    static
    {


        CREATOR = ParcelableCompat.newCreator(new ViewPager$SavedState$1());
    }

    ViewPager$SavedState(Parcel  r1, ClassLoader  r2)
    {
        super(r1);


        this.<init>(r1);

        if (r2 == null)
        {
            r2 = this.getClass().getClassLoader();
        }

        position = r1.readInt();
        adapterState = r1.readParcelable(r2);
        loader = r2;
    }

    public ViewPager$SavedState(Parcelable  r1)
    {
        super(r1);


        this.<init>(r1);
    }

    public String toString()
    {


        return (new StringBuilder()).append("FragmentPager.SavedState{").append(Integer.toHexString(System.identityHashCode(this))).append(" position=").append(position).append("}").toString();
    }

    public void writeToParcel(Parcel  r1, int  i0)
    {


        this.writeToParcel(r1, i0);
        r1.writeInt(position);
        r1.writeParcelable(adapterState, i0);
    }
}
