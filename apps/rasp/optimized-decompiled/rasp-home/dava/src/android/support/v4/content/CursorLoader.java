package android.support.v4.content;

import android.content.Context;
import android.net.Uri;
import android.database.Cursor;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Arrays;
import android.content.ContentResolver;

public class CursorLoader extends AsyncTaskLoader
{
    Cursor mCursor;
    final Loader$ForceLoadContentObserver mObserver;
    String[] mProjection;
    String mSelection;
    String[] mSelectionArgs;
    String mSortOrder;
    Uri mUri;

    public CursorLoader(Context  r1)
    {
        super(r1);


        this.<init>(r1);
        mObserver = new Loader$ForceLoadContentObserver(this);
    }

    public CursorLoader(Context  r1, Uri  r2, String[]  r3, String  r4, String[]  r5, String  r6)
    {
        super(r1);


        this.<init>(r1);
        mObserver = new Loader$ForceLoadContentObserver(this);
        mUri = r2;
        mProjection = r3;
        mSelection = r4;
        mSelectionArgs = r5;
        mSortOrder = r6;
    }

    public void deliverResult(Cursor  r1)
    {

        Cursor r2;
        if (this.isReset() == false)
        {
            r2 = mCursor;
            mCursor = r1;

            if (this.isStarted() != false)
            {
                this.deliverResult(r1);
            }

            if (r2 != null)
            {
                if (r2 != r1)
                {
                    if (r2.isClosed() == false)
                    {
                        r2.close();
                    }
                }
            }
        }
        else
        {
            if (r1 != null)
            {
                r1.close();
            }
        }
    }

    public void deliverResult(Object  r1)
    {


        this.deliverResult((Cursor) r1);
    }

    public void dump(String  r1, FileDescriptor  r2, PrintWriter  r3, String[]  r4)
    {


        this.dump(r1, r2, r3, r4);
        r3.print(r1);
        r3.print("mUri=");
        r3.println(mUri);
        r3.print(r1);
        r3.print("mProjection=");
        r3.println(Arrays.toString(mProjection));
        r3.print(r1);
        r3.print("mSelection=");
        r3.println(mSelection);
        r3.print(r1);
        r3.print("mSelectionArgs=");
        r3.println(Arrays.toString(mSelectionArgs));
        r3.print(r1);
        r3.print("mSortOrder=");
        r3.println(mSortOrder);
        r3.print(r1);
        r3.print("mCursor=");
        r3.println(mCursor);
        r3.print(r1);
        r3.print("mContentChanged=");
        r3.println(mContentChanged);
    }

    public String[] getProjection()
    {


        return mProjection;
    }

    public String getSelection()
    {


        return mSelection;
    }

    public String[] getSelectionArgs()
    {


        return mSelectionArgs;
    }

    public String getSortOrder()
    {


        return mSortOrder;
    }

    public Uri getUri()
    {


        return mUri;
    }

    public Cursor loadInBackground()
    {

        Cursor r7;
        r7 = this.getContext().getContentResolver().query(mUri, mProjection, mSelection, mSelectionArgs, mSortOrder);

        if (r7 != null)
        {
            r7.getCount();
            r7.registerContentObserver(mObserver);
        }

        return r7;
    }

    public Object loadInBackground()
    {


        return this.loadInBackground();
    }

    public void onCanceled(Cursor  r1)
    {


        if (r1 != null)
        {
            if (r1.isClosed() == false)
            {
                r1.close();
            }
        }
    }

    public void onCanceled(Object  r1)
    {


        this.onCanceled((Cursor) r1);
    }

    protected void onReset()
    {


        this.onReset();
        this.onStopLoading();

        if (mCursor != null)
        {
            if (mCursor.isClosed() == false)
            {
                mCursor.close();
            }
        }

        mCursor = null;
    }

    protected void onStartLoading()
    {


        if (mCursor != null)
        {
            this.deliverResult(mCursor);
        }

        label_0:
        {
            if (this.takeContentChanged() == false)
            {
                if (mCursor != null)
                {
                    break label_0;
                }
            }

            this.forceLoad();
        } //end label_0:

    }

    protected void onStopLoading()
    {


        this.cancelLoad();
    }

    public void setProjection(String[]  r1)
    {


        mProjection = r1;
    }

    public void setSelection(String  r1)
    {


        mSelection = r1;
    }

    public void setSelectionArgs(String[]  r1)
    {


        mSelectionArgs = r1;
    }

    public void setSortOrder(String  r1)
    {


        mSortOrder = r1;
    }

    public void setUri(Uri  r1)
    {


        mUri = r1;
    }
}
