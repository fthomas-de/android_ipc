package android.support.v4.app;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import android.os.Bundle;
import java.util.List;

public abstract class FragmentManager
{
    public static final int POP_BACK_STACK_INCLUSIVE = 1;

    public FragmentManager()
    {


        this.<init>();
    }

    public abstract void addOnBackStackChangedListener(android.support.v4.app.FragmentManager$OnBackStackChangedListener  r0);

    public abstract android.support.v4.app.FragmentTransaction beginTransaction();

    public abstract void dump(java.lang.String  r0, java.io.FileDescriptor  r1, java.io.PrintWriter  r2, java.lang.String[]  r3);

    public static void enableDebugLogging(boolean  z0)
    {


        FragmentManagerImpl.DEBUG = z0;
    }

    public abstract boolean executePendingTransactions();

    public abstract android.support.v4.app.Fragment findFragmentById(int  i0);

    public abstract android.support.v4.app.Fragment findFragmentByTag(java.lang.String  r0);

    public abstract android.support.v4.app.FragmentManager$BackStackEntry getBackStackEntryAt(int  i0);

    public abstract int getBackStackEntryCount();

    public abstract android.support.v4.app.Fragment getFragment(android.os.Bundle  r0, java.lang.String  r1);

    public abstract java.util.List getFragments();

    public abstract boolean isDestroyed();

    public FragmentTransaction openTransaction()
    {


        return this.beginTransaction();
    }

    public abstract void popBackStack();

    public abstract void popBackStack(int  i0, int  i1);

    public abstract void popBackStack(java.lang.String  r0, int  i1);

    public abstract boolean popBackStackImmediate();

    public abstract boolean popBackStackImmediate(int  i0, int  i1);

    public abstract boolean popBackStackImmediate(java.lang.String  r0, int  i1);

    public abstract void putFragment(android.os.Bundle  r0, java.lang.String  r1, android.support.v4.app.Fragment  r2);

    public abstract void removeOnBackStackChangedListener(android.support.v4.app.FragmentManager$OnBackStackChangedListener  r0);

    public abstract android.support.v4.app.Fragment$SavedState saveFragmentInstanceState(android.support.v4.app.Fragment  r0);
}
