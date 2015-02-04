package android.support.v4.util;

import java.util.Set;
import java.util.Map$Entry;
import java.util.Collection;
import java.util.Iterator;

final class MapCollections$EntrySet implements java.util.Set
{
    final MapCollections this$0;

    MapCollections$EntrySet(MapCollections  r1)
    {


        this$0 = r1;
        this.<init>();
    }

    public boolean add(Object  r1)
    {


        return this.add((Map$Entry) r1);
    }

    public boolean add(Map$Entry  r1)
    {


        throw new UnsupportedOperationException();
    }

    public boolean addAll(Collection  r1)
    {

        int i0;
        Iterator r3;
        Map$Entry r7;
        boolean z1;
        i0 = this$0.colGetSize();
        r3 = r1.iterator();

        while (r3.hasNext() != false)
        {
            r7 = (Map$Entry) r3.next();
            this$0.colPut(r7.getKey(), r7.getValue());
        }

        if (i0 == this$0.colGetSize())
        {
            z1 = false;
        }
        else
        {
            z1 = true;
        }

        return z1;
    }

    public void clear()
    {


        this$0.colClear();
    }

    public boolean contains(Object  r1)
    {

        boolean z0;
        int i0;
        Map$Entry r5;
        z0 = false;

        if (r1 instanceof Map$Entry != false)
        {
            r5 = (Map$Entry) r1;
            i0 = this$0.colIndexOfKey(r5.getKey());

            if (i0 >= 0)
            {
                z0 = ContainerHelpers.equal(this$0.colGetEntry(i0, 1), r5.getValue());
            }
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

    public boolean equals(Object  r1)
    {


        return MapCollections.equalsSetHelper(this, r1);
    }

    public int hashCode()
    {

        int i1, i2, i5, i6;
        Object r2, r3;
        i1 = 0;
        i2 = this$0.colGetSize() + -1;

        while (i2 >= 0)
        {
            r2 = this$0.colGetEntry(i2, 0);
            r3 = this$0.colGetEntry(i2, 1);

            if (r2 != null)
            {
                i5 = r2.hashCode();
            }
            else
            {
                i5 = 0;
            }

            if (r3 != null)
            {
                i6 = r3.hashCode();
            }
            else
            {
                i6 = 0;
            }

            i1 = i1 + (i6 ^ i5);
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


        return new MapCollections$MapIterator(this$0);
    }

    public boolean remove(Object  r1)
    {


        throw new UnsupportedOperationException();
    }

    public boolean removeAll(Collection  r1)
    {


        throw new UnsupportedOperationException();
    }

    public boolean retainAll(Collection  r1)
    {


        throw new UnsupportedOperationException();
    }

    public int size()
    {


        return this$0.colGetSize();
    }

    public Object[] toArray()
    {


        throw new UnsupportedOperationException();
    }

    public Object[] toArray(Object[]  r1)
    {


        throw new UnsupportedOperationException();
    }
}
