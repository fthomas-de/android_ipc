package android.support.v4.util;

import java.util.Map;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.lang.reflect.Array;

abstract class MapCollections
{
    MapCollections$EntrySet mEntrySet;
    MapCollections$KeySet mKeySet;
    MapCollections$ValuesCollection mValues;

    MapCollections()
    {


        this.<init>();
    }

    protected abstract void colClear();

    protected abstract java.lang.Object colGetEntry(int  i0, int  i1);

    protected abstract java.util.Map colGetMap();

    protected abstract int colGetSize();

    protected abstract int colIndexOfKey(java.lang.Object  r0);

    protected abstract int colIndexOfValue(java.lang.Object  r0);

    protected abstract void colPut(java.lang.Object  r0, java.lang.Object  r1);

    protected abstract void colRemoveAt(int  i0);

    protected abstract java.lang.Object colSetValue(int  i0, java.lang.Object  r1);

    public static boolean containsAllHelper(Map  r0, Collection  r1)
    {

        Iterator r2;
        boolean z2;
        r2 = r1.iterator();

        label_0:
        {
            while (r2.hasNext() != false)
            {
                if (r0.containsKey(r2.next()) == false)
                {
                    z2 = false;
                    break label_0;
                }
            }

            z2 = true;
        } //end label_0:


        return z2;
    }

    public static boolean equalsSetHelper(Set  r0, Object  r1)
    {

        boolean z0, z1, $z3;
        Set r4;
        int $i1, $i3;
        z0 = true;
        z1 = false;

        label_5:
        if (r0 != r1)
        {
            if (r1 instanceof Set != false)
            {
                r4 = (Set) r1;

                label_4:
                {
                    label_3:
                    {
                        label_2:
                        {
                            label_1:
                            {
                                try
                                {
                                    $i1 = r0.size();
                                }
                                catch (NullPointerException $r5)
                                {
                                    break label_2;
                                }
                                catch (ClassCastException $r6)
                                {
                                    break label_1;
                                }

                                try
                                {
                                    $i3 = r4.size();
                                }
                                catch (NullPointerException $r5)
                                {
                                    break label_2;
                                }
                                catch (ClassCastException $r6)
                                {
                                    break label_1;
                                }

                                if ($i1 != $i3)
                                {
                                    break label_3;
                                }
                                else
                                {
                                    try
                                    {
                                        $z3 = r0.containsAll(r4);
                                    }
                                    catch (NullPointerException $r5)
                                    {
                                        break label_2;
                                    }
                                    catch (ClassCastException $r6)
                                    {
                                        break label_1;
                                    }

                                    if ($z3 == false)
                                    {
                                        break label_3;
                                    }
                                    else
                                    {
                                        break label_4;
                                    }
                                }
                            } //end label_1:


                            break label_5;
                        } //end label_2:


                        break label_5;
                    } //end label_3:


                    z0 = false;
                } //end label_4:


                z1 = z0;
            }
        }
        else
        {
            z1 = true;
        }

        return z1;
    }

    public Set getEntrySet()
    {


        if (mEntrySet == null)
        {
            mEntrySet = new MapCollections$EntrySet(this);
        }

        return mEntrySet;
    }

    public Set getKeySet()
    {


        if (mKeySet == null)
        {
            mKeySet = new MapCollections$KeySet(this);
        }

        return mKeySet;
    }

    public Collection getValues()
    {


        if (mValues == null)
        {
            mValues = new MapCollections$ValuesCollection(this);
        }

        return mValues;
    }

    public static boolean removeAllHelper(Map  r0, Collection  r1)
    {

        int i0;
        Iterator r2;
        boolean z1;
        i0 = r0.size();
        r2 = r1.iterator();

        while (r2.hasNext() != false)
        {
            r0.remove(r2.next());
        }

        if (i0 == r0.size())
        {
            z1 = false;
        }
        else
        {
            z1 = true;
        }

        return z1;
    }

    public static boolean retainAllHelper(Map  r0, Collection  r1)
    {

        int i0;
        Iterator r3;
        boolean z2;
        i0 = r0.size();
        r3 = r0.keySet().iterator();

        while (r3.hasNext() != false)
        {
            if (r1.contains(r3.next()) == false)
            {
                r3.remove();
            }
        }

        if (i0 == r0.size())
        {
            z2 = false;
        }
        else
        {
            z2 = true;
        }

        return z2;
    }

    public Object[] toArrayHelper(int  i0)
    {

        int i1, i2;
        Object[] r1;
        i1 = this.colGetSize();
        r1 = new Object[i1];
        i2 = 0;

        while (i2 < i1)
        {
            r1[i2] = this.colGetEntry(i2, i0);
            i2 = i2 + 1;
        }

        return r1;
    }

    public Object[] toArrayHelper(Object[]  r1, int  i0)
    {

        int i1, i3;
        i1 = this.colGetSize();

        if (r1.length < i1)
        {
            r1 = (Object[]) (Object[]) Array.newInstance(r1.getClass().getComponentType(), i1);
        }

        i3 = 0;

        while (i3 < i1)
        {
            r1[i3] = this.colGetEntry(i3, i0);
            i3 = i3 + 1;
        }

        if (r1.length > i1)
        {
            r1[i1] = null;
        }

        return r1;
    }
}
