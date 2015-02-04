package android.support.v4.app;

import android.view.View$BaseSavedState;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable$Creator;

class FragmentTabHost$SavedState extends View$BaseSavedState
{
    public static final Parcelable$Creator CREATOR;
    String curTab;

    static
    {


        CREATOR = new FragmentTabHost$SavedState$1();
    }

    private FragmentTabHost$SavedState(Parcel  r1)
    {
        super(r1);


        this.<init>(r1);
        curTab = r1.readString();
    }

    FragmentTabHost$SavedState(Parcel  r1, FragmentTabHost$1  r2)
    {
        this(r1);


        this.<init>(r1);
    }

    FragmentTabHost$SavedState(Parcelable  r1)
    {
        super(r1);


        this.<init>(r1);
    }

    public String toString()
    {


        return (new StringBuilder()).append("FragmentTabHost.SavedState{").append(Integer.toHexString(System.identityHashCode(this))).append(" curTab=").append(curTab).append("}").toString();
    }

    public void writeToParcel(Parcel  r1, int  i0)
    {


        this.writeToParcel(r1, i0);
        r1.writeString(curTab);
    }
}
