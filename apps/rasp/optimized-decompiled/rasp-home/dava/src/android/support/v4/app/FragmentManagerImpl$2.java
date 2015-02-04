package android.support.v4.app;


class FragmentManagerImpl$2 implements java.lang.Runnable
{
    final FragmentManagerImpl this$0;

    FragmentManagerImpl$2(FragmentManagerImpl  r1)
    {


        this$0 = r1;
        this.<init>();
    }

    public void run()
    {


        this$0.popBackStackState(this$0.mActivity.mHandler, null, -1, 0);
    }
}
