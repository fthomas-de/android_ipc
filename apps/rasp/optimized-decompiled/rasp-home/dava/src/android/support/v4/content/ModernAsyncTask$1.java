package android.support.v4.content;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

final class ModernAsyncTask$1 implements java.util.concurrent.ThreadFactory
{
    private final AtomicInteger mCount;

    ModernAsyncTask$1()
    {


        this.<init>();
        mCount = new AtomicInteger(1);
    }

    public Thread newThread(Runnable  r1)
    {


        return new Thread(r1, (new StringBuilder()).append("ModernAsyncTask #").append(mCount.getAndIncrement()).toString());
    }
}
