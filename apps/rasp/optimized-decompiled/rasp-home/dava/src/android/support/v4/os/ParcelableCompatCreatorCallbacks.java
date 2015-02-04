package android.support.v4.os;

import android.os.Parcel;

public abstract interface ParcelableCompatCreatorCallbacks
{

    public abstract java.lang.Object createFromParcel(android.os.Parcel  r0, java.lang.ClassLoader  r1);

    public abstract java.lang.Object[] newArray(int  i0);
}
