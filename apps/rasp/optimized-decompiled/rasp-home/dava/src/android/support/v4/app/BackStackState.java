package android.support.v4.app;

import android.os.Parcelable;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.text.TextUtils;
import java.util.ArrayList;
import android.util.Log;

final class BackStackState implements android.os.Parcelable
{
    public static final Parcelable$Creator CREATOR;
    final int mBreadCrumbShortTitleRes;
    final CharSequence mBreadCrumbShortTitleText;
    final int mBreadCrumbTitleRes;
    final CharSequence mBreadCrumbTitleText;
    final int mIndex;
    final String mName;
    final int[] mOps;
    final int mTransition;
    final int mTransitionStyle;

    static
    {


        CREATOR = new BackStackState$1();
    }

    public BackStackState(Parcel  r1)
    {


        this.<init>();
        mOps = r1.createIntArray();
        mTransition = r1.readInt();
        mTransitionStyle = r1.readInt();
        mName = r1.readString();
        mIndex = r1.readInt();
        mBreadCrumbTitleRes = r1.readInt();
        mBreadCrumbTitleText = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(r1);
        mBreadCrumbShortTitleRes = r1.readInt();
        mBreadCrumbShortTitleText = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(r1);
    }

    public BackStackState(FragmentManagerImpl  r1, BackStackRecord  r2)
    {

        int i0, i2, i3, i4, i9, i11, i12, i13, i15, i17, i19, i22, i25;
        BackStackRecord$Op r3, r9;
        int[] r11;
        this.<init>();
        i0 = 0;
        r3 = r2.mHead;

        while (r3 != null)
        {
            if (r3.removed != null)
            {
                i0 = i0 + r3.removed.size();
            }

            r3 = r3.next;
        }

        mOps = new int[r2.mNumOp * 7 + i0];

        if (r2.mAddToBackStack != false)
        {
            r9 = r2.mHead;
            i2 = 0;

            while (r9 != null)
            {
                i9 = i2 + 1;
                mOps[i2] = r9.cmd;
                r11 = mOps;
                i11 = i9 + 1;

                if (r9.fragment == null)
                {
                    i12 = -1;
                }
                else
                {
                    i12 = r9.fragment.mIndex;
                }

                r11[i9] = i12;
                i13 = i11 + 1;
                mOps[i11] = r9.enterAnim;
                i15 = i13 + 1;
                mOps[i13] = r9.exitAnim;
                i17 = i15 + 1;
                mOps[i15] = r9.popEnterAnim;
                i19 = i17 + 1;
                mOps[i17] = r9.popExitAnim;

                if (r9.removed == null)
                {
                    i25 = i19 + 1;
                    mOps[i19] = 0;
                }
                else
                {
                    i3 = r9.removed.size();
                    mOps[i19] = i3;
                    i4 = 0;
                    i22 = i19 + 1;

                    if (i4 >= i3)
                    {
                        i25 = i22;
                    }
                    else
                    {
                        mOps[i22] = ((Fragment) r9.removed.get(i4)).mIndex;
                        i4 = i4 + 1;
                        i22 = i22 + 1;
                    }
                }

                r9 = r9.next;
                i2 = i25;
            }

            mTransition = r2.mTransition;
            mTransitionStyle = r2.mTransitionStyle;
            mName = r2.mName;
            mIndex = r2.mIndex;
            mBreadCrumbTitleRes = r2.mBreadCrumbTitleRes;
            mBreadCrumbTitleText = r2.mBreadCrumbTitleText;
            mBreadCrumbShortTitleRes = r2.mBreadCrumbShortTitleRes;
            mBreadCrumbShortTitleText = r2.mBreadCrumbShortTitleText;
            return;
        }
        else
        {
            throw new IllegalStateException("Not on back stack");
        }
    }

    public int describeContents()
    {


        return 0;
    }

    public BackStackRecord instantiate(FragmentManagerImpl  r1)
    {

        BackStackRecord r2;
        int i0, i1, i2, i3, i4, i5, i10, i11, i13, i15, i17, i19;
        BackStackRecord$Op r4;
        Fragment r42;
        r2 = new BackStackRecord(r1);
        i0 = 0;
        i1 = 0;

        while (i0 < mOps.length)
        {
            r4 = new BackStackRecord$Op();
            i2 = i0 + 1;
            r4.cmd = mOps[i0];

            if (FragmentManagerImpl.DEBUG != false)
            {
                Log.v("FragmentManager", (new StringBuilder()).append("Instantiate ").append(r2).append(" op #").append(i1).append(" base fragment #").append(mOps[i2]).toString());
            }

            i10 = i2 + 1;
            i3 = mOps[i2];

            if (i3 < 0)
            {
                r4.fragment = null;
            }
            else
            {
                r4.fragment = (Fragment) r1.mActive.get(i3);
            }

            i11 = i10 + 1;
            r4.enterAnim = mOps[i10];
            i13 = i11 + 1;
            r4.exitAnim = mOps[i11];
            i15 = i13 + 1;
            r4.popEnterAnim = mOps[i13];
            i17 = i15 + 1;
            r4.popExitAnim = mOps[i15];
            i19 = i17 + 1;
            i4 = mOps[i17];

            if (i4 > 0)
            {
                r4.removed = new ArrayList(i4);
                i5 = 0;

                if (i5 < i4)
                {
                    if (FragmentManagerImpl.DEBUG != false)
                    {
                        Log.v("FragmentManager", (new StringBuilder()).append("Instantiate ").append(r2).append(" set remove fragment #").append(mOps[i19]).toString());
                    }

                    r42 = (Fragment) r1.mActive.get(mOps[i19]);
                    r4.removed.add(r42);
                    i5 = i5 + 1;
                    i19 = i19 + 1;
                }
            }

            i0 = i19;
            r2.addOp(r4);
            i1 = i1 + 1;
        }

        r2.mTransition = mTransition;
        r2.mTransitionStyle = mTransitionStyle;
        r2.mName = mName;
        r2.mIndex = mIndex;
        r2.mAddToBackStack = true;
        r2.mBreadCrumbTitleRes = mBreadCrumbTitleRes;
        r2.mBreadCrumbTitleText = mBreadCrumbTitleText;
        r2.mBreadCrumbShortTitleRes = mBreadCrumbShortTitleRes;
        r2.mBreadCrumbShortTitleText = mBreadCrumbShortTitleText;
        r2.bumpBackStackNesting((int) 1);
        return r2;
    }

    public void writeToParcel(Parcel  r1, int  i0)
    {


        r1.writeIntArray(mOps);
        r1.writeInt(mTransition);
        r1.writeInt(mTransitionStyle);
        r1.writeString(mName);
        r1.writeInt(mIndex);
        r1.writeInt(mBreadCrumbTitleRes);
        TextUtils.writeToParcel(mBreadCrumbTitleText, r1, 0);
        r1.writeInt(mBreadCrumbShortTitleRes);
        TextUtils.writeToParcel(mBreadCrumbShortTitleText, r1, 0);
    }
}
