package android.support.v4.widget;

import android.database.DataSetObserver;

class CursorAdapter$MyDataSetObserver extends DataSetObserver
{
    final CursorAdapter this$0;

    private CursorAdapter$MyDataSetObserver(CursorAdapter  r1)
    {


        this$0 = r1;
        this.<init>();
    }

    CursorAdapter$MyDataSetObserver(CursorAdapter  r1, CursorAdapter$1  r2)
    {
        this(r1);


        this.<init>(r1);
    }

    public void onChanged()
    {


        this$0.mDataValid = true;
        this$0.notifyDataSetChanged();
    }

    public void onInvalidated()
    {


        this$0.mDataValid = false;
        this$0.notifyDataSetInvalidated();
    }
}
