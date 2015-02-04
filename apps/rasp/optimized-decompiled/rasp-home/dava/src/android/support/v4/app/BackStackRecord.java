package android.support.v4.app;

import android.util.Log;
import java.util.ArrayList;
import java.io.PrintWriter;
import android.support.v4.util.LogWriter;
import java.io.FileDescriptor;

final class BackStackRecord extends FragmentTransaction implements android.support.v4.app.FragmentManager$BackStackEntry, java.lang.Runnable
{
    static final int OP_ADD = 1;
    static final int OP_ATTACH = 7;
    static final int OP_DETACH = 6;
    static final int OP_HIDE = 4;
    static final int OP_NULL = 0;
    static final int OP_REMOVE = 3;
    static final int OP_REPLACE = 2;
    static final int OP_SHOW = 5;
    static final String TAG = "FragmentManager";
    boolean mAddToBackStack;
    boolean mAllowAddToBackStack;
    int mBreadCrumbShortTitleRes;
    CharSequence mBreadCrumbShortTitleText;
    int mBreadCrumbTitleRes;
    CharSequence mBreadCrumbTitleText;
    boolean mCommitted;
    int mEnterAnim;
    int mExitAnim;
    BackStackRecord$Op mHead;
    int mIndex;
    final FragmentManagerImpl mManager;
    String mName;
    int mNumOp;
    int mPopEnterAnim;
    int mPopExitAnim;
    BackStackRecord$Op mTail;
    int mTransition;
    int mTransitionStyle;

    public BackStackRecord(FragmentManagerImpl  r1)
    {


        this.<init>();
        mAllowAddToBackStack = true;
        mIndex = -1;
        mManager = r1;
    }

    public FragmentTransaction add(int  i0, Fragment  r1)
    {


        this.doAddOp(i0, r1, null, 1);
        return this;
    }

    public FragmentTransaction add(int  i0, Fragment  r1, String  r2)
    {


        this.doAddOp(i0, r1, r2, 1);
        return this;
    }

    public FragmentTransaction add(Fragment  r1, String  r2)
    {


        this.doAddOp(0, r1, r2, 1);
        return this;
    }

    void addOp(BackStackRecord$Op  r1)
    {


        if (mHead != null)
        {
            r1.prev = mTail;
            mTail.next = r1;
            mTail = r1;
        }
        else
        {
            mTail = r1;
            mHead = r1;
        }

        r1.enterAnim = mEnterAnim;
        r1.exitAnim = mExitAnim;
        r1.popEnterAnim = mPopEnterAnim;
        r1.popExitAnim = mPopExitAnim;
        mNumOp = mNumOp + 1;
    }

    public FragmentTransaction addToBackStack(String  r1)
    {


        if (mAllowAddToBackStack != false)
        {
            mAddToBackStack = true;
            mName = r1;
            return this;
        }
        else
        {
            throw new IllegalStateException("This FragmentTransaction is not allowed to be added to the back stack.");
        }
    }

    public FragmentTransaction attach(Fragment  r1)
    {

        BackStackRecord$Op r2;
        r2 = new BackStackRecord$Op();
        r2.cmd = 7;
        r2.fragment = r1;
        this.addOp(r2);
        return this;
    }

    void bumpBackStackNesting(int  i0)
    {

        BackStackRecord$Op r3;
        int i1;
        Fragment r13, r28;
        if (mAddToBackStack != false)
        {
            if (FragmentManagerImpl.DEBUG != false)
            {
                Log.v("FragmentManager", (new StringBuilder()).append("Bump nesting in ").append(this).append(" by ").append(i0).toString());
            }

            r3 = mHead;

            while (r3 != null)
            {
                if (r3.fragment != null)
                {
                    r13 = r3.fragment;
                    r13.mBackStackNesting = r13.mBackStackNesting + i0;

                    if (FragmentManagerImpl.DEBUG != false)
                    {
                        Log.v("FragmentManager", (new StringBuilder()).append("Bump nesting of ").append(r3.fragment).append(" to ").append(r3.fragment.mBackStackNesting).toString());
                    }
                }

                if (r3.removed != null)
                {
                    i1 = r3.removed.size() + -1;

                    if (i1 >= 0)
                    {
                        r28 = (Fragment) r3.removed.get(i1);
                        r28.mBackStackNesting = r28.mBackStackNesting + i0;

                        if (FragmentManagerImpl.DEBUG != false)
                        {
                            Log.v("FragmentManager", (new StringBuilder()).append("Bump nesting of ").append(r28).append(" to ").append(r28.mBackStackNesting).toString());
                        }

                        i1 = i1 + -1;
                    }
                }

                r3 = r3.next;
            }
        }
    }

    public int commit()
    {


        return this.commitInternal(false);
    }

    public int commitAllowingStateLoss()
    {


        return this.commitInternal(true);
    }

    int commitInternal(boolean  z0)
    {

        Object n0;
        n0 = null;

        if (mCommitted == false)
        {
            if (FragmentManagerImpl.DEBUG != false)
            {
                Log.v("FragmentManager", (new StringBuilder()).append("Commit: ").append(this).toString());
                this.dump("  ", n0, new PrintWriter(new LogWriter("FragmentManager")), n0);
            }

            mCommitted = true;

            if (mAddToBackStack == false)
            {
                mIndex = -1;
            }
            else
            {
                mIndex = mManager.allocBackStackIndex(this);
            }

            mManager.enqueueAction(this, z0);
            return mIndex;
        }
        else
        {
            throw new IllegalStateException("commit already called");
        }
    }

    public FragmentTransaction detach(Fragment  r1)
    {

        BackStackRecord$Op r2;
        r2 = new BackStackRecord$Op();
        r2.cmd = 6;
        r2.fragment = r1;
        this.addOp(r2);
        return this;
    }

    public FragmentTransaction disallowAddToBackStack()
    {


        if (mAddToBackStack == false)
        {
            mAllowAddToBackStack = false;
            return this;
        }
        else
        {
            throw new IllegalStateException("This transaction is already being added to the back stack");
        }
    }

    private void doAddOp(int  i0, Fragment  r1, String  r2, int  i1)
    {

        BackStackRecord$Op r6;
        r1.mFragmentManager = mManager;

        if (r2 != null)
        {
            if (r1.mTag != null)
            {
                if (r2.equals(r1.mTag) == false)
                {
                    throw new IllegalStateException((new StringBuilder()).append("Can\'t change tag of fragment ").append(r1).append(": was ").append(r1.mTag).append(" now ").append(r2).toString());
                }
            }

            r1.mTag = r2;
        }

        if (i0 != 0)
        {
            if (r1.mFragmentId != 0)
            {
                if (r1.mFragmentId != i0)
                {
                    throw new IllegalStateException((new StringBuilder()).append("Can\'t change container ID of fragment ").append(r1).append(": was ").append(r1.mFragmentId).append(" now ").append(i0).toString());
                }
            }

            r1.mFragmentId = i0;
            r1.mContainerId = i0;
        }

        r6 = new BackStackRecord$Op();
        r6.cmd = i1;
        r6.fragment = r1;
        this.addOp(r6);
    }

    public void dump(String  r1, FileDescriptor  r2, PrintWriter  r3, String[]  r4)
    {


        this.dump(r1, r3, true);
    }

    public void dump(String  r1, PrintWriter  r2, boolean  z0)
    {

        String r5, r7;
        BackStackRecord$Op r6;
        int i0, i1;
        label_6:
        if (z0 != false)
        {
            r2.print(r1);
            r2.print("mName=");
            r2.print(mName);
            r2.print(" mIndex=");
            r2.print(mIndex);
            r2.print(" mCommitted=");
            r2.println(mCommitted);

            if (mTransition != 0)
            {
                r2.print(r1);
                r2.print("mTransition=#");
                r2.print(Integer.toHexString(mTransition));
                r2.print(" mTransitionStyle=#");
                r2.println(Integer.toHexString(mTransitionStyle));
            }

            label_0:
            {
                if (mEnterAnim == 0)
                {
                    if (mExitAnim == 0)
                    {
                        break label_0;
                    }
                }

                r2.print(r1);
                r2.print("mEnterAnim=#");
                r2.print(Integer.toHexString(mEnterAnim));
                r2.print(" mExitAnim=#");
                r2.println(Integer.toHexString(mExitAnim));
            } //end label_0:


            label_1:
            {
                if (mPopEnterAnim == 0)
                {
                    if (mPopExitAnim == 0)
                    {
                        break label_1;
                    }
                }

                r2.print(r1);
                r2.print("mPopEnterAnim=#");
                r2.print(Integer.toHexString(mPopEnterAnim));
                r2.print(" mPopExitAnim=#");
                r2.println(Integer.toHexString(mPopExitAnim));
            } //end label_1:


            label_2:
            {
                if (mBreadCrumbTitleRes == 0)
                {
                    if (mBreadCrumbTitleText == null)
                    {
                        break label_2;
                    }
                }

                r2.print(r1);
                r2.print("mBreadCrumbTitleRes=#");
                r2.print(Integer.toHexString(mBreadCrumbTitleRes));
                r2.print(" mBreadCrumbTitleText=");
                r2.println(mBreadCrumbTitleText);
            } //end label_2:


            if (mBreadCrumbShortTitleRes == 0)
            {
                if (mBreadCrumbShortTitleText == null)
                {
                    break label_6;
                }
            }

            r2.print(r1);
            r2.print("mBreadCrumbShortTitleRes=#");
            r2.print(Integer.toHexString(mBreadCrumbShortTitleRes));
            r2.print(" mBreadCrumbShortTitleText=");
            r2.println(mBreadCrumbShortTitleText);
        }

        if (mHead != null)
        {
            r2.print(r1);
            r2.println("Operations:");
            r5 = (new StringBuilder()).append(r1).append("    ").toString();
            r6 = mHead;
            i0 = 0;

            while (r6 != null)
            {
                label_4:
                switch (r6.cmd)
                {
                    case 0:
                        r7 = "NULL";
                        break label_4;

                    case 1:
                        r7 = "ADD";
                        break label_4;

                    case 2:
                        r7 = "REPLACE";
                        break label_4;

                    case 3:
                        r7 = "REMOVE";
                        break label_4;

                    case 4:
                        r7 = "HIDE";
                        break label_4;

                    case 5:
                        r7 = "SHOW";
                        break label_4;

                    case 6:
                        r7 = "DETACH";
                        break label_4;

                    case 7:
                        r7 = "ATTACH";
                        break label_4;

                    default:
                        r7 = (new StringBuilder()).append("cmd=").append(r6.cmd).toString();
                        break label_4;
                }

                r2.print(r1);
                r2.print("  Op #");
                r2.print(i0);
                r2.print(": ");
                r2.print(r7);
                r2.print(" ");
                r2.println(r6.fragment);

                label_5:
                if (z0 != false)
                {
                    label_3:
                    {
                        if (r6.enterAnim == 0)
                        {
                            if (r6.exitAnim == 0)
                            {
                                break label_3;
                            }
                        }

                        r2.print(r1);
                        r2.print("enterAnim=#");
                        r2.print(Integer.toHexString(r6.enterAnim));
                        r2.print(" exitAnim=#");
                        r2.println(Integer.toHexString(r6.exitAnim));
                    } //end label_3:


                    if (r6.popEnterAnim == 0)
                    {
                        if (r6.popExitAnim == 0)
                        {
                            break label_5;
                        }
                    }

                    r2.print(r1);
                    r2.print("popEnterAnim=#");
                    r2.print(Integer.toHexString(r6.popEnterAnim));
                    r2.print(" popExitAnim=#");
                    r2.println(Integer.toHexString(r6.popExitAnim));
                }

                if (r6.removed != null)
                {
                    if (r6.removed.size() > 0)
                    {
                        i1 = 0;

                        if (i1 < r6.removed.size())
                        {
                            r2.print(r5);

                            if (r6.removed.size() != 1)
                            {
                                if (i1 == 0)
                                {
                                    r2.println("Removed:");
                                }

                                r2.print(r5);
                                r2.print("  #");
                                r2.print(i1);
                                r2.print(": ");
                            }
                            else
                            {
                                r2.print("Removed: ");
                            }

                            r2.println(r6.removed.get(i1));
                            i1 = i1 + 1;
                        }
                    }
                }

                r6 = r6.next;
                i0 = i0 + 1;
            }
        }
    }

    public CharSequence getBreadCrumbShortTitle()
    {

        CharSequence r3;
        if (mBreadCrumbShortTitleRes == 0)
        {
            r3 = mBreadCrumbShortTitleText;
        }
        else
        {
            r3 = mManager.mActivity.getText(mBreadCrumbShortTitleRes);
        }

        return r3;
    }

    public int getBreadCrumbShortTitleRes()
    {


        return mBreadCrumbShortTitleRes;
    }

    public CharSequence getBreadCrumbTitle()
    {

        CharSequence r3;
        if (mBreadCrumbTitleRes == 0)
        {
            r3 = mBreadCrumbTitleText;
        }
        else
        {
            r3 = mManager.mActivity.getText(mBreadCrumbTitleRes);
        }

        return r3;
    }

    public int getBreadCrumbTitleRes()
    {


        return mBreadCrumbTitleRes;
    }

    public int getId()
    {


        return mIndex;
    }

    public String getName()
    {


        return mName;
    }

    public int getTransition()
    {


        return mTransition;
    }

    public int getTransitionStyle()
    {


        return mTransitionStyle;
    }

    public FragmentTransaction hide(Fragment  r1)
    {

        BackStackRecord$Op r2;
        r2 = new BackStackRecord$Op();
        r2.cmd = 4;
        r2.fragment = r1;
        this.addOp(r2);
        return this;
    }

    public boolean isAddToBackStackAllowed()
    {


        return mAllowAddToBackStack;
    }

    public boolean isEmpty()
    {

        boolean z0;
        if (mNumOp != 0)
        {
            z0 = false;
        }
        else
        {
            z0 = true;
        }

        return z0;
    }

    public void popFromBackStack(boolean  z0)
    {

        Object n0;
        BackStackRecord$Op r5;
        Fragment r6, r21, r26, r28, r30, r32, r34, r36;
        int i1;
        n0 = null;

        if (FragmentManagerImpl.DEBUG != false)
        {
            Log.v("FragmentManager", (new StringBuilder()).append("popFromBackStack: ").append(this).toString());
            this.dump("  ", n0, new PrintWriter(new LogWriter("FragmentManager")), n0);
        }

        this.bumpBackStackNesting(-1);
        r5 = mTail;

        while (r5 != null)
        {
            label_7:
            switch (r5.cmd)
            {
                case 1:
                    r6 = r5.fragment;
                    r6.mNextAnim = r5.popExitAnim;
                    mManager.removeFragment(r6, FragmentManagerImpl.reverseTransit(mTransition), mTransitionStyle);
                    break label_7;

                case 2:
                    r21 = r5.fragment;

                    if (r21 != null)
                    {
                        r21.mNextAnim = r5.popExitAnim;
                        mManager.removeFragment(r21, FragmentManagerImpl.reverseTransit(mTransition), mTransitionStyle);
                    }

                    if (r5.removed == null)
                    {
                        break label_7;
                    }
                    else
                    {
                        i1 = 0;

                        if (i1 >= r5.removed.size())
                        {
                            break label_7;
                        }
                        else
                        {
                            r26 = (Fragment) r5.removed.get(i1);
                            r26.mNextAnim = r5.popEnterAnim;
                            mManager.addFragment(r26, false);
                            i1 = i1 + 1;
                        }
                    }

                case 3:
                    r28 = r5.fragment;
                    r28.mNextAnim = r5.popEnterAnim;
                    mManager.addFragment(r28, false);
                    break label_7;

                case 4:
                    r30 = r5.fragment;
                    r30.mNextAnim = r5.popEnterAnim;
                    mManager.showFragment(r30, FragmentManagerImpl.reverseTransit(mTransition), mTransitionStyle);
                    break label_7;

                case 5:
                    r32 = r5.fragment;
                    r32.mNextAnim = r5.popExitAnim;
                    mManager.hideFragment(r32, FragmentManagerImpl.reverseTransit(mTransition), mTransitionStyle);
                    break label_7;

                case 6:
                    r34 = r5.fragment;
                    r34.mNextAnim = r5.popEnterAnim;
                    mManager.attachFragment(r34, FragmentManagerImpl.reverseTransit(mTransition), mTransitionStyle);
                    break label_7;

                case 7:
                    r36 = r5.fragment;
                    r36.mNextAnim = r5.popEnterAnim;
                    mManager.detachFragment(r36, FragmentManagerImpl.reverseTransit(mTransition), mTransitionStyle);
                    break label_7;

                default:
                    throw new IllegalArgumentException((new StringBuilder()).append("Unknown cmd: ").append(r5.cmd).toString());
            }

            r5 = r5.prev;
        }

        if (z0 != false)
        {
            mManager.moveToState(mManager.mCurState, FragmentManagerImpl.reverseTransit(mTransition), mTransitionStyle, true);
        }

        if (mIndex >= 0)
        {
            mManager.freeBackStackIndex(mIndex);
            mIndex = -1;
        }
    }

    public FragmentTransaction remove(Fragment  r1)
    {

        BackStackRecord$Op r2;
        r2 = new BackStackRecord$Op();
        r2.cmd = 3;
        r2.fragment = r1;
        this.addOp(r2);
        return this;
    }

    public FragmentTransaction replace(int  i0, Fragment  r1)
    {


        return this.replace(i0, r1, null);
    }

    public FragmentTransaction replace(int  i0, Fragment  r1, String  r2)
    {


        if (i0 != 0)
        {
            this.doAddOp(i0, r1, r2, 2);
            return this;
        }
        else
        {
            throw new IllegalArgumentException("Must use non-zero containerViewId");
        }
    }

    public void run()
    {

        BackStackRecord$Op r3;
        Fragment r4, r19, r26, r50, r52, r54, r56, r58;
        int i0;
        if (FragmentManagerImpl.DEBUG != false)
        {
            Log.v("FragmentManager", (new StringBuilder()).append("Run: ").append(this).toString());
        }

        if (mAddToBackStack != false)
        {
            if (mIndex < 0)
            {
                throw new IllegalStateException("addToBackStack() called after commit()");
            }
        }

        this.bumpBackStackNesting((int) 1);
        r3 = mHead;

        while (r3 != null)
        {
            label_9:
            switch (r3.cmd)
            {
                case 1:
                    r4 = r3.fragment;
                    r4.mNextAnim = r3.enterAnim;
                    mManager.addFragment(r4, false);
                    break label_9;

                case 2:
                    r19 = r3.fragment;

                    if (mManager.mAdded != null)
                    {
                        i0 = 0;

                        if (i0 < mManager.mAdded.size())
                        {
                            r26 = (Fragment) mManager.mAdded.get(i0);

                            if (FragmentManagerImpl.DEBUG != false)
                            {
                                Log.v("FragmentManager", (new StringBuilder()).append("OP_REPLACE: adding=").append(r19).append(" old=").append(r26).toString());
                            }

                            label_8:
                            {
                                if (r19 != null)
                                {
                                    if (r26.mContainerId != r19.mContainerId)
                                    {
                                        break label_8;
                                    }
                                }

                                if (r26 != r19)
                                {
                                    if (r3.removed == null)
                                    {
                                        r3.removed = new ArrayList();
                                    }

                                    r3.removed.add(r26);
                                    r26.mNextAnim = r3.exitAnim;

                                    if (mAddToBackStack != false)
                                    {
                                        r26.mBackStackNesting = r26.mBackStackNesting + 1;

                                        if (FragmentManagerImpl.DEBUG != false)
                                        {
                                            Log.v("FragmentManager", (new StringBuilder()).append("Bump nesting of ").append(r26).append(" to ").append(r26.mBackStackNesting).toString());
                                        }
                                    }

                                    mManager.removeFragment(r26, mTransition, mTransitionStyle);
                                }
                                else
                                {
                                    r19 = null;
                                    r3.fragment = r19;
                                }
                            } //end label_8:


                            i0 = i0 + 1;
                        }
                    }

                    if (r19 == null)
                    {
                        break label_9;
                    }
                    else
                    {
                        r19.mNextAnim = r3.enterAnim;
                        mManager.addFragment(r19, false);
                        break label_9;
                    }

                case 3:
                    r50 = r3.fragment;
                    r50.mNextAnim = r3.exitAnim;
                    mManager.removeFragment(r50, mTransition, mTransitionStyle);
                    break label_9;

                case 4:
                    r52 = r3.fragment;
                    r52.mNextAnim = r3.exitAnim;
                    mManager.hideFragment(r52, mTransition, mTransitionStyle);
                    break label_9;

                case 5:
                    r54 = r3.fragment;
                    r54.mNextAnim = r3.enterAnim;
                    mManager.showFragment(r54, mTransition, mTransitionStyle);
                    break label_9;

                case 6:
                    r56 = r3.fragment;
                    r56.mNextAnim = r3.exitAnim;
                    mManager.detachFragment(r56, mTransition, mTransitionStyle);
                    break label_9;

                case 7:
                    r58 = r3.fragment;
                    r58.mNextAnim = r3.enterAnim;
                    mManager.attachFragment(r58, mTransition, mTransitionStyle);
                    break label_9;

                default:
                    throw new IllegalArgumentException((new StringBuilder()).append("Unknown cmd: ").append(r3.cmd).toString());
            }

            r3 = r3.next;
        }

        mManager.moveToState(mManager.mCurState, mTransition, mTransitionStyle, true);

        if (mAddToBackStack != false)
        {
            mManager.addBackStackState(this);
        }
    }

    public FragmentTransaction setBreadCrumbShortTitle(int  i0)
    {


        mBreadCrumbShortTitleRes = i0;
        mBreadCrumbShortTitleText = null;
        return this;
    }

    public FragmentTransaction setBreadCrumbShortTitle(CharSequence  r1)
    {


        mBreadCrumbShortTitleRes = 0;
        mBreadCrumbShortTitleText = r1;
        return this;
    }

    public FragmentTransaction setBreadCrumbTitle(int  i0)
    {


        mBreadCrumbTitleRes = i0;
        mBreadCrumbTitleText = null;
        return this;
    }

    public FragmentTransaction setBreadCrumbTitle(CharSequence  r1)
    {


        mBreadCrumbTitleRes = 0;
        mBreadCrumbTitleText = r1;
        return this;
    }

    public FragmentTransaction setCustomAnimations(int  i0, int  i1)
    {


        return this.setCustomAnimations(i0, i1, 0, 0);
    }

    public FragmentTransaction setCustomAnimations(int  i0, int  i1, int  i2, int  i3)
    {


        mEnterAnim = i0;
        mExitAnim = i1;
        mPopEnterAnim = i2;
        mPopExitAnim = i3;
        return this;
    }

    public FragmentTransaction setTransition(int  i0)
    {


        mTransition = i0;
        return this;
    }

    public FragmentTransaction setTransitionStyle(int  i0)
    {


        mTransitionStyle = i0;
        return this;
    }

    public FragmentTransaction show(Fragment  r1)
    {

        BackStackRecord$Op r2;
        r2 = new BackStackRecord$Op();
        r2.cmd = 5;
        r2.fragment = r1;
        this.addOp(r2);
        return this;
    }

    public String toString()
    {

        StringBuilder r1;
        r1 = new StringBuilder(128);
        r1.append("BackStackEntry{");
        r1.append(Integer.toHexString(System.identityHashCode(this)));

        if (mIndex >= 0)
        {
            r1.append(" #");
            r1.append(mIndex);
        }

        if (mName != null)
        {
            r1.append(" ");
            r1.append(mName);
        }

        r1.append("}");
        return r1.toString();
    }
}
