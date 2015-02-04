package android.support.v4.content;

import android.database.ContentObserver;
import android.os.Handler;

public final class Loader$ForceLoadContentObserver extends ContentObserver
{
    final Loader this$0;

    public Loader$ForceLoadContentObserver(Loader  r1)
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
