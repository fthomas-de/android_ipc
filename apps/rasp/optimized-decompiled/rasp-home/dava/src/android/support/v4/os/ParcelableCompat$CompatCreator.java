package android.support.v4.os;

import android.os.Parcelable$Creator;
import android.os.Parcel;

class ParcelableCompat$CompatCreator implements android.os.Parcelable$Creator
{
    final ParcelableCompatCreatorCallbacks mCallbacks;

    public ParcelableCompat$CompatCreator(ParcelableCompatCreatorCallbacks  r1)
    {


        this.<init>();
        mCallbacks = r1;
    }

    public Object createFromParcel(Parcel  r1)
    {


        return mCallbacks.createFromParcel(r1, null);
    }

    public Object[] newArray(int  i0)
    {


        return mCallbacks.newArray(i0);
    }
}
