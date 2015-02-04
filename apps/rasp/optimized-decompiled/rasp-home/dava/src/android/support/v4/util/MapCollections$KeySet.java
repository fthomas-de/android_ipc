package android.support.v4.util;

import java.util.Set;
import java.util.Collection;
import java.util.Iterator;

final class MapCollections$KeySet implements java.util.Set
{
    final MapCollections this$0;

    MapCollections$KeySet(MapCollections  r1)
    {


        this$0 = r1;
        this.<init>();
    }

    public boolean add(Object  r1)
    {


        throw new UnsupportedOperationException();
    }

    public boolean addAll(Collection  r1)
    {


        throw new UnsupportedOperationException();
    }

    public void clear()
    {


        this$0.colClear();
    }

    public boolean contains(Object  r1)
    {

        boolean z0;
        if (this$0.colIndexOfKey(r1) < 0)
        {
            z0 = false;
        }
        else
        {
            z0 = true;
        }

        return z0;
    }

    public boolean containsAll(Collection  r1)
    {


        return MapCollections.containsAllHelper(this$0.colGetMap(), r1);
    }

    public boolean equals(Object  r1)
    {


        return MapCollections.equalsSetHelper(this, r1);
    }

    public int hashCode()
    {

        int i1, i2, i4;
        Object r2;
        i1 = 0;
        i2 = this$0.colGetSize() + -1;

        while (i2 >= 0)
        {
            r2 = this$0.colGetEntry(i2, 0);

            if (r2 != null)
            {
                i4 = r2.hashCode();
            }
            else
            {
                i4 = 0;
            }

            i1 = i1 + i4;
            i2 = i2 + -1;
        }

        return i1;
    }

    public boolean isEmpty()
    {

        boolean z0;
        if (this$0.colGetSize() != 0)
        {
            z0 = false;
        }
        else
        {
            z0 = true;
        }

        return z0;
    }

    public Iterator iterator()
    {


        return new MapCollections$ArrayIterator(this$0, 0);
    }

    public boolean remove(Object  r1)
    {

        int i0;
        boolean z0;
        i0 = this$0.colIndexOfKey(r1);

        if (i0 < 0)
        {
            z0 = false;
        }
        else
        {
            this$0.colRemoveAt(i0);
            z0 = true;
        }

        return z0;
    }

    public boolean removeAll(Collection  r1)
    {


        return MapCollections.removeAllHelper(this$0.colGetMap(), r1);
    }

    public boolean retainAll(Collection  r1)
    {


        return MapCollections.retainAllHelper(this$0.colGetMap(), r1);
    }

    public int size()
    {


        return this$0.colGetSize();
    }

    public Object[] toArray()
    {


        return this$0.toArrayHelper(0);
    }

    public Object[] toArray(Object[]  r1)
    {


        return this$0.toArrayHelper(r1, 0);
    }
}
