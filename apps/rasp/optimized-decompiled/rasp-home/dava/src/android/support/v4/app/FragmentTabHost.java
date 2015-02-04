package android.support.v4.app;

import android.widget.TabHost;
import android.widget.TabHost$OnTabChangeListener;
import android.content.Context;
import java.util.ArrayList;
import android.util.AttributeSet;
import android.widget.TabHost$TabSpec;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TabWidget;
import android.widget.FrameLayout;
import android.widget.FrameLayout$LayoutParams;
import android.widget.LinearLayout$LayoutParams;
import android.content.res.TypedArray;
import android.os.Parcelable;

public class FragmentTabHost extends TabHost implements android.widget.TabHost$OnTabChangeListener
{
    private boolean mAttached;
    private int mContainerId;
    private Context mContext;
    private FragmentManager mFragmentManager;
    private FragmentTabHost$TabInfo mLastTab;
    private TabHost$OnTabChangeListener mOnTabChangeListener;
    private FrameLayout mRealTabContent;
    private final ArrayList mTabs;

    public FragmentTabHost(Context  r1)
    {
        super(r1, n0);

        Object n0;
        n0 = null;
        this.<init>(r1, n0);
        mTabs = new ArrayList();
        this.initFragmentTabHost(r1, n0);
    }

    public FragmentTabHost(Context  r1, AttributeSet  r2)
    {
        super(r1, r2);


        this.<init>(r1, r2);
        mTabs = new ArrayList();
        this.initFragmentTabHost(r1, r2);
    }

    public void addTab(TabHost$TabSpec  r1, Class  r2, Bundle  r3)
    {

        String r6;
        FragmentTabHost$TabInfo r7;
        FragmentTransaction r8;
        r1.setContent(new FragmentTabHost$DummyTabFactory(mContext));
        r6 = r1.getTag();
        r7 = new FragmentTabHost$TabInfo(r6, r2, r3);

        if (mAttached != false)
        {
            FragmentTabHost$TabInfo.access$102(r7, mFragmentManager.findFragmentByTag(r6));

            if (FragmentTabHost$TabInfo.access$100(r7) != null)
            {
                if (FragmentTabHost$TabInfo.access$100(r7).isDetached() == false)
                {
                    r8 = mFragmentManager.beginTransaction();
                    r8.detach(FragmentTabHost$TabInfo.access$100(r7));
                    r8.commit();
                }
            }
        }

        mTabs.add(r7);
        this.addTab(r1);
    }

    private FragmentTransaction doTabChanged(String  r1, FragmentTransaction  r2)
    {

        FragmentTabHost$TabInfo r3, r9;
        int i0;
        r3 = null;
        i0 = 0;

        while (i0 < mTabs.size())
        {
            r9 = (FragmentTabHost$TabInfo) mTabs.get(i0);

            if (FragmentTabHost$TabInfo.access$200(r9).equals(r1) != false)
            {
                r3 = r9;
            }

            i0 = i0 + 1;
        }

        if (r3 != null)
        {
            if (mLastTab != r3)
            {
                if (r2 == null)
                {
                    r2 = mFragmentManager.beginTransaction();
                }

                if (mLastTab != null)
                {
                    if (FragmentTabHost$TabInfo.access$100(mLastTab) != null)
                    {
                        r2.detach(FragmentTabHost$TabInfo.access$100(mLastTab));
                    }
                }

                if (r3 != null)
                {
                    if (FragmentTabHost$TabInfo.access$100(r3) != null)
                    {
                        r2.attach(FragmentTabHost$TabInfo.access$100(r3));
                    }
                    else
                    {
                        FragmentTabHost$TabInfo.access$102(r3, Fragment.instantiate(mContext, FragmentTabHost$TabInfo.access$300(r3).getName(), FragmentTabHost$TabInfo.access$400(r3)));
                        r2.add(mContainerId, FragmentTabHost$TabInfo.access$100(r3), FragmentTabHost$TabInfo.access$200(r3));
                    }
                }

                mLastTab = r3;
            }

            return r2;
        }
        else
        {
            throw new IllegalStateException((new StringBuilder()).append("No tab known for tag ").append(r1).toString());
        }
    }

    private void ensureContent()
    {


        if (mRealTabContent == null)
        {
            mRealTabContent = (FrameLayout) this.findViewById(mContainerId);

            if (mRealTabContent == null)
            {
                throw new IllegalStateException((new StringBuilder()).append("No tab content FrameLayout found for id ").append(mContainerId).toString());
            }
        }
    }

    private void ensureHierarchy(Context  r1)
    {

        LinearLayout r3;
        TabWidget r4;
        FrameLayout r5, r9;
        if (this.findViewById(16908307) == null)
        {
            r3 = new LinearLayout(r1);
            r3.setOrientation(1);
            this.addView(r3, new FrameLayout$LayoutParams(-1, -1));
            r4 = new TabWidget(r1);
            r4.setId(16908307);
            r4.setOrientation(0);
            r3.addView(r4, new LinearLayout$LayoutParams(-1, -2, 0.0F));
            r5 = new FrameLayout(r1);
            r5.setId(16908305);
            r3.addView(r5, new LinearLayout$LayoutParams(0, 0, 0.0F));
            r9 = new FrameLayout(r1);
            mRealTabContent = r9;
            mRealTabContent.setId(mContainerId);
            r3.addView(r9, new LinearLayout$LayoutParams(-1, 0, 1.0F));
        }
    }

    private void initFragmentTabHost(Context  r1, AttributeSet  r2)
    {

        TypedArray r3;
        int[] r4;
        r4 = new int[1];
        r4[0] = 16842995;
        r3 = r1.obtainStyledAttributes(r2, r4, 0, 0);
        mContainerId = r3.getResourceId(0, 0);
        r3.recycle();
        this.setOnTabChangedListener(this);
    }

    protected void onAttachedToWindow()
    {

        String r1;
        FragmentTransaction r2, r17;
        int i0;
        FragmentTabHost$TabInfo r7;
        this.onAttachedToWindow();
        r1 = this.getCurrentTabTag();
        r2 = null;
        i0 = 0;

        while (i0 < mTabs.size())
        {
            r7 = (FragmentTabHost$TabInfo) mTabs.get(i0);
            FragmentTabHost$TabInfo.access$102(r7, mFragmentManager.findFragmentByTag(FragmentTabHost$TabInfo.access$200(r7)));

            if (FragmentTabHost$TabInfo.access$100(r7) != null)
            {
                if (FragmentTabHost$TabInfo.access$100(r7).isDetached() == false)
                {
                    if (FragmentTabHost$TabInfo.access$200(r7).equals(r1) == false)
                    {
                        if (r2 == null)
                        {
                            r2 = mFragmentManager.beginTransaction();
                        }

                        r2.detach(FragmentTabHost$TabInfo.access$100(r7));
                    }
                    else
                    {
                        mLastTab = r7;
                    }
                }
            }

            i0 = i0 + 1;
        }

        mAttached = true;
        r17 = this.doTabChanged(r1, r2);

        if (r17 != null)
        {
            r17.commit();
            mFragmentManager.executePendingTransactions();
        }
    }

    protected void onDetachedFromWindow()
    {


        this.onDetachedFromWindow();
        mAttached = false;
    }

    protected void onRestoreInstanceState(Parcelable  r1)
    {

        FragmentTabHost$SavedState r4;
        r4 = (FragmentTabHost$SavedState) r1;
        this.onRestoreInstanceState(r4.getSuperState());
        this.setCurrentTabByTag(r4.curTab);
    }

    protected Parcelable onSaveInstanceState()
    {

        FragmentTabHost$SavedState r2;
        r2 = new FragmentTabHost$SavedState(this.onSaveInstanceState());
        r2.curTab = this.getCurrentTabTag();
        return r2;
    }

    public void onTabChanged(String  r1)
    {

        FragmentTransaction r2;
        if (mAttached != false)
        {
            r2 = this.doTabChanged(r1, null);

            if (r2 != null)
            {
                r2.commit();
            }
        }

        if (mOnTabChangeListener != null)
        {
            mOnTabChangeListener.onTabChanged(r1);
        }
    }

    public void setOnTabChangedListener(TabHost$OnTabChangeListener  r1)
    {


        mOnTabChangeListener = r1;
    }

    public void setup()
    {


        throw new IllegalStateException("Must call setup() that takes a Context and FragmentManager");
    }

    public void setup(Context  r1, FragmentManager  r2)
    {


        this.ensureHierarchy(r1);
        this.setup();
        mContext = r1;
        mFragmentManager = r2;
        this.ensureContent();
    }

    public void setup(Context  r1, FragmentManager  r2, int  i0)
    {


        this.ensureHierarchy(r1);
        this.setup();
        mContext = r1;
        mFragmentManager = r2;
        mContainerId = i0;
        this.ensureContent();
        mRealTabContent.setId(i0);

        if (this.getId() == -1)
        {
            this.setId(16908306);
        }
    }
}
