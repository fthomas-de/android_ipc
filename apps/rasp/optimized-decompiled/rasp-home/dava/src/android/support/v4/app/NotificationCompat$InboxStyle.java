package android.support.v4.app;

import java.util.ArrayList;

public class NotificationCompat$InboxStyle extends NotificationCompat$Style
{
    ArrayList mTexts;

    public NotificationCompat$InboxStyle()
    {


        this.<init>();
        mTexts = new ArrayList();
    }

    public NotificationCompat$InboxStyle(NotificationCompat$Builder  r1)
    {


        this.<init>();
        mTexts = new ArrayList();
        this.setBuilder(r1);
    }

    public NotificationCompat$InboxStyle addLine(CharSequence  r1)
    {


        mTexts.add(r1);
        return this;
    }

    public NotificationCompat$InboxStyle setBigContentTitle(CharSequence  r1)
    {


        mBigContentTitle = r1;
        return this;
    }

    public NotificationCompat$InboxStyle setSummaryText(CharSequence  r1)
    {


        mSummaryText = r1;
        mSummaryTextSet = true;
        return this;
    }
}
