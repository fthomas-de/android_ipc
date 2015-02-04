package android.support.v4.widget;

import android.content.Context;
import android.database.Cursor;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.view.View;

public abstract class ResourceCursorAdapter extends CursorAdapter
{
    private int mDropDownLayout;
    private LayoutInflater mInflater;
    private int mLayout;

    public ResourceCursorAdapter(Context  r1, int  i0, Cursor  r2)
    {
        super(r1, r2);


        this.<init>(r1, r2);
        mDropDownLayout = i0;
        mLayout = i0;
        mInflater = (LayoutInflater) r1.getSystemService("layout_inflater");
    }

    public ResourceCursorAdapter(Context  r1, int  i0, Cursor  r2, int  i1)
    {
        super(r1, r2, i1);


        this.<init>(r1, r2, i1);
        mDropDownLayout = i0;
        mLayout = i0;
        mInflater = (LayoutInflater) r1.getSystemService("layout_inflater");
    }

    public ResourceCursorAdapter(Context  r1, int  i0, Cursor  r2, boolean  z0)
    {
        super(r1, r2, z0);


        this.<init>(r1, r2, z0);
        mDropDownLayout = i0;
        mLayout = i0;
        mInflater = (LayoutInflater) r1.getSystemService("layout_inflater");
    }

    public View newDropDownView(Context  r1, Cursor  r2, ViewGroup  r3)
    {


        return mInflater.inflate(mDropDownLayout, r3, false);
    }

    public View newView(Context  r1, Cursor  r2, ViewGroup  r3)
    {


        return mInflater.inflate(mLayout, r3, false);
    }

    public void setDropDownViewResource(int  i0)
    {


        mDropDownLayout = i0;
    }

    public void setViewResource(int  i0)
    {


        mLayout = i0;
    }
}
