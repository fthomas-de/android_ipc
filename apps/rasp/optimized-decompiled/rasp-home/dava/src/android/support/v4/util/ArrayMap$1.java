package android.support.v4.util;

import java.util.Map;

class ArrayMap$1 extends MapCollections
{
    final ArrayMap this$0;

    ArrayMap$1(ArrayMap  r1)
    {


        this$0 = r1;
        this.<init>();
    }

    protected void colClear()
    {


        this$0.clear();
    }

    protected Object colGetEntry(int  i0, int  i1)
    {


        return this$0.mArray[(i0 << 1) + i1];
    }

    protected Map colGetMap()
    {


        return this$0;
    }

    protected int colGetSize()
    {


        return this$0.mSize;
    }

    protected int colIndexOfKey(Object  r1)
    {

        int i1;
        if (r1 != null)
        {
            i1 = this$0.indexOf(r1, r1.hashCode());
        }
        else
        {
            i1 = this$0.indexOfNull();
        }

        return i1;
    }

    protected int colIndexOfValue(Object  r1)
    {


        return this$0.indexOfValue(r1);
    }

    protected void colPut(Object  r1, Object  r2)
    {


        this$0.put(r1, r2);
    }

    protected void colRemoveAt(int  i0)
    {


        this$0.removeAt(i0);
    }

    protected Object colSetValue(int  i0, Object  r1)
    {


        return this$0.setValueAt(i0, r1);
    }
}
