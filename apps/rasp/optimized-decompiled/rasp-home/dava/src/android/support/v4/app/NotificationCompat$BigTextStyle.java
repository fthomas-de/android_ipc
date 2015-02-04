package android.support.v4.app;


public class NotificationCompat$BigTextStyle extends NotificationCompat$Style
{
    CharSequence mBigText;

    public NotificationCompat$BigTextStyle()
    {


        this.<init>();
    }

    public NotificationCompat$BigTextStyle(NotificationCompat$Builder  r1)
    {


        this.<init>();
        this.setBuilder(r1);
    }

    public NotificationCompat$BigTextStyle bigText(CharSequence  r1)
    {


        mBigText = r1;
        return this;
    }

    public NotificationCompat$BigTextStyle setBigContentTitle(CharSequence  r1)
    {


        mBigContentTitle = r1;
        return this;
    }

    public NotificationCompat$BigTextStyle setSummaryText(CharSequence  r1)
    {


        mSummaryText = r1;
        mSummaryTextSet = true;
        return this;
    }
}
