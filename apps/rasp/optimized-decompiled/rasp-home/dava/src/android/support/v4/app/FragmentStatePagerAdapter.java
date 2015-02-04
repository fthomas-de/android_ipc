package android.support.v4.app;

import android.support.v4.view.PagerAdapter;
import java.util.ArrayList;
import android.view.ViewGroup;
import android.view.View;
import android.os.Parcelable;
import java.util.Iterator;
import android.os.Bundle;
import android.util.Log;

public abstract class FragmentStatePagerAdapter extends PagerAdapter
{
    private static final boolean DEBUG = false;
    private static final String TAG = "FragmentStatePagerAdapter";
    private FragmentTransaction mCurTransaction;
    private Fragment mCurrentPrimaryItem;
    private final FragmentManager mFragmentManager;
    private ArrayList mFragments;
    private ArrayList mSavedState;

    public FragmentStatePagerAdapter(FragmentManager  r1)
    {

        Object n0;
        n0 = null;
        this.<init>();
        mCurTransaction = n0;
        mSavedState = new ArrayList();
        mFragments = new ArrayList();
        mCurrentPrimaryItem = n0;
        mFragmentManager = r1;
    }

    public void destroyItem(ViewGroup  r1, int  i0, Object  r2)
    {

        Object n0;
        Fragment r6;
        n0 = null;
        r6 = (Fragment) r2;

        if (mCurTransaction == null)
        {
            mCurTransaction = mFragmentManager.beginTransaction();
        }

        while (mSavedState.size() <= i0)
        {
            mSavedState.add(n0);
        }

        mSavedState.set(i0, mFragmentManager.saveFragmentInstanceState(r6));
        mFragments.set(i0, n0);
        mCurTransaction.remove(r6);
    }

    public void finishUpdate(ViewGroup  r1)
    {


        if (mCurTransaction != null)
        {
            mCurTransaction.commitAllowingStateLoss();
            mCurTransaction = null;
            mFragmentManager.executePendingTransactions();
        }
    }

    public abstract android.support.v4.app.Fragment getItem(int  i0);

    public Object instantiateItem(ViewGroup  r1, int  i0)
    {

        Fragment r4, r7;
        Fragment$SavedState r13;
        label_0:
        {
            if (mFragments.size() > i0)
            {
                r7 = (Fragment) mFragments.get(i0);

                if (r7 != null)
                {
                    break label_0;
                }
            }

            if (mCurTransaction == null)
            {
                mCurTransaction = mFragmentManager.beginTransaction();
            }

            r4 = this.getItem(i0);

            if (mSavedState.size() > i0)
            {
                r13 = (Fragment$SavedState) mSavedState.get(i0);

                if (r13 != null)
                {
                    r4.setInitialSavedState(r13);
                }
            }

            while (mFragments.size() <= i0)
            {
                mFragments.add(null);
            }

            r4.setMenuVisibility(false);
            r4.setUserVisibleHint(false);
            mFragments.set(i0, r4);
            mCurTransaction.add(r1.getId(), r4);
            r7 = r4;
        } //end label_0:


        return r7;
    }

    public boolean isViewFromObject(View  r1, Object  r2)
    {

        boolean z0;
        if (((Fragment) r2).getView() != r1)
        {
            z0 = false;
        }
        else
        {
            z0 = true;
        }

        return z0;
    }

    public void restoreState(Parcelable  r1, ClassLoader  r2)
    {

        Parcelable[] r5;
        int i0, i1;
        Iterator r8;
        Fragment r10;
        Bundle r12;
        String r17;
        if (r1 != null)
        {
            r12 = (Bundle) r1;
            r12.setClassLoader(r2);
            r5 = r12.getParcelableArray("states");
            mSavedState.clear();
            mFragments.clear();

            if (r5 != null)
            {
                i0 = 0;

                while (i0 < r5.length)
                {
                    mSavedState.add((Fragment$SavedState) r5[i0]);
                    i0 = i0 + 1;
                }
            }

            r8 = r12.keySet().iterator();

            while (r8.hasNext() != false)
            {
                r17 = (String) r8.next();

                if (r17.startsWith("f") != false)
                {
                    i1 = Integer.parseInt(r17.substring(1));
                    r10 = mFragmentManager.getFragment(r12, r17);

                    if (r10 == null)
                    {
                        Log.w("FragmentStatePagerAdapter", (new StringBuilder()).append("Bad fragment at key ").append(r17).toString());
                    }
                    else
                    {
                        if (mFragments.size() > i1)
                        {
                            r10.setMenuVisibility(false);
                            mFragments.set(i1, r10);
                        }
                        else
                        {
                            mFragments.add(null);
                        }
                    }
                }
            }
        }
    }

    public Parcelable saveState()
    {

        Bundle r1;
        Fragment$SavedState[] r3;
        int i0;
        String r6;
        Fragment r13;
        r1 = null;

        if (mSavedState.size() > 0)
        {
            r1 = new Bundle();
            r3 = new Fragment$SavedState[mSavedState.size()];
            mSavedState.toArray(r3);
            r1.putParcelableArray("states", r3);
        }

        i0 = 0;

        while (i0 < mFragments.size())
        {
            r13 = (Fragment) mFragments.get(i0);

            if (r13 != null)
            {
                if (r1 == null)
                {
                    r1 = new Bundle();
                }

                r6 = (new StringBuilder()).append("f").append(i0).toString();
                mFragmentManager.putFragment(r1, r6, r13);
            }

            i0 = i0 + 1;
        }

        return r1;
    }

    public void setPrimaryItem(ViewGroup  r1, int  i0, Object  r2)
    {

        Fragment r5;
        r5 = (Fragment) r2;

        if (r5 != mCurrentPrimaryItem)
        {
            if (mCurrentPrimaryItem != null)
            {
                mCurrentPrimaryItem.setMenuVisibility(false);
                mCurrentPrimaryItem.setUserVisibleHint(false);
            }

            if (r5 != null)
            {
                r5.setMenuVisibility(true);
                r5.setUserVisibleHint(true);
            }

            mCurrentPrimaryItem = r5;
        }
    }

    public void startUpdate(ViewGroup  r1)
    {

    }
}
