package android.support.v4.app;


public abstract class FragmentTransaction
{
    public static final int TRANSIT_ENTER_MASK = 4096;
    public static final int TRANSIT_EXIT_MASK = 8192;
    public static final int TRANSIT_FRAGMENT_CLOSE = 8194;
    public static final int TRANSIT_FRAGMENT_FADE = 4099;
    public static final int TRANSIT_FRAGMENT_OPEN = 4097;
    public static final int TRANSIT_NONE = 0;
    public static final int TRANSIT_UNSET = -1;

    public FragmentTransaction()
    {


        this.<init>();
    }

    public abstract android.support.v4.app.FragmentTransaction add(int  i0, android.support.v4.app.Fragment  r1);

    public abstract android.support.v4.app.FragmentTransaction add(int  i0, android.support.v4.app.Fragment  r1, java.lang.String  r2);

    public abstract android.support.v4.app.FragmentTransaction add(android.support.v4.app.Fragment  r0, java.lang.String  r1);

    public abstract android.support.v4.app.FragmentTransaction addToBackStack(java.lang.String  r0);

    public abstract android.support.v4.app.FragmentTransaction attach(android.support.v4.app.Fragment  r0);

    public abstract int commit();

    public abstract int commitAllowingStateLoss();

    public abstract android.support.v4.app.FragmentTransaction detach(android.support.v4.app.Fragment  r0);

    public abstract android.support.v4.app.FragmentTransaction disallowAddToBackStack();

    public abstract android.support.v4.app.FragmentTransaction hide(android.support.v4.app.Fragment  r0);

    public abstract boolean isAddToBackStackAllowed();

    public abstract boolean isEmpty();

    public abstract android.support.v4.app.FragmentTransaction remove(android.support.v4.app.Fragment  r0);

    public abstract android.support.v4.app.FragmentTransaction replace(int  i0, android.support.v4.app.Fragment  r1);

    public abstract android.support.v4.app.FragmentTransaction replace(int  i0, android.support.v4.app.Fragment  r1, java.lang.String  r2);

    public abstract android.support.v4.app.FragmentTransaction setBreadCrumbShortTitle(int  i0);

    public abstract android.support.v4.app.FragmentTransaction setBreadCrumbShortTitle(java.lang.CharSequence  r0);

    public abstract android.support.v4.app.FragmentTransaction setBreadCrumbTitle(int  i0);

    public abstract android.support.v4.app.FragmentTransaction setBreadCrumbTitle(java.lang.CharSequence  r0);

    public abstract android.support.v4.app.FragmentTransaction setCustomAnimations(int  i0, int  i1);

    public abstract android.support.v4.app.FragmentTransaction setCustomAnimations(int  i0, int  i1, int  i2, int  i3);

    public abstract android.support.v4.app.FragmentTransaction setTransition(int  i0);

    public abstract android.support.v4.app.FragmentTransaction setTransitionStyle(int  i0);

    public abstract android.support.v4.app.FragmentTransaction show(android.support.v4.app.Fragment  r0);
}
