package android.support.v4.widget;

import android.database.ContentObserver;
import android.os.Handler;

class CursorAdapter$ChangeObserver extends ContentObserver
{
    final CursorAdapter this$0;

    public CursorAdapter$ChangeObserver(CursorAdapter  r1)
    {
        super(new Handler());


        this$0 = r1;
        this.<init>(new Handler());
    }

    public boolean deliverSelfNotifications()
    {


        return true;
    }

    public void onChange(boolean  z0)
    {


        this$0.onContentChanged();
    }
}
