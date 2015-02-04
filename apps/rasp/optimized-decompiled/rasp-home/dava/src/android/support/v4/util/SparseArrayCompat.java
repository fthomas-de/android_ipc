package android.support.v4.util;


public class SparseArrayCompat implements java.lang.Cloneable
{
    private static final Object DELETED;
    private boolean mGarbage;
    private int[] mKeys;
    private int mSize;
    private Object[] mValues;

    static
    {


        DELETED = new Object();
    }

    public SparseArrayCompat()
    {
        this(10);


        this.<init>(10);
    }

    public SparseArrayCompat(int  i0)
    {

        int i1;
        this.<init>();
        mGarbage = false;

        if (i0 != 0)
        {
            i1 = ContainerHelpers.idealIntArraySize(i0);
            mKeys = new int[i1];
            mValues = new Object[i1];
        }
        else
        {
            mKeys = ContainerHelpers.EMPTY_INTS;
            mValues = ContainerHelpers.EMPTY_OBJECTS;
        }

        mSize = (int) 0;
    }

    public void append(int  i0, Object  r1)
    {

        int i4, i5;
        int[] r2;
        Object[] r3;
        label_0:
        {
            if (mSize != 0)
            {
                if (i0 <= mKeys[mSize + -1])
                {
                    this.put(i0, r1);
                    break label_0;
                }
            }

            if (mGarbage != false)
            {
                if (mSize >= mKeys.length)
                {
                    this.gc();
                }
            }

            i4 = mSize;

            if (i4 >= mKeys.length)
            {
                i5 = ContainerHelpers.idealIntArraySize(i4 + 1);
                r2 = new int[i5];
                r3 = new Object[i5];
                System.arraycopy(mKeys, 0, r2, 0, mKeys.length);
                System.arraycopy(mValues, 0, r3, 0, mValues.length);
                mKeys = r2;
                mValues = r3;
            }

            mKeys[i4] = i0;
            mValues[i4] = r1;
            mSize = i4 + 1;
        } //end label_0:

    }

    public void clear()
    {

        int i0, i1;
        Object[] r1;
        i0 = mSize;
        r1 = mValues;
        i1 = 0;

        while (i1 < i0)
        {
            r1[i1] = null;
            i1 = i1 + 1;
        }

        mSize = (int) 0;
        mGarbage = false;
    }

    public SparseArrayCompat clone()
    {

        SparseArrayCompat r1, r5;
        Object $r4, r7, r10;
        int[] r6, r8;
        Object[] r9, r11;
        r1 = null;

        label_3:
        {
            label_2:
            {
                label_1:
                {
                    try
                    {
                        $r4 = this.clone();
                    }
                    catch (CloneNotSupportedException $r12)
                    {
                        break label_1;
                    }

                    r5 = (SparseArrayCompat) $r4;

                    r1 = r5;

                    r6 = mKeys;

                    r7 = r6.clone();

                    r8 = (int[]) r7;

                    r5.mKeys = r8;

                    r9 = mValues;

                    r10 = r9.clone();

                    r11 = (Object[]) r10;
                    break label_2;
                } //end label_1:


                break label_3;
            } //end label_2:


            r5.mValues = r11;
        } //end label_3:


        return r1;
    }

    public Object clone() throws java.lang.CloneNotSupportedException
    {


        return this.clone();
    }

    public void delete(int  i0)
    {

        int i2;
        i2 = ContainerHelpers.binarySearch(mKeys, mSize, i0);

        if (i2 >= 0)
        {
            if (mValues[i2] != DELETED)
            {
                mValues[i2] = DELETED;
                mGarbage = true;
            }
        }
    }

    private void gc()
    {

        int i0, i1, i2;
        int[] r1;
        Object[] r2;
        Object r3;
        i0 = mSize;
        i1 = 0;
        r1 = mKeys;
        r2 = mValues;
        i2 = 0;

        while (i2 < i0)
        {
            r3 = r2[i2];

            if (r3 != DELETED)
            {
                if (i2 != i1)
                {
                    r1[i1] = r1[i2];
                    r2[i1] = r3;
                    r2[i2] = null;
                }

                i1 = i1 + 1;
            }

            i2 = i2 + 1;
        }

        mGarbage = false;
        mSize = i1;
    }

    public Object get(int  i0)
    {


        return this.get(i0, null);
    }

    public Object get(int  i0, Object  r1)
    {

        int i2;
        i2 = ContainerHelpers.binarySearch(mKeys, mSize, i0);

        if (i2 >= 0)
        {
            if (mValues[i2] != DELETED)
            {
                r1 = mValues[i2];
            }
        }

        return r1;
    }

    public int indexOfKey(int  i0)
    {


        if (mGarbage != false)
        {
            this.gc();
        }

        return ContainerHelpers.binarySearch(mKeys, mSize, i0);
    }

    public int indexOfValue(Object  r1)
    {

        int i0;
        if (mGarbage != false)
        {
            this.gc();
        }

        i0 = 0;

        label_4:
        {
            while (i0 < mSize)
            {
                if (mValues[i0] != r1)
                {
                    i0 = i0 + 1;
                }
                else
                {
                    break label_4;
                }
            }

            i0 = -1;
        } //end label_4:


        return i0;
    }

    public int keyAt(int  i0)
    {


        if (mGarbage != false)
        {
            this.gc();
        }

        return mKeys[i0];
    }

    public void put(int  i0, Object  r1)
    {

        int i3, i4, i6;
        int[] r3;
        Object[] r4;
        i3 = ContainerHelpers.binarySearch(mKeys, mSize, i0);

        label_5:
        if (i3 < 0)
        {
            i6 = i3 ^ -1;

            if (i6 < mSize)
            {
                if (mValues[i6] == DELETED)
                {
                    mKeys[i6] = i0;
                    mValues[i6] = r1;
                    break label_5;
                }
            }

            if (mGarbage != false)
            {
                if (mSize >= mKeys.length)
                {
                    this.gc();
                    i6 = ContainerHelpers.binarySearch(mKeys, mSize, i0) ^ -1;
                }
            }

            if (mSize >= mKeys.length)
            {
                i4 = ContainerHelpers.idealIntArraySize(mSize + 1);
                r3 = new int[i4];
                r4 = new Object[i4];
                System.arraycopy(mKeys, 0, r3, 0, mKeys.length);
                System.arraycopy(mValues, 0, r4, 0, mValues.length);
                mKeys = r3;
                mValues = r4;
            }

            if (mSize - i6 != 0)
            {
                System.arraycopy(mKeys, i6, mKeys, i6 + 1, mSize - i6);
                System.arraycopy(mValues, i6, mValues, i6 + 1, mSize - i6);
            }

            mKeys[i6] = i0;
            mValues[i6] = r1;
            mSize = mSize + 1;
        }
        else
        {
            mValues[i3] = r1;
        }
    }

    public void remove(int  i0)
    {


        this.delete(i0);
    }

    public void removeAt(int  i0)
    {


        if (mValues[i0] != DELETED)
        {
            mValues[i0] = DELETED;
            mGarbage = true;
        }
    }

    public void removeAtRange(int  i0, int  i1)
    {

        int i4, i5;
        i4 = Math.min(mSize, i0 + i1);
        i5 = i0;

        while (i5 < i4)
        {
            this.removeAt(i5);
            i5 = i5 + 1;
        }
    }

    public void setValueAt(int  i0, Object  r1)
    {


        if (mGarbage != false)
        {
            this.gc();
        }

        mValues[i0] = r1;
    }

    public int size()
    {


        if (mGarbage != false)
        {
            this.gc();
        }

        return mSize;
    }

    public String toString()
    {

        StringBuilder r1;
        int i1;
        Object r2;
        String r3;
        if (this.size() > 0)
        {
            r1 = new StringBuilder(mSize * 28);
            r1.append('{');
            i1 = 0;

            while (i1 < mSize)
            {
                if (i1 > 0)
                {
                    r1.append(", ");
                }

                r1.append(this.keyAt(i1));
                r1.append('=');
                r2 = this.valueAt(i1);

                if (r2 == this)
                {
                    r1.append("(this Map)");
                }
                else
                {
                    r1.append(r2);
                }

                i1 = i1 + 1;
            }

            r1.append('}');
            r3 = r1.toString();
        }
        else
        {
            r3 = "{}";
        }

        return r3;
    }

    public Object valueAt(int  i0)
    {


        if (mGarbage != false)
        {
            this.gc();
        }

        return mValues[i0];
    }
}