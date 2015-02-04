package android.support.v4.util;

import java.io.Writer;
import android.util.Log;

public class LogWriter extends Writer
{
    private StringBuilder mBuilder;
    private final String mTag;

    public LogWriter(String  r1)
    {


        this.<init>();
        mBuilder = new StringBuilder(128);
        mTag = r1;
    }

    public void close()
    {


        this.flushBuilder();
    }

    public void flush()
    {


        this.flushBuilder();
    }

    private void flushBuilder()
    {


        if (mBuilder.length() > 0)
        {
            Log.d(mTag, mBuilder.toString());
            mBuilder.delete(0, mBuilder.length());
        }
    }

    public void write(char[]  r1, int  i0, int  i1)
    {

        int i2;
        char c4;
        i2 = 0;

        while (i2 < i1)
        {
            c4 = r1[i0 + i2];

            if (c4 != '\n')
            {
                mBuilder.append(c4);
            }
            else
            {
                this.flushBuilder();
            }

            i2 = i2 + 1;
        }
    }
}
