package android.support.v13.app;

import android.support.v4.view.PagerAdapter;
import android.app.FragmentManager;
import android.view.ViewGroup;
import android.app.FragmentTransaction;
import android.app.Fragment;
import android.view.View;
import android.os.Parcelable;

public abstract class FragmentPagerAdapter extends PagerAdapter
{
    private static final boolean DEBUG = false;
    private static final String TAG = "FragmentPagerAdapter";
    private FragmentTransaction mCurTransaction;
    private Fragment mCurrentPrimaryItem;
    private final FragmentManager mFragmentManager;

    public FragmentPagerAdapter(FragmentManager  r1)
    {

        Object n0;
        n0 = null;
        this.<init>();
        mCurTransaction = n0;
        mCurrentPrimaryItem = n0;
        mFragmentManager = r1;
    }

    public void destroyItem(ViewGroup  r1, int  i0, Object  r2)
    {


        if (mCurTransaction == null)
        {
            mCurTransaction = mFragmentManager.beginTransaction();
        }

        mCurTransaction.detach((Fragment) r2);
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

    public abstract android.app.Fragment getItem(int  i0);

    public long getItemId(int  i0)
    {


        return (long) i0;
    }

    public Object instantiateItem(ViewGroup  r1, int  i0)
    {

        long l1;
        String r3;
        Fragment r4;
        if (mCurTransaction == null)
        {
            mCurTransaction = mFragmentManager.beginTransaction();
        }

        l1 = this.getItemId(i0);
        r3 = FragmentPagerAdapter.makeFragmentName(r1.getId(), l1);
        r4 = mFragmentManager.findFragmentByTag(r3);

        if (r4 == null)
        {
            r4 = this.getItem(i0);
            mCurTransaction.add(r1.getId(), r4, FragmentPagerAdapter.makeFragmentName(r1.getId(), l1));
        }
        else
        {
            mCurTransaction.attach(r4);
        }

        if (r4 != mCurrentPrimaryItem)
        {
            FragmentCompat.setMenuVisibility(r4, false);
            FragmentCompat.setUserVisibleHint(r4, false);
        }

        return r4;
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

    private static String makeFragmentName(int  i0, long  l1)
    {


        return (new StringBuilder()).append("android:switcher:").append(i0).append(":").append(l1).toString();
    }

    public void restoreState(Parcelable  r1, ClassLoader  r2)
    {

    }

    public Parcelable saveState()
    {


        return null;
    }

    public void setPrimaryItem(ViewGroup  r1, int  i0, Object  r2)
    {

        Fragment r5;
        r5 = (Fragment) r2;

        if (r5 != mCurrentPrimaryItem)
        {
            if (mCurrentPrimaryItem != null)
            {
                FragmentCompat.setMenuVisibility(mCurrentPrimaryItem, false);
                FragmentCompat.setUserVisibleHint(mCurrentPrimaryItem, false);
            }

            if (r5 != null)
            {
                FragmentCompat.setMenuVisibility(r5, true);
                FragmentCompat.setUserVisibleHint(r5, true);
            }

            mCurrentPrimaryItem = r5;
        }
    }

    public void startUpdate(ViewGroup  r1)
    {

    }
}
