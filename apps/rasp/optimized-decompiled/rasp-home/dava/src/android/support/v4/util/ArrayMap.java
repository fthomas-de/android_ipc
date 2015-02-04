package android.support.v4.util;

import java.util.Map;
import java.util.Collection;
import java.util.Set;
import java.util.Iterator;
import java.util.Map$Entry;

public class ArrayMap extends SimpleArrayMap implements java.util.Map
{
    MapCollections mCollections;

    public ArrayMap()
    {


        this.<init>();
    }

    public ArrayMap(int  i0)
    {
        super(i0);


        this.<init>(i0);
    }

    public ArrayMap(SimpleArrayMap  r1)
    {
        super(r1);


        this.<init>(r1);
    }

    public boolean containsAll(Collection  r1)
    {


        return MapCollections.containsAllHelper(this, r1);
    }

    public Set entrySet()
    {


        return this.getCollection().getEntrySet();
    }

    private MapCollections getCollection()
    {


        if (mCollections == null)
        {
            mCollections = new ArrayMap$1(this);
        }

        return mCollections;
    }

    public Set keySet()
    {


        return this.getCollection().getKeySet();
    }

    public void putAll(Map  r1)
    {

        Iterator r2;
        Map$Entry r5;
        this.ensureCapacity(mSize + r1.size());
        r2 = r1.entrySet().iterator();

        while (r2.hasNext() != false)
        {
            r5 = (Map$Entry) r2.next();
            this.put(r5.getKey(), r5.getValue());
        }
    }

    public boolean removeAll(Collection  r1)
    {


        return MapCollections.removeAllHelper(this, r1);
    }

    public boolean retainAll(Collection  r1)
    {


        return MapCollections.retainAllHelper(this, r1);
    }

    public Collection values()
    {


        return this.getCollection().getValues();
    }
}
