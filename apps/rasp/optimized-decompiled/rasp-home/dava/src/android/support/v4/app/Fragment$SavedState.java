package android.support.v4.app;

import android.os.Parcelable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class Fragment$SavedState implements android.os.Parcelable
{
    public static final Parcelable$Creator CREATOR;
    final Bundle mState;

    static
    {


        CREATOR = new Fragment$SavedState$1();
    }

    Fragment$SavedState(Bundle  r1)
    {


        this.<init>();
        mState = r1;
    }

    Fragment$SavedState(Parcel  r1, ClassLoader  r2)
    {


        this.<init>();
        mState = r1.readBundle();

        if (r2 != null)
        {
            if (mState != null)
            {
                mState.setClassLoader(r2);
            }
        }
    }

    public int describeContents()
    {


        return 0;
    }

    public void writeToParcel(Parcel  r1, int  i0)
    {


        r1.writeBundle(mState);
    }
}
