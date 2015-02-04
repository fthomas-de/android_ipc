package android.support.v4.app;


class FragmentManagerImpl$1 implements java.lang.Runnable
{
    final FragmentManagerImpl this$0;

    FragmentManagerImpl$1(FragmentManagerImpl  r1)
    {


        this$0 = r1;
        this.<init>();
    }

    public void run()
    {


        this$0.execPendingActions();
    }
}
