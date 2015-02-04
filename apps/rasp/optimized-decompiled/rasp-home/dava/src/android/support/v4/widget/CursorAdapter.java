package android.support.v4.widget;

import android.widget.BaseAdapter;
import android.widget.Filterable;
import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.FilterQueryProvider;
import android.database.DataSetObserver;

public abstract class CursorAdapter extends BaseAdapter implements android.widget.Filterable, android.support.v4.widget.CursorFilter$CursorFilterClient
{
    public static final int FLAG_AUTO_REQUERY = 1;
    public static final int FLAG_REGISTER_CONTENT_OBSERVER = 2;
    protected boolean mAutoRequery;
    protected CursorAdapter$ChangeObserver mChangeObserver;
    protected Context mContext;
    protected Cursor mCursor;
    protected CursorFilter mCursorFilter;
    protected DataSetObserver mDataSetObserver;
    protected boolean mDataValid;
    protected FilterQueryProvider mFilterQueryProvider;
    protected int mRowIDColumn;

    public CursorAdapter(Context  r1, Cursor  r2)
    {


        this.<init>();
        this.init(r1, r2, 1);
    }

    public CursorAdapter(Context  r1, Cursor  r2, int  i0)
    {


        this.<init>();
        this.init(r1, r2, i0);
    }

    public CursorAdapter(Context  r1, Cursor  r2, boolean  z0)
    {

        byte b0;
        this.<init>();

        if (z0 == false)
        {
            b0 = (byte) (byte) 2;
        }
        else
        {
            b0 = (byte) (byte) 1;
        }

        this.init(r1, r2, b0);
    }

    public abstract void bindView(android.view.View  r0, android.content.Context  r1, android.database.Cursor  r2);

    public void changeCursor(Cursor  r1)
    {

        Cursor r2;
        r2 = this.swapCursor(r1);

        if (r2 != null)
        {
            r2.close();
        }
    }

    public CharSequence convertToString(Cursor  r1)
    {

        String r2;
        if (r1 != null)
        {
            r2 = r1.toString();
        }
        else
        {
            r2 = "";
        }

        return r2;
    }

    public int getCount()
    {

        int i0;
        label_0:
        {
            if (mDataValid != false)
            {
                if (mCursor != null)
                {
                    i0 = mCursor.getCount();
                    break label_0;
                }
            }

            i0 = 0;
        } //end label_0:


        return i0;
    }

    public Cursor getCursor()
    {


        return mCursor;
    }

    public View getDropDownView(int  i0, View  r1, ViewGroup  r2)
    {

        View r4;
        if (mDataValid == false)
        {
            r4 = null;
        }
        else
        {
            mCursor.moveToPosition(i0);

            if (r1 != null)
            {
                r4 = r1;
            }
            else
            {
                r4 = this.newDropDownView(mContext, mCursor, r2);
            }

            this.bindView(r4, mContext, mCursor);
        }

        return r4;
    }

    public Filter getFilter()
    {


        if (mCursorFilter == null)
        {
            mCursorFilter = new CursorFilter(this);
        }

        return mCursorFilter;
    }

    public FilterQueryProvider getFilterQueryProvider()
    {


        return mFilterQueryProvider;
    }

    public Object getItem(int  i0)
    {

        Cursor r3;
        label_1:
        {
            if (mDataValid != false)
            {
                if (mCursor != null)
                {
                    mCursor.moveToPosition(i0);
                    r3 = mCursor;
                    break label_1;
                }
            }

            r3 = null;
        } //end label_1:


        return r3;
    }

    public long getItemId(int  i0)
    {

        long l1;
        l1 = 0L;

        if (mDataValid != false)
        {
            if (mCursor != null)
            {
                if (mCursor.moveToPosition(i0) != false)
                {
                    l1 = mCursor.getLong(mRowIDColumn);
                }
            }
        }

        return l1;
    }

    public View getView(int  i0, View  r1, ViewGroup  r2)
    {

        View r5;
        if (mDataValid != false)
        {
            if (mCursor.moveToPosition(i0) != false)
            {
                if (r1 != null)
                {
                    r5 = r1;
                }
                else
                {
                    r5 = this.newView(mContext, mCursor, r2);
                }

                this.bindView(r5, mContext, mCursor);
                return r5;
            }
            else
            {
                throw new IllegalStateException((new StringBuilder()).append("couldn\'t move cursor to position ").append(i0).toString());
            }
        }
        else
        {
            throw new IllegalStateException("this should only be called when the cursor is valid");
        }
    }

    public boolean hasStableIds()
    {


        return true;
    }

    void init(Context  r1, Cursor  r2, int  i0)
    {

        Object n0;
        boolean z1;
        int i2;
        n0 = null;
        z1 = true;

        if ((i0 & 1) != (int) 1)
        {
            mAutoRequery = false;
        }
        else
        {
            i0 = i0 | 2;
            mAutoRequery = true;
        }

        if (r2 == null)
        {
            z1 = false;
        }

        mCursor = r2;
        mDataValid = z1;
        mContext = r1;

        if (z1 == false)
        {
            i2 = -1;
        }
        else
        {
            i2 = r2.getColumnIndexOrThrow("_id");
        }

        mRowIDColumn = i2;

        if ((i0 & 2) != 2)
        {
            mChangeObserver = n0;
            mDataSetObserver = n0;
        }
        else
        {
            mChangeObserver = new CursorAdapter$ChangeObserver(this);
            mDataSetObserver = new CursorAdapter$MyDataSetObserver(this, n0);
        }

        if (z1 != false)
        {
            if (mChangeObserver != null)
            {
                r2.registerContentObserver(mChangeObserver);
            }

            if (mDataSetObserver != null)
            {
                r2.registerDataSetObserver(mDataSetObserver);
            }
        }
    }

    protected void init(Context  r1, Cursor  r2, boolean  z0)
    {

        byte b0;
        if (z0 == false)
        {
            b0 = (byte) (byte) 2;
        }
        else
        {
            b0 = (byte) (byte) 1;
        }

        this.init(r1, r2, b0);
    }

    public View newDropDownView(Context  r1, Cursor  r2, ViewGroup  r3)
    {


        return this.newView(r1, r2, r3);
    }

    public abstract android.view.View newView(android.content.Context  r0, android.database.Cursor  r1, android.view.ViewGroup  r2);

    protected void onContentChanged()
    {


        if (mAutoRequery != false)
        {
            if (mCursor != null)
            {
                if (mCursor.isClosed() == false)
                {
                    mDataValid = mCursor.requery();
                }
            }
        }
    }

    public Cursor runQueryOnBackgroundThread(CharSequence  r1)
    {

        Cursor r4;
        if (mFilterQueryProvider == null)
        {
            r4 = mCursor;
        }
        else
        {
            r4 = mFilterQueryProvider.runQuery(r1);
        }

        return r4;
    }

    public void setFilterQueryProvider(FilterQueryProvider  r1)
    {


        mFilterQueryProvider = r1;
    }

    public Cursor swapCursor(Cursor  r1)
    {

        Cursor r3;
        if (r1 != mCursor)
        {
            r3 = mCursor;

            if (r3 != null)
            {
                if (mChangeObserver != null)
                {
                    r3.unregisterContentObserver(mChangeObserver);
                }

                if (mDataSetObserver != null)
                {
                    r3.unregisterDataSetObserver(mDataSetObserver);
                }
            }

            mCursor = r1;

            if (r1 == null)
            {
                mRowIDColumn = -1;
                mDataValid = false;
                this.notifyDataSetInvalidated();
            }
            else
            {
                if (mChangeObserver != null)
                {
                    r1.registerContentObserver(mChangeObserver);
                }

                if (mDataSetObserver != null)
                {
                    r1.registerDataSetObserver(mDataSetObserver);
                }

                mRowIDColumn = r1.getColumnIndexOrThrow("_id");
                mDataValid = true;
                this.notifyDataSetChanged();
            }
        }
        else
        {
            r3 = null;
        }

        return r3;
    }
}
