package android.support.v4.util;

import java.util.Iterator;

final class MapCollections$ArrayIterator implements java.util.Iterator
{
    boolean mCanRemove;
    int mIndex;
    final int mOffset;
    int mSize;
    final MapCollections this$0;

    MapCollections$ArrayIterator(MapCollections  r1, int  i0)
    {


        this$0 = r1;
        this.<init>();
        mCanRemove = false;
        mOffset = i0;
        mSize = r1.colGetSize();
    }

    public boolean hasNext()
    {

        boolean z0;
        if (mIndex >= mSize)
        {
            z0 = false;
        }
        else
        {
            z0 = true;
        }

        return z0;
    }

    public Object next()
    {

        Object r2;
        r2 = this$0.colGetEntry(mIndex, mOffset);
        mIndex = mIndex + 1;
        mCanRemove = true;
        return r2;
    }

    public void remove()
    {


        if (mCanRemove != false)
        {
            mIndex = mIndex + -1;
            mSize = mSize + -1;
            mCanRemove = false;
            this$0.colRemoveAt(mIndex);
            return;
        }
        else
        {
            throw new IllegalStateException();
        }
    }
}
