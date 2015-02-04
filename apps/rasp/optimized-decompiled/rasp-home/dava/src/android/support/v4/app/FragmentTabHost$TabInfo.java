package android.support.v4.app;

import android.os.Bundle;

final class FragmentTabHost$TabInfo
{
    private final Bundle args;
    private final Class clss;
    private Fragment fragment;
    private final String tag;

    FragmentTabHost$TabInfo(String  r1, Class  r2, Bundle  r3)
    {


        this.<init>();
        tag = r1;
        clss = r2;
        args = r3;
    }

    static Fragment access$100(FragmentTabHost$TabInfo  r0)
    {


        return r0.fragment;
    }

    static Fragment access$102(FragmentTabHost$TabInfo  r0, Fragment  r1)
    {


        r0.fragment = r1;
        return r1;
    }

    static String access$200(FragmentTabHost$TabInfo  r0)
    {


        return r0.tag;
    }

    static Class access$300(FragmentTabHost$TabInfo  r0)
    {


        return r0.clss;
    }

    static Bundle access$400(FragmentTabHost$TabInfo  r0)
    {


        return r0.args;
    }
}
