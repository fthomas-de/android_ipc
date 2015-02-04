package android.support.v4.content;

import android.content.Context;
import android.support.v4.util.DebugUtils;
import java.io.FileDescriptor;
import java.io.PrintWriter;

public class Loader
{
    boolean mAbandoned;
    boolean mContentChanged;
    Context mContext;
    int mId;
    Loader$OnLoadCompleteListener mListener;
    boolean mProcessingChange;
    boolean mReset;
    boolean mStarted;

    public Loader(Context  r1)
    {


        this.<init>();
        mStarted = false;
        mAbandoned = false;
        mReset = true;
        mContentChanged = false;
        mProcessingChange = false;
        mContext = r1.getApplicationContext();
    }

    public void abandon()
    {


        mAbandoned = true;
        this.onAbandon();
    }

    public void commitContentChanged()
    {


        mProcessingChange = false;
    }

    public String dataToString(Object  r1)
    {

        StringBuilder r2;
        r2 = new StringBuilder(64);
        DebugUtils.buildShortClassTag(r1, r2);
        r2.append("}");
        return r2.toString();
    }

    public void deliverResult(Object  r1)
    {


        if (mListener != null)
        {
            mListener.onLoadComplete(this, r1);
        }
    }

    public void dump(String  r1, FileDescriptor  r2, PrintWriter  r3, String[]  r4)
    {


        r3.print(r1);
        r3.print("mId=");
        r3.print(mId);
        r3.print(" mListener=");
        r3.println(mListener);

        label_0:
        {
            if (mStarted == false)
            {
                if (mContentChanged == false)
                {
                    if (mProcessingChange == false)
                    {
                        break label_0;
                    }
                }
            }

            r3.print(r1);
            r3.print("mStarted=");
            r3.print(mStarted);
            r3.print(" mContentChanged=");
            r3.print(mContentChanged);
            r3.print(" mProcessingChange=");
            r3.println(mProcessingChange);
        } //end label_0:


        label_1:
        {
            if (mAbandoned == false)
            {
                if (mReset == false)
                {
                    break label_1;
                }
            }

            r3.print(r1);
            r3.print("mAbandoned=");
            r3.print(mAbandoned);
            r3.print(" mReset=");
            r3.println(mReset);
        } //end label_1:

    }

    public void forceLoad()
    {


        this.onForceLoad();
    }

    public Context getContext()
    {


        return mContext;
    }

    public int getId()
    {


        return mId;
    }

    public boolean isAbandoned()
    {


        return mAbandoned;
    }

    public boolean isReset()
    {


        return mReset;
    }

    public boolean isStarted()
    {


        return mStarted;
    }

    protected void onAbandon()
    {

    }

    public void onContentChanged()
    {


        if (mStarted == false)
        {
            mContentChanged = true;
        }
        else
        {
            this.forceLoad();
        }
    }

    protected void onForceLoad()
    {

    }

    protected void onReset()
    {

    }

    protected void onStartLoading()
    {

    }

    protected void onStopLoading()
    {

    }

    public void registerListener(int  i0, Loader$OnLoadCompleteListener  r1)
    {


        if (mListener == null)
        {
            mListener = r1;
            mId = i0;
            return;
        }
        else
        {
            throw new IllegalStateException("There is already a listener registered");
        }
    }

    public void reset()
    {


        this.onReset();
        mReset = true;
        mStarted = false;
        mAbandoned = false;
        mContentChanged = false;
        mProcessingChange = false;
    }

    public void rollbackContentChanged()
    {


        if (mProcessingChange != false)
        {
            mContentChanged = true;
        }
    }

    public final void startLoading()
    {


        mStarted = true;
        mReset = false;
        mAbandoned = false;
        this.onStartLoading();
    }

    public void stopLoading()
    {


        mStarted = false;
        this.onStopLoading();
    }

    public boolean takeContentChanged()
    {

        boolean z0;
        z0 = mContentChanged;
        mContentChanged = false;
        mProcessingChange = mProcessingChange | z0;
        return z0;
    }

    public String toString()
    {

        StringBuilder r1;
        r1 = new StringBuilder(64);
        DebugUtils.buildShortClassTag(this, r1);
        r1.append(" id=");
        r1.append(mId);
        r1.append("}");
        return r1.toString();
    }

    public void unregisterListener(Loader$OnLoadCompleteListener  r1)
    {


        if (mListener != null)
        {
            if (mListener == r1)
            {
                mListener = null;
                return;
            }
            else
            {
                throw new IllegalArgumentException("Attempting to unregister the wrong listener");
            }
        }
        else
        {
            throw new IllegalStateException("No listener register");
        }
    }
}
