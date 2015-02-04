package android.support.v4.util;

import java.util.Iterator;
import java.util.Map$Entry;

final class MapCollections$MapIterator implements java.util.Iterator, java.util.Map$Entry
{
    int mEnd;
    boolean mEntryValid;
    int mIndex;
    final MapCollections this$0;

    MapCollections$MapIterator(MapCollections  r1)
    {


        this$0 = r1;
        this.<init>();
        mEntryValid = false;
        mEnd = r1.colGetSize() + -1;
        mIndex = -1;
    }

    public final boolean equals(Object  r1)
    {

        boolean z0, z1;
        Map$Entry r6;
        z0 = true;
        z1 = false;

        if (mEntryValid != false)
        {
            if (r1 instanceof Map$Entry != false)
            {
                r6 = (Map$Entry) r1;

                label_0:
                {
                    if (ContainerHelpers.equal(r6.getKey(), this$0.colGetEntry(mIndex, (int) 0)) != false)
                    {
                        if (ContainerHelpers.equal(r6.getValue(), this$0.colGetEntry(mIndex, (int) 1)) != false)
                        {
                            break label_0;
                        }
                    }

                    z0 = false;
                } //end label_0:


                z1 = z0;
            }

            return z1;
        }
        else
        {
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }
    }

    public Object getKey()
    {


        if (mEntryValid != false)
        {
            return this$0.colGetEntry(mIndex, 0);
        }
        else
        {
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }
    }

    public Object getValue()
    {


        if (mEntryValid != false)
        {
            return this$0.colGetEntry(mIndex, 1);
        }
        else
        {
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }
    }

    public boolean hasNext()
    {

        boolean z0;
        if (mIndex >= mEnd)
        {
            z0 = false;
        }
        else
        {
            z0 = true;
        }

        return z0;
    }

    public final int hashCode()
    {

        int i0, i4;
        Object r1, r2;
        i0 = 0;

        if (mEntryValid != false)
        {
            r1 = this$0.colGetEntry(mIndex, 0);
            r2 = this$0.colGetEntry(mIndex, 1);

            if (r1 != null)
            {
                i4 = r1.hashCode();
            }
            else
            {
                i4 = 0;
            }

            if (r2 != null)
            {
                i0 = r2.hashCode();
            }

            return i0 ^ i4;
        }
        else
        {
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }
    }

    public Object next()
    {


        return this.next();
    }

    public Map$Entry next()
    {


        mIndex = mIndex + 1;
        mEntryValid = true;
        return this;
    }

    public void remove()
    {


        if (mEntryValid != false)
        {
            this$0.colRemoveAt(mIndex);
            mIndex = mIndex + -1;
            mEnd = mEnd + -1;
            mEntryValid = false;
            return;
        }
        else
        {
            throw new IllegalStateException();
        }
    }

    public Object setValue(Object  r1)
    {


        if (mEntryValid != false)
        {
            return this$0.colSetValue(mIndex, r1);
        }
        else
        {
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }
    }

    public final String toString()
    {


        return (new StringBuilder()).append(this.getKey()).append("=").append(this.getValue()).toString();
    }
}
