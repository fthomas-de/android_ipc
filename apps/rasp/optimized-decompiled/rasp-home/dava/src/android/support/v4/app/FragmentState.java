package android.support.v4.app;

import android.os.Parcelable;
import android.os.Parcel;
import android.os.Bundle;
import android.util.Log;
import android.os.Parcelable$Creator;

final class FragmentState implements android.os.Parcelable
{
    public static final Parcelable$Creator CREATOR;
    final Bundle mArguments;
    final String mClassName;
    final int mContainerId;
    final boolean mDetached;
    final int mFragmentId;
    final boolean mFromLayout;
    final int mIndex;
    Fragment mInstance;
    final boolean mRetainInstance;
    Bundle mSavedFragmentState;
    final String mTag;

    static
    {


        CREATOR = new FragmentState$1();
    }

    public FragmentState(Parcel  r1)
    {

        boolean z0, z2, z3;
        z0 = true;
        this.<init>();
        mClassName = r1.readString();
        mIndex = r1.readInt();

        if (r1.readInt() == 0)
        {
            z2 = false;
        }
        else
        {
            z2 = true;
        }

        mFromLayout = z2;
        mFragmentId = r1.readInt();
        mContainerId = r1.readInt();
        mTag = r1.readString();

        if (r1.readInt() == 0)
        {
            z3 = false;
        }
        else
        {
            z3 = true;
        }

        mRetainInstance = z3;

        if (r1.readInt() == 0)
        {
            z0 = false;
        }

        mDetached = z0;
        mArguments = r1.readBundle();
        mSavedFragmentState = r1.readBundle();
    }

    public FragmentState(Fragment  r1)
    {


        this.<init>();
        mClassName = r1.getClass().getName();
        mIndex = r1.mIndex;
        mFromLayout = r1.mFromLayout;
        mFragmentId = r1.mFragmentId;
        mContainerId = r1.mContainerId;
        mTag = r1.mTag;
        mRetainInstance = r1.mRetainInstance;
        mDetached = r1.mDetached;
        mArguments = r1.mArguments;
    }

    public int describeContents()
    {


        return 0;
    }

    public Fragment instantiate(FragmentActivity  r1, Fragment  r2)
    {

        Fragment r6;
        if (mInstance == null)
        {
            if (mArguments != null)
            {
                mArguments.setClassLoader(r1.getClassLoader());
            }

            mInstance = Fragment.instantiate(r1, mClassName, mArguments);

            if (mSavedFragmentState != null)
            {
                mSavedFragmentState.setClassLoader(r1.getClassLoader());
                mInstance.mSavedFragmentState = mSavedFragmentState;
            }

            mInstance.setIndex(mIndex, r2);
            mInstance.mFromLayout = mFromLayout;
            mInstance.mRestored = true;
            mInstance.mFragmentId = mFragmentId;
            mInstance.mContainerId = mContainerId;
            mInstance.mTag = mTag;
            mInstance.mRetainInstance = mRetainInstance;
            mInstance.mDetached = mDetached;
            mInstance.mFragmentManager = r1.mFragments;

            if (FragmentManagerImpl.DEBUG != false)
            {
                Log.v("FragmentManager", (new StringBuilder()).append("Instantiated fragment ").append(mInstance).toString());
            }

            r6 = mInstance;
        }
        else
        {
            r6 = mInstance;
        }

        return r6;
    }

    public void writeToParcel(Parcel  r1, int  i0)
    {

        byte b1, b4, b7;
        b1 = (byte) (byte) 1;
        r1.writeString(mClassName);
        r1.writeInt(mIndex);

        if (mFromLayout == false)
        {
            b4 = (byte) (byte) 0;
        }
        else
        {
            b4 = (byte) (byte) 1;
        }

        r1.writeInt(b4);
        r1.writeInt(mFragmentId);
        r1.writeInt(mContainerId);
        r1.writeString(mTag);

        if (mRetainInstance == false)
        {
            b7 = (byte) (byte) 0;
        }
        else
        {
            b7 = (byte) (byte) 1;
        }

        r1.writeInt(b7);

        if (mDetached == false)
        {
            b1 = (byte) (byte) 0;
        }

        r1.writeInt(b1);
        r1.writeBundle(mArguments);
        r1.writeBundle(mSavedFragmentState);
    }
}
