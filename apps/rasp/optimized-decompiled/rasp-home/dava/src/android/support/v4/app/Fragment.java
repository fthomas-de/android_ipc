package android.support.v4.app;

import android.content.ComponentCallbacks;
import android.view.View$OnCreateContextMenuListener;
import android.support.v4.util.SimpleArrayMap;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.content.res.Resources;
import android.view.View;
import android.content.Context;
import android.content.Intent;
import android.app.Activity;
import android.content.res.Configuration;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.ContextMenu;
import android.view.ContextMenu$ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.ViewGroup;
import android.util.AttributeSet;
import android.os.Parcelable;
import android.support.v4.util.DebugUtils;
import android.util.SparseArray;

public class Fragment implements android.content.ComponentCallbacks, android.view.View$OnCreateContextMenuListener
{
    static final int ACTIVITY_CREATED = 2;
    static final int CREATED = 1;
    static final int INITIALIZING = 0;
    static final int RESUMED = 5;
    static final int STARTED = 4;
    static final int STOPPED = 3;
    private static final SimpleArrayMap sClassMap;
    FragmentActivity mActivity;
    boolean mAdded;
    View mAnimatingAway;
    Bundle mArguments;
    int mBackStackNesting;
    boolean mCalled;
    boolean mCheckedForLoaderManager;
    FragmentManagerImpl mChildFragmentManager;
    ViewGroup mContainer;
    int mContainerId;
    boolean mDeferStart;
    boolean mDetached;
    int mFragmentId;
    FragmentManagerImpl mFragmentManager;
    boolean mFromLayout;
    boolean mHasMenu;
    boolean mHidden;
    boolean mInLayout;
    int mIndex;
    View mInnerView;
    LoaderManagerImpl mLoaderManager;
    boolean mLoadersStarted;
    boolean mMenuVisible;
    int mNextAnim;
    Fragment mParentFragment;
    boolean mRemoving;
    boolean mRestored;
    boolean mResumed;
    boolean mRetainInstance;
    boolean mRetaining;
    Bundle mSavedFragmentState;
    SparseArray mSavedViewState;
    int mState;
    int mStateAfterAnimating;
    String mTag;
    Fragment mTarget;
    int mTargetIndex;
    int mTargetRequestCode;
    boolean mUserVisibleHint;
    View mView;
    String mWho;

    static
    {


        sClassMap = new SimpleArrayMap();
    }

    public Fragment()
    {


        this.<init>();
        mState = 0;
        mIndex = -1;
        mTargetIndex = -1;
        mMenuVisible = true;
        mUserVisibleHint = true;
    }

    public void dump(String  r1, FileDescriptor  r2, PrintWriter  r3, String[]  r4)
    {


        r3.print(r1);
        r3.print("mFragmentId=#");
        r3.print(Integer.toHexString(mFragmentId));
        r3.print(" mContainerId=#");
        r3.print(Integer.toHexString(mContainerId));
        r3.print(" mTag=");
        r3.println(mTag);
        r3.print(r1);
        r3.print("mState=");
        r3.print(mState);
        r3.print(" mIndex=");
        r3.print(mIndex);
        r3.print(" mWho=");
        r3.print(mWho);
        r3.print(" mBackStackNesting=");
        r3.println(mBackStackNesting);
        r3.print(r1);
        r3.print("mAdded=");
        r3.print(mAdded);
        r3.print(" mRemoving=");
        r3.print(mRemoving);
        r3.print(" mResumed=");
        r3.print(mResumed);
        r3.print(" mFromLayout=");
        r3.print(mFromLayout);
        r3.print(" mInLayout=");
        r3.println(mInLayout);
        r3.print(r1);
        r3.print("mHidden=");
        r3.print(mHidden);
        r3.print(" mDetached=");
        r3.print(mDetached);
        r3.print(" mMenuVisible=");
        r3.print(mMenuVisible);
        r3.print(" mHasMenu=");
        r3.println(mHasMenu);
        r3.print(r1);
        r3.print("mRetainInstance=");
        r3.print(mRetainInstance);
        r3.print(" mRetaining=");
        r3.print(mRetaining);
        r3.print(" mUserVisibleHint=");
        r3.println(mUserVisibleHint);

        if (mFragmentManager != null)
        {
            r3.print(r1);
            r3.print("mFragmentManager=");
            r3.println(mFragmentManager);
        }

        if (mActivity != null)
        {
            r3.print(r1);
            r3.print("mActivity=");
            r3.println(mActivity);
        }

        if (mParentFragment != null)
        {
            r3.print(r1);
            r3.print("mParentFragment=");
            r3.println(mParentFragment);
        }

        if (mArguments != null)
        {
            r3.print(r1);
            r3.print("mArguments=");
            r3.println(mArguments);
        }

        if (mSavedFragmentState != null)
        {
            r3.print(r1);
            r3.print("mSavedFragmentState=");
            r3.println(mSavedFragmentState);
        }

        if (mSavedViewState != null)
        {
            r3.print(r1);
            r3.print("mSavedViewState=");
            r3.println(mSavedViewState);
        }

        if (mTarget != null)
        {
            r3.print(r1);
            r3.print("mTarget=");
            r3.print(mTarget);
            r3.print(" mTargetRequestCode=");
            r3.println(mTargetRequestCode);
        }

        if (mNextAnim != 0)
        {
            r3.print(r1);
            r3.print("mNextAnim=");
            r3.println(mNextAnim);
        }

        if (mContainer != null)
        {
            r3.print(r1);
            r3.print("mContainer=");
            r3.println(mContainer);
        }

        if (mView != null)
        {
            r3.print(r1);
            r3.print("mView=");
            r3.println(mView);
        }

        if (mInnerView != null)
        {
            r3.print(r1);
            r3.print("mInnerView=");
            r3.println(mView);
        }

        if (mAnimatingAway != null)
        {
            r3.print(r1);
            r3.print("mAnimatingAway=");
            r3.println(mAnimatingAway);
            r3.print(r1);
            r3.print("mStateAfterAnimating=");
            r3.println(mStateAfterAnimating);
        }

        if (mLoaderManager != null)
        {
            r3.print(r1);
            r3.println("Loader Manager:");
            mLoaderManager.dump((new StringBuilder()).append(r1).append("  ").toString(), r2, r3, r4);
        }

        if (mChildFragmentManager != null)
        {
            r3.print(r1);
            r3.println((new StringBuilder()).append("Child ").append(mChildFragmentManager).append(":").toString());
            mChildFragmentManager.dump((new StringBuilder()).append(r1).append("  ").toString(), r2, r3, r4);
        }
    }

    public final boolean equals(Object  r1)
    {


        return this.equals(r1);
    }

    Fragment findFragmentByWho(String  r1)
    {


        if (r1.equals(mWho) == false)
        {
            if (mChildFragmentManager == null)
            {
                this = null;
            }
            else
            {
                this = mChildFragmentManager.findFragmentByWho(r1);
            }
        }

        return this;
    }

    public final FragmentActivity getActivity()
    {


        return mActivity;
    }

    public final Bundle getArguments()
    {


        return mArguments;
    }

    public final FragmentManager getChildFragmentManager()
    {


        if (mChildFragmentManager == null)
        {
            this.instantiateChildFragmentManager();

            if (mState < 5)
            {
                if (mState < 4)
                {
                    if (mState < 2)
                    {
                        if (mState >= 1)
                        {
                            mChildFragmentManager.dispatchCreate();
                        }
                    }
                    else
                    {
                        mChildFragmentManager.dispatchActivityCreated();
                    }
                }
                else
                {
                    mChildFragmentManager.dispatchStart();
                }
            }
            else
            {
                mChildFragmentManager.dispatchResume();
            }
        }

        return mChildFragmentManager;
    }

    public final FragmentManager getFragmentManager()
    {


        return mFragmentManager;
    }

    public final int getId()
    {


        return mFragmentId;
    }

    public LayoutInflater getLayoutInflater(Bundle  r1)
    {


        return mActivity.getLayoutInflater();
    }

    public LoaderManager getLoaderManager()
    {

        LoaderManagerImpl r4;
        if (mLoaderManager == null)
        {
            if (mActivity != null)
            {
                mCheckedForLoaderManager = true;
                mLoaderManager = mActivity.getLoaderManager(mWho, mLoadersStarted, true);
                r4 = mLoaderManager;
            }
            else
            {
                throw new IllegalStateException((new StringBuilder()).append("Fragment ").append(this).append(" not attached to Activity").toString());
            }
        }
        else
        {
            r4 = mLoaderManager;
        }

        return r4;
    }

    public final Fragment getParentFragment()
    {


        return mParentFragment;
    }

    public final Resources getResources()
    {


        if (mActivity != null)
        {
            return mActivity.getResources();
        }
        else
        {
            throw new IllegalStateException((new StringBuilder()).append("Fragment ").append(this).append(" not attached to Activity").toString());
        }
    }

    public final boolean getRetainInstance()
    {


        return mRetainInstance;
    }

    public final String getString(int  i0)
    {


        return this.getResources().getString(i0);
    }

    public final String getString(int  i0, Object[]  r1)
    {


        return this.getResources().getString(i0, r1);
    }

    public final String getTag()
    {


        return mTag;
    }

    public final Fragment getTargetFragment()
    {


        return mTarget;
    }

    public final int getTargetRequestCode()
    {


        return mTargetRequestCode;
    }

    public final CharSequence getText(int  i0)
    {


        return this.getResources().getText(i0);
    }

    public boolean getUserVisibleHint()
    {


        return mUserVisibleHint;
    }

    public View getView()
    {


        return mView;
    }

    public final boolean hasOptionsMenu()
    {


        return mHasMenu;
    }

    public final int hashCode()
    {


        return this.hashCode();
    }

    void initState()
    {

        Object n0;
        n0 = null;
        mIndex = -1;
        mWho = n0;
        mAdded = false;
        mRemoving = false;
        mResumed = false;
        mFromLayout = false;
        mInLayout = false;
        mRestored = false;
        mBackStackNesting = (int) 0;
        mFragmentManager = n0;
        mActivity = n0;
        mFragmentId = (int) 0;
        mContainerId = (int) 0;
        mTag = n0;
        mHidden = false;
        mDetached = false;
        mRetaining = false;
        mLoaderManager = n0;
        mLoadersStarted = false;
        mCheckedForLoaderManager = false;
    }

    public static Fragment instantiate(Context  r0, String  r1)
    {


        return Fragment.instantiate(r0, r1, null);
    }

    public static Fragment instantiate(Context  r0, String  r1, Bundle  r2)
    {

        SimpleArrayMap r3, r13;
        Object r4, $r15;
        Class r9, $r12, $r17;
        ClassLoader $r10, $r19;
        Fragment r16;
        label_3:
        {
            label_2:
            {
                label_1:
                {
                    label_0:
                    {
                        r3 = sClassMap;

                        r4 = r3.get(r1);

                        r9 = (Class) r4;

                        if (r9 == null)
                        {
                            $r10 = r0.getClassLoader();

                            try
                            {
                                $r12 = $r10.loadClass(r1);
                            }
                            catch (ClassNotFoundException $r21)
                            {
                                break label_0;
                            }

                            r9 = $r12;

                            r13 = sClassMap;

                            r13.put(r1, $r12);
                        }

                        try
                        {
                            $r15 = r9.newInstance();
                        }
                        catch (InstantiationException $r30)
                        {
                            break label_1;
                        }
                        catch (IllegalAccessException $r42)
                        {
                            break label_2;
                        }

                        r16 = (Fragment) $r15;

                        if (r2 == null)
                        {
                            break label_3;
                        }
                        else
                        {
                            $r17 = r16.getClass();

                            $r19 = $r17.getClassLoader();

                            r2.setClassLoader($r19);

                            r16.mArguments = r2;
                            break label_3;
                        }
                    } //end label_0:


                    throw new Fragment$InstantiationException((new StringBuilder()).append("Unable to instantiate fragment ").append(r1).append(": make sure class name exists, is public, and has an").append(" empty constructor that is public").toString(), $r21);
                } //end label_1:


                throw new Fragment$InstantiationException((new StringBuilder()).append("Unable to instantiate fragment ").append(r1).append(": make sure class name exists, is public, and has an").append(" empty constructor that is public").toString(), $r30);
            } //end label_2:


            throw new Fragment$InstantiationException((new StringBuilder()).append("Unable to instantiate fragment ").append(r1).append(": make sure class name exists, is public, and has an").append(" empty constructor that is public").toString(), $r42);
        } //end label_3:


        return r16;
    }

    void instantiateChildFragmentManager()
    {


        mChildFragmentManager = new FragmentManagerImpl();
        mChildFragmentManager.attachActivity(mActivity, new Fragment$1(this), this);
    }

    public final boolean isAdded()
    {

        boolean z1;
        label_4:
        {
            if (mActivity != null)
            {
                if (mAdded != false)
                {
                    z1 = true;
                    break label_4;
                }
            }

            z1 = false;
        } //end label_4:


        return z1;
    }

    public final boolean isDetached()
    {


        return mDetached;
    }

    public final boolean isHidden()
    {


        return mHidden;
    }

    final boolean isInBackStack()
    {

        boolean z0;
        if (mBackStackNesting <= 0)
        {
            z0 = false;
        }
        else
        {
            z0 = true;
        }

        return z0;
    }

    public final boolean isInLayout()
    {


        return mInLayout;
    }

    public final boolean isMenuVisible()
    {


        return mMenuVisible;
    }

    public final boolean isRemoving()
    {


        return mRemoving;
    }

    public final boolean isResumed()
    {


        return mResumed;
    }

    static boolean isSupportFragmentClass(Context  r0, String  r1)
    {

        SimpleArrayMap r2, r9;
        Object r3;
        Class r5, $r8, r11;
        ClassLoader $r6;
        boolean z0;
        label_7:
        {
            label_6:
            {
                label_5:
                {
                    r2 = sClassMap;

                    r3 = r2.get(r1);

                    r5 = (Class) r3;

                    if (r5 == null)
                    {
                        $r6 = r0.getClassLoader();

                        try
                        {
                            $r8 = $r6.loadClass(r1);
                        }
                        catch (ClassNotFoundException $r12)
                        {
                            break label_5;
                        }

                        r5 = $r8;

                        r9 = sClassMap;

                        r9.put(r1, $r8);
                    }

                    r11 = class "android/support/v4/app/Fragment";
                    break label_6;
                } //end label_5:


                z0 = false;
                break label_7;
            } //end label_6:


            z0 = r11.isAssignableFrom(r5);
        } //end label_7:


        return z0;
    }

    public final boolean isVisible()
    {

        boolean z2;
        label_8:
        {
            if (this.isAdded() != false)
            {
                if (this.isHidden() == false)
                {
                    if (mView != null)
                    {
                        if (mView.getWindowToken() != null)
                        {
                            if (mView.getVisibility() == 0)
                            {
                                z2 = true;
                                break label_8;
                            }
                        }
                    }
                }
            }

            z2 = false;
        } //end label_8:


        return z2;
    }

    public void onActivityCreated(Bundle  r1)
    {


        mCalled = true;
    }

    public void onActivityResult(int  i0, int  i1, Intent  r1)
    {

    }

    public void onAttach(Activity  r1)
    {


        mCalled = true;
    }

    public void onConfigurationChanged(Configuration  r1)
    {


        mCalled = true;
    }

    public boolean onContextItemSelected(MenuItem  r1)
    {


        return false;
    }

    public void onCreate(Bundle  r1)
    {


        mCalled = true;
    }

    public Animation onCreateAnimation(int  i0, boolean  z0, int  i1)
    {


        return null;
    }

    public void onCreateContextMenu(ContextMenu  r1, View  r2, ContextMenu$ContextMenuInfo  r3)
    {


        this.getActivity().onCreateContextMenu(r1, r2, r3);
    }

    public void onCreateOptionsMenu(Menu  r1, MenuInflater  r2)
    {

    }

    public View onCreateView(LayoutInflater  r1, ViewGroup  r2, Bundle  r3)
    {


        return null;
    }

    public void onDestroy()
    {


        mCalled = true;

        if (mCheckedForLoaderManager == false)
        {
            mCheckedForLoaderManager = true;
            mLoaderManager = mActivity.getLoaderManager(mWho, mLoadersStarted, false);
        }

        if (mLoaderManager != null)
        {
            mLoaderManager.doDestroy();
        }
    }

    public void onDestroyOptionsMenu()
    {

    }

    public void onDestroyView()
    {


        mCalled = true;
    }

    public void onDetach()
    {


        mCalled = true;
    }

    public void onHiddenChanged(boolean  z0)
    {

    }

    public void onInflate(Activity  r1, AttributeSet  r2, Bundle  r3)
    {


        mCalled = true;
    }

    public void onLowMemory()
    {


        mCalled = true;
    }

    public boolean onOptionsItemSelected(MenuItem  r1)
    {


        return false;
    }

    public void onOptionsMenuClosed(Menu  r1)
    {

    }

    public void onPause()
    {


        mCalled = true;
    }

    public void onPrepareOptionsMenu(Menu  r1)
    {

    }

    public void onResume()
    {


        mCalled = true;
    }

    public void onSaveInstanceState(Bundle  r1)
    {

    }

    public void onStart()
    {


        mCalled = true;

        if (mLoadersStarted == false)
        {
            mLoadersStarted = true;

            if (mCheckedForLoaderManager == false)
            {
                mCheckedForLoaderManager = true;
                mLoaderManager = mActivity.getLoaderManager(mWho, mLoadersStarted, false);
            }

            if (mLoaderManager != null)
            {
                mLoaderManager.doStart();
            }
        }
    }

    public void onStop()
    {


        mCalled = true;
    }

    public void onViewCreated(View  r1, Bundle  r2)
    {

    }

    public void onViewStateRestored(Bundle  r1)
    {


        mCalled = true;
    }

    void performActivityCreated(Bundle  r1)
    {


        if (mChildFragmentManager != null)
        {
            mChildFragmentManager.noteStateNotSaved();
        }

        mCalled = false;
        this.onActivityCreated(r1);

        if (mCalled != false)
        {
            if (mChildFragmentManager != null)
            {
                mChildFragmentManager.dispatchActivityCreated();
            }

            return;
        }
        else
        {
            throw new SuperNotCalledException((new StringBuilder()).append("Fragment ").append(this).append(" did not call through to super.onActivityCreated()").toString());
        }
    }

    void performConfigurationChanged(Configuration  r1)
    {


        this.onConfigurationChanged(r1);

        if (mChildFragmentManager != null)
        {
            mChildFragmentManager.dispatchConfigurationChanged(r1);
        }
    }

    boolean performContextItemSelected(MenuItem  r1)
    {

        boolean z0;
        z0 = true;

        label_9:
        {
            if (mHidden == false)
            {
                if (this.onContextItemSelected(r1) == false)
                {
                    if (mChildFragmentManager != null)
                    {
                        if (mChildFragmentManager.dispatchContextItemSelected(r1) != false)
                        {
                            break label_9;
                        }
                    }
                }
                else
                {
                    break label_9;
                }
            }

            z0 = false;
        } //end label_9:


        return z0;
    }

    void performCreate(Bundle  r1)
    {

        Parcelable r5;
        if (mChildFragmentManager != null)
        {
            mChildFragmentManager.noteStateNotSaved();
        }

        mCalled = false;
        this.onCreate(r1);

        if (mCalled != false)
        {
            if (r1 != null)
            {
                r5 = r1.getParcelable("android:support:fragments");

                if (r5 != null)
                {
                    if (mChildFragmentManager == null)
                    {
                        this.instantiateChildFragmentManager();
                    }

                    mChildFragmentManager.restoreAllState(r5, null);
                    mChildFragmentManager.dispatchCreate();
                }
            }

            return;
        }
        else
        {
            throw new SuperNotCalledException((new StringBuilder()).append("Fragment ").append(this).append(" did not call through to super.onCreate()").toString());
        }
    }

    boolean performCreateOptionsMenu(Menu  r1, MenuInflater  r2)
    {

        boolean z0;
        z0 = false;

        if (mHidden == false)
        {
            if (mHasMenu != false)
            {
                if (mMenuVisible != false)
                {
                    z0 = true;
                    this.onCreateOptionsMenu(r1, r2);
                }
            }

            if (mChildFragmentManager != null)
            {
                z0 = z0 | mChildFragmentManager.dispatchCreateOptionsMenu(r1, r2);
            }
        }

        return z0;
    }

    View performCreateView(LayoutInflater  r1, ViewGroup  r2, Bundle  r3)
    {


        if (mChildFragmentManager != null)
        {
            mChildFragmentManager.noteStateNotSaved();
        }

        return this.onCreateView(r1, r2, r3);
    }

    void performDestroy()
    {


        if (mChildFragmentManager != null)
        {
            mChildFragmentManager.dispatchDestroy();
        }

        mCalled = false;
        this.onDestroy();

        if (mCalled != false)
        {
            return;
        }
        else
        {
            throw new SuperNotCalledException((new StringBuilder()).append("Fragment ").append(this).append(" did not call through to super.onDestroy()").toString());
        }
    }

    void performDestroyView()
    {


        if (mChildFragmentManager != null)
        {
            mChildFragmentManager.dispatchDestroyView();
        }

        mCalled = false;
        this.onDestroyView();

        if (mCalled != false)
        {
            if (mLoaderManager != null)
            {
                mLoaderManager.doReportNextStart();
            }

            return;
        }
        else
        {
            throw new SuperNotCalledException((new StringBuilder()).append("Fragment ").append(this).append(" did not call through to super.onDestroyView()").toString());
        }
    }

    void performLowMemory()
    {


        this.onLowMemory();

        if (mChildFragmentManager != null)
        {
            mChildFragmentManager.dispatchLowMemory();
        }
    }

    boolean performOptionsItemSelected(MenuItem  r1)
    {

        boolean z0;
        z0 = true;

        label_10:
        {
            if (mHidden == false)
            {
                if (mHasMenu != false)
                {
                    if (mMenuVisible != false)
                    {
                        if (this.onOptionsItemSelected(r1) != false)
                        {
                            break label_10;
                        }
                    }
                }

                if (mChildFragmentManager != null)
                {
                    if (mChildFragmentManager.dispatchOptionsItemSelected(r1) != false)
                    {
                        break label_10;
                    }
                }
            }

            z0 = false;
        } //end label_10:


        return z0;
    }

    void performOptionsMenuClosed(Menu  r1)
    {


        if (mHidden == false)
        {
            if (mHasMenu != false)
            {
                if (mMenuVisible != false)
                {
                    this.onOptionsMenuClosed(r1);
                }
            }

            if (mChildFragmentManager != null)
            {
                mChildFragmentManager.dispatchOptionsMenuClosed(r1);
            }
        }
    }

    void performPause()
    {


        if (mChildFragmentManager != null)
        {
            mChildFragmentManager.dispatchPause();
        }

        mCalled = false;
        this.onPause();

        if (mCalled != false)
        {
            return;
        }
        else
        {
            throw new SuperNotCalledException((new StringBuilder()).append("Fragment ").append(this).append(" did not call through to super.onPause()").toString());
        }
    }

    boolean performPrepareOptionsMenu(Menu  r1)
    {

        boolean z0;
        z0 = false;

        if (mHidden == false)
        {
            if (mHasMenu != false)
            {
                if (mMenuVisible != false)
                {
                    z0 = true;
                    this.onPrepareOptionsMenu(r1);
                }
            }

            if (mChildFragmentManager != null)
            {
                z0 = z0 | mChildFragmentManager.dispatchPrepareOptionsMenu(r1);
            }
        }

        return z0;
    }

    void performReallyStop()
    {


        if (mChildFragmentManager != null)
        {
            mChildFragmentManager.dispatchReallyStop();
        }

        if (mLoadersStarted != false)
        {
            mLoadersStarted = false;

            if (mCheckedForLoaderManager == false)
            {
                mCheckedForLoaderManager = true;
                mLoaderManager = mActivity.getLoaderManager(mWho, mLoadersStarted, false);
            }

            if (mLoaderManager != null)
            {
                if (mActivity.mRetaining != false)
                {
                    mLoaderManager.doRetain();
                }
                else
                {
                    mLoaderManager.doStop();
                }
            }
        }
    }

    void performResume()
    {


        if (mChildFragmentManager != null)
        {
            mChildFragmentManager.noteStateNotSaved();
            mChildFragmentManager.execPendingActions();
        }

        mCalled = false;
        this.onResume();

        if (mCalled != false)
        {
            if (mChildFragmentManager != null)
            {
                mChildFragmentManager.dispatchResume();
                mChildFragmentManager.execPendingActions();
            }

            return;
        }
        else
        {
            throw new SuperNotCalledException((new StringBuilder()).append("Fragment ").append(this).append(" did not call through to super.onResume()").toString());
        }
    }

    void performSaveInstanceState(Bundle  r1)
    {

        Parcelable r3;
        this.onSaveInstanceState(r1);

        if (mChildFragmentManager != null)
        {
            r3 = mChildFragmentManager.saveAllState();

            if (r3 != null)
            {
                r1.putParcelable("android:support:fragments", r3);
            }
        }
    }

    void performStart()
    {


        if (mChildFragmentManager != null)
        {
            mChildFragmentManager.noteStateNotSaved();
            mChildFragmentManager.execPendingActions();
        }

        mCalled = false;
        this.onStart();

        if (mCalled != false)
        {
            if (mChildFragmentManager != null)
            {
                mChildFragmentManager.dispatchStart();
            }

            if (mLoaderManager != null)
            {
                mLoaderManager.doReportStart();
            }

            return;
        }
        else
        {
            throw new SuperNotCalledException((new StringBuilder()).append("Fragment ").append(this).append(" did not call through to super.onStart()").toString());
        }
    }

    void performStop()
    {


        if (mChildFragmentManager != null)
        {
            mChildFragmentManager.dispatchStop();
        }

        mCalled = false;
        this.onStop();

        if (mCalled != false)
        {
            return;
        }
        else
        {
            throw new SuperNotCalledException((new StringBuilder()).append("Fragment ").append(this).append(" did not call through to super.onStop()").toString());
        }
    }

    public void registerForContextMenu(View  r1)
    {


        r1.setOnCreateContextMenuListener(this);
    }

    final void restoreViewState(Bundle  r1)
    {


        if (mSavedViewState != null)
        {
            mInnerView.restoreHierarchyState(mSavedViewState);
            mSavedViewState = null;
        }

        mCalled = false;
        this.onViewStateRestored(r1);

        if (mCalled != false)
        {
            return;
        }
        else
        {
            throw new SuperNotCalledException((new StringBuilder()).append("Fragment ").append(this).append(" did not call through to super.onViewStateRestored()").toString());
        }
    }

    public void setArguments(Bundle  r1)
    {


        if (mIndex < 0)
        {
            mArguments = r1;
            return;
        }
        else
        {
            throw new IllegalStateException("Fragment already active");
        }
    }

    public void setHasOptionsMenu(boolean  z0)
    {


        if (mHasMenu != z0)
        {
            mHasMenu = z0;

            if (this.isAdded() != false)
            {
                if (this.isHidden() == false)
                {
                    mActivity.supportInvalidateOptionsMenu();
                }
            }
        }
    }

    final void setIndex(int  i0, Fragment  r1)
    {


        mIndex = i0;

        if (r1 == null)
        {
            mWho = (new StringBuilder()).append("android:fragment:").append(mIndex).toString();
        }
        else
        {
            mWho = (new StringBuilder()).append(r1.mWho).append(":").append(mIndex).toString();
        }
    }

    public void setInitialSavedState(Fragment$SavedState  r1)
    {

        Bundle r5;
        if (mIndex < 0)
        {
            label_11:
            {
                if (r1 != null)
                {
                    if (r1.mState != null)
                    {
                        r5 = r1.mState;
                        break label_11;
                    }
                }

                r5 = null;
            } //end label_11:


            mSavedFragmentState = r5;
            return;
        }
        else
        {
            throw new IllegalStateException("Fragment already active");
        }
    }

    public void setMenuVisibility(boolean  z0)
    {


        if (mMenuVisible != z0)
        {
            mMenuVisible = z0;

            if (mHasMenu != false)
            {
                if (this.isAdded() != false)
                {
                    if (this.isHidden() == false)
                    {
                        mActivity.supportInvalidateOptionsMenu();
                    }
                }
            }
        }
    }

    public void setRetainInstance(boolean  z0)
    {


        if (z0 != false)
        {
            if (mParentFragment != null)
            {
                throw new IllegalStateException("Can\'t retain fragements that are nested in other fragments");
            }
        }

        mRetainInstance = z0;
    }

    public void setTargetFragment(Fragment  r1, int  i0)
    {


        mTarget = r1;
        mTargetRequestCode = i0;
    }

    public void setUserVisibleHint(boolean  z0)
    {

        boolean z2;
        if (mUserVisibleHint == false)
        {
            if (z0 != false)
            {
                if (mState < 4)
                {
                    mFragmentManager.performPendingDeferredStart(this);
                }
            }
        }

        mUserVisibleHint = z0;

        if (z0 != false)
        {
            z2 = false;
        }
        else
        {
            z2 = true;
        }

        mDeferStart = z2;
    }

    public void startActivity(Intent  r1)
    {


        if (mActivity != null)
        {
            mActivity.startActivityFromFragment(this, r1, -1);
            return;
        }
        else
        {
            throw new IllegalStateException((new StringBuilder()).append("Fragment ").append(this).append(" not attached to Activity").toString());
        }
    }

    public void startActivityForResult(Intent  r1, int  i0)
    {


        if (mActivity != null)
        {
            mActivity.startActivityFromFragment(this, r1, i0);
            return;
        }
        else
        {
            throw new IllegalStateException((new StringBuilder()).append("Fragment ").append(this).append(" not attached to Activity").toString());
        }
    }

    public String toString()
    {

        StringBuilder r1;
        r1 = new StringBuilder(128);
        DebugUtils.buildShortClassTag(this, r1);

        if (mIndex >= 0)
        {
            r1.append(" #");
            r1.append(mIndex);
        }

        if (mFragmentId != 0)
        {
            r1.append(" id=0x");
            r1.append(Integer.toHexString(mFragmentId));
        }

        if (mTag != null)
        {
            r1.append(" ");
            r1.append(mTag);
        }

        r1.append('}');
        return r1.toString();
    }

    public void unregisterForContextMenu(View  r1)
    {


        r1.setOnCreateContextMenuListener(null);
    }
}
