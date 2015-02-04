package android.support.v4.app;

import android.os.Parcelable;
import android.os.Parcel;
import android.os.Parcelable$Creator;

final class FragmentManagerState implements android.os.Parcelable
{
    public static final Parcelable$Creator CREATOR;
    FragmentState[] mActive;
    int[] mAdded;
    BackStackState[] mBackStack;

    static
    {


        CREATOR = new FragmentManagerState$1();
    }

    public FragmentManagerState()
    {


        this.<init>();
    }

    public FragmentManagerState(Parcel  r1)
    {


        this.<init>();
        mActive = (FragmentState[]) r1.createTypedArray(FragmentState.CREATOR);
        mAdded = r1.createIntArray();
        mBackStack = (BackStackState[]) r1.createTypedArray(BackStackState.CREATOR);
    }

    public int describeContents()
    {


        return 0;
    }

    public void writeToParcel(Parcel  r1, int  i0)
    {


        r1.writeTypedArray(mActive, i0);
        r1.writeIntArray(mAdded);
        r1.writeTypedArray(mBackStack, i0);
    }
}
