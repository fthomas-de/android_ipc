package android.support.v4.app;

import android.app.Activity;
import android.os.Handler;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import android.os.Build$VERSION;
import android.view.Window;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.util.SimpleArrayMap;
import android.content.Intent;
import java.util.ArrayList;
import android.util.Log;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.content.Context;
import android.util.AttributeSet;
import android.content.res.TypedArray;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.content.res.Resources;
import android.content.res.Resources$NotFoundException;

public class FragmentActivity extends Activity
{
    static final String FRAGMENTS_TAG = "android:support:fragments";
    private static final int HONEYCOMB = 11;
    static final int MSG_REALLY_STOPPED = 1;
    static final int MSG_RESUME_PENDING = 2;
    private static final String TAG = "FragmentActivity";
    SimpleArrayMap mAllLoaderManagers;
    boolean mCheckedForLoaderManager;
    final FragmentContainer mContainer;
    boolean mCreated;
    final FragmentManagerImpl mFragments;
    final Handler mHandler;
    LoaderManagerImpl mLoaderManager;
    boolean mLoadersStarted;
    boolean mOptionsMenuInvalidated;
    boolean mReallyStopped;
    boolean mResumed;
    boolean mRetaining;
    boolean mStopped;

    public FragmentActivity()
    {


        this.<init>();
        mHandler = new FragmentActivity$1(this);
        mFragments = new FragmentManagerImpl();
        mContainer = new FragmentActivity$2(this);
    }

    void doReallyStop(boolean  z0)
    {


        if (mReallyStopped == false)
        {
            mReallyStopped = true;
            mRetaining = z0;
            mHandler.removeMessages((int) 1);
            this.onReallyStop();
        }
    }

    public void dump(String  r1, FileDescriptor  r2, PrintWriter  r3, String[]  r4)
    {

        String r5;
        if (Build$VERSION.SDK_INT >= 11)
        {
        }

        r3.print(r1);
        r3.print("Local FragmentActivity ");
        r3.print(Integer.toHexString(System.identityHashCode(this)));
        r3.println(" State:");
        r5 = (new StringBuilder()).append(r1).append("  ").toString();
        r3.print(r5);
        r3.print("mCreated=");
        r3.print(mCreated);
        r3.print("mResumed=");
        r3.print(mResumed);
        r3.print(" mStopped=");
        r3.print(mStopped);
        r3.print(" mReallyStopped=");
        r3.println(mReallyStopped);
        r3.print(r5);
        r3.print("mLoadersStarted=");
        r3.println(mLoadersStarted);

        if (mLoaderManager != null)
        {
            r3.print(r1);
            r3.print("Loader Manager ");
            r3.print(Integer.toHexString(System.identityHashCode(mLoaderManager)));
            r3.println(":");
            mLoaderManager.dump((new StringBuilder()).append(r1).append("  ").toString(), r2, r3, r4);
        }

        mFragments.dump(r1, r2, r3, r4);
        r3.print(r1);
        r3.println("View Hierarchy:");
        this.dumpViewHierarchy((new StringBuilder()).append(r1).append("  ").toString(), r3, this.getWindow().getDecorView());
    }

    private void dumpViewHierarchy(String  r1, PrintWriter  r2, View  r3)
    {

        int i0, i1;
        ViewGroup r8;
        String r12;
        r2.print(r1);

        if (r3 != null)
        {
            r2.println(FragmentActivity.viewToString(r3));

            if (r3 instanceof ViewGroup != false)
            {
                r8 = (ViewGroup) r3;
                i0 = r8.getChildCount();

                if (i0 > 0)
                {
                    r12 = (new StringBuilder()).append(r1).append("  ").toString();
                    i1 = 0;

                    while (i1 < i0)
                    {
                        this.dumpViewHierarchy(r12, r2, r8.getChildAt(i1));
                        i1 = i1 + 1;
                    }
                }
            }
        }
        else
        {
            r2.println("null");
        }
    }

    public Object getLastCustomNonConfigurationInstance()
    {

        Object r2;
        FragmentActivity$NonConfigurationInstances r3;
        r3 = (FragmentActivity$NonConfigurationInstances) this.getLastNonConfigurationInstance();

        if (r3 == null)
        {
            r2 = null;
        }
        else
        {
            r2 = r3.custom;
        }

        return r2;
    }

    LoaderManagerImpl getLoaderManager(String  r1, boolean  z0, boolean  z1)
    {

        LoaderManagerImpl r6;
        if (mAllLoaderManagers == null)
        {
            mAllLoaderManagers = new SimpleArrayMap();
        }

        r6 = (LoaderManagerImpl) mAllLoaderManagers.get(r1);

        if (r6 != null)
        {
            r6.updateActivity(this);
        }
        else
        {
            if (z1 != false)
            {
                r6 = new LoaderManagerImpl(r1, this, z0);
                mAllLoaderManagers.put(r1, r6);
            }
        }

        return r6;
    }

    public FragmentManager getSupportFragmentManager()
    {


        return mFragments;
    }

    public LoaderManager getSupportLoaderManager()
    {

        LoaderManagerImpl r2;
        if (mLoaderManager == null)
        {
            mCheckedForLoaderManager = true;
            mLoaderManager = this.getLoaderManager("(root)", mLoadersStarted, true);
            r2 = mLoaderManager;
        }
        else
        {
            r2 = mLoaderManager;
        }

        return r2;
    }

    void invalidateSupportFragment(String  r1)
    {

        LoaderManagerImpl r5;
        if (mAllLoaderManagers != null)
        {
            r5 = (LoaderManagerImpl) mAllLoaderManagers.get(r1);

            if (r5 != null)
            {
                if (r5.mRetaining == false)
                {
                    r5.doDestroy();
                    mAllLoaderManagers.remove(r1);
                }
            }
        }
    }

    protected void onActivityResult(int  i0, int  i1, Intent  r1)
    {

        int i2, i3;
        Fragment r17;
        mFragments.noteStateNotSaved();
        i2 = i0 >> 16;

        label_0:
        if (i2 == 0)
        {
            this.onActivityResult(i0, i1, r1);
        }
        else
        {
            i3 = i2 + -1;

            if (mFragments.mActive != null)
            {
                if (i3 >= 0)
                {
                    if (i3 < mFragments.mActive.size())
                    {
                        r17 = (Fragment) mFragments.mActive.get(i3);

                        if (r17 != null)
                        {
                            r17.onActivityResult(65535 & i0, i1, r1);
                            break label_0;
                        }
                        else
                        {
                            Log.w("FragmentActivity", (new StringBuilder()).append("Activity result no fragment exists for index: 0x").append(Integer.toHexString(i0)).toString());
                            break label_0;
                        }
                    }
                }
            }

            Log.w("FragmentActivity", (new StringBuilder()).append("Activity result fragment index out of range: 0x").append(Integer.toHexString(i0)).toString());
        }
    }

    public void onAttachFragment(Fragment  r1)
    {

    }

    public void onBackPressed()
    {


        if (mFragments.popBackStackImmediate() == false)
        {
            this.finish();
        }
    }

    public void onConfigurationChanged(Configuration  r1)
    {


        this.onConfigurationChanged(r1);
        mFragments.dispatchConfigurationChanged(r1);
    }

    protected void onCreate(Bundle  r1)
    {

        ArrayList r2;
        Parcelable r6;
        FragmentActivity$NonConfigurationInstances r10;
        FragmentManagerImpl r13;
        r2 = null;
        mFragments.attachActivity(this, mContainer, (Fragment) r2);

        if (this.getLayoutInflater().getFactory() == null)
        {
            this.getLayoutInflater().setFactory(this);
        }

        this.onCreate(r1);
        r10 = (FragmentActivity$NonConfigurationInstances) this.getLastNonConfigurationInstance();

        if (r10 != null)
        {
            mAllLoaderManagers = r10.loaders;
        }

        if (r1 != null)
        {
            r6 = r1.getParcelable("android:support:fragments");
            r13 = mFragments;

            if (r10 != null)
            {
                r2 = r10.fragments;
            }

            r13.restoreAllState(r6, r2);
        }

        mFragments.dispatchCreate();
    }

    public boolean onCreatePanelMenu(int  i0, Menu  r1)
    {

        boolean z2;
        if (i0 != 0)
        {
            z2 = this.onCreatePanelMenu(i0, r1);
        }
        else
        {
            z2 = this.onCreatePanelMenu(i0, r1) | mFragments.dispatchCreateOptionsMenu(r1, this.getMenuInflater());

            if (Build$VERSION.SDK_INT < 11)
            {
                z2 = true;
            }
        }

        return z2;
    }

    public View onCreateView(String  r1, Context  r2, AttributeSet  r3)
    {

        Fragment r4;
        int i0, i2, i5;
        String r6, r8;
        TypedArray r7;
        Object n0;
        View r10;
        r4 = null;
        i0 = 0;

        if ("fragment".equals(r1) != false)
        {
            r6 = r3.getAttributeValue((String) r4, "class");
            r7 = r2.obtainStyledAttributes(r3, FragmentActivity$FragmentTag.Fragment);

            if (r6 == null)
            {
                r6 = r7.getString(0);
            }

            i2 = r7.getResourceId((int) 1, -1);
            r8 = r7.getString(2);
            r7.recycle();

            if (Fragment.isSupportFragmentClass(this, r6) != false)
            {
                n0 = null;

                if (n0 != null)
                {
                    i0 = ((View) n0).getId();
                }

                if (i0 == -1)
                {
                    if (i2 == -1)
                    {
                        if (r8 == null)
                        {
                            throw new IllegalArgumentException((new StringBuilder()).append(r3.getPositionDescription()).append(": Must specify unique android:id, android:tag, or have a parent with an id for ").append(r6).toString());
                        }
                    }
                }

                if (i2 != -1)
                {
                    r4 = mFragments.findFragmentById(i2);
                }

                if (r4 == null)
                {
                    if (r8 != null)
                    {
                        r4 = mFragments.findFragmentByTag(r8);
                    }
                }

                if (r4 == null)
                {
                    if (i0 != -1)
                    {
                        r4 = mFragments.findFragmentById(i0);
                    }
                }

                if (FragmentManagerImpl.DEBUG != false)
                {
                    Log.v("FragmentActivity", (new StringBuilder()).append("onCreateView: id=0x").append(Integer.toHexString(i2)).append(" fname=").append(r6).append(" existing=").append(r4).toString());
                }

                if (r4 != null)
                {
                    if (r4.mInLayout == false)
                    {
                        r4.mInLayout = true;

                        if (r4.mRetaining == false)
                        {
                            r4.onInflate(this, r3, r4.mSavedFragmentState);
                        }

                        mFragments.moveToState(r4);
                    }
                    else
                    {
                        throw new IllegalArgumentException((new StringBuilder()).append(r3.getPositionDescription()).append(": Duplicate id 0x").append(Integer.toHexString(i2)).append(", tag ").append(r8).append(", or parent id 0x").append(Integer.toHexString(i0)).append(" with another fragment for ").append(r6).toString());
                    }
                }
                else
                {
                    r4 = Fragment.instantiate(this, r6);
                    r4.mFromLayout = true;

                    if (i2 == 0)
                    {
                        i5 = i0;
                    }
                    else
                    {
                        i5 = i2;
                    }

                    r4.mFragmentId = i5;
                    r4.mContainerId = i0;
                    r4.mTag = r8;
                    r4.mInLayout = true;
                    r4.mFragmentManager = mFragments;
                    r4.onInflate(this, r3, r4.mSavedFragmentState);
                    mFragments.addFragment(r4, true);
                }

                if (r4.mView != null)
                {
                    if (i2 != 0)
                    {
                        r4.mView.setId(i2);
                    }

                    if (r4.mView.getTag() == null)
                    {
                        r4.mView.setTag(r8);
                    }

                    r10 = r4.mView;
                }
                else
                {
                    throw new IllegalStateException((new StringBuilder()).append("Fragment ").append(r6).append(" did not create a view.").toString());
                }
            }
            else
            {
                r10 = this.onCreateView(r1, r2, r3);
            }
        }
        else
        {
            r10 = this.onCreateView(r1, r2, r3);
        }

        return r10;
    }

    protected void onDestroy()
    {


        this.onDestroy();
        this.doReallyStop(false);
        mFragments.dispatchDestroy();

        if (mLoaderManager != null)
        {
            mLoaderManager.doDestroy();
        }
    }

    public boolean onKeyDown(int  i0, KeyEvent  r1)
    {

        boolean z0;
        label_1:
        {
            if (Build$VERSION.SDK_INT < 5)
            {
                if (i0 == 4)
                {
                    if (r1.getRepeatCount() == 0)
                    {
                        this.onBackPressed();
                        z0 = true;
                        break label_1;
                    }
                }
            }

            z0 = this.onKeyDown(i0, r1);
        } //end label_1:


        return z0;
    }

    public void onLowMemory()
    {


        this.onLowMemory();
        mFragments.dispatchLowMemory();
    }

    public boolean onMenuItemSelected(int  i0, MenuItem  r1)
    {

        boolean z1;
        label_2:
        if (this.onMenuItemSelected(i0, r1) == false)
        {
            switch (i0)
            {
                case 0:
                    z1 = mFragments.dispatchOptionsItemSelected(r1);
                    break label_2;

                case 6:
                    z1 = mFragments.dispatchContextItemSelected(r1);
                    break label_2;

                default:
                    z1 = false;
                    break label_2;
            }
        }
        else
        {
            z1 = true;
        }

        return z1;
    }

    protected void onNewIntent(Intent  r1)
    {


        this.onNewIntent(r1);
        mFragments.noteStateNotSaved();
    }

    public void onPanelClosed(int  i0, Menu  r1)
    {


        switch (i0)
        {
            case 0:
                mFragments.dispatchOptionsMenuClosed(r1);

            default:
                this.onPanelClosed(i0, r1);
                return;
        }
    }

    protected void onPause()
    {


        this.onPause();
        mResumed = false;

        if (mHandler.hasMessages(2) != false)
        {
            mHandler.removeMessages(2);
            this.onResumeFragments();
        }

        mFragments.dispatchPause();
    }

    protected void onPostResume()
    {


        this.onPostResume();
        mHandler.removeMessages(2);
        this.onResumeFragments();
        mFragments.execPendingActions();
    }

    protected boolean onPrepareOptionsPanel(View  r1, Menu  r2)
    {


        return this.onPreparePanel(0, r1, r2);
    }

    public boolean onPreparePanel(int  i0, View  r1, Menu  r2)
    {

        boolean z5;
        label_3:
        {
            if (i0 == 0)
            {
                if (r2 != null)
                {
                    if (mOptionsMenuInvalidated != false)
                    {
                        mOptionsMenuInvalidated = false;
                        r2.clear();
                        this.onCreatePanelMenu(i0, r2);
                    }

                    z5 = this.onPrepareOptionsPanel(r1, r2) | mFragments.dispatchPrepareOptionsMenu(r2);
                    break label_3;
                }
            }

            z5 = this.onPreparePanel(i0, r1, r2);
        } //end label_3:


        return z5;
    }

    void onReallyStop()
    {


        if (mLoadersStarted != false)
        {
            mLoadersStarted = false;

            if (mLoaderManager != null)
            {
                if (mRetaining != false)
                {
                    mLoaderManager.doRetain();
                }
                else
                {
                    mLoaderManager.doStop();
                }
            }
        }

        mFragments.dispatchReallyStop();
    }

    protected void onResume()
    {


        this.onResume();
        mHandler.sendEmptyMessage(2);
        mResumed = true;
        mFragments.execPendingActions();
    }

    protected void onResumeFragments()
    {


        mFragments.dispatchResume();
    }

    public Object onRetainCustomNonConfigurationInstance()
    {


        return null;
    }

    public final Object onRetainNonConfigurationInstance()
    {

        Object n0;
        Object r1;
        ArrayList r2;
        boolean z1;
        int i0, i1, i2;
        LoaderManagerImpl[] r3;
        LoaderManagerImpl r4;
        FragmentActivity$NonConfigurationInstances r6;
        n0 = null;

        if (mStopped != false)
        {
            this.doReallyStop(true);
        }

        r1 = this.onRetainCustomNonConfigurationInstance();
        r2 = mFragments.retainNonConfig();
        z1 = false;

        if (mAllLoaderManagers != null)
        {
            i0 = mAllLoaderManagers.size();
            r3 = new LoaderManagerImpl[i0];
            i1 = i0 + -1;

            while (i1 >= 0)
            {
                r3[i1] = (LoaderManagerImpl) mAllLoaderManagers.valueAt(i1);
                i1 = i1 + -1;
            }

            i2 = 0;

            while (i2 < i0)
            {
                r4 = r3[i2];

                if (r4.mRetaining == false)
                {
                    r4.doDestroy();
                    mAllLoaderManagers.remove(r4.mWho);
                }
                else
                {
                    z1 = true;
                }

                i2 = i2 + 1;
            }
        }

        label_4:
        {
            if (r2 == null)
            {
                if (z1 == false)
                {
                    if (r1 == null)
                    {
                        r6 = n0;
                        break label_4;
                    }
                }
            }

            r6 = new FragmentActivity$NonConfigurationInstances();
            r6.activity = n0;
            r6.custom = r1;
            r6.children = n0;
            r6.fragments = r2;
            r6.loaders = mAllLoaderManagers;
        } //end label_4:


        return r6;
    }

    protected void onSaveInstanceState(Bundle  r1)
    {

        Parcelable r3;
        this.onSaveInstanceState(r1);
        r3 = mFragments.saveAllState();

        if (r3 != null)
        {
            r1.putParcelable("android:support:fragments", r3);
        }
    }

    protected void onStart()
    {

        int i0, i1, i2;
        LoaderManagerImpl[] r2;
        LoaderManagerImpl r3;
        this.onStart();
        mStopped = false;
        mReallyStopped = false;
        mHandler.removeMessages((int) 1);

        if (mCreated == false)
        {
            mCreated = true;
            mFragments.dispatchActivityCreated();
        }

        mFragments.noteStateNotSaved();
        mFragments.execPendingActions();

        if (mLoadersStarted == false)
        {
            mLoadersStarted = true;

            if (mLoaderManager == null)
            {
                if (mCheckedForLoaderManager == false)
                {
                    mLoaderManager = this.getLoaderManager("(root)", mLoadersStarted, false);

                    if (mLoaderManager != null)
                    {
                        if (mLoaderManager.mStarted == false)
                        {
                            mLoaderManager.doStart();
                        }
                    }
                }
            }
            else
            {
                mLoaderManager.doStart();
            }

            mCheckedForLoaderManager = true;
        }

        mFragments.dispatchStart();

        if (mAllLoaderManagers != null)
        {
            i0 = mAllLoaderManagers.size();
            r2 = new LoaderManagerImpl[i0];
            i1 = i0 + -1;

            while (i1 >= 0)
            {
                r2[i1] = (LoaderManagerImpl) mAllLoaderManagers.valueAt(i1);
                i1 = i1 + -1;
            }

            i2 = 0;

            while (i2 < i0)
            {
                r3 = r2[i2];
                r3.finishRetain();
                r3.doReportStart();
                i2 = i2 + 1;
            }
        }
    }

    protected void onStop()
    {


        this.onStop();
        mStopped = true;
        mHandler.sendEmptyMessage((int) 1);
        mFragments.dispatchStop();
    }

    public void startActivityForResult(Intent  r1, int  i0)
    {


        if (i0 != -1)
        {
            if ((-65536 & i0) != 0)
            {
                throw new IllegalArgumentException("Can only use lower 16 bits for requestCode");
            }
        }

        this.startActivityForResult(r1, i0);
    }

    public void startActivityFromFragment(Fragment  r1, Intent  r2, int  i0)
    {


        if (i0 != -1)
        {
            if ((-65536 & i0) == 0)
            {
                this.startActivityForResult(r2, (r1.mIndex + 1 << 16) + (65535 & i0));
            }
            else
            {
                throw new IllegalArgumentException("Can only use lower 16 bits for requestCode");
            }
        }
        else
        {
            this.startActivityForResult(r2, -1);
        }
    }

    public void supportInvalidateOptionsMenu()
    {


        if (Build$VERSION.SDK_INT < 11)
        {
            mOptionsMenuInvalidated = true;
        }
        else
        {
            ActivityCompatHoneycomb.invalidateOptionsMenu(this);
        }
    }

    private static String viewToString(View  r0)
    {

        char c1, c4, c10, c11, c12, c13, c14, c15, c16, c17;
        StringBuilder r1;
        int i6;
        Resources r2;
        String r3, $r37, $r38, $r39, r40, r43, r46;
        c1 = 'F';
        c4 = '.';
        r1 = new StringBuilder(128);
        r1.append(r0.getClass().getName());
        r1.append('{');
        r1.append(Integer.toHexString(System.identityHashCode(r0)));
        r1.append(' ');

        label_7:
        switch (r0.getVisibility())
        {
            case 0:
                r1.append('V');
                break label_7;

            case 4:
                r1.append('I');
                break label_7;

            case 8:
                r1.append('G');
                break label_7;

            default:
                r1.append('.');
                break label_7;
        }

        if (r0.isFocusable() == false)
        {
            c10 = '.';
        }
        else
        {
            c10 = 'F';
        }

        r1.append(c10);

        if (r0.isEnabled() == false)
        {
            c11 = '.';
        }
        else
        {
            c11 = 'E';
        }

        r1.append(c11);

        if (r0.willNotDraw() == false)
        {
            c12 = 'D';
        }
        else
        {
            c12 = '.';
        }

        r1.append(c12);

        if (r0.isHorizontalScrollBarEnabled() == false)
        {
            c13 = '.';
        }
        else
        {
            c13 = 'H';
        }

        r1.append(c13);

        if (r0.isVerticalScrollBarEnabled() == false)
        {
            c14 = '.';
        }
        else
        {
            c14 = 'V';
        }

        r1.append(c14);

        if (r0.isClickable() == false)
        {
            c15 = '.';
        }
        else
        {
            c15 = 'C';
        }

        r1.append(c15);

        if (r0.isLongClickable() == false)
        {
            c16 = '.';
        }
        else
        {
            c16 = 'L';
        }

        r1.append(c16);
        r1.append(' ');

        if (r0.isFocused() == false)
        {
            c1 = '.';
        }

        r1.append(c1);

        if (r0.isSelected() == false)
        {
            c17 = '.';
        }
        else
        {
            c17 = 'S';
        }

        r1.append(c17);

        if (r0.isPressed() != false)
        {
            c4 = 'P';
        }

        r1.append(c4);
        r1.append(' ');
        r1.append(r0.getLeft());
        r1.append(',');
        r1.append(r0.getTop());
        r1.append('-');
        r1.append(r0.getRight());
        r1.append(',');
        r1.append(r0.getBottom());
        i6 = r0.getId();

        label_8:
        if (i6 != -1)
        {
            r1.append(" #");
            r1.append(Integer.toHexString(i6));
            r2 = r0.getResources();

            if (i6 != 0)
            {
                if (r2 != null)
                {
                    label_6:
                    {
                        label_5:
                        switch (-16777216 & i6)
                        {
                            case 16777216:
                                try
                                {
                                    r3 = "android";
                                }
                                catch (Resources$NotFoundException $r55)
                                {
                                    break label_6;
                                }

                                break label_5;

                            case 2130706432:
                                try
                                {
                                    r3 = "app";
                                }
                                catch (Resources$NotFoundException $r55)
                                {
                                    break label_6;
                                }

                                break label_5;

                            default:
                                try
                                {
                                    $r37 = r2.getResourcePackageName(i6);
                                }
                                catch (Resources$NotFoundException $r55)
                                {
                                    break label_6;
                                }

                                r3 = $r37;
                                break label_5;
                        }

                        try
                        {
                            $r38 = r2.getResourceTypeName(i6);
                        }
                        catch (Resources$NotFoundException $r55)
                        {
                            break label_6;
                        }

                        try
                        {
                            $r39 = r2.getResourceEntryName(i6);
                        }
                        catch (Resources$NotFoundException $r55)
                        {
                            break label_6;
                        }

                        try
                        {
                            r40 = " ";
                        }
                        catch (Resources$NotFoundException $r55)
                        {
                            break label_6;
                        }

                        r1.append(r40);

                        try
                        {
                            r1.append(r3);
                        }
                        catch (Resources$NotFoundException $r55)
                        {
                            break label_6;
                        }

                        r43 = ":";

                        try
                        {
                            r1.append(r43);
                        }
                        catch (Resources$NotFoundException $r55)
                        {
                            break label_6;
                        }

                        r1.append($r38);

                        try
                        {
                            r46 = "/";
                        }
                        catch (Resources$NotFoundException $r55)
                        {
                            break label_6;
                        }

                        r1.append(r46);

                        try
                        {
                            r1.append($r39);
                            break label_8;
                        }
                        catch (Resources$NotFoundException $r55)
                        {
                        }
                    } //end label_6:

                }
            }
        }

        r1.append("}");
        return r1.toString();
    }
}
