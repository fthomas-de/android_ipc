package android.support.v4.os;

import android.os.Parcelable$ClassLoaderCreator;
import android.os.Parcel;

class ParcelableCompatCreatorHoneycombMR2 implements android.os.Parcelable$ClassLoaderCreator
{
    private final ParcelableCompatCreatorCallbacks mCallbacks;

    public ParcelableCompatCreatorHoneycombMR2(ParcelableCompatCreatorCallbacks  r1)
    {


        this.<init>();
        mCallbacks = r1;
    }

    public Object createFromParcel(Parcel  r1)
    {


        return mCallbacks.createFromParcel(r1, null);
    }

    public Object createFromParcel(Parcel  r1, ClassLoader  r2)
    {


        return mCallbacks.createFromParcel(r1, r2);
    }

    public Object[] newArray(int  i0)
    {


        return mCallbacks.newArray(i0);
    }
}
