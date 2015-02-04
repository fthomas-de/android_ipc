package android.support.v4.view;

import android.database.DataSetObservable;
import android.view.View;
import android.view.ViewGroup;
import android.database.DataSetObserver;
import android.os.Parcelable;

public abstract class PagerAdapter
{
    public static final int POSITION_NONE = -2;
    public static final int POSITION_UNCHANGED = -1;
    private DataSetObservable mObservable;

    public PagerAdapter()
    {


        this.<init>();
        mObservable = new DataSetObservable();
    }

    public void destroyItem(View  r1, int  i0, Object  r2)
    {


        throw new UnsupportedOperationException("Required method destroyItem was not overridden");
    }

    public void destroyItem(ViewGroup  r1, int  i0, Object  r2)
    {


        this.destroyItem(r1, i0, r2);
    }

    public void finishUpdate(View  r1)
    {

    }

    public void finishUpdate(ViewGroup  r1)
    {


        this.finishUpdate(r1);
    }

    public abstract int getCount();

    public int getItemPosition(Object  r1)
    {


        return -1;
    }

    public CharSequence getPageTitle(int  i0)
    {


        return null;
    }

    public float getPageWidth(int  i0)
    {


        return 1.0F;
    }

    public Object instantiateItem(View  r1, int  i0)
    {


        throw new UnsupportedOperationException("Required method instantiateItem was not overridden");
    }

    public Object instantiateItem(ViewGroup  r1, int  i0)
    {


        return this.instantiateItem(r1, i0);
    }

    public abstract boolean isViewFromObject(android.view.View  r0, java.lang.Object  r1);

    public void notifyDataSetChanged()
    {


        mObservable.notifyChanged();
    }

    public void registerDataSetObserver(DataSetObserver  r1)
    {


        mObservable.registerObserver(r1);
    }

    public void restoreState(Parcelable  r1, ClassLoader  r2)
    {

    }

    public Parcelable saveState()
    {


        return null;
    }

    public void setPrimaryItem(View  r1, int  i0, Object  r2)
    {

    }

    public void setPrimaryItem(ViewGroup  r1, int  i0, Object  r2)
    {


        this.setPrimaryItem(r1, i0, r2);
    }

    public void startUpdate(View  r1)
    {

    }

    public void startUpdate(ViewGroup  r1)
    {


        this.startUpdate(r1);
    }

    public void unregisterDataSetObserver(DataSetObserver  r1)
    {


        mObservable.unregisterObserver(r1);
    }
}
