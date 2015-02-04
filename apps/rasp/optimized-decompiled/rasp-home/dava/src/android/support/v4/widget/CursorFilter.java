package android.support.v4.widget;

import android.widget.Filter;
import android.database.Cursor;
import android.widget.Filter$FilterResults;

class CursorFilter extends Filter
{
    CursorFilter$CursorFilterClient mClient;

    CursorFilter(CursorFilter$CursorFilterClient  r1)
    {


        this.<init>();
        mClient = r1;
    }

    public CharSequence convertResultToString(Object  r1)
    {


        return mClient.convertToString((Cursor) r1);
    }

    protected Filter$FilterResults performFiltering(CharSequence  r1)
    {

        Cursor r3;
        Filter$FilterResults r4;
        r3 = mClient.runQueryOnBackgroundThread(r1);
        r4 = new Filter$FilterResults();

        if (r3 == null)
        {
            r4.count = 0;
            r4.values = null;
        }
        else
        {
            r4.count = r3.getCount();
            r4.values = r3;
        }

        return r4;
    }

    protected void publishResults(CharSequence  r1, Filter$FilterResults  r2)
    {

        Cursor r4;
        r4 = mClient.getCursor();

        if (r2.values != null)
        {
            if (r2.values != r4)
            {
                mClient.changeCursor((Cursor) r2.values);
            }
        }
    }
}
