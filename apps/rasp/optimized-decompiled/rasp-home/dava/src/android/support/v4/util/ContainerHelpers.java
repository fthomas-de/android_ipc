package android.support.v4.util;


class ContainerHelpers
{
    static final int[] EMPTY_INTS;
    static final long[] EMPTY_LONGS;
    static final Object[] EMPTY_OBJECTS;

    static
    {


        EMPTY_INTS = new int[0];
        EMPTY_LONGS = new long[0];
        EMPTY_OBJECTS = new Object[0];
    }

    ContainerHelpers()
    {


        this.<init>();
    }

    static int binarySearch(int[]  r0, int  i0, int  i1)
    {

        int i2, i3, i5, i6;
        i2 = 0;
        i3 = i0 + -1;

        label_0:
        {
            while (i2 <= i3)
            {
                i5 = i2 + i3 >>> 1;
                i6 = r0[i5];

                if (i6 >= i1)
                {
                    if (i6 <= i1)
                    {
                        break label_0;
                    }
                    else
                    {
                        i3 = i5 + -1;
                    }
                }
                else
                {
                    i2 = i5 + 1;
                }
            }

            i5 = i2 ^ -1;
        } //end label_0:


        return i5;
    }

    static int binarySearch(long[]  r0, int  i0, long  l1)
    {

        int i2, i3, i5;
        long l6;
        i2 = 0;
        i3 = i0 + -1;

        label_1:
        {
            while (i2 <= i3)
            {
                i5 = i2 + i3 >>> 1;
                l6 = r0[i5];

                if (l6 - l1 >= 0)
                {
                    if (l6 - l1 <= 0)
                    {
                        break label_1;
                    }
                    else
                    {
                        i3 = i5 + -1;
                    }
                }
                else
                {
                    i2 = i5 + 1;
                }
            }

            i5 = i2 ^ -1;
        } //end label_1:


        return i5;
    }

    public static boolean equal(Object  r0, Object  r1)
    {

        boolean z1;
        label_3:
        {
            label_2:
            if (r0 != r1)
            {
                if (r0 != null)
                {
                    if (r0.equals(r1) != false)
                    {
                        break label_2;
                    }
                }

                z1 = false;
                break label_3;
            }

            z1 = true;
        } //end label_3:


        return z1;
    }

    public static int idealByteArraySize(int  i0)
    {

        int i2;
        i2 = 4;

        label_4:
        {
            while (i2 < 32)
            {
                if (i0 > (1 << i2) + -12)
                {
                    i2 = i2 + 1;
                }
                else
                {
                    i0 = (1 << i2) + -12;
                    break label_4;
                }
            }
        } //end label_4:


        return i0;
    }

    public static int idealIntArraySize(int  i0)
    {


        return ContainerHelpers.idealByteArraySize(i0 * 4) / 4;
    }

    public static int idealLongArraySize(int  i0)
    {


        return ContainerHelpers.idealByteArraySize(i0 * 8) / 8;
    }
}
