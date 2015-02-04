package android.support.v4.util;

import java.util.Collection;
import java.util.Iterator;

final class MapCollections$ValuesCollection implements java.util.Collection
{
    final MapCollections this$0;

    MapCollections$ValuesCollection(MapCollections  r1)
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
        if (this$0.colIndexOfValue(r1) < 0)
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

        Iterator r2;
        boolean z2;
        r2 = r1.iterator();

        label_0:
        {
            while (r2.hasNext() != false)
            {
                if (this.contains(r2.next()) == false)
                {
                    z2 = false;
                    break label_0;
                }
            }

            z2 = true;
        } //end label_0:


        return z2;
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


        return new MapCollections$ArrayIterator(this$0, 1);
    }

    public boolean remove(Object  r1)
    {

        int i0;
        boolean z0;
        i0 = this$0.colIndexOfValue(r1);

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

        int i0, i1;
        boolean z0;
        i0 = this$0.colGetSize();
        z0 = false;
        i1 = 0;

        while (i1 < i0)
        {
            if (r1.contains(this$0.colGetEntry(i1, 1)) != false)
            {
                this$0.colRemoveAt(i1);
                i1 = i1 + -1;
                i0 = i0 + -1;
                z0 = true;
            }

            i1 = i1 + 1;
        }

        return z0;
    }

    public boolean retainAll(Collection  r1)
    {

        int i0, i1;
        boolean z0;
        i0 = this$0.colGetSize();
        z0 = false;
        i1 = 0;

        while (i1 < i0)
        {
            if (r1.contains(this$0.colGetEntry(i1, 1)) == false)
            {
                this$0.colRemoveAt(i1);
                i1 = i1 + -1;
                i0 = i0 + -1;
                z0 = true;
            }

            i1 = i1 + 1;
        }

        return z0;
    }

    public int size()
    {


        return this$0.colGetSize();
    }

    public Object[] toArray()
    {


        return this$0.toArrayHelper(1);
    }

    public Object[] toArray(Object[]  r1)
    {


        return this$0.toArrayHelper(r1, 1);
    }
}
