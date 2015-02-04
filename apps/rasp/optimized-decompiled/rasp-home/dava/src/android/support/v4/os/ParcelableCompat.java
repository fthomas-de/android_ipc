package android.support.v4.os;

import android.os.Build$VERSION;
import android.os.Parcelable$Creator;

public class ParcelableCompat
{

    public ParcelableCompat()
    {


        this.<init>();
    }

    public static Parcelable$Creator newCreator(ParcelableCompatCreatorCallbacks  r0)
    {


        if (Build$VERSION.SDK_INT >= 13)
        {
            ParcelableCompatCreatorHoneycombMR2Stub.instantiate(r0);
        }

        return new ParcelableCompat$CompatCreator(r0);
    }
}
