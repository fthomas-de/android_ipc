package android.support.v4.app;


class FragmentManagerImpl$3 implements java.lang.Runnable
{
    final FragmentManagerImpl this$0;
    final int val$flags;
    final String val$name;

    FragmentManagerImpl$3(FragmentManagerImpl  r1, String  r2, int  i0)
    {


        this$0 = r1;
        val$name = r2;
        val$flags = i0;
        this.<init>();
    }

    public void run()
    {


        this$0.popBackStackState(this$0.mActivity.mHandler, val$name, -1, val$flags);
    }
}
