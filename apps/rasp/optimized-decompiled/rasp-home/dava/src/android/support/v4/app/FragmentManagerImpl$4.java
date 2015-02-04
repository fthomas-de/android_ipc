package android.support.v4.app;


class FragmentManagerImpl$4 implements java.lang.Runnable
{
    final FragmentManagerImpl this$0;
    final int val$flags;
    final int val$id;

    FragmentManagerImpl$4(FragmentManagerImpl  r1, int  i0, int  i1)
    {


        this$0 = r1;
        val$id = i0;
        val$flags = i1;
        this.<init>();
    }

    public void run()
    {


        this$0.popBackStackState(this$0.mActivity.mHandler, null, val$id, val$flags);
    }
}
